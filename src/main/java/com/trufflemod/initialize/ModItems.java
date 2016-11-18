package com.trufflemod.initialize;

import com.trufflemod.item.*;
import com.trufflemod.item.food.ItemGoldenTruffle;
import com.trufflemod.item.food.NetherTruffle;
import com.trufflemod.item.food.RottenTomato;
import com.trufflemod.item.food.TrufflePizza;
import com.trufflemod.lib.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;

public class ModItems {

	public static void registerItems() {
		initializeItem();
		registerItem();
	}


	//Items

		public static Item aquamarineGem;
		public static Item spinelGem;
	    public static Item hellishSpice;
        public static Item thistleStamens;
        public static Item powderedThistleStamens;
	    public static Item itemPineDoor;
		public static Item itemEmilUpgrade;

	//Food & Ingredients

		public static Item truffle;
		public static Item cookedTruffle;
		public static Item trufflePizza;
		public static Item meatPizza;
		public static Item cheese;
		public static Item tomato;
		public static Item salt;
		public static Item pizzaMargarita;
		public static Item exclusiveMargarita;
		public static Item flour;
		public static Item pizzaDough;
		public static Item pineNuts;
		public static Item arugula;
		public static Item netherTruffle;
		public static Item rottenTomato;
	    public static Item blueberry;
	    public static Item goldenTruffle;

	//Miscellaneous

		public static Item grindStone;
		public static Item rennetBucket;
		public static Item pineCone;
		public static Item avocadoRecord;
		public static Item sGlass_Bottle;
		public static Item thistle;
	    public static Item potionDouche;
		public static Item unstableHappiness;
		public static Item littleSack;
	    public static Item ninjaStar;
	    public static Item jumperBomb;

	//Seeds

		public static Item tomatoSeeds;
		public static Item arugulaSeeds;

	//Tools

		public static Item mortar_and_pestle;
		public static Item knife;





	private static void initializeItem() {

	//Items

	    aquamarineGem = new TMItem().setUnlocalizedName("aquamarineGem").setTextureName("aquamarineGem");
		spinelGem = new TMItem().setUnlocalizedName("spinelGem").setTextureName("spinelGem");
        hellishSpice = new ItemHellishSpice();
        thistleStamens = new TMItem().setUnlocalizedName("thistleStamens").setTextureName("thistleStamens");
        powderedThistleStamens = new TMItem().setUnlocalizedName("powderedThistleStamens").setTextureName("powderedStamens");
        itemPineDoor = new ItemPineDoor();
        itemEmilUpgrade = new ItemEmilUpgrade();

	//Food & Ingredients

		salt = new TMItem().setUnlocalizedName("salt").setTextureName("salt");
		flour = new TMItem().setUnlocalizedName("flour").setTextureName("flour");
		truffle = new TMItem().setUnlocalizedName("defaultTruffle").setTextureName("defaultTruffle");
		pizzaDough = new TMItem().setUnlocalizedName("pizzaDough").setTextureName("pizzaDough");
		exclusiveMargarita = new TMItemFood(8, 4.0f, false).setUnlocalizedName("exclusiveMargarita").setTextureName("exclusiveMargarita");
		pizzaMargarita = new TMItemFood(6, 2.5f, false).setUnlocalizedName("pizzaMargarita").setTextureName("pizzaMargarita");
		trufflePizza = new TrufflePizza(7, 3.5f, false).setUnlocalizedName("trufflePizza").setTextureName("trufflePizza");
		tomato = new TMItemFood(2, 0.5f, false).setUnlocalizedName("tomato").setTextureName("tomato");
		cookedTruffle = new TMItemFood(2, 0.5f, true).setUnlocalizedName("cookedTruffle").setTextureName("cookedTruffle");
		cheese = new TMItemFood(2, 0.4f, false).setUnlocalizedName("cheese").setTextureName("cheese");
		pineNuts = new TMItemFood(1, 0.1f, false).setUnlocalizedName("itemPineNuts").setTextureName("pineNuts");
		arugula = new TMItemFood(1, 0.3f, false).setUnlocalizedName("arugula").setTextureName("arugula");
		netherTruffle = new NetherTruffle(1, 0.3F, true);
		meatPizza = new TMItemFood(8, 3.3f, false).setUnlocalizedName("meatPizza").setTextureName("meatPizza");
		rottenTomato = new RottenTomato(1, 1, false);
        blueberry = new TMItemFood(1, 0.2f, false).setUnlocalizedName("blueberry").setTextureName("blueberry");
        goldenTruffle = new ItemGoldenTruffle(3, 0, false);

	//Miscellaneous

		rennetBucket = new RennetBucket();
		pineCone = new TMItem().setUnlocalizedName("itemPineCone").setTextureName("itemPineCone");
		avocadoRecord = new AvocadoRecord("avocadoRepublic");
		sGlass_Bottle = new SGlassBottle();
		potionDouche = new PotionDouchebaggery();
        unstableHappiness = new UnstableHappiness();
        littleSack = new ItemLittleSack();
        ninjaStar = new TestItem();
        jumperBomb = new ItemJumperBomb();

	//Seeds & Flowers

		tomatoSeeds = new ItemSeeds(ModBlocks.tomatoCrop, Blocks.farmland).setCreativeTab(CreativeTabTM.truffleModTab).setUnlocalizedName("tomatoSeeds").setTextureName(Reference.MODID + ":tomatoSeeds");
		arugulaSeeds = new ItemSeeds(ModBlocks.aGrass, Blocks.farmland).setCreativeTab(CreativeTabTM.truffleModTab).setUnlocalizedName("arugulaSeeds").setTextureName(Reference.MODID + ":arugulaSeeds");
		thistle = new ItemThistle();

	//Tools

		mortar_and_pestle = new ItemMortarAndPestle();
		knife = new ItemKnife().setMaxStackSize(1);
		grindStone = new TMItem().setUnlocalizedName("grindStone").setTextureName("grindStone").setMaxStackSize(1).setContainerItem(ModItems.grindStone);
    }



