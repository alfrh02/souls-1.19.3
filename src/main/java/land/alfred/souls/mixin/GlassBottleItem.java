package land.alfred.souls.mixin;

import land.alfred.souls.registry.EntityRegistry;
import land.alfred.souls.registry.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HuskEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;

import static net.minecraft.entity.EntityType.ZOMBIE;

@Mixin(net.minecraft.item.GlassBottleItem.class)
public abstract class GlassBottleItem {
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (entity.getType() == ZOMBIE) {
            HuskEntity huskEntity = new HuskEntity(EntityType.HUSK, entity.getEntityWorld());
            huskEntity.setHealth(entity.getHealth());
            huskEntity.setBaby(entity.isBaby());
            huskEntity.setCustomName(entity.getCustomName());
            huskEntity.setPosition(entity.getPos());
            huskEntity.setOnFire(entity.isOnFire());
            huskEntity.setHeadYaw(entity.getHeadYaw());
            huskEntity.setBodyYaw(entity.getBodyYaw());

            user.incrementStat(Stats.USED.getOrCreateStat(Items.GLASS_BOTTLE));
            entity.playSound(SoundEvents.PARTICLE_SOUL_ESCAPE, 4.0F, 1.0F);
            user.setStackInHand(user.getActiveHand(), ItemUsage.exchangeStack(stack, user, new ItemStack(ItemRegistry.SOUL_BOTTLE)));
            user.playSound(SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH, 1.0F, 1.0F);
            entity.world.spawnEntity(huskEntity);
            entity.remove(Entity.RemovalReason.DISCARDED);

            huskEntity.setCustomName(Text.of("Soulless Husk"));
            huskEntity.setCustomNameVisible(true);
        }
        return ActionResult.PASS;
    }
}
