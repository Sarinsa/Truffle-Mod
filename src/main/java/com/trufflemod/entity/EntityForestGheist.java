package com.trufflemod.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityForestGheist extends EntityGhast {

    public EntityForestGheist(World world) {
        super(world);
        this.setSize(11.5F, 11.5F);
        this.experienceValue = 10;
    }



    @Override
    protected void applyEntityAttributes() {

        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(150.0D);
    }


    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage) {

        if (this.isEntityInvulnerable()) {

            return false;
        }
        else if ("fireball".equals(damageSource.getDamageType()) && damageSource.getEntity() instanceof EntityPlayer) {

            float fireballDamage = 1F;

            super.attackEntityFrom(damageSource, fireballDamage);
            return true;
        }

        return false;
    }


    protected float getSoundVolume() {

        return 11.5F;
    }


    @Override
    protected float getSoundPitch() {
        return 0.6f;
    }
}
