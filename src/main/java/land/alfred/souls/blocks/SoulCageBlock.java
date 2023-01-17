package land.alfred.souls.blocks;

import land.alfred.souls.registry.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.SoulSpeedEnchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class SoulCageBlock extends Block {
    public static final int MAX_SOULS = 8;
    public static final IntProperty SOULS = IntProperty.of("souls", 0, MAX_SOULS);
    public SoulCageBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(SOULS, 0));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{SOULS});
    }

    public static int getLightLevel(BlockState state, int maxLevel) {
        return MathHelper.floor((float)((Integer)state.get(SOULS) - 0) / 8.0F * (float)maxLevel);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        Boolean isSoulBottle = itemStack.isOf(ItemRegistry.SOUL_BOTTLE);
        if (!isSoulBottle && !itemStack.isOf(Items.GLASS_BOTTLE)){
            return ActionResult.PASS;
        }
        else if (isSoulBottle && state.get(SOULS) < MAX_SOULS) {
            world.setBlockState(pos, (BlockState)state.with(SOULS, (Integer)state.get(SOULS) + 1), 3);
            if (!player.isCreative()) {
                player.setStackInHand(hand, new ItemStack(Items.GLASS_BOTTLE));
            }
            return ActionResult.SUCCESS;
        }
        else if (isSoulBottle && state.get(SOULS) == MAX_SOULS) {
            return ActionResult.PASS;
        }
        else if (itemStack.isOf(Items.GLASS_BOTTLE) && state.get(SOULS) > 0) {
            world.setBlockState(pos, (BlockState)state.with(SOULS, (Integer)state.get(SOULS) - 1), 3);
            if (!player.isCreative()) {
                player.setStackInHand(hand, new ItemStack(ItemRegistry.SOUL_BOTTLE));
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(SOULS) > 0 && random.nextInt(9 - state.get(SOULS)) == 0) {
            world.addParticle(ParticleTypes.SOUL, (double) pos.getX() + random.nextDouble(), (double) pos.getY() + random.nextDouble(), (double) pos.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
        }
    }
}
