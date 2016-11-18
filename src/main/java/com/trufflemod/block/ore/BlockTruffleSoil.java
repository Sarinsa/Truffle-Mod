package com.trufflemod.block.ore;

import com.trufflemod.block.TMBlock;
import com.trufflemod.initialize.ModItems;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.util.Random;

public class BlockTruffleSoil extends TMBlock {


	public BlockTruffleSoil() {
		super(Material.ground);
		this.setTickRandomly(true);
		this.setName("truffleSoil");
		this.setHardness(1.0f);
		this.setResistance(1.0f);
		this.setStepSound(soundTypeGravel);
	}



	@Override
	public int quantityDropped(Random random) {

		return 1 + random.nextInt(3);
	}



	@Override
	public Item getItemDropped(int metadata, Random random, int fortune) {

		return ModItems.truffle;
	}
}
