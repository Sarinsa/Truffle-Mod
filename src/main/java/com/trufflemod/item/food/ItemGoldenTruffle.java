package com.trufflemod.item.food;

import com.trufflemod.item.TMItemFood;
import com.trufflemod.utils.DamageSources;
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
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class ItemGoldenTruffle extends TMItemFood {


    private static final String truffleTypes[] = new String[]{"Tier1", "Tier2"};

    private Random rand = new Random();


    public ItemGoldenTruffle(int healAmount, int saturation, boolean bool) {
        super(healAmount, saturation, bool);
        this.setUnlocalizedName("goldenTruffle");
        this.setTextureName("goldenTruffle");
        this.setHasSubtypes(true);
        this.setAlwaysEdible();
    }


    @Override
    public String getUnlocalizedName(ItemStack itemStack) {

        return this.getUnlocalizedName() + truffleTypes[itemStack.getItemDamage()];
    }



    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack, int pass) {

        return itemStack.getItemDamage() == 1;
    }


    public EnumRarity getRarity(ItemStack item) {
        return item.getItemDamage() == 0 ? EnumRarity.uncommon : EnumRarity.rare;
    }


    @Override
    @SuppressWarnings({"unchecked"})
    public void getSubItems(Item item, CreativeTabs creativeTab, List list) {
        list.add(new ItemStack(item, 0, 0));
        list.add(new ItemStack(item, 0, 1));
    }


    @Override
    public void onFoodEaten(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
            if (itemStack.getItemDamage() == 1) {

                int random = rand.nextInt(100);

                if (random <= 50) {

                    entityPlayer.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 7200, 3));
                    entityPlayer.addPotionEffect(new PotionEffect(Potion.regeneration.id, 7200, 3));
                    entityPlayer.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 7200, 3));
                    entityPlayer.addPotionEffect(new PotionEffect(Potion.resistance.id, 7200, 2));
                    entityPlayer.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 1200, 2));

                } else {

                    entityPlayer.attackEntityFrom(DamageSources.goldenTruffle, 100f);
                }

            } else if (itemStack.getItemDamage() == 0) {

                entityPlayer.addPotionEffect(new PotionEffect(Potion.regeneration.id, 380, 1));
                System.out.println(itemStack.getItemDamage());

            }
        }
    }

