package land.alfred.souls.entities;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class SoullessHusk extends ZombieVillagerEntity {

    private int conversionTimer;

    public SoullessHusk(EntityType<? extends ZombieVillagerEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        if (!this.world.isClient && this.isAlive()) {
            this.conversionTimer -= 0.1f;
            if (this.conversionTimer <= 0) {
                this.finishConversion((ServerWorld)this.world);
            }
        }
        super.tick();
    }

    private void finishConversion(ServerWorld world) {
        SkeletonEntity skeletonEntity = (SkeletonEntity)this.convertTo(EntityType.SKELETON, true);
    }

    public SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_HUSK_AMBIENT;
    }

    public SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_HUSK_HURT;
    }

    public SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_HUSK_DEATH;
    }

    public SoundEvent getStepSound() {
        return SoundEvents.ENTITY_HUSK_STEP;
    }
}
