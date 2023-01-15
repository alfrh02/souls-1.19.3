package land.alfred.souls.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class SoulfulPumpkinBlock extends CarvedPumpkinBlock {
    public SoulfulPumpkinBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
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
}
