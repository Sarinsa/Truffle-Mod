package com.trufflemod.initialize;

import com.trufflemod.TruffleMod;
import com.trufflemod.entity.*;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EntityList;

public final class EntityHandler {

    @SuppressWarnings("unchecked")
    private static void registerEntity(Class entityClass, String name) {

        int entityId = EntityRegistry.findGlobalUniqueEntityId();

        EntityRegistry.registerGlobalEntityID(entityClass, name, entityId);
        EntityRegistry.registerModEntity(entityClass, name, entityId, TruffleMod.instance, 70, 1, true);
    }


    @SuppressWarnings("unchecked")
    private static void registerEntityWithEggs(Class entityClass, String name, int eggFill, int eggSpot) {

        int entityId = EntityRegistry.findGlobalUniqueEntityId();

        EntityRegistry.registerGlobalEntityID(entityClass, name, entityId);
        EntityRegistry.registerModEntity(entityClass, name, entityId, TruffleMod.instance, 70, 1, true);
        EntityList.entityEggs.put(Integer.valueOf(entityId), new EntityList.EntityEggInfo(entityId, eggFill, eggSpot));
    }


    public static void registerEntities() {

        EntityHandler.registerEntity(EntityPotionDouchebaggery.class, "entityPotionDouchebaggery");
        EntityHandler.registerEntity(EntityGrenade.class, "entityGrenade");
        EntityHandler.registerEntity(EntityPineCone.class, "entityPineCone");
        EntityHandler.registerEntity(EntityJumperBomb.class, "entityJumperBomb");
        EntityHandler.registerEntityWithEggs(EntityForestGheist.class, "Forest Gheist", 14289966, 23444371);
    }
}
