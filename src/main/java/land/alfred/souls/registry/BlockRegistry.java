package land.alfred.souls.registry;

import land.alfred.souls.Souls;
import land.alfred.souls.blocks.SoulsCarvedPumpkinBlock;
import net.minecraft.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class BlockRegistry {

    public static Block SOULFUL_PUMPKIN;
    public static Block CARVED_PUMPKIN;

    public static void init() {
        SOULFUL_PUMPKIN = register(new CarvedPumpkinBlock(AbstractBlock.Settings.of(Material.GOURD, MapColor.ORANGE).strength(1.0F).sounds(BlockSoundGroup.WOOD).luminance((state) -> {
            return 10;
        })), "soulful_pumpkin");
        //CARVED_PUMPKIN = registerOverride(new SoulsCarvedPumpkinBlock(AbstractBlock.Settings.of(Material.GOURD, MapColor.ORANGE).strength(1.0F).sounds(BlockSoundGroup.WOOD)), "carved_pumpkin");
    }

    public static Block register(Block block, String name) {
        return Registry.register(Registries.BLOCK, new Identifier(Souls.MODID, name), block);
    }

    public static Block registerOverride(Block block, String name) {
        return Registry.register(Registries.BLOCK, new Identifier("minecraft", name), block);
    }
}
