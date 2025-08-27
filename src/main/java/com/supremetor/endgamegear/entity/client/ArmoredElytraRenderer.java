package com.supremetor.endgamegear.entity.client;

import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.ArmorEntityModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.ElytraEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ArmoredElytraRenderer implements ArmorRenderer {
    private final ArmorEntityModel<BipedEntityRenderState> armorModel;
    private final ElytraEntityModel elytraModelAdult;
    private final ElytraEntityModel elytraModelBaby;

    private final Identifier armorTexture;
    private final Identifier wingTexture;

    public ArmoredElytraRenderer(Identifier armorTexture, Identifier wingTexture) {
        this.armorTexture = armorTexture;
        this.wingTexture  = wingTexture;

        // Build an outer armor model.
        // Dilation defines a scale-up for the armor model over the player's model to make the armor slightly bigger than the player.
        // A dilation of 1.0f is what is used for normal armors.
        ModelData armorData = ArmorEntityModel.getModelData(new Dilation(1.0f));
        TexturedModelData armorTmd = TexturedModelData.of(armorData, 64, 32);
        this.armorModel = new ArmorEntityModel<>(armorTmd.createModel());

        // Build elytra models.
        // The baby model variation exists in case the item ever gets equipped to a baby mob (baby zombie, etc.).
        TexturedModelData elytraTmd = ElytraEntityModel.getTexturedModelData();
        TexturedModelData elytraTmdBaby = ElytraEntityModel.getTexturedModelData().transform(ElytraEntityModel.BABY_TRANSFORMER);

        this.elytraModelAdult = new ElytraEntityModel(elytraTmd.createModel());
        this.elytraModelBaby  = new ElytraEntityModel(elytraTmdBaby.createModel());
    }

    @Override
    public void render(MatrixStack matrices,
                       VertexConsumerProvider consumers,
                       ItemStack stack,
                       BipedEntityRenderState state,
                       EquipmentSlot slot,
                       int light,
                       BipedEntityModel<BipedEntityRenderState> contextModel) {

        // This should only be used for chestplates, so exit early if the slot is not a chest slot.
        // The code could be modified to support other slots, but that would go unused right now, so I won't do that.
        if (slot != EquipmentSlot.CHEST) return;

        // Draw the armor model.
        // Ensure the armor model is drawn correctly and is in sync with the player pose using the render state and
        contextModel.copyTransforms(this.armorModel);
        this.armorModel.setVisible(false);
        this.armorModel.body.visible      = true;
        this.armorModel.rightArm.visible  = true;
        this.armorModel.leftArm.visible   = true;
        this.armorModel.setAngles(state);
        ArmorRenderer.renderPart(matrices, consumers, light, stack, this.armorModel, this.armorTexture);

        // Draw the elytra model.
        // Double check that the item actually has the GLIDER component before continuing.
        // Ensure the model matches the player state
        if (stack.contains(DataComponentTypes.GLIDER)) {
            final ElytraEntityModel wingModel = state.baby ? this.elytraModelBaby : this.elytraModelAdult;
            wingModel.setAngles(state);

            VertexConsumer wingVC = ItemRenderer.getArmorGlintConsumer(
                    consumers,
                    RenderLayer.getArmorCutoutNoCull(this.wingTexture),
                    stack.hasGlint()
            );
            wingModel.render(matrices, wingVC, light, OverlayTexture.DEFAULT_UV);
        }
    }
}
