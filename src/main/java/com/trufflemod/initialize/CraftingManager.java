package com.trufflemod.initialize;


import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class CraftingManager {


    public static void registerCrafting() {

        addOreDictionary();
        addCraftingRecipes();
        addSmeltingRecipes();
    }


    private static void addOreDictionary() {

        OreDictionary.registerOre("plankWood", ModBlocks.pinePlanks);
        OreDictionary.registerOre("treeLeaves", ModBlocks.pineLeaves);
        OreDictionary.registerOre("logWood", ModBlocks.pineLog);
        OreDictionary.registerOre("slabWood", ModBlocks.pineSlab);
        OreDictionary.registerOre("treeSapling", ModBlocks.pineSapling);
        OreDictionary.registerOre("stairWood", ModBlocks.pineStairs);
        OreDictionary.registerOre("record", ModItems.avocadoRecord);
        OreDictionary.registerOre("itemSalt", ModItems.salt);
        OreDictionary.registerOre("cropTomato", ModItems.tomato);
        OreDictionary.registerOre("toolMortarandpestle", ModItems.mortar_and_pestle);
        OreDictionary.registerOre("flourEqualswheat", ModItems.flour);
    }


    private static void addCraftingRecipes() {

        //shaped

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.grindStone, 1), " C ", "C C", " C ", 'C', Blocks.stone);
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.cheeseBlock, 1), "CCC", "CCC", "CCC", 'C', ModItems.cheese);
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockAquamarine, 1), "AAA", "AAA", "AAA", 'A', ModItems.aquamarineGem);
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockSpinel, 1), "SSS", "SSS", "SSS", 'S', ModItems.spinelGem);
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.pineStairs, 4), "  P", " PP", "PPP", 'P', ModBlocks.pinePlanks);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.littleSack, 1), " S ", "L L", " L ", 'S', Items.string, 'L', Items.leather);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.itemPineDoor, 1), " PP", " PP", " PP", 'P', ModBlocks.pinePlanks);

        //Shaped Ore Recipes

        GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.mortar_and_pestle, " W ", "G G", " G ", 'W', "stickWood", 'G', "plankWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.knife, " I ", "S  ", "   ", 'I', Items.iron_ingot, 'S', "stickWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.pineFence, 3), "PSP", "PSP", 'P', ModBlocks.pinePlanks, 'S', "stickWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.woodenTub, "W W", "W W", "WSW", 'W', "plankWood", 'S', "slabWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.grindStone, "PGP", "W W", 'P', "plankWood", 'G', ModItems.grindStone, 'W', "logWood"));

        //shapeless

        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pizzaMargarita, 1), ModItems.cheese, new ItemStack((ModItems.sGlass_Bottle.setContainerItem(ModItems.sGlass_Bottle)), 0, 1), ModItems.pizzaDough);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.tomatoSeeds, 1), ModItems.tomato);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.trufflePizza, 1), ModItems.pizzaMargarita, ModItems.cookedTruffle, Items.cooked_beef);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.rennetBucket, 1, 0), Items.water_bucket.setContainerItem(Items.bucket), ModItems.powderedThistleStamens);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pineNuts, 1), new ItemStack(ModItems.knife, 1, OreDictionary.WILDCARD_VALUE), ModItems.pineCone);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.cheese, 9), new ItemStack(ModItems.knife, 1, OreDictionary.WILDCARD_VALUE), ModBlocks.cheeseBlock);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.aquamarineGem, 9), ModBlocks.blockAquamarine);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.sGlass_Bottle, 1), Items.glass_bottle);
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.pinePlanks, 4), ModBlocks.pineLog);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.trufflePizza, 1, 1), ModItems.hellishSpice, ModItems.trufflePizza.setContainerItem(Items.glass_bottle), new ItemStack(Items.potionitem, 1, 8201));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.trufflePizza, 1, 2), ModItems.hellishSpice, ModItems.trufflePizza.setContainerItem(Items.glass_bottle), new ItemStack(Items.potionitem, 1, 8194));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.trufflePizza, 1, 3), ModItems.hellishSpice, ModItems.trufflePizza.setContainerItem(Items.glass_bottle), new ItemStack(Items.potionitem, 1, 8227));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.hellishSpice, 3), new ItemStack(Items.dye, 1, 15), Items.blaze_powder, ModItems.rottenTomato);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.meatPizza, 1), ModItems.pizzaMargarita, Items.cooked_beef, Items.cooked_chicken);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.thistleStamens, 3), ModItems.thistle);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.powderedThistleStamens, 2), ModItems.thistleStamens, ModItems.thistleStamens, ModItems.thistleStamens, ModItems.mortar_and_pestle.setContainerItem(ModItems.mortar_and_pestle));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.flour, 1), ModItems.mortar_and_pestle.setContainerItem(ModItems.mortar_and_pestle), Items.wheat);

        //Shapeless Ore Recipes

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.cheeseBlock, 1), new ItemStack(ModItems.rennetBucket.setContainerItem(Items.bucket), 1, 1), Items.milk_bucket.setContainerItem(Items.bucket), "itemSalt"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.pizzaDough, 1), "itemSalt", Items.water_bucket.setContainerItem(Items.bucket), "flourEqualswheat"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.exclusiveMargarita, 1), ModItems.pizzaMargarita, "cropTomato", ModItems.pineNuts, ModItems.arugula));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.sGlass_Bottle, 1, 1), ModItems.mortar_and_pestle.setContainerItem(ModItems.mortar_and_pestle), "cropTomato", ModItems.sGlass_Bottle));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.dye, 1, 5), ModItems.thistle, "toolMortarandpestle"));
    }


    private static void addSmeltingRecipes() {

        GameRegistry.addSmelting(ModItems.truffle, new ItemStack(ModItems.cookedTruffle), 22.0f);
        GameRegistry.addSmelting(ModBlocks.oreAquamarine, new ItemStack(ModItems.aquamarineGem), 24.5f);
        GameRegistry.addSmelting(ModBlocks.netherOreSpinel, new ItemStack(ModItems.spinelGem), 26.5f);
    }
}



































