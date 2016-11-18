package com.trufflemod.tileentity;

import com.trufflemod.initialize.ModBlocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityGrindStone extends TileEntity {

    private int xPos, zPos;


    public TileEntityGrindStone(World world) {
        this.setWorldObj(world);
        this.worldObj = this.getWorldObj();
        this.getCanvasCoordsFromMeta();
    }


    public TileEntityGrindStone() {

    }


    public void setCanvasBlock() {

        this.getCanvasCoordsFromMeta();

        this.getWorldObj().setBlock(xPos, this.yCoord, zPos, ModBlocks.grindStone);
        this.getWorldObj().setBlockMetadataWithNotify(xPos, this.yCoord, zPos, 0, 2);

        System.out.println(this.getWorldObj());
    }


    public void removeCanvas() {

        this.getWorldObj().setBlockToAir(xPos, this.yCoord, zPos);
    }



    private void getCanvasCoordsFromMeta() {


        int meta = this.getBlockMetadata();

        xPos = this.xCoord;
        zPos = this.zCoord;

        switch (meta) {

            case 1:
                xPos += 1;
                break;
            case 2:
                xPos -= 1;
                break;
            case 3:
                zPos -= 1;
                break;
            case 4:
                zPos += 1;
                break;
        }
    }


    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);
    }


    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);
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
}