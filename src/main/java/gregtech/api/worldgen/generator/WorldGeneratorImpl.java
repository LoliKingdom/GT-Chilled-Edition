package gregtech.api.worldgen.generator;

import gregtech.common.ConfigHolder;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.EnumSet;
import java.util.Random;

import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.*;

public class WorldGeneratorImpl implements IWorldGenerator {

    private static final EnumSet<EventType> ORE_EVENT_TYPES = EnumSet.noneOf(OreGenEvent.GenerateMinable.EventType.class);
    public static final int GRID_SIZE_X = 3;
    public static final int GRID_SIZE_Z = 3;

    static {
        ORE_EVENT_TYPES.add(COAL);
        ORE_EVENT_TYPES.add(DIAMOND);
        ORE_EVENT_TYPES.add(GOLD);
        ORE_EVENT_TYPES.add(IRON);
        ORE_EVENT_TYPES.add(LAPIS);
        ORE_EVENT_TYPES.add(REDSTONE);
        ORE_EVENT_TYPES.add(QUARTZ);
        ORE_EVENT_TYPES.add(EMERALD);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onOreGenerate(OreGenEvent.GenerateMinable event) {
        if (ConfigHolder.disableVanillaOres && ORE_EVENT_TYPES.contains(event.getType())) {
            event.setResult(Result.DENY);
        }
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int selfGridX = Math.floorDiv(chunkX, GRID_SIZE_X);
        int selfGridZ = Math.floorDiv(chunkZ, GRID_SIZE_Z);
        generateInternal(world, selfGridX, selfGridZ, chunkX, chunkZ, random);
    }

    private void generateInternal(World world, int selfGridX, int selfGridZ, int chunkX, int chunkZ, Random random) {
        int halfSizeX = (GRID_SIZE_X - 1) / 2;
        int halfSizeZ = (GRID_SIZE_Z - 1) / 2;
        for (int gridX = -halfSizeX; gridX <= halfSizeX; gridX++) {
            for (int gridZ = -halfSizeZ; gridZ <= halfSizeZ; gridZ++) {
                CachedGridEntry cachedGridEntry = CachedGridEntry.getOrCreateEntry(world, selfGridX + gridX, selfGridZ + gridZ, chunkX, chunkZ);
                cachedGridEntry.populateChunk(world, chunkX, chunkZ, random);
            }
        }
    }

}
