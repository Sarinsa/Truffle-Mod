package com.trufflemod.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityGrenade extends EntityThrowable {

    private float power;
    private int smoke;

    protected ItemStack itemStack;

    private float gravity;


    public EntityGrenade(World world) {
        super(world);
    }


    public EntityGrenade(World world, EntityLivingBase entityLivingBase) {
        super(world, entityLivingBase);

        EntityPlayer player = (EntityPlayer) entityLivingBase;
        this.itemStack = player.getHeldItem();

        System.out.println(itemStack.getItemDamage());

        if (itemStack.getItemDamage() == 0) {

            this.smoke = 10;
            this.power = 4.3F;
            this.setGravity(0.0F);
        }
        else
        {
            this.smoke = 50;
            this.power = 20.5F;
            this.setGravity(10.5F);
        }
    }


    private float setGravity(float gravity) {

        return this.gravity = gravity;
    }


    @Override
    protected float func_70183_g() {

        return this.gravity;
    }




    @Override
    protected void onImpact(MovingObjectPosition movingObjectPosition) {

        for (int i = 0; i < smoke; i++) {

            worldObj.spawnParticle("largesmoke", this.posX, this.posY, this.posZ, smoke/2, smoke/2, smoke/2);
        }

        if (!worldObj.isRemote) {

            this.setDead();
            worldObj.createExplosion(null, this.posX, this.posY, this.posZ, this.power, true);
        }
    }
}