	private static void registerItem() {

		GameRegistry.registerItem(truffle, truffle.getUnlocalizedName());
    	GameRegistry.registerItem(cookedTruffle, cookedTruffle.getUnlocalizedName());
    	GameRegistry.registerItem(trufflePizza, trufflePizza.getUnlocalizedName());
		GameRegistry.registerItem(pizzaMargarita, pizzaMargarita.getUnlocalizedName());
		GameRegistry.registerItem(exclusiveMargarita, exclusiveMargarita.getUnlocalizedName());
    	GameRegistry.registerItem(cheese, cheese.getUnlocalizedName());
    	GameRegistry.registerItem(tomato, tomato.getUnlocalizedName());
    	GameRegistry.registerItem(tomatoSeeds, tomatoSeeds.getUnlocalizedName());
		GameRegistry.registerItem(arugulaSeeds, arugulaSeeds.getUnlocalizedName());
    	GameRegistry.registerItem(salt, salt.getUnlocalizedName());
    	GameRegistry.registerItem(grindStone, grindStone.getUnlocalizedName());
    	GameRegistry.registerItem(mortar_and_pestle, mortar_and_pestle.getUnlocalizedName());
    	GameRegistry.registerItem(flour, flour.getUnlocalizedName());
    	GameRegistry.registerItem(pizzaDough, pizzaDough.getUnlocalizedName());
    	GameRegistry.registerItem(rennetBucket, rennetBucket.getUnlocalizedName());
        GameRegistry.registerItem(pineCone, pineCone.getUnlocalizedName());
        GameRegistry.registerItem(pineNuts, pineNuts.getUnlocalizedName());
		GameRegistry.registerItem(knife, knife.getUnlocalizedName());
		GameRegistry.registerItem(aquamarineGem, aquamarineGem.getUnlocalizedName());
		GameRegistry.registerItem(spinelGem, spinelGem.getUnlocalizedName());
		GameRegistry.registerItem(avocadoRecord, avocadoRecord.getUnlocalizedName());
		GameRegistry.registerItem(arugula, arugula.getUnlocalizedName());
		GameRegistry.registerItem(sGlass_Bottle, sGlass_Bottle.getUnlocalizedName());
		GameRegistry.registerItem(hellishSpice, hellishSpice.getUnlocalizedName());
		GameRegistry.registerItem(rottenTomato, rottenTomato.getUnlocalizedName());
		GameRegistry.registerItem(netherTruffle, netherTruffle.getUnlocalizedName());
		GameRegistry.registerItem(meatPizza, meatPizza.getUnlocalizedName());
		GameRegistry.registerItem(thistle, thistle.getUnlocalizedName());
        GameRegistry.registerItem(potionDouche, potionDouche.getUnlocalizedName());
		GameRegistry.registerItem(blueberry, blueberry.getUnlocalizedName());
        GameRegistry.registerItem(goldenTruffle, goldenTruffle.getUnlocalizedName());
        GameRegistry.registerItem(unstableHappiness, unstableHappiness.getUnlocalizedName());
        GameRegistry.registerItem(littleSack, littleSack.getUnlocalizedName());
        GameRegistry.registerItem(ninjaStar, ninjaStar.getUnlocalizedName());
        GameRegistry.registerItem(jumperBomb, jumperBomb.getUnlocalizedName());
        GameRegistry.registerItem(thistleStamens, thistleStamens.getUnlocalizedName());
        GameRegistry.registerItem(powderedThistleStamens, powderedThistleStamens.getUnlocalizedName());
        GameRegistry.registerItem(itemPineDoor, itemPineDoor.getUnlocalizedName());
        GameRegistry.registerItem(itemEmilUpgrade, itemEmilUpgrade.getUnlocalizedName());
	}
 }    

