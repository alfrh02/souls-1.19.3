package land.alfred.souls.client.renderers;

import land.alfred.souls.Souls;
import land.alfred.souls.client.SoulsClient;
import land.alfred.souls.entities.SoullessHusk;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class SoullessHuskEntityRenderer extends MobEntityRenderer {

    public SoullessHuskEntityRenderer(EntityRendererFactory.Context context, EntityModel entityModel, float f) {
        super(context, new ZombieEntityModel(context.getPart(SoulsClient.MODEL_SOULLESS_HUSK_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(Entity entity) {
        return new Identifier(Souls.MODID, "textures/entity/soulless_husk.png");
    }
}
