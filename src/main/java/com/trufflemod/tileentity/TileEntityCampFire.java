package com.trufflemod.tileentity;

import com.trufflemod.initialize.ModBlocks;
import com.trufflemod.initialize.ModItems;
import com.trufflemod.utils.ShadowUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public class TileEntityCampFire extends TileEntity {

    private boolean burning;
    private int burnTime;

    public ArrayList<Integer> fuelStacks = new ArrayList<Integer>();
    private ArrayList<String> oreDictEntries = ShadowUtil.getArrayList(
            "stickWood",
            "logWood",
            "plankWood",
            "blockCoal",
            "treeSapling",
            "stairWood",
            "slabWood"
    );

    private Block tileBlock;


    public TileEntityCampFire(World world) {
        this.setWorldObj(world);
        this.tileBlock = this.getBlockType();
    }

    public TileEntityCampFire() {

    }

    private int setBurnTime(int num) {
        this.burnTime = num;
        markDirty();
        return this.burnTime;
    }

    public int getBurnTime() {
        return burnTime;
    }

    private int getItemBurnTime(ItemStack itemStack) {
        return TileEntityFurnace.getItemBurnTime(itemStack) * 3;
    }

    private void setIsBurning(Boolean bool) {
        burning = bool;
        markDirty();
    }

    public boolean isBurning() {
        return burning;
    }

    private void setFuel(ItemStack itemStack) {
        this.fuelStacks.add(getItemBurnTime(itemStack));
        markDirty();
    }

    private boolean hasFuel() {
        return !this.fuelStacks.isEmpty();
    }

    @Override
    public void updateEntity() {

        if (this.burnTime > 0 && this.isBurning()) {
            --burnTime;
        }
        else if (burnTime == 0) {
            if (hasFuel()) {
                setBurnTime(this.fuelStacks.remove(0));
            }
            else
            {
                setIsBurning(false);
            }
        }

        if (worldObj.isRaining() && worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord, this.zCoord) && this.isBurning()) {

            this.extinguish();
        }

        this.markDirty();
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);

        NBTTagList nbtTagList = new NBTTagList();

        for (int number : fuelStacks) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setInteger("fuelStack", number);
            nbtTagList.appendTag(nbt);
        }

        nbtTagCompound.setBoolean("burning", this.burning);
        nbtTagCompound.setInteger("burnTime", this.burnTime);
        nbtTagCompound.setTag("fuelStacks", nbtTagList);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        this.burning = nbtTagCompound.getBoolean("burning");
        this.burnTime = nbtTagCompound.getInteger("burnTime");
        this.fuelStacks = new ArrayList<Integer>();

        NBTTagList nbtTagList = nbtTagCompound.getTagList("fuelStacks", 10);
        for (int i = 0; i < nbtTagList.tagCount(); i++) {
            this.fuelStacks.add(
                    nbtTagList.getCompoundTagAt(i).getInteger("fuelStack")
            );
        }
    }


    @Override
    public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {

        this.readFromNBT(packet.func_148857_g());
    }

    @Override
    public Packet getDescriptionPacket() {

        NBTTagCompound nbtTagCompound = new NBTTagCompound();

        this.writeToNBT(nbtTagCompound);

        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTagCompound);
    }


    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float F1, float F2, float F3) {

        ItemStack itemStack = player.inventory.getCurrentItem();

        if (itemStack == null) {
            return false;
        }

        else if (this.isValidFuel(itemStack) && this.fuelStacks.size() < 4) {
            this.setFuel(itemStack);

            if (!player.capabilities.isCreativeMode) {

                --itemStack.stackSize;
            }
        }


        else if (itemStack.getItem() == Item.getItemFromBlock(ModBlocks.blockCauldronFoundation) && world.isAirBlock(x, y + 1, z)) {

            world.setBlock(x, y + 1, z, ModBlocks.blockCauldronFoundation);

            if (!player.capabilities.isCreativeMode) {

                --itemStack.stackSize;
            }

            this.markDirty();

            return false;
        }


        else if (this.isBurning() && itemStack.getItem() == Items.water_bucket) {

            if (!player.capabilities.isCreativeMode) {

                player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.bucket));
                player.inventory.markDirty();
            }

            this.extinguish();

            world.spawnParticle("cloud", (double) x + 0.5, (double) y + 0.45, (double) z + 0.5, 0D, 0D, 0D);
            world.spawnParticle("cloud", (double) x + 0.6, (double) y + 0.45, (double) z + 0.4, 0D, 0D, 0D);
            world.spawnParticle("cloud", (double) x + 0.4, (double) y + 0.50, (double) z + 0.5, 0D, 0D, 0D);

            world.spawnParticle("smoke", (double) x + 0.6, (double) y + 0.75, (double) z + 0.5, 0D, 0D, 0D);
            world.spawnParticle("smoke", (double) x + 0.5, (double) y + 0.80, (double) z + 0.5, 0D, 0D, 0D);
            world.spawnParticle("smoke", (double) x + 0.4, (double) y + 0.85, (double) z + 0.5, 0D, 0D, 0D);
            world.spawnParticle("smoke", (double) x + 0.5, (double) y + 0.70, (double) z + 0.6, 0D, 0D, 0D);
        }


        else if ((itemStack.getItem() == Items.flint_and_steel || itemStack.getItem() == Items.fire_charge) && !this.isBurning()) {

            if (burnTime > 0) {

                world.playSound(x, y, z, "fire.ignite", 4F, 1F, false);
                world.spawnParticle("lava", (double)x + 0.5, (double)y + 0.10, (double)z + 0.5, 0D, 0D, 0D);

                this.setIsBurning(true);
            }
        }

        else if (itemStack.getItem() == ModItems.rennetBucket && itemStack.getItemDamage() == 0 && this.isBurning()) {

            player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ModItems.rennetBucket, 1, 1));
            world.playSound(x, y, z, "random.fizz", 3F, 1.3F, false);
        }
        this.markDirty();

        return true;
    }


    public boolean isValidFuel(ItemStack itemStack) {

        return itemStack.getItem() == Items.coal && ShadowUtil.isValidItem(oreDictEntries, itemStack);
    }


    private void extinguish() {

        this.setIsBurning(false);

        if (!this.worldObj.isRemote) {

            this.worldObj.playSound(this.xCoord, this.yCoord, this.zCoord, "random.fizz", 2.5F, 1.2F, false);
        }

        worldObj.spawnParticle("cloud", (double) this.xCoord + 0.5, (double) this.yCoord + 0.45, (double) this.zCoord + 0.5, 0D, 0D, 0D);
        worldObj.spawnParticle("cloud", (double) this.xCoord + 0.6, (double) this.yCoord + 0.45, (double) this.zCoord + 0.4, 0D, 0D, 0D);
        worldObj.spawnParticle("cloud", (double) this.xCoord + 0.4, (double) this.yCoord + 0.50, (double) this.zCoord + 0.5, 0D, 0D, 0D);

        worldObj.spawnParticle("smoke", (double) this.xCoord + 0.6, (double) this.yCoord + 0.75, (double) this.zCoord + 0.5, 0D, 0D, 0D);
        worldObj.spawnParticle("smoke", (double) this.xCoord + 0.5, (double) this.yCoord + 0.80, (double) this.zCoord + 0.5, 0D, 0D, 0D);
        worldObj.spawnParticle("smoke", (double) this.xCoord + 0.4, (double) this.yCoord + 0.85, (double) this.zCoord + 0.5, 0D, 0D, 0D);
        worldObj.spawnParticle("smoke", (double) this.xCoord + 0.5, (double) this.yCoord + 0.70, (double) this.zCoord + 0.6, 0D, 0D, 0D);

        markDirty();
    }
}
