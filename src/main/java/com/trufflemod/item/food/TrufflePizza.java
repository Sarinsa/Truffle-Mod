package com.trufflemod.item.food;

import com.trufflemod.item.TMItemFood;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.util.List;

public class TrufflePizza extends TMItemFood {

    private static final String[] pizzaTypes = new String[] {"0", "strength", "speed", "fireResistance"};


    public TrufflePizza(int healAmount, float saturation, boolean bool) {
        super(healAmount, saturation, bool);
        this.setUnlocalizedName("trufflePizza");
        this.setTextureName("trufflePizza");
        this.setHasSubtypes(true);
    }



    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return this.getUnlocalizedName() + "_" + pizzaTypes[itemStack.getItemDamage()];
    }



    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack, int pass) {

        return itemStack.getItemDamage() > 0;
    }




    @Override
    public EnumRarity getRarity(ItemStack item) {
        return item.getItemDamage() == 0 ? EnumRarity.common : EnumRarity.uncommon;
    }



    @Override
    public void onFoodEaten(ItemStack item, World world, EntityPlayer entityPlayer) {

        if (item.getItemDamage() == 1)
        {
            entityPlayer.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 3600, 1));

        }
        else if (item.getItemDamage() == 2)
        {
            entityPlayer.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 3600, 0));

        }
        else if (item.getItemDamage() == 3)
        {
            entityPlayer.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 3600, 1));
        }
    }





    @SideOnly(Side.CLIENT)
    @Override
    @SuppressWarnings("unchecked")
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
        list.add(new ItemStack(item, 1, 2));
        list.add(new ItemStack(item, 1, 3));
    }





    @SideOnly(Side.CLIENT)
    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool) {
        if (itemStack.getItemDamage() == 0)  {

            list.add("");



        } else if (itemStack.getItemDamage() == 1) {

            list.add("Strength (3:00)");
            list.add("");
            list.add(EnumChatFormatting.DARK_PURPLE + "When Applied:");
            list.add(EnumChatFormatting.BLUE + "+150% Strength");



        } else if (itemStack.getItemDamage() == 2) {

            list.add("Speed (3:00)");
            list.add("");
            list.add(EnumChatFormatting.DARK_PURPLE + "When Applied:");
            list.add(EnumChatFormatting.BLUE + "+20% Speed");




        } else if (itemStack.getItemDamage() == 3) {

            list.add("Fire Resistance (3:00)");
        }
    }
}