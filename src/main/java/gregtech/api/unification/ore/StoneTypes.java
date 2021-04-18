package gregtech.api.unification.ore;

import gregtech.api.unification.material.Materials;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.objects.blocks.stone.BlockRockVariant;
import net.minecraft.block.*;
import net.minecraft.block.BlockStone.EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

import java.util.EnumMap;
import java.util.Map;

import static gregtech.api.unification.ore.StoneType.AFFECTED_BY_GRAVITY;
import static gregtech.api.unification.ore.StoneType.UNBREAKABLE;

public class StoneTypes {

    // Default . . .
    public static final StoneType STONE = new StoneType(0, "stone", new ResourceLocation("blocks/stone"), SoundType.STONE, OrePrefix.ore, Materials.Stone, "pickaxe", 0,
        () -> Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, EnumType.STONE),
        state -> state.getBlock() instanceof BlockStone && state.getValue(BlockStone.VARIANT) == BlockStone.EnumType.STONE);

    /*
    public static final StoneType GRANITE = new StoneType(1, "granite", new ResourceLocation("blocks/stone_granite"), SoundType.STONE, OrePrefix.ore, Materials.GraniteBlack, "pickaxe", 0,
        () -> Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, EnumType.GRANITE),
        state -> state.getBlock() instanceof BlockStone && state.getValue(BlockStone.VARIANT) == EnumType.GRANITE);

    public static final StoneType DIORITE = new StoneType(2, "diorite", new ResourceLocation("blocks/stone_diorite"), SoundType.STONE, OrePrefix.ore, Materials.Diorite, "pickaxe", 0,
        () -> Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, EnumType.DIORITE),
        state -> state.getBlock() instanceof BlockStone && state.getValue(BlockStone.VARIANT) == EnumType.DIORITE);

    public static final StoneType ANDESITE = new StoneType(3, "andesite", new ResourceLocation("blocks/stone_andesite"), SoundType.STONE, OrePrefix.ore, Materials.Andesite, "pickaxe", 0,
        () -> Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE),
        state -> state.getBlock() instanceof BlockStone && state.getValue(BlockStone.VARIANT) == EnumType.ANDESITE);
     */

    /*
    public static final StoneType GRAVEL = new StoneType(4, "gravel", new ResourceLocation("blocks/gravel"), SoundType.SAND, OrePrefix.oreGravel, Materials.Flint, "shovel", AFFECTED_BY_GRAVITY,
        Blocks.GRAVEL::getDefaultState,
        state -> state.getBlock() instanceof BlockGravel);
     */

    /*
    public static final StoneType BEDROCK = new StoneType(5, "bedrock", new ResourceLocation("blocks/bedrock"), SoundType.STONE, OrePrefix.ore, Materials.Stone, "pickaxe", UNBREAKABLE,
        Blocks.BEDROCK::getDefaultState,
        state -> state.getBlock() == Blocks.BEDROCK);
     */

    // 6
    public static final StoneType NETHERRACK = new StoneType(1, "netherrack", new ResourceLocation("blocks/netherrack"), SoundType.STONE, OrePrefix.oreNetherrack, Materials.Netherrack, "pickaxe", 0,
        Blocks.NETHERRACK::getDefaultState,
        state -> state.getBlock() == Blocks.NETHERRACK);

    // 7
    public static final StoneType ENDSTONE = new StoneType(2, "endstone", new ResourceLocation("blocks/end_stone"), SoundType.STONE, OrePrefix.oreEndstone, Materials.Endstone, "pickaxe", 0,
        Blocks.END_STONE::getDefaultState,
        state -> state.getBlock() == Blocks.END_STONE);

    // TODO: TFC Sandstone
    /*
    public static final StoneType SANDSTONE = new StoneType(8, "sandstone", new ResourceLocation("blocks/sandstone_normal"), new ResourceLocation("blocks/sandstone_top"), SoundType.STONE, OrePrefix.oreSand, Materials.SiliconDioxide, "pickaxe", 0,
        () -> Blocks.SANDSTONE.getDefaultState().withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.DEFAULT),
        state -> state.getBlock() instanceof BlockSandStone && state.getValue(BlockSandStone.TYPE) == BlockSandStone.EnumType.DEFAULT);

    public static final StoneType RED_SANDSTONE = new StoneType(9, "red_sandstone", new ResourceLocation("blocks/red_sandstone_normal"), new ResourceLocation("blocks/red_sandstone_top"), SoundType.STONE, OrePrefix.oreSand, Materials.SiliconDioxide, "pickaxe", 0,
        () -> Blocks.RED_SANDSTONE.getDefaultState().withProperty(BlockRedSandstone.TYPE, BlockRedSandstone.EnumType.DEFAULT),
        state -> state.getBlock() instanceof BlockRedSandstone && state.getValue(BlockRedSandstone.TYPE) == BlockRedSandstone.EnumType.DEFAULT);
     */

    // private static final Map<Rock, Map<Rock.Type, StoneType>> ROCK_MAP = new Object2ObjectOpenHashMap<>(TFCRegistries.ROCKS.getKeys().size());

    static {
        int id = 3;
        for (Rock rock : TFCRegistries.ROCKS) {
            // Map<Rock.Type, StoneType> nestedMap = new EnumMap<>(Rock.Type.class);
            // ROCK_MAP.put(rock, nestedMap);
            for (Rock.Type type : new Rock.Type[] { Rock.Type.GRAVEL, Rock.Type.SAND, Rock.Type.RAW }) {
                new StoneType(id++, rock.toString() + type.toString(), new ResourceLocation(TerraFirmaCraft.MOD_ID, "blocks/stonetypes/" + type.toString() + "/" + rock.toString()), SoundType.STONE, OrePrefix.ore, Materials.Stone, "pickaxe", 0,
                        () -> BlockRockVariant.get(rock, type).getDefaultState(),
                        s -> s == BlockRockVariant.get(rock, type).getDefaultState());
            }
        }
    }

    public static void init() { }

}
