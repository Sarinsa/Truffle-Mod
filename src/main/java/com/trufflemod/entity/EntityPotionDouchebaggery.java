package com.trufflemod.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public class EntityPotionDouchebaggery extends EntityThrowable {


    public EntityPotionDouchebaggery(World world) {
        super(world);
    }

    public EntityPotionDouchebaggery(World world, EntityLivingBase entityLivingBase) {
        super(world, entityLivingBase);
    }

    public EntityPotionDouchebaggery(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }


    @Override
    protected float getGravityVelocity() {
        return 0.05F;
    }


    protected float func_70182_d() {
        return 0.5F;
    }

    protected float func_70183_g() {
        return -20.0F;
    }


    @Override
    protected void onImpact(MovingObjectPosition movingObjectPosition) {

        if (!this.worldObj.isRemote) {

            AxisAlignedBB axisalignedbb = this.boundingBox.expand(4.0D, 2.0D, 4.0D);
            List list1 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);

            if (list1 != null && !list1.isEmpty()) {

                Iterator iterator = list1.iterator();

                while (iterator.hasNext()) {

                    EntityLivingBase entitylivingbase = (EntityLivingBase)iterator.next();
                    double d0 = this.getDistanceSqToEntity(entitylivingbase);

                    if (d0 < 16.0D) {

                        entitylivingbase.addPotionEffect(new PotionEffect(Potion.hunger.id, 720000, 3));
                        entitylivingbase.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 720000, 3));
                        entitylivingbase.addPotionEffect(new PotionEffect(Potion.confusion.id, 720000, 1));
                        entitylivingbase.addPotionEffect(new PotionEffect(Potion.weakness.id, 720000, 2));
                        entitylivingbase.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 720000, 2));

                    }
                }
            }

            this.worldObj.playAuxSFX(2002, (int) Math.round(this.posX), (int) Math.round(this.posY), (int) Math.round(this.posZ), 0);
            this.setDead();
        }

        if (!worldObj.isRemote && worldObj.canBlockSeeTheSky((int)this.posX, (int)this.posY, (int)this.posZ)) {

            worldObj.addWeatherEffect(new EntityLightningBolt(worldObj, posX, posY, posZ));
        }
    }
}
