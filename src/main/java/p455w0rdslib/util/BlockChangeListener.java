package p455w0rdslib.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldEventListener;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import p455w0rdslib.api.event.IChunkListeningWorldEventListener;

import javax.annotation.Nullable;

public abstract class BlockChangeListener implements IWorldEventListener, IChunkListeningWorldEventListener {
    protected final World world;

    protected BlockChangeListener(World world) {
        this.world = world;
    }

    protected abstract boolean isMatchingBlock(IBlockState state);
    protected abstract void onBlockAdded(BlockPos pos, IBlockState state);
    protected abstract void onBlockRemoved(BlockPos pos, IBlockState state);

    @Override
    public void onChunkLoad(World worldIn, Chunk chunk) {
        int xOffset = chunk.x << 4;
        int zOffset = chunk.z << 4;

        for (int y = 0; y < chunk.getWorld().getHeight(); y++) {
            for (int z = 0; z < 16; z++) {
                for (int x = 0; x < 16; x++) {
                    IBlockState state = chunk.getBlockState(xOffset + x, y, zOffset + z);
                    if (isMatchingBlock(state)) {
                        onBlockAdded(new BlockPos(xOffset + x, y, zOffset + z), state);
                    }
                }
            }
        }
    }

    @Override
    public void onChunkUnload(World worldIn, Chunk chunk) {
        int xOffset = chunk.x << 4;
        int zOffset = chunk.z << 4;

        for (int y = 0; y < chunk.getWorld().getHeight(); y++) {
            for (int z = 0; z < 16; z++) {
                for (int x = 0; x < 16; x++) {
                    IBlockState state = chunk.getBlockState(xOffset + x, y, zOffset + z);
                    if (isMatchingBlock(state)) {
                        onBlockRemoved(new BlockPos(xOffset + x, y, zOffset + z), state);
                    }
                }
            }
        }
    }

    @Override
    public void notifyBlockUpdate(World worldIn, BlockPos pos, IBlockState oldState, IBlockState newState, int flags) {
        if (oldState != newState) {
            if (isMatchingBlock(oldState)) {
                onBlockRemoved(pos, oldState);
            }
            if (isMatchingBlock(newState)) {
                onBlockAdded(pos, newState);
            }
        }
    }

    @Override
    public void notifyLightSet(BlockPos pos) {

    }

    @Override
    public void markBlockRangeForRenderUpdate(int x1, int y1, int z1, int x2, int y2, int z2) {

    }

    @Override
    public void playSoundToAllNearExcept(@Nullable EntityPlayer player, SoundEvent soundIn, SoundCategory category, double x, double y, double z, float volume, float pitch) {

    }

    @Override
    public void playRecord(SoundEvent soundIn, BlockPos pos) {

    }

    @Override
    public void spawnParticle(int particleID, boolean ignoreRange, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int... parameters) {

    }

    @Override
    public void spawnParticle(int id, boolean ignoreRange, boolean p_190570_3_, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int... parameters) {

    }

    @Override
    public void onEntityAdded(Entity entityIn) {

    }

    @Override
    public void onEntityRemoved(Entity entityIn) {

    }

    @Override
    public void broadcastSound(int soundID, BlockPos pos, int data) {

    }

    @Override
    public void playEvent(EntityPlayer player, int type, BlockPos blockPosIn, int data) {

    }

    @Override
    public void sendBlockBreakProgress(int breakerId, BlockPos pos, int progress) {

    }
}
