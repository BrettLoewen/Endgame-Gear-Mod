package com.supremetor.endgamegear.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class DurabilityRocketItem extends FireworkRocketItem {
    public DurabilityRocketItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        // Remove firework launch when user clicks on block
        return ActionResult.PASS;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        // From FireworkRocketItem
        // Copied firework glider propulsion logic, but with item consumption replaced with item damage
        if (user.isGliding()) {
            ItemStack itemStack = user.getStackInHand(hand);
            if (world instanceof ServerWorld serverWorld) {
                if (user.detachAllHeldLeashes(null)) {
                    world.playSoundFromEntity(null, user, SoundEvents.ITEM_LEAD_BREAK, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                }

                ProjectileEntity.spawn(new FireworkRocketEntity(world, itemStack, user), serverWorld, itemStack);
                itemStack.damage(1, user, LivingEntity.getSlotForHand(hand));
                user.incrementStat(Stats.USED.getOrCreateStat(this));
            }

            return ActionResult.SUCCESS;
        } else {
            return ActionResult.PASS;
        }
    }
}
