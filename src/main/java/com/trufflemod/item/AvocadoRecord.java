package com.trufflemod.item;

import com.trufflemod.initialize.CreativeTabTM;
import com.trufflemod.lib.Reference;
import net.minecraft.block.BlockJukebox;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AvocadoRecord extends ItemRecord {

    private static final Map records = new HashMap();

    private final String recordName;


    @SuppressWarnings("unchecked")
    public AvocadoRecord(String recordName) {
        super(recordName);
        this.recordName = recordName;
        this.maxStackSize = 1;
        this.setUnlocalizedName("avocadoRecord");
        this.setTextureName(Reference.MODID + ":avocadoRecord");
        this.setCreativeTab(CreativeTabTM.truffleModTab);

        records.put(recordName, this);
    }



    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {

        if (world.getBlock(x, y, z) == Blocks.jukebox && world.getBlockMetadata(x, y, z) == 0) {

            if (world.isRemote) {
                return true;
            }
            else
            {
                ((BlockJukebox) Blocks.jukebox).func_149926_b(world, x, y, z, itemStack);
                world.playAuxSFXAtEntity(null, 1005, x, y, z, Item.getIdFromItem(this));
                --itemStack.stackSize;
                return true;
            }
        }
        else
        {
            return false;
        }
    }



    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool) {

        if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
            list.add("Avocado Republic");
            list.add("");
            list.add("A record containing the soundfile of");
            list.add("a funk jazz jam me and my band came up");
            list.add("with on a rainy, gray Wednesday!");
            list.add("");
            list.add(EnumChatFormatting.ITALIC + "(It is pretty dull)");

        }
        else
        {

            list.add("Avocado Republic");
            list.add("");
            list.add("Press" + EnumChatFormatting.GREEN + " Left CTRL " + EnumChatFormatting.GRAY + "for");
            list.add("for additional information");
        }
    }



    @Override
    public ResourceLocation getRecordResource(String name) {

        return new ResourceLocation(Reference.MODID + ":" + recordName);
    }
}


