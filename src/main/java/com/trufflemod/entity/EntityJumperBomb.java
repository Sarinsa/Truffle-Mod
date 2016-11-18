package com.trufflemod.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityJumperBomb extends EntityThrowable {


    public EntityJumperBomb(World world) {
        super(world);
    }

    public EntityJumperBomb(World world, EntityLivingBase entityLivingBase) {
        super(world, entityLivingBase);
    }


    @Override
    protected void onImpact(MovingObjectPosition movingObjectPosition) {

        for (int i = 0; i < 5; i++) {

            worldObj.spawnParticle("smoke", this.posX, this.posY, this.posZ, 0f, 0f, 0f);
        }

        if (!worldObj.isRemote) {

            this.setDead();

            for (int explosion = 0; explosion < 5; explosion++) {

                worldObj.createExplosion(null, this.posX, this.posY, this.posZ, 0.7f, false);
            }
        }
    }
}
