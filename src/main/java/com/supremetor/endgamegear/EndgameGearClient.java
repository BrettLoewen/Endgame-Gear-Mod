package com.supremetor.endgamegear;

import com.supremetor.endgamegear.entity.client.ArmoredElytraFeatureRenderer;
import com.supremetor.endgamegear.entity.client.ArmoredElytraRenderer;
import com.supremetor.endgamegear.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;

public class EndgameGearClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
//        ArmorRenderer.register(
//                new ArmoredElytraRenderer(
//                        Identifier.of("endgame-gear", "textures/entity/equipment/humanoid/dragonite.png"),
//                        Identifier.of("minecraft", "textures/entity/equipment/wings/elytra.png")
//                ),
//                ModItems.DRAGONITE_CHESTPLATE
//        );

        LivingEntityFeatureRendererRegistrationCallback.EVENT.register(
                (entityType, entityRenderer, registrationHelper, context) -> {
                    // only register for biped renderers (players, zombies, etc.)
                    if (entityRenderer.getModel() instanceof BipedEntityModel<?>) {
                        // registrationHelper gives you context objects; pass loader and the texture you want
                        Identifier wingTex = Identifier.of("minecraft", "textures/entity/equipment/wings/elytra.png");

                        @SuppressWarnings("unchecked")
                        FeatureRendererContext<BipedEntityRenderState, BipedEntityModel<BipedEntityRenderState>> ctx =
                                (FeatureRendererContext<BipedEntityRenderState, BipedEntityModel<BipedEntityRenderState>>) entityRenderer;

                        registrationHelper.register(new ArmoredElytraFeatureRenderer<>(ctx, wingTex));
                    }
                }
        );
    }
}
