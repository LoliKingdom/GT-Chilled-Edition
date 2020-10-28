package gregtech.api.unification.ore;

import gregtech.api.unification.material.Materials;
import net.minecraft.block.*;
import net.minecraft.block.BlockStone.EnumType;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

import static gregtech.api.unification.ore.StoneType.AFFECTED_BY_GRAVITY;
import static gregtech.api.unification.ore.StoneType.UNBREAKABLE;

public class StoneTypes {

    public static StoneType STONE = new StoneType(0, "stone", new ResourceLocation("blocks/stone"), SoundType.STONE, OrePrefix.ore, Materials.Stone, "pickaxe", 0,
        () -> Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, EnumType.STONE),
        state -> state.getBlock() instanceof BlockStone && state.getValue(BlockStone.VARIANT) == BlockStone.EnumType.STONE);

    public static StoneType GRANITE = new StoneType(1, "granite", new ResourceLocation("blocks/stone_granite"), SoundType.STONE, OrePrefix.ore, Materials.GraniteBlack, "pickaxe", 0,
        () -> Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, EnumType.GRANITE),
        state -> state.getBlock() instanceof BlockStone && state.getValue(BlockStone.VARIANT) == EnumType.GRANITE);

    public static StoneType DIORITE = new StoneType(2, "diorite", new ResourceLocation("blocks/stone_diorite"), SoundType.STONE, OrePrefix.ore, Materials.Diorite, "pickaxe", 0,
        () -> Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, EnumType.DIORITE),
        state -> state.getBlock() instanceof BlockStone && state.getValue(BlockStone.VARIANT) == EnumType.DIORITE);

    public static StoneType ANDESITE = new StoneType(3, "andesite", new ResourceLocation("blocks/stone_andesite"), SoundType.STONE, OrePrefix.ore, Materials.Andesite, "pickaxe", 0,
        () -> Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE),
        state -> state.getBlock() instanceof BlockStone && state.getValue(BlockStone.VARIANT) == EnumType.ANDESITE);

    public static StoneType GRAVEL = new StoneType(4, "gravel", new ResourceLocation("blocks/gravel"), SoundType.SAND, OrePrefix.oreGravel, Materials.Flint, "shovel", AFFECTED_BY_GRAVITY,
        Blocks.GRAVEL::getDefaultState,
        state -> state.getBlock() instanceof BlockGravel);

    public static StoneType BEDROCK = new StoneType(5, "bedrock", new ResourceLocation("blocks/bedrock"), SoundType.STONE, OrePrefix.ore, Materials.Stone, "pickaxe", UNBREAKABLE,
        Blocks.BEDROCK::getDefaultState,
        state -> state.getBlock() == Blocks.BEDROCK);

    public static StoneType NETHERRACK = new StoneType(6, "netherrack", new ResourceLocation("blocks/netherrack"), SoundType.STONE, OrePrefix.oreNetherrack, Materials.Netherrack, "pickaxe", 0,
        Blocks.NETHERRACK::getDefaultState,
        state -> state.getBlock() == Blocks.NETHERRACK);

    public static StoneType ENDSTONE = new StoneType(7, "endstone", new ResourceLocation("blocks/end_stone"), SoundType.STONE, OrePrefix.oreEndstone, Materials.Endstone, "pickaxe", 0,
        Blocks.END_STONE::getDefaultState,
        state -> state.getBlock() == Blocks.END_STONE);

    public static StoneType SANDSTONE = new StoneType(8, "sandstone", new ResourceLocation("blocks/sandstone_normal"), new ResourceLocation("blocks/sandstone_top"), SoundType.STONE, OrePrefix.oreSand, Materials.SiliconDioxide, "pickaxe", 0,
        () -> Blocks.SANDSTONE.getDefaultState().withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.DEFAULT),
        state -> state.getBlock() instanceof BlockSandStone && state.getValue(BlockSandStone.TYPE) == BlockSandStone.EnumType.DEFAULT);

    public static StoneType RED_SANDSTONE = new StoneType(9, "red_sandstone", new ResourceLocation("blocks/red_sandstone_normal"), new ResourceLocation("blocks/red_sandstone_top"), SoundType.STONE, OrePrefix.oreSand, Materials.SiliconDioxide, "pickaxe", 0,
        () -> Blocks.RED_SANDSTONE.getDefaultState().withProperty(BlockRedSandstone.TYPE, BlockRedSandstone.EnumType.DEFAULT),
        state -> state.getBlock() instanceof BlockRedSandstone && state.getValue(BlockRedSandstone.TYPE) == BlockRedSandstone.EnumType.DEFAULT);

}
