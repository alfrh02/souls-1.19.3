package land.alfred.souls;

import land.alfred.souls.entities.SoullessHusk;
import land.alfred.souls.registry.BlockRegistry;
import land.alfred.souls.registry.EntityRegistry;
import land.alfred.souls.registry.ItemRegistry;
import land.alfred.souls.registry.SoundRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Souls implements ModInitializer {

    public static String MODID = "souls";
    private static final ItemGroup ITEM_GROUP = FabricItemGroup
            .builder(new Identifier(MODID, "item_group"))
            .icon(() -> new ItemStack(ItemRegistry.SOUL_BOTTLE))
            .entries((enabledFeatures, entries, operatorEnabled) -> {
                entries.add(ItemRegistry.SOUL_BOTTLE);
                entries.add(ItemRegistry.SOULFUL_PUMPKIN);
            })
            .build();

    @Override
    public void onInitialize() {
        ItemRegistry.init();
        SoundRegistry.init();
        BlockRegistry.init();

        FabricDefaultAttributeRegistry.register(EntityRegistry.SOULLESS_HUSK, SoullessHusk.createHostileAttributes());
    }
}
