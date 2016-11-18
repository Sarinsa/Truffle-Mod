package com.trufflemod.initialize;


import com.trufflemod.block.*;
import com.trufflemod.block.ore.*;
import com.trufflemod.item.*;
import com.trufflemod.lib.Reference;
import com.trufflemod.tileentity.TileEntityCampFire;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;


public class ModBlocks {


	public static void registerBlocks() {
		initializeBlock();
		registerItemBlock();
	}

	public static Block tSoil;
	public static Block saltOre;
	public static Block tomatoCrop;
	public static Block pineCone;
	public static Block aGrass;
	public static Block cheeseBlock;
	public static Block blockEmil;
	public static Block oreAquamarine;
	public static Block netherOreSpinel;
	public static Block blockAquamarine;
	public static Block blockSpinel;
	public static Block blockDavid;
	public static Block pineLog;
	public static Block pineLeaves;
	public static Block pinePlanks;
	public static Block pineSapling;
	public static Block grindStone;
	public static Block netherTSoil;
	public static Block netherTSoilCrop;
	public static Block flowerThistle;
	public static Block blueberryBush;
	public static Block pineFence;
	public static Block woodenTub;
	public static Block pineStairs;
	public static Block testBlock;
	public static Block flowerGaliumV;
	public static Block campFire;
	public static Block pineDoor;
	public static Block pineSlab;
	public static Block double_pineSlab;
	public static Block oreSoundAspect;
    public static Block blockCauldronFoundation;
	public static Block blockOlaf;





    private static void initializeBlock() {
		tSoil = new BlockTruffleSoil();
		saltOre = new OreSalt();
		tomatoCrop = new CropTomato();
		pineCone = new BlockPineCone();
		aGrass = new CropArugula();
		cheeseBlock = new BlockCheese();
		blockEmil = new BlockEmil();
		oreAquamarine = new OreAquamarine();
		netherOreSpinel = new OreNetherSpinel();
		blockAquamarine = new BlockAquamarine();
		blockSpinel = new BlockSpinel();
		blockDavid = new BlockDavid();
		pineLog = new BlockPineLog();
		pineLeaves = new PineLeaves();
		pinePlanks = new BlockPinePlanks();
		pineSapling = new PineSapling();
		grindStone = new BlockGrindStone();
		netherTSoil = new NetherTruffleSoil();
		netherTSoilCrop = new CropNetherTSoil();
		flowerThistle = new FlowerThistle();
        blueberryBush = new BlueberryBush();
		pineFence = new PineFence(Reference.MODID + ":pinePlanks", Material.wood);
		woodenTub = new BlockWoodenTub();
		pineStairs = new BlockPineStairs(ModBlocks.pinePlanks, 0);
		testBlock = new TestBlock();
		flowerGaliumV = new FlowerGaliumV();
		campFire = new BlockCampFire();
		pineDoor = new BlockPineDoor();
		pineSlab = new BlockPineSlab(false).setBlockName("pineSlab").setCreativeTab(CreativeTabTM.truffleModTab);
		double_pineSlab = new BlockPineSlab(true).setBlockName("double_pineSlab");
		oreSoundAspect = new OreSoundAspect();
        blockCauldronFoundation = new BlockCauldronFoundation();
		blockOlaf = new BlockOlaf();
	}

	private static void registerItemBlock() {

		GameRegistry.registerBlock(tSoil, tSoil.getUnlocalizedName());
		GameRegistry.registerBlock(saltOre, saltOre.getUnlocalizedName());
		GameRegistry.registerBlock(tomatoCrop, tomatoCrop.getUnlocalizedName());
		GameRegistry.registerBlock(pineCone, pineCone.getUnlocalizedName());
		GameRegistry.registerBlock(aGrass, aGrass.getUnlocalizedName());
		GameRegistry.registerBlock(cheeseBlock, cheeseBlock.getUnlocalizedName());
		GameRegistry.registerBlock(blockEmil, ItemBlockEmil.class, blockEmil.getUnlocalizedName());
		GameRegistry.registerBlock(oreAquamarine, oreAquamarine.getUnlocalizedName());
		GameRegistry.registerBlock(netherOreSpinel, netherOreSpinel.getUnlocalizedName());
		GameRegistry.registerBlock(blockAquamarine, ItemBlockAquamarine.class ,blockAquamarine.getUnlocalizedName());
		GameRegistry.registerBlock(blockSpinel, ItemBlockSpinel.class, blockSpinel.getUnlocalizedName());
		GameRegistry.registerBlock(blockDavid, blockDavid.getUnlocalizedName());
		GameRegistry.registerBlock(pineLeaves, pineLeaves.getUnlocalizedName());
		GameRegistry.registerBlock(pineLog, pineLog.getUnlocalizedName());
		GameRegistry.registerBlock(pinePlanks, pinePlanks.getUnlocalizedName());
		GameRegistry.registerBlock(pineSapling, pineSapling.getUnlocalizedName());
		GameRegistry.registerBlock(grindStone, ItemBlockGrindStone.class, grindStone.getUnlocalizedName());
		GameRegistry.registerBlock(netherTSoil, netherTSoil.getUnlocalizedName());
		GameRegistry.registerBlock(netherTSoilCrop, netherTSoilCrop.getUnlocalizedName());
		GameRegistry.registerBlock(flowerThistle, flowerThistle.getUnlocalizedName());
        GameRegistry.registerBlock(blueberryBush, blueberryBush.getUnlocalizedName());
		GameRegistry.registerBlock(pineFence, pineFence.getUnlocalizedName());
		GameRegistry.registerBlock(woodenTub, woodenTub.getUnlocalizedName());
		GameRegistry.registerBlock(pineStairs, pineStairs.getUnlocalizedName());
		GameRegistry.registerBlock(testBlock, testBlock.getUnlocalizedName());
		GameRegistry.registerBlock(flowerGaliumV, flowerGaliumV.getUnlocalizedName());
		GameRegistry.registerBlock(campFire, campFire.getUnlocalizedName());
        GameRegistry.registerBlock(pineDoor, pineDoor.getUnlocalizedName());
        GameRegistry.registerBlock(pineSlab, pineSlab.getUnlocalizedName());
		GameRegistry.registerBlock(double_pineSlab, double_pineSlab.getUnlocalizedName());
		GameRegistry.registerBlock(oreSoundAspect, ItemBlockOreSoundAspect.class, oreSoundAspect.getUnlocalizedName());
        GameRegistry.registerBlock(blockCauldronFoundation, blockCauldronFoundation.getUnlocalizedName());
		GameRegistry.registerBlock(blockOlaf, blockOlaf.getUnlocalizedName());
	}
}


