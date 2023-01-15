package land.alfred.souls.registry;

import land.alfred.souls.Souls;
import land.alfred.souls.items.SoulBottleItem;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.item.Items;

public class ItemRegistry {

    public static Item SOUL_BOTTLE;

    public static BlockItem SOULFUL_PUMPKIN;

    public static void init() {
        SOUL_BOTTLE = registerItem(new SoulBottleItem(new Item.Settings().
                recipeRemainder(Items.GLASS_BOTTLE)
                .maxCount(16)),
                "soul_bottle");

        SOULFUL_PUMPKIN = registerBlockItem(new BlockItem(BlockRegistry.SOULFUL_PUMPKIN, new Item.Settings().recipeRemainder(Items.CARVED_PUMPKIN)), "soulful_pumpkin");
    }

    public static Item registerItem(Item item, String name) {
        return Registry.register(Registries.ITEM, new Identifier(Souls.MODID, name), item);
    }

    public static BlockItem registerBlockItem(BlockItem item, String name) {
        return Registry.register(Registries.ITEM, new Identifier(Souls.MODID, name), item);
    }
}