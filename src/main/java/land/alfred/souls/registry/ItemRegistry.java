package land.alfred.souls.registry;

import land.alfred.souls.Souls;
import land.alfred.souls.items.SoulBottleItem;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.item.Items;

public class ItemRegistry {

    public static Item SOUL_BOTTLE;
    public static Item GLASS_BOTTLE;

    public static BlockItem SOULFUL_PUMPKIN;
    public static BlockItem SOUL_CAGE;

    public static void init() {
        // Mod Items //
        SOUL_BOTTLE = registerItem(new SoulBottleItem(new Item.Settings().
                recipeRemainder(Items.GLASS_BOTTLE)
                .maxCount(1)),
                "soul_bottle");

        // Mod BlockItems //
        SOULFUL_PUMPKIN = registerBlockItem(new BlockItem(BlockRegistry.SOULFUL_PUMPKIN, new Item.Settings().recipeRemainder(Items.CARVED_PUMPKIN)), "soulful_pumpkin");
        SOUL_CAGE = registerBlockItem(new BlockItem(BlockRegistry.SOUL_CAGE, new Item.Settings()), "soul_cage");
    }

    public static Item registerItem(Item item, String name) {
        return Registry.register(Registries.ITEM, new Identifier(Souls.MODID, name), item);
    }

    public static BlockItem registerBlockItem(BlockItem item, String name) {
        return Registry.register(Registries.ITEM, new Identifier(Souls.MODID, name), item);
    }
}