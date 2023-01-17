package land.alfred.souls.client;

import land.alfred.souls.Souls;
import land.alfred.souls.client.renderers.SoullessHuskEntityRenderer;
import land.alfred.souls.registry.BlockRegistry;
import land.alfred.souls.registry.EntityRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SoulsClient implements ClientModInitializer {

    public static final EntityModelLayer MODEL_SOULLESS_HUSK_LAYER = new EntityModelLayer(new Identifier(Souls.MODID, "soulless_husk"), "main");

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.SOUL_CAGE, RenderLayer.getCutout());
    }
}
