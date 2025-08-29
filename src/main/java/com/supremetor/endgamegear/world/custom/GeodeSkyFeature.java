package com.supremetor.endgamegear.world.custom;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

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
        int diameter = radius * 2 + 1;

        // Create hollow sphere
        for (int dx = -radius - 1; dx <= radius + 1; dx++) {
            for (int dy = -radius - 1; dy <= radius + 1; dy++) {
                for (int dz = -radius - 1; dz <= radius + 1; dz++) {
                    double dist = Math.sqrt(dx*dx + dy*dy + dz*dz);

                    BlockPos pos = origin.add(dx, dy, dz);

                    if (dist <= radius && dist >= radius - 1.5) {
                        // Outer shell
                        world.setBlockState(pos, config.outerBlock(), 3);
                    } else if (dist <= radius - 1.5 && dist >= radius - 2.5) {
                        // Inner lining
                        world.setBlockState(pos, config.innerBlock(), 3);
                    } else if (dist < radius - 2.5) {
                        // Hollow cavity (air)
                        world.setBlockState(pos, net.minecraft.block.Blocks.AIR.getDefaultState(), 3);
                    }
                }
            }
        }

        // Add special blocks in the cavity wall
        int specials = config.specialCount().get(random);
        for (int i = 0; i < specials; i++) {
            double theta = random.nextDouble() * Math.PI * 2;
            double phi = random.nextDouble() * Math.PI;

            int sx = origin.getX() + (int) (Math.sin(phi) * Math.cos(theta) * (radius - 2));
            int sy = origin.getY() + (int) (Math.cos(phi) * (radius - 2));
            int sz = origin.getZ() + (int) (Math.sin(phi) * Math.sin(theta) * (radius - 2));

            BlockPos specialPos = new BlockPos(sx, sy, sz);
            if (world.getBlockState(specialPos).isOf(config.innerBlock().getBlock())) {
                world.setBlockState(specialPos, config.specialBlock(), 3);
            }
        }

        return true;
    }
}
