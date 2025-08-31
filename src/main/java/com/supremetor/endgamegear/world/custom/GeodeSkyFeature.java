package com.supremetor.endgamegear.world.custom;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
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

        // Determine the amount, direction, and size of cracks in the geode
        int crackCount = config.crackCount().get(random);   // Determine amount
        List<Vec3d> crackDirs = new ArrayList<>();
        for (int i = 0; i < crackCount; i++) {  // Determine direction
            double x = random.nextDouble() * 2 - 1;
            double y = random.nextDouble() * 2 - 1;
            double z = random.nextDouble() * 2 - 1;
            Vec3d dir = new Vec3d(x, y, z).normalize();
            crackDirs.add(dir);
        }
        double crackAngle = Math.toRadians(config.crackWidth().get(random));    // Determine size

        // Create a hollow sphere:
        // Do a 3d loop through every block pos in the sphere (all possible offsets from the origin).
        // Use the distance from the origin of the block pos to determine which block (outer, inner, or AIR) should be placed.
        for (int dx = -radius - 1; dx <= radius + 1; dx++) {
            for (int dy = -radius - 1; dy <= radius + 1; dy++) {
                for (int dz = -radius - 1; dz <= radius + 1; dz++) {
                    double dist = Math.sqrt(dx*dx + dy*dy + dz*dz);

                    BlockPos pos = origin.add(dx, dy, dz);

                    // Determine if this block pos is in a crack.
                    // Get the direction from the center of the geode to this block,
                    // Get the angle of that direction
                    // If that angle is within the crack angle, this block is in the crack
                    Vec3d toBlock = new Vec3d(dx, dy, dz).normalize();
                    boolean inCrack = false;
                    for (Vec3d dir : crackDirs) {
                        double angle = Math.acos(toBlock.dotProduct(dir));
                        if (angle < crackAngle) {
                            inCrack = true;
                            break;
                        }
                    }

                    // If this block is in a crack,
                    // And if this is an outer or inner block (not hollow cavity),
                    // This block should be air and skipped in placement.
                    if (inCrack && dist <= radius && dist >= radius - 3) {
                        world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                        continue;
                    }

                    // Place the correct block for the correct layer of the geode for the block pos.
                    // Outer layer
                    if (dist <= radius && dist >= radius - 1.5) {
                        world.setBlockState(pos, config.outerBlock().get(random, pos), 3);
                    }
                    // Inner layer
                    else if (dist <= radius - 1.5 && dist >= radius - 2.5) {
                        world.setBlockState(pos, config.innerBlock().get(random, pos), 3);
                        innerBlocks.add(pos);
                    }
                    // Hollow cavity
                    else if (dist < radius - 2.5) {
                        world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                    }
                }
            }
        }

        // Add special blocks to the inner wall.
        // Pick a random block pos from the inner wall (the list is filled earlier) and swap it to the special block.
        int specials = config.specialCount().get(random);
        for (int i = 0; i < specials; i++) {
            // Skip special block placement if there are no more inner blocks to replace
            if (!innerBlocks.isEmpty()) {
                // Randomly select an inner block to replace
                int rand = random.nextInt(innerBlocks.size());
                BlockPos specialPos = innerBlocks.get(rand);

                // Replace the inner block with a special block
                world.setBlockState(specialPos, config.specialBlock().get(random, specialPos), 3);

                // Remove this inner block so it cannot be randomly selected again
                innerBlocks.remove(rand);
            }
        }

        return true;
    }
}
