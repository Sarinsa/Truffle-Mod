package com.trufflemod.utils;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Collections;

import static net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE;

public class ShadowUtil {

    /**
     * Gets list of entities within the bounds of the block, had it been placed there in the world, and then check if list is empty.
     * */
    public static boolean isBlockPlaceable(World world, int x, int y, int z, Block block) {

        return world.getEntitiesWithinAABB(Entity.class, block.getCollisionBoundingBoxFromPool(world, x, y, z)).size() == 0;
    }


    public static <E> ArrayList<E> getArrayList(E... input) {
        ArrayList<E> returnList = new ArrayList<E>();

        Collections.addAll(returnList, input);

        return returnList;
    }


    public static boolean isValidItem(String oreDictValue, ItemStack itemStack) {
        return getMatchingItem(oreDictValue, itemStack) != null;
    }

    public static ItemStack getMatchingItem(String oreDictValue, ItemStack itemStack) {
        if (itemStack == null)
            return null;

        ArrayList<ItemStack> array = OreDictionary.getOres(oreDictValue);
        for (ItemStack stack : array) {

            if (stack.getItem() == itemStack.getItem() && (stack.getItemDamage() == WILDCARD_VALUE || stack.getItemDamage() == itemStack.getItemDamage()))
                return itemStack.copy();

        }
        return null;
    }

    public static boolean isValidItem(ArrayList<String> oreDictEntries, ItemStack itemStack) {
        for (String oreDictValue : oreDictEntries)
            if (isValidItem(oreDictValue, itemStack))
                return true;

        return false;
    }


    //-------------------
    public static boolean isValidItem(String oreDictValue, Block block) {
        return isValidItem(oreDictValue, block, 0);
    }

    public static boolean isValidItem(String oreDictValue, Block block, int metadata) {
        return isValidItem(oreDictValue, new ItemStack(block, 1, metadata));
    }

    public static boolean isValidItem(String oreDictValue, World world, int x, int y, int z) {
        return isValidItem(oreDictValue, world.getBlock(x,y,z), world.getBlockMetadata(x, y, z));
    }

    public static boolean isValidItem(String oreDictValue, Item item) {
        return isValidItem(oreDictValue, item, 0);
    }

    public static boolean isValidItem(String oreDictValue, Item item, int damage) {
        return isValidItem(oreDictValue, new ItemStack(item, 1, damage));
    }
//-------------------


    //-------------------
    public static ItemStack getMatchingItem(String oreDictValue, Block block) {
        return getMatchingItem(oreDictValue, block, 0);
    }

    public static ItemStack getMatchingItem(String oreDictValue, Block block, int metadata) {
        return getMatchingItem(oreDictValue, new ItemStack(block, 1, metadata));
    }

    public static ItemStack getMatchingItem(String oreDictValue, World world, int x, int y, int z) {
        return getMatchingItem(oreDictValue, world.getBlock(x,y,z), world.getBlockMetadata(x,y,z));
    }

    public static ItemStack getMatchingItem(String oreDictValue, Item item) {
        return getMatchingItem(oreDictValue, item, 0);
    }

    public static ItemStack getMatchingItem(String oreDictValue, Item item, int damage) {
        return getMatchingItem(oreDictValue, new ItemStack(item, 1, damage));
    }
//-------------------

    //-------------------
    public static boolean isValidItem(ArrayList<String> oreDictEntries, Block block) {
        return isValidItem(oreDictEntries, block, 0);
    }

    public static boolean isValidItem(ArrayList<String> oreDictEntries, Block block, int metadata) {
        return isValidItem(oreDictEntries, new ItemStack(block, 1, metadata));
    }

    public static boolean isValidItem(ArrayList<String> oreDictEntries, World world, int x, int y, int z) {
        return isValidItem(oreDictEntries, new ItemStack(world.getBlock(x,y,z), 1, world.getBlockMetadata(x,y,z)));
    }

    public static boolean isValidItem(ArrayList<String> oreDictEntries, Item item) {
        return isValidItem(oreDictEntries, item, 0);
    }

    public static boolean isValidItem(ArrayList<String> oreDictEntries, Item item, int damage) {
        return isValidItem(oreDictEntries, new ItemStack(item, 1, damage));
    }
//-------------------
}
