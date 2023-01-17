package land.alfred.souls.mixin;

import land.alfred.souls.blocks.SoulfulPumpkinBlock;
import land.alfred.souls.registry.BlockRegistry;
import land.alfred.souls.registry.ItemRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(net.minecraft.block.CarvedPumpkinBlock.class)
public abstract class CarvedPumpkinBlock extends HorizontalFacingBlock {
    protected CarvedPumpkinBlock(Settings settings) {
        super(settings);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(ItemRegistry.SOUL_BOTTLE)) {
            if (!world.isClient) {
                Direction direction = state.get(Properties.HORIZONTAL_FACING);
                world.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 0.8F, 1.0F);
                world.playSound((PlayerEntity)null, pos, SoundEvents.PARTICLE_SOUL_ESCAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.PLAYERS, 1.0F, 1.0F);
                world.setBlockState(pos, (BlockState) BlockRegistry.SOULFUL_PUMPKIN.getDefaultState().with(SoulfulPumpkinBlock.FACING, direction), 11);

                world.addParticle(ParticleTypes.SOUL, pos.getX(), pos.getY(), pos.getZ(), 0.05 * (double)direction.getOffsetX() + world.random.nextDouble() * 0.02, 0.05, 0.05 * (double)direction.getOffsetZ() + world.random.nextDouble() * 0.02);

                player.incrementStat(Stats.USED.getOrCreateStat(ItemRegistry.SOUL_BOTTLE));
            }

            return ActionResult.success(world.isClient);
        } else {
            return super.onUse(state, world, pos, player, hand, hit);
        }
    }
}
