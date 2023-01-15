package land.alfred.souls.mixin;

import land.alfred.souls.registry.ItemRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(net.minecraft.entity.mob.ZombieEntity.class)
public abstract class ZombieEntity {
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        System.out.println(player + " interacted!");
        return ActionResult.FAIL;
    }
}
