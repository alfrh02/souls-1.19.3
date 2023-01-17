package land.alfred.souls.blocks;

import land.alfred.souls.registry.BlockRegistry;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.Material;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.MaterialPredicate;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.Iterator;

public class SoulfulPumpkinBlock extends CarvedPumpkinBlock {
    private BlockPattern woodGolemPattern;

    public SoulfulPumpkinBlock(Settings settings) {
        super(settings);
    }

    //ripped from PumpkinBlock.class, refer to there for unaltered code
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.GLASS_BOTTLE)) {
            if (!world.isClient) {
                Direction direction = state.get(Properties.HORIZONTAL_FACING);
                world.playSound((PlayerEntity)null, pos, SoundEvents.PARTICLE_SOUL_ESCAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH, SoundCategory.PLAYERS, 1.0F, 1.0F);
                world.setBlockState(pos, (BlockState)Blocks.CARVED_PUMPKIN.getDefaultState().with(CarvedPumpkinBlock.FACING, direction), 11);

                world.addParticle(ParticleTypes.SOUL, pos.getX(), pos.getY(), pos.getZ(), 0.05 * (double)direction.getOffsetX() + world.random.nextDouble() * 0.02, 0.05, 0.05 * (double)direction.getOffsetZ() + world.random.nextDouble() * 0.02);

                player.incrementStat(Stats.USED.getOrCreateStat(Items.GLASS_BOTTLE));
            }

            return ActionResult.success(world.isClient);
        } else {
            return super.onUse(state, world, pos, player, hand, hit);
        }
    }

    // rework this its not very good
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        Direction dir = state.get(FACING);
        if (random.nextInt(8) == 0) {
            switch(dir) {
                case NORTH:
                    world.addParticle(ParticleTypes.SOUL_FIRE_FLAME, (double)pos.getX(), (double)pos.getY() - 0.5, (double)pos.getZ() - 1.1, 0.0, 0.0, 0.0);
                case EAST:
                    world.addParticle(ParticleTypes.SOUL_FIRE_FLAME, (double)pos.getX() + 1.1, (double)pos.getY() + random.nextDouble() / 1.5, (double)pos.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
                case SOUTH:
                    world.addParticle(ParticleTypes.SOUL_FIRE_FLAME, (double)pos.getX() + random.nextDouble(), (double)pos.getY() + random.nextDouble() / 1.5, (double)pos.getZ() + 1.1, 0.0, 0.0, 0.0);
                case WEST:
                    world.addParticle(ParticleTypes.SOUL_FIRE_FLAME, (double)pos.getX() - 1.1, (double)pos.getY() + random.nextDouble() / 1.5, (double)pos.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
            }
        }
    }
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!oldState.isOf(state.getBlock())) {
            this.trySpawnEntity(world, pos);
        }
    }

    private void trySpawnEntity(World world, BlockPos pos) {
        BlockPattern.Result result = this.getWoodGolemPattern().searchAround(world, pos);
        if (result != null) {
            SnowGolemEntity snowGolemEntity = (SnowGolemEntity) EntityType.SNOW_GOLEM.create(world);
            if (snowGolemEntity != null) {
                spawnEntity(world, result, snowGolemEntity, result.translate(0, 2, 0).getBlockPos());
            }
        }
    }

    private static void spawnEntity(World world, BlockPattern.Result patternResult, Entity entity, BlockPos pos) {
        breakPatternBlocks(world, patternResult);
        // wood golem will be 1 block tall! remove the + 1.0 for pos.getY()!
        if (patternResult.getForwards() == Direction.NORTH || patternResult.getForwards() == Direction.SOUTH) {
            entity.refreshPositionAndAngles((double) pos.getX() - 0.5, (double) pos.getY() + 1.0, (double) pos.getZ() + 0.5, 0.0F, 0.0F);
        } else {
            entity.refreshPositionAndAngles((double) pos.getX() + 0.5, (double) pos.getY() + 1.0, (double) pos.getZ() - 0.5, 0.0F, 0.0F);
        }
        BlockPos hatPos = new BlockPos(entity.getBlockPos().getX(), entity.getBlockPos().getY() + 2, entity.getBlockPos().getZ());
        if (world.getBlockState(hatPos).getBlock() == Blocks.BLACK_CARPET) {
            System.out.println("black hat");
            entity.setCustomName(Text.of("Wooden Golem [Black Hat]"));
        } else {
            entity.setCustomName(Text.of("Wooden Golem"));
        }
        entity.setCustomNameVisible(true);
        entity.setNoGravity(true);
        world.spawnEntity(entity);
        Iterator var4 = world.getNonSpectatingEntities(ServerPlayerEntity.class, entity.getBoundingBox().expand(5.0)).iterator();

        while(var4.hasNext()) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)var4.next();
            Criteria.SUMMONED_ENTITY.trigger(serverPlayerEntity, entity);
        }
        updatePatternBlocks(world, patternResult);
    }

    private BlockPattern getWoodGolemPattern() {
        if (this.woodGolemPattern == null) {
            this.woodGolemPattern = BlockPatternBuilder.start().aisle(new String[]{"~^~", "-#-"}).where('^', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(BlockRegistry.SOULFUL_PUMPKIN))).where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.STRIPPED_OAK_LOG))).where ('-', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.OAK_BUTTON))).where('~', CachedBlockPosition.matchesBlockState(MaterialPredicate.create(Material.AIR))).build();
        }
        return this.woodGolemPattern;
    }
}
