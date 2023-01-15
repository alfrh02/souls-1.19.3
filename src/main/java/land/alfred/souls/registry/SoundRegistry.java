package land.alfred.souls.registry;

import land.alfred.souls.Souls;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundRegistry {

    public static final Identifier SOUL_BOTTLE_ID = new Identifier(Souls.MODID + ":soul_bottle");
    public static final SoundEvent SOUL_BOTTLE = SoundEvent.of(new Identifier(Souls.MODID + ":soul_bottle"));

    public static void init() {
        register(SOUL_BOTTLE, SOUL_BOTTLE_ID);
    }

    public static SoundEvent register(SoundEvent event, Identifier id) {
        return Registry.register(Registries.SOUND_EVENT, id, event);
    }

}
