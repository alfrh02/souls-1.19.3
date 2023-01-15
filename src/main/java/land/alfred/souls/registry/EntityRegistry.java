package land.alfred.souls.registry;

import land.alfred.souls.Souls;
import land.alfred.souls.entities.SoullessHusk;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EntityRegistry {

    public static final EntityType<SoullessHusk> SOULLESS_HUSK = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(Souls.MODID, "soulless_husk"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SoullessHusk::new).dimensions(EntityDimensions.fixed(1f, 1f)).build()
    );
}
