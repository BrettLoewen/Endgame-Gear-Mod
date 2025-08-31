package com.supremetor.endgamegear.world.custom;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.ArrayList;
import java.util.List;

public class GeodeSkyFeature extends Feature<GeodeSkyConfig> {
    public GeodeSkyFeature(Codec<GeodeSkyConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<GeodeSkyConfig> context) {
        GeodeSkyConfig config = context.getConfig();
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();

        int radius = config.radius().get(random);

        List<BlockPos> innerBlocks = new ArrayList<>();

        // Create a hollow sphere:
        // Do a 3d loop through every block pos in the sphere (all possible offsets from the origin).
        // Use the distance from the origin of the block pos to determine which block (outer, inner, or AIR) should be placed.
        for (int dx = -radius - 1; dx <= radius + 1; dx++) {
            for (int dy = -radius - 1; dy <= radius + 1; dy++) {
                for (int dz = -radius - 1; dz <= radius + 1; dz++) {
                    double dist = Math.sqrt(dx*dx + dy*dy + dz*dz);

                    BlockPos pos = origin.add(dx, dy, dz);

                    if (dist <= radius && dist >= radius - 1.5) {
                        world.setBlockState(pos, config.outerBlock().get(random, pos), 3);
                    } else if (dist <= radius - 1.5 && dist >= radius - 2.5) {
                        world.setBlockState(pos, config.innerBlock().get(random, pos), 3);
                        innerBlocks.add(pos);
                    } else if (dist < radius - 2.5) {
                        world.setBlockState(pos, net.minecraft.block.Blocks.AIR.getDefaultState(), 3);
                    }
                }
            }
        }

        // Add special blocks to the inner wall.
        // Pick a random block pos from the inner wall (the list is filled earlier) and swap it to the special block.
        int specials = config.specialCount().get(random);
        for (int i = 0; i < specials; i++) {
            BlockPos specialPos = innerBlocks.get(random.nextInt(innerBlocks.size()));
            world.setBlockState(specialPos, config.specialBlock().get(random, specialPos), 3);
        }

        return true;
    }
}
