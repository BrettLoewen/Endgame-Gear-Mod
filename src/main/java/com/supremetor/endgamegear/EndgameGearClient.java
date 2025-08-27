package com.supremetor.endgamegear;

import com.supremetor.endgamegear.entity.client.ArmoredElytraFeatureRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.util.Identifier;

public class EndgameGearClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Register the Armored Elytra Feature Renderer
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register(
                (entityType, entityRenderer, registrationHelper, context) -> {
                    // Only register for biped renderers (players, zombies, etc.)
                    if (entityRenderer.getModel() instanceof BipedEntityModel<?>) {
                        // Use the vanilla elytra texture for now
                        Identifier wingTexture = Identifier.of("minecraft", "textures/entity/equipment/wings/elytra.png");

                        @SuppressWarnings("unchecked")
                        FeatureRendererContext<BipedEntityRenderState, BipedEntityModel<BipedEntityRenderState>> ctx =
                                (FeatureRendererContext<BipedEntityRenderState, BipedEntityModel<BipedEntityRenderState>>) entityRenderer;

                        registrationHelper.register(new ArmoredElytraFeatureRenderer<>(ctx, wingTexture));
                    }
                }
        );
    }
}
