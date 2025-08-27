package com.supremetor.endgamegear;

import com.supremetor.endgamegear.entity.client.ArmoredElytraRenderer;
import com.supremetor.endgamegear.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class EndgameGearClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
//        EntityRendererFactory.Context context = MinecraftClient.getInstance();
//        ArmorRenderer.register(
//                new ArmoredElytraRenderer(loader),
//                ModItems.DRAGONITE_CHESTPLATE
//        );
        ArmorRenderer.register(
                new ArmoredElytraRenderer(
//                        "endgame-gear", "dragonite",
//                        "minecraft", "diamond",
//                        Identifier.of("minecraft", "textures/entity/equipment/humanoid/diamond.png"),
                        Identifier.of("endgame-gear", "textures/entity/equipment/humanoid/dragonite.png"),
                        Identifier.of("minecraft", "textures/entity/equipment/wings/elytra.png")
                ),
                ModItems.DRAGONITE_CHESTPLATE
        );
    }
}
