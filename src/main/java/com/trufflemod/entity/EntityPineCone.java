package com.trufflemod.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityPineCone extends EntityThrowable {


    public EntityPineCone(World world) {
        super(world);
        this.setFire(1000);
    }

    public EntityPineCone(World world, EntityLivingBase entityLivingBase) {
        super(world, entityLivingBase);
        this.setFire(1000);
    }


    @Override
    public void onImpact(MovingObjectPosition movingObjectPosition) {

        if (movingObjectPosition.entityHit != null) {

            float dealtDamage = 0.5F;

            movingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), dealtDamage);

            if (!movingObjectPosition.entityHit.isImmuneToFire()) {

                movingObjectPosition.entityHit.setFire(10);
            }
        }
        else
        {
            if (movingObjectPosition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {

                int x = movingObjectPosition.blockX;
                int y = movingObjectPosition.blockY;
                int z = movingObjectPosition.blockZ;

                if (worldObj.isAirBlock(x, y + 1, z) || worldObj.getBlock(x, y, z).getCollisionBoundingBoxFromPool(worldObj, x, y, z) == null) {

                    worldObj.setBlock(x, y + 1, z, Blocks.fire);
                }
            }

            if (!worldObj.isRemote) {
                this.setDead();
            }
        }
    }


    @Override
    public void onUpdate() {

        super.onUpdate();

        worldObj.spawnParticle("smoke", this.posX, this.posY, this.posZ, 0F, 0F, 0F);
    }
}