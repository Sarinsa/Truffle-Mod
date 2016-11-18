package com.trufflemod.block.ore;

import com.trufflemod.block.TMBlock;
import com.trufflemod.initialize.CreativeTabTM;
import com.trufflemod.initialize.ModItems;
import com.trufflemod.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;

import java.util.Random;

public class OreSalt extends TMBlock {

	private Random random = new Random();

	public OreSalt() {
		super(Material.rock);
		this.setName("saltOre");
		this.setHardness(1.5f);
		this.setResistance(1.0f);
		this.setHarvestLevel("pickaxe", 1);
		this.setStepSound(soundTypeStone);
	}


	@Override
	public int getExpDrop(IBlockAccess iBlockAccess, int metadata, int fortune) {

        return this.getItemDropped(metadata, random, fortune) != Item.getItemFromBlock(this) ? 1 + random.nextInt(3) : 0;
    }




	@Override
	public int quantityDropped(Random random)
	{
		return 1 + random.nextInt(3);
	}




	@Override
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		return ModItems.salt;
	}
}
