package com.supremetor.endgamegear.entity.client;

import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.ArmorEntityModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.ElytraEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ArmoredElytraRenderer implements ArmorRenderer {
//    private final ElytraEntityModel elytraModel;
//
//    public ArmoredElytraRenderer(EntityRendererFactory.Context context) {
//        this.elytraModel = new ElytraEntityModel(context.getPart(EntityModelLayers.ELYTRA));
//    }
//
//    @Override
//    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, ItemStack itemStack,
//                       BipedEntityRenderState bipedEntityRenderState, EquipmentSlot equipmentSlot, int light,
//                       BipedEntityModel<BipedEntityRenderState> bipedEntityModel) {
//        if (equipmentSlot == EquipmentSlot.CHEST) {
//            ArmorRenderer.renderPart(matrixStack, vertexConsumerProvider, light, itemStack, bipedEntityModel, bipedEntityRenderState);
//
//            MinecraftClient client = MinecraftClient.getInstance();
//            Identifier elytraTexture = Identifier.of("textures.entity.elytra.png");
//
//            matrixStack.push();
//            matrixStack.translate(0.0D, 0.0D, 0.125D);
//
//            this.elytraModel.setAngles(bipedEntityRenderState);
//            VertexConsumer consumer = vertexConsumerProvider.getBuffer(RenderLayer.getArmorCutoutNoCull(elytraTexture));
//            this.elytraModel.render(matrixStack, consumer, light, OverlayTexture.DEFAULT_UV);
//
//            matrixStack.pop();
//        }
//    }

    private final ArmorEntityModel<BipedEntityRenderState> chestModel;
    private final ElytraEntityModel elytraModelAdult;
    private final ElytraEntityModel elytraModelBaby;

    private final Identifier chestTexture; // your armor chest texture
    private final Identifier wingTexture;  // your wing/elytra texture

    public ArmoredElytraRenderer(Identifier chestTexture, Identifier wingTexture) {
        this.chestTexture = chestTexture;
        this.wingTexture  = wingTexture;

        // Build an outer armor model (1.0F dilation = outer)
        ModelData armorData = ArmorEntityModel.getModelData(new Dilation(1.0F)); // 1.21 API
        TexturedModelData armorTmd = TexturedModelData.of(armorData, 64, 64);
        this.chestModel = new ArmorEntityModel<>(armorTmd.createModel());

        // Build elytra models (adult + baby)
        TexturedModelData elytraTmd      = ElytraEntityModel.getTexturedModelData();
        TexturedModelData elytraTmdBaby  = ElytraEntityModel.getTexturedModelData()
                .transform(ElytraEntityModel.BABY_TRANSFORMER);

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

        if (slot != EquipmentSlot.CHEST) return;

        // ---- 1) draw the chestplate armor ----
        // keep the armor model in sync with the player pose
        contextModel.copyTransforms(this.chestModel); // 1.21 API
        this.chestModel.setVisible(false);
        this.chestModel.body.visible      = true;
        this.chestModel.rightArm.visible  = true;
        this.chestModel.leftArm.visible   = true;
        this.chestModel.setAngles(state); // pose via render state

        ArmorRenderer.renderPart(matrices, consumers, light, stack, this.chestModel, this.chestTexture);
        // (renderPart handles glint + the correct RenderLayer for armor) :contentReference[oaicite:1]{index=1}

        // ---- 2) draw the wings (elytra) on top of the chestplate ----
        // Only render wings if your item has the GLIDER component (fits your “glider datacomponenttype” setup)
        if (stack.contains(DataComponentTypes.GLIDER)) { // 1.21 data components
            final ElytraEntityModel wingModel = state.baby ? this.elytraModelBaby : this.elytraModelAdult;
            wingModel.setAngles(state); // uses BipedEntityRenderState in 1.21 :contentReference[oaicite:2]{index=2}

            VertexConsumer wingVC = ItemRenderer.getArmorGlintConsumer(
                    consumers,
                    RenderLayer.getArmorCutoutNoCull(this.wingTexture),
                    stack.hasGlint()
            );
            wingModel.render(matrices, wingVC, light, OverlayTexture.DEFAULT_UV);
        }
    }
}
