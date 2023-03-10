package land.alfred.souls.registry;

import land.alfred.souls.Souls;
import land.alfred.souls.blocks.SoulCageBlock;
import land.alfred.souls.blocks.SoulfulPumpkinBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class BlockRegistry {

    public static Block SOULFUL_PUMPKIN;
    public static Block SOUL_CAGE;
    public static Block CARVED_PUMPKIN;

    public static void init() {
        SOULFUL_PUMPKIN = register(new SoulfulPumpkinBlock(AbstractBlock.Settings.of(Material.GOURD, MapColor.ORANGE).strength(1.0F).sounds(BlockSoundGroup.WOOD).luminance((state) -> {
            return 10;
        })), "soulful_pumpkin");
        SOUL_CAGE = register(new SoulCageBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.BLACK).requiresTool().strength(50.0F, 1200.0F).sounds(BlockSoundGroup.METAL).luminance((state) -> {
            return SoulCageBlock.getLightLevel(state, 15);
        }).nonOpaque()), "soul_cage");
        //CARVED_PUMPKIN = registerVanilla(new SoulsCarvedPumpkinBlock(AbstractBlock.Settings.of(Material.GOURD, MapColor.ORANGE).strength(1.0F).sounds(BlockSoundGroup.WOOD)), "carved_pumpkin");
    }

    public static Block register(Block block, String name) {
        return Registry.register(Registries.BLOCK, new Identifier(Souls.MODID, name), block);
    }

    public static Block registerVanilla(Block block, String name) {
        return Registry.register(Registries.BLOCK, new Identifier("minecraft", name), block);
    }
}
