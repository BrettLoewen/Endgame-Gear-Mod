package com.supremetor.endgamegear.entity.client;

import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.ElytraEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ArmoredElytraFeatureRenderer<T extends BipedEntityRenderState, M extends BipedEntityModel<T>>
        extends FeatureRenderer<T, M> {

    private final ElytraEntityModel adultModel;
    private final ElytraEntityModel babyModel;
    private final Identifier wingTexture;

    public ArmoredElytraFeatureRenderer(FeatureRendererContext<T, M> context, Identifier wingTexture) {
        super(context);

        // Build elytra models.
        // The baby model variation exists in case the item ever gets equipped to a baby mob (baby zombie, etc.).
        TexturedModelData elytraTmd = ElytraEntityModel.getTexturedModelData();
        TexturedModelData elytraTmdBaby = ElytraEntityModel.getTexturedModelData().transform(ElytraEntityModel.BABY_TRANSFORMER);

        this.adultModel = new ElytraEntityModel(elytraTmd.createModel());
        this.babyModel  = new ElytraEntityModel(elytraTmdBaby.createModel());

        this.wingTexture = wingTexture;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T state, float limbAngle, float limbDistance) {

        // BipedEntityRenderState exposes the equipped chest stack - use it
        ItemStack chest = state.equippedChestStack;
        if (chest == null || !chest.contains(DataComponentTypes.GLIDER)) return;

        final ElytraEntityModel model = state.baby ? this.babyModel : this.adultModel;

        // Sync pose/angles with the current render state
        model.setAngles(state);

        VertexConsumer vc = ItemRenderer.getArmorGlintConsumer(
                vertexConsumers,
                RenderLayer.getArmorCutoutNoCull(this.wingTexture),
                chest.hasGlint()
        );
        model.render(matrices, vc, light, OverlayTexture.DEFAULT_UV);
    }

//    @Override
//    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T state, float limbAngle, float limbDistance) {
//
//    }
}
