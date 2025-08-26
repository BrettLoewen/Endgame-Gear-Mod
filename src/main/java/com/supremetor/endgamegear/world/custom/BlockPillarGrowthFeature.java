package com.supremetor.endgamegear.world.custom;

import com.mojang.serialization.Codec;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.WeightedListIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class BlockPillarGrowthFeature extends Feature<BlockPillarGrowthConfig> {
    public BlockPillarGrowthFeature(Codec<BlockPillarGrowthConfig> configCodec) {
        super(configCodec);
    }

    // Up has the highest odds, but there are still good odds to move sideways.
    // Down (0) is not included because the pillar should not 'grow' downwards.
    private static final IntProvider PILLAR_DIRECTION_PROVIDER = new WeightedListIntProvider(Pool.<IntProvider>builder()
        .add(ConstantIntProvider.create(1), 6)
        .add(ConstantIntProvider.create(2), 1)
        .add(ConstantIntProvider.create(3), 1)
        .add(ConstantIntProvider.create(4), 1)
        .add(ConstantIntProvider.create(5), 1)
        .build());

    @Override
    public boolean generate(FeatureContext<BlockPillarGrowthConfig> context) {
        BlockPillarGrowthConfig config = context.getConfig();
        BlockPos origin = context.getOrigin();
        WorldAccess world = context.getWorld();
        Random random = context.getRandom();

        // Ensure the pillar is placed on the correct surface
        // Scan downward until the surface is found or the void is reached
        BlockPos.Mutable pos = origin.mutableCopy();
        while (pos.getY() > world.getBottomY() + 5) {
            if (config.surfaceTest().test(world.getBlockState(pos), random)) {
                break;
            }
            pos.move(Direction.DOWN);
        }

        // Double check that the while loop didn't exit on the world bottom condition but on the block check break
        if (!config.surfaceTest().test(world.getBlockState(pos), random)) {
            return false;
        }

        // Start building the pillar just above the surface
        BlockPos.Mutable innerPos = pos.up().mutableCopy();

        // If the pillar needs to be underwater, confirm it's underwater
        if (!world.getFluidState(innerPos).isOf(Fluids.WATER) && config.mustBeUnderwater()) {
            return false;
        }

        int height = config.height().get(random);

        // For each block in the pillar
        for (int i = 0; i < height; i++) {
            // Place the inside of the pillar
            world.setBlockState(innerPos, config.innerBlock().get(random, innerPos), 3);

            // Place a full shell around the new inner block
            for (Direction dir : Direction.values()) {
                BlockPos side = innerPos.offset(dir);
                if (world.getBlockState(side).isReplaceable()) {
                    world.setBlockState(side, config.shellBlock().get(random, side), 3);
                }
            }

            // Move to the BlockPos that will be used for the next iteration of the loop.
            // Done afterward to ensure the pillar will not be floating above the surface.
            // There is no check to prevent the pillar from 'growing' back into itself, but this should be fine.
            innerPos.move(Direction.values()[PILLAR_DIRECTION_PROVIDER.get(random)], 1);
        }

        return true;
    }
}
