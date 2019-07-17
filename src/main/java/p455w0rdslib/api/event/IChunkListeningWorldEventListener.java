package p455w0rdslib.api.event;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public interface IChunkListeningWorldEventListener {
    void onChunkLoad(World worldIn, Chunk chunk);
    void onChunkUnload(World worldIn, Chunk chunk);
}
