package land.alfred.souls.registry;

import land.alfred.souls.Souls;
import land.alfred.souls.items.SoulBottleItem;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.minecraft.item.Items.GLASS_BOTTLE;

public class ItemRegistry {

    public static Item SOUL_BOTTLE;

    public static void init() {
        SOUL_BOTTLE = registerItem(new SoulBottleItem(new Item.Settings().
                recipeRemainder(GLASS_BOTTLE)
                .maxCount(16)),
                "soul_bottle");
    }

    public static Item registerItem(Item item, String name) {
        return Registry.register(Registries.ITEM, new Identifier(Souls.MODID, name), item);
    }
}