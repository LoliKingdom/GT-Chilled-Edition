package gregtech.loaders.recipe;

import gregtech.api.GTValues;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockConcrete.ConcreteVariant;
import gregtech.common.blocks.BlockGranite.GraniteVariant;
import gregtech.common.blocks.BlockMachineCasing.MachineCasingType;
import gregtech.common.blocks.BlockMetalCasing.MetalCasingType;
import gregtech.common.blocks.BlockMineral.MineralVariant;
import gregtech.common.blocks.BlockMultiblockCasing.MultiblockCasingType;
import gregtech.common.blocks.BlockTransparentCasing;
import gregtech.common.blocks.BlockTurbineCasing.TurbineCasingType;
import gregtech.common.blocks.BlockWireCoil.CoilType;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.blocks.StoneBlock;
import gregtech.common.blocks.StoneBlock.ChiselingVariant;
import gregtech.common.blocks.wood.BlockGregLog.LogVariant;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.common.metatileentities.storage.MetaTileEntityQuantumChest;
import gregtech.common.metatileentities.storage.MetaTileEntityQuantumTank;
import gregtech.loaders.recipe.chemistry.AssemblerRecipeLoader;
import gregtech.loaders.recipe.chemistry.ChemistryRecipes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fluids.FluidStack;

import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.api.util.DyeUtil.getOredictColorName;
import static gregtech.common.items.MetaItems.*;
import static gregtech.common.metatileentities.MetaTileEntities.WORKBENCH;

public class MachineRecipeLoader {

    private MachineRecipeLoader() {
    }

    public static void init() {
        ChemistryRecipes.init();
        FuelRecipes.registerFuels();
        AssemblyLineLoader.init();
        AssemblerRecipeLoader.init();
        ComponentRecipes.register();
        ComponentRecipesHighTier.register();
        MiscRecipeLoader.init();
        BatteryRecipes.init();

        CircuitRecipes.init();
        registerCutterRecipes();
        registerDecompositionRecipes();
        registerBlastFurnaceRecipes();
        registerAssemblerRecipes();
        registerAlloyRecipes();
        registerBendingCompressingRecipes();
        registerCokeOvenRecipes();
        registerFluidRecipes();
        registerMixingCrystallizationRecipes();
        registerPrimitiveBlastFurnaceRecipes();
        registerRecyclingRecipes();
        registerStoneBricksRecipes();
        registerOrganicRecyclingRecipes();
        registerNBTRemoval();
    }

    private static void registerBendingCompressingRecipes() {

        COMPRESSOR_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, Materials.Fireclay)
                .outputs(MetaItems.COMPRESSED_FIRECLAY.getStackForm())
                .duration(80).EUt(4)
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder()
                .duration(100).EUt(16)
                .notConsumable(MetaItems.SHAPE_MOLD_CREDIT.getStackForm())
                .input(OrePrefix.plate, Materials.Cupronickel, 1)
                .outputs(MetaItems.CREDIT_CUPRONICKEL.getStackForm(4))
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder()
                .duration(100).EUt(16)
                .notConsumable(MetaItems.SHAPE_MOLD_CREDIT.getStackForm())
                .input(OrePrefix.plate, Materials.Brass, 1)
                .outputs(MetaItems.COIN_DOGE.getStackForm(4))
                .buildAndRegister();

        for (MetaItem<?>.MetaValueItem shapeMold : SHAPE_MOLDS) {
            FORMING_PRESS_RECIPES.recipeBuilder()
                    .duration(120).EUt(22)
                    .notConsumable(shapeMold.getStackForm())
                    .inputs(MetaItems.SHAPE_EMPTY.getStackForm())
                    .outputs(shapeMold.getStackForm())
                    .buildAndRegister();
        }

        for (MetaItem<?>.MetaValueItem shapeExtruder : SHAPE_EXTRUDERS) {
            FORMING_PRESS_RECIPES.recipeBuilder()
                    .duration(120).EUt(22)
                    .notConsumable(shapeExtruder.getStackForm())
                    .inputs(MetaItems.SHAPE_EMPTY.getStackForm())
                    .outputs(shapeExtruder.getStackForm())
                    .buildAndRegister();
        }

        BENDER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(OrePrefix.plate, Materials.Steel, 4)
                .outputs(MetaItems.SHAPE_EMPTY.getStackForm())
                .duration(180).EUt(12)
                .buildAndRegister();

        BENDER_RECIPES.recipeBuilder()
                .circuitMeta(12)
                .input(OrePrefix.plate, Materials.Tin, 2)
                .outputs(MetaItems.FLUID_CELL.getStackForm())
                .duration(200).EUt(8)
                .buildAndRegister();

        BENDER_RECIPES.recipeBuilder()
                .circuitMeta(12)
                .input(OrePrefix.plate, Materials.Steel)
                .outputs(MetaItems.FLUID_CELL.getStackForm())
                .duration(100).EUt(8)
                .buildAndRegister();

        BENDER_RECIPES.recipeBuilder()
                .circuitMeta(12)
                .input(OrePrefix.plate, Polytetrafluoroethylene)
                .outputs(MetaItems.FLUID_CELL.getStackForm(4))
                .duration(100).EUt(8)
                .buildAndRegister();

        BENDER_RECIPES.recipeBuilder()
                .circuitMeta(12)
                .input(OrePrefix.plate, Polybenzimidazole)
                .outputs(MetaItems.FLUID_CELL.getStackForm(16))
                .duration(100).EUt(8)
                .buildAndRegister();

        EXTRUDER_RECIPES.recipeBuilder()
                .notConsumable(MetaItems.SHAPE_EXTRUDER_CELL)
                .input(OrePrefix.ingot, Materials.Tin, 2)
                .outputs(MetaItems.FLUID_CELL.getStackForm())
                .duration(128).EUt(30)
                .buildAndRegister();

        EXTRUDER_RECIPES.recipeBuilder()
                .notConsumable(MetaItems.SHAPE_EXTRUDER_CELL)
                .input(OrePrefix.ingot, Materials.Steel)
                .outputs(MetaItems.FLUID_CELL.getStackForm())
                .duration(128).EUt(30)
                .buildAndRegister();

        EXTRUDER_RECIPES.recipeBuilder()
                .notConsumable(MetaItems.SHAPE_EXTRUDER_CELL)
                .input(OrePrefix.ingot, Polytetrafluoroethylene)
                .outputs(MetaItems.FLUID_CELL.getStackForm(4))
                .duration(128).EUt(30)
                .buildAndRegister();

        EXTRUDER_RECIPES.recipeBuilder()
                .notConsumable(MetaItems.SHAPE_EXTRUDER_CELL)
                .input(OrePrefix.ingot, Polybenzimidazole)
                .outputs(MetaItems.FLUID_CELL.getStackForm(16))
                .duration(128).EUt(30)
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, Materials.NetherQuartz)
                .output(OrePrefix.plate, Materials.NetherQuartz)
                .duration(400).EUt(2).buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, Materials.CertusQuartz)
                .output(OrePrefix.plate, Materials.CertusQuartz)
                .duration(400).EUt(2).buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, Materials.Quartzite)
                .output(OrePrefix.plate, Materials.Quartzite)
                .duration(400).EUt(2).buildAndRegister();
    }

    // todo this should be done better but will work for now
    private static void registerPrimitiveBlastFurnaceRecipes() {
        PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder().input(ingot, Iron).input(gem, Coal, 2).output(ingot, Steel).duration(1500).buildAndRegister();
        PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder().input(ingot, Iron).input(dust, Coal, 2).output(ingot, Steel).duration(1500).buildAndRegister();
        PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder().input(ingot, Iron).input(gem, Charcoal, 2).output(ingot, Steel).duration(1500).buildAndRegister();
        PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder().input(ingot, Iron).input(dust, Charcoal, 2).output(ingot, Steel).duration(1500).buildAndRegister();

        PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder().input(block, Iron).input(block, Coal, 2).output(block, Steel).duration(13500).buildAndRegister();
        PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder().input(block, Iron).input(block, Charcoal, 2).output(block, Steel).duration(13500).buildAndRegister();

        PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder().input(ingot, WroughtIron).input(gem, Coal, 2).output(ingot, Steel).duration(600).buildAndRegister();
        PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder().input(ingot, WroughtIron).input(dust, Coal, 2).output(ingot, Steel).duration(600).buildAndRegister();
        PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder().input(ingot, WroughtIron).input(gem, Charcoal, 2).output(ingot, Steel).duration(600).buildAndRegister();
        PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder().input(ingot, WroughtIron).input(dust, Charcoal, 2).output(ingot, Steel).duration(600).buildAndRegister();

        PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder().input(block, WroughtIron).input(block, Coal, 2).output(block, Steel).duration(5600).buildAndRegister();
        PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder().input(block, WroughtIron).input(block, Charcoal, 2).output(block, Steel).duration(5600).buildAndRegister();
    }

    private static void registerCokeOvenRecipes() {
        COKE_OVEN_RECIPES.recipeBuilder().input(log, Wood).output(gem, Charcoal).fluidOutputs(Creosote.getFluid(250)).duration(900).buildAndRegister();
        COKE_OVEN_RECIPES.recipeBuilder().input(gem, Coal).output(gem, Coke).fluidOutputs(Creosote.getFluid(500)).duration(900).buildAndRegister();
        COKE_OVEN_RECIPES.recipeBuilder().input(block, Coal).output(block, Coke).fluidOutputs(Creosote.getFluid(4500)).duration(8100).buildAndRegister();
    }

    private static void registerStoneBricksRecipes() {
        //decorative blocks: normal variant -> brick variant
        registerBrickRecipe(MetaBlocks.CONCRETE, ConcreteVariant.LIGHT_CONCRETE, ConcreteVariant.LIGHT_BRICKS);
        registerBrickRecipe(MetaBlocks.CONCRETE, ConcreteVariant.DARK_CONCRETE, ConcreteVariant.DARK_BRICKS);
        registerBrickRecipe(MetaBlocks.GRANITE, GraniteVariant.BLACK_GRANITE, GraniteVariant.BLACK_GRANITE_BRICKS);
        registerBrickRecipe(MetaBlocks.GRANITE, GraniteVariant.RED_GRANITE, GraniteVariant.RED_GRANITE_BRICKS);
        registerBrickRecipe(MetaBlocks.MINERAL, MineralVariant.MARBLE, MineralVariant.MARBLE_BRICKS);
        registerBrickRecipe(MetaBlocks.MINERAL, MineralVariant.BASALT, MineralVariant.BASALT_BRICKS);

        //decorative blocks: normal chiseling -> different chiseling
        registerChiselingRecipes(MetaBlocks.CONCRETE);
        registerChiselingRecipes(MetaBlocks.GRANITE);
        registerChiselingRecipes(MetaBlocks.MINERAL);
    }

    private static void registerMixingCrystallizationRecipes() {

        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, Materials.Stone, 1)
                .fluidInputs(Materials.Lubricant.getFluid(20), Materials.Water.getFluid(4980))
                .fluidOutputs(Materials.DrillingFluid.getFluid(5000))
                .duration(64).EUt(16)
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, Materials.Clay, 1)
                .input(OrePrefix.dust, Materials.Stone, 3)
                .fluidInputs(Materials.Water.getFluid(500))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Materials.Concrete.getFluid(576))
                .duration(20).EUt(16)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(100).EUt(16)
                .input(dust, Calcite, 2)
                .input(dust, Stone)
                .input(dust, Clay)
                .input(dust, QuartzSand)
                .fluidInputs(Water.getFluid(2000))
                .fluidOutputs(Concrete.getFluid(2304))
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .inputs(MetaBlocks.CONCRETE.getItemVariant(ConcreteVariant.LIGHT_CONCRETE, ChiselingVariant.NORMAL))
                .fluidInputs(Materials.Water.getFluid(144))
                .outputs(MetaBlocks.CONCRETE.getItemVariant(ConcreteVariant.DARK_CONCRETE, ChiselingVariant.NORMAL))
                .duration(12).EUt(4)
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .duration(64).EUt(16)
                .fluidInputs(Materials.Water.getFluid(1000))
                .input("sand", 2)
                .input(OrePrefix.dust, Materials.Stone, 6)
                .input(OrePrefix.dust, Materials.Flint)
                .fluidOutputs(Materials.ConstructionFoam.getFluid(1000))
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .duration(600).EUt(120)
                .input(OrePrefix.dust, Materials.Ruby, 4)
                .input(OrePrefix.dust, Materials.Redstone, 5)
                .outputs(MetaItems.ENERGIUM_DUST.getStackForm(9))
                .buildAndRegister();

        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().inputs(MetaItems.ENERGIUM_DUST.getStackForm(9))
                .fluidInputs(Materials.Water.getFluid(1800))
                .outputs(MetaItems.ENERGY_CRYSTAL.getStackForm())
                .duration(2000).EUt(120)
                .buildAndRegister();

        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(MetaItems.ENERGIUM_DUST.getStackForm(9))
                .fluidInputs(Materials.DistilledWater.getFluid(1800))
                .outputs(MetaItems.ENERGY_CRYSTAL.getStackForm())
                .duration(1500).EUt(120)
                .buildAndRegister();

        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, Materials.SiliconDioxide)
                .fluidInputs(Materials.DistilledWater.getFluid(200))
                .chancedOutput(OreDictUnifier.get(OrePrefix.gem, Materials.Quartzite), 1000, 1000)
                .duration(1500).EUt(24).buildAndRegister();

        //todo find UU-Matter replacement
//        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
//            .input(OrePrefix.dust, Materials.NetherStar)
//            .fluidInputs(Materials.UUMatter.getFluid(576))
//            .chancedOutput(new ItemStack(Items.NETHER_STAR), 3333, 3333)
//            .duration(72000).EUt(480).buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(OrePrefix.crushedPurified, Materials.Sphalerite)
                .input(OrePrefix.crushedPurified, Materials.Galena)
                .fluidInputs(Materials.SulfuricAcid.getFluid(4000))
                .fluidOutputs(Materials.IndiumConcentrate.getFluid(1000))
                .duration(60).EUt(150).buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, Materials.Sodium)
                .input(OrePrefix.dust, Materials.Potassium)
                .fluidOutputs(Materials.SodiumPotassium.getFluid(1000))
                .duration(400).EUt(30).buildAndRegister();

    }

    private static void registerOrganicRecyclingRecipes() {


    }

    private static final MaterialStack[][] alloySmelterList = {
            {new MaterialStack(Materials.Copper, 3L), new MaterialStack(Materials.Tin, 1), new MaterialStack(Materials.Bronze, 4L)},
            {new MaterialStack(Materials.Copper, 3L), new MaterialStack(Materials.Zinc, 1), new MaterialStack(Materials.Brass, 4L)},
            {new MaterialStack(Materials.Copper, 1), new MaterialStack(Materials.Nickel, 1), new MaterialStack(Materials.Cupronickel, 2L)},
            {new MaterialStack(Materials.Copper, 1), new MaterialStack(Materials.Redstone, 4L), new MaterialStack(Materials.RedAlloy, 1)},
            {new MaterialStack(Materials.AnnealedCopper, 3L), new MaterialStack(Materials.Tin, 1), new MaterialStack(Materials.Bronze, 4L)},
            {new MaterialStack(Materials.AnnealedCopper, 3L), new MaterialStack(Materials.Zinc, 1), new MaterialStack(Materials.Brass, 4L)},
            {new MaterialStack(Materials.AnnealedCopper, 1), new MaterialStack(Materials.Nickel, 1), new MaterialStack(Materials.Cupronickel, 2L)},
            {new MaterialStack(Materials.AnnealedCopper, 1), new MaterialStack(Materials.Redstone, 4L), new MaterialStack(Materials.RedAlloy, 1)},
            {new MaterialStack(Materials.Iron, 1), new MaterialStack(Materials.Tin, 1), new MaterialStack(Materials.TinAlloy, 2L)},
            {new MaterialStack(Materials.WroughtIron, 1), new MaterialStack(Materials.Tin, 1), new MaterialStack(Materials.TinAlloy, 2L)},
            {new MaterialStack(Materials.Iron, 2L), new MaterialStack(Materials.Nickel, 1), new MaterialStack(Materials.Invar, 3L)},
            {new MaterialStack(Materials.WroughtIron, 2L), new MaterialStack(Materials.Nickel, 1), new MaterialStack(Materials.Invar, 3L)},
            {new MaterialStack(Materials.Tin, 9L), new MaterialStack(Materials.Antimony, 1), new MaterialStack(Materials.SolderingAlloy, 10L)},
            {new MaterialStack(Materials.Lead, 4L), new MaterialStack(Materials.Antimony, 1), new MaterialStack(Materials.BatteryAlloy, 5L)},
            {new MaterialStack(Materials.Gold, 1), new MaterialStack(Materials.Silver, 1), new MaterialStack(Materials.Electrum, 2L)},
            {new MaterialStack(Materials.Magnesium, 1), new MaterialStack(Materials.Aluminium, 2L), new MaterialStack(Materials.Magnalium, 3L)}};

    private static void registerAlloyRecipes() {
        for (MaterialStack[] stack : alloySmelterList) {
            if (stack[0].material.hasProperty(PropertyKey.INGOT)) {
                RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder()
                        .duration((int) stack[2].amount * 50).EUt(16)
                        .input(OrePrefix.ingot, stack[0].material, (int) stack[0].amount)
                        .input(OrePrefix.dust, stack[1].material, (int) stack[1].amount)
                        .outputs(OreDictUnifier.get(OrePrefix.ingot, stack[2].material, (int) stack[2].amount))
                        .buildAndRegister();
            }
            if (stack[1].material.hasProperty(PropertyKey.INGOT)) {
                RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder()
                        .duration((int) stack[2].amount * 50).EUt(16)
                        .input(OrePrefix.dust, stack[0].material, (int) stack[0].amount)
                        .input(OrePrefix.ingot, stack[1].material, (int) stack[1].amount)
                        .outputs(OreDictUnifier.get(OrePrefix.ingot, stack[2].material, (int) stack[2].amount))
                        .buildAndRegister();
            }
            if (stack[0].material.hasProperty(PropertyKey.INGOT)
                    && stack[1].material.hasProperty(PropertyKey.INGOT)) {
                RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder()
                        .duration((int) stack[2].amount * 50).EUt(16)
                        .input(OrePrefix.ingot, stack[0].material, (int) stack[0].amount)
                        .input(OrePrefix.ingot, stack[1].material, (int) stack[1].amount)
                        .outputs(OreDictUnifier.get(OrePrefix.ingot, stack[2].material, (int) stack[2].amount))
                        .buildAndRegister();
            }
            RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder()
                    .duration((int) stack[2].amount * 50).EUt(16)
                    .input(OrePrefix.dust, stack[0].material, (int) stack[0].amount)
                    .input(OrePrefix.dust, stack[1].material, (int) stack[1].amount)
                    .outputs(OreDictUnifier.get(OrePrefix.ingot, stack[2].material, (int) stack[2].amount))
                    .buildAndRegister();
        }

        COMPRESSOR_RECIPES.recipeBuilder().inputs(MetaItems.INGOT_MIXED_METAL.getStackForm()).outputs(MetaItems.ADVANCED_ALLOY_PLATE.getStackForm()).duration(300).EUt(2).buildAndRegister();
        BENDER_RECIPES.recipeBuilder().inputs(MetaItems.INGOT_MIXED_METAL.getStackForm()).circuitMeta(1).outputs(MetaItems.ADVANCED_ALLOY_PLATE.getStackForm()).duration(100).EUt(8).buildAndRegister();
        FORMING_PRESS_RECIPES.recipeBuilder().inputs(ADVANCED_ALLOY_PLATE.getStackForm(4)).input(OrePrefix.plate, Materials.Diamond).input(OrePrefix.plate, Materials.Iridium, 4).outputs(MetaItems.INGOT_IRIDIUM_ALLOY.getStackForm()).duration(100).EUt(256).buildAndRegister();
        IMPLOSION_RECIPES.recipeBuilder().inputs(MetaItems.INGOT_IRIDIUM_ALLOY.getStackForm()).outputs(MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm()).output(OrePrefix.dustTiny, Materials.DarkAsh, 4).explosivesAmount(8).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().inputs(MetaItems.CARBON_FIBERS.getStackForm(2)).outputs(MetaItems.CARBON_MESH.getStackForm()).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().inputs(MetaItems.CARBON_MESH.getStackForm()).outputs(MetaItems.CARBON_PLATE.getStackForm()).buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(400).EUt(4).input(OrePrefix.dust, Materials.Glass, 3).inputs(MetaItems.ADVANCED_ALLOY_PLATE.getStackForm()).outputs(MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockTransparentCasing.CasingType.REINFORCED_GLASS, 4)).buildAndRegister();
        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(400).EUt(4).inputs(new ItemStack(Blocks.GLASS, 3)).inputs(MetaItems.ADVANCED_ALLOY_PLATE.getStackForm()).outputs(MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockTransparentCasing.CasingType.REINFORCED_GLASS, 4)).buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(10).EUt(7).input(OrePrefix.ingot, Materials.Rubber, 2).notConsumable(MetaItems.SHAPE_MOLD_PLATE).output(OrePrefix.plate, Materials.Rubber).buildAndRegister();
        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(100).EUt(7).input(OrePrefix.dust, Materials.Sulfur).input(OrePrefix.dust, Materials.RawRubber, 3).output(OrePrefix.ingot, Materials.Rubber).buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(150).EUt(7).inputs(OreDictUnifier.get("sand")).inputs(new ItemStack(Items.CLAY_BALL)).outputs(COKE_OVEN_BRICK.getStackForm(2)).buildAndRegister();
    }

    private static void registerAssemblerRecipes() {
        for (EnumDyeColor dyeColor : EnumDyeColor.values()) {
            CANNER_RECIPES.recipeBuilder()
                    .inputs(MetaItems.SPRAY_EMPTY.getStackForm())
                    .input(getOredictColorName(dyeColor), 1)
                    .outputs(MetaItems.SPRAY_CAN_DYES[dyeColor.getMetadata()].getStackForm())
                    .EUt(8).duration(200)
                    .buildAndRegister();
        }

        Material[] coverMaterials = new Material[]{Materials.Iron, Materials.WroughtIron, Materials.Aluminium};

        for (Material material : coverMaterials) {
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(new ItemStack(Items.IRON_DOOR))
                    .input(OrePrefix.plate, material, 2)
                    .outputs(MetaItems.COVER_SHUTTER.getStackForm(2))
                    .EUt(16).duration(800)
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(WORKBENCH.getStackForm())
                    .input(plate, material)
                    .outputs(COVER_CRAFTING.getStackForm())
                    .EUt(16).duration(800)
                    .buildAndRegister();

            for (FluidStack solder : new FluidStack[]{Tin.getFluid(L), SolderingAlloy.getFluid(L / 2)}) {
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                        .inputs(new ItemStack(Blocks.LEVER))
                        .input(OrePrefix.plate, material)
                        .fluidInputs(solder)
                        .outputs(MetaItems.COVER_MACHINE_CONTROLLER.getStackForm(1))
                        .EUt(16).duration(200)
                        .buildAndRegister();

                ASSEMBLER_RECIPES.recipeBuilder()
                        .input(cableGtSingle, Copper, 4)
                        .input(circuit, MarkerMaterials.Tier.Basic)
                        .input(plate, material)
                        .fluidInputs(solder)
                        .outputs(COVER_ENERGY_DETECTOR.getStackForm())
                        .EUt(16).duration(800)
                        .buildAndRegister();

                ASSEMBLER_RECIPES.recipeBuilder()
                        .inputs(new ItemStack(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE))
                        .input(plate, material)
                        .fluidInputs(solder)
                        .outputs(COVER_FLUID_DETECTOR.getStackForm())
                        .EUt(16).duration(800)
                        .buildAndRegister();

                ASSEMBLER_RECIPES.recipeBuilder()
                        .inputs(new ItemStack(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE))
                        .input(plate, material)
                        .fluidInputs(solder)
                        .outputs(COVER_ITEM_DETECTOR.getStackForm())
                        .EUt(16).duration(800)
                        .buildAndRegister();
            }
        }

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Glass)
                .input(foil, Aluminium, 4)
                .input(circuit, MarkerMaterials.Tier.Basic)
                .input(wireFine, Copper, 4)
                .outputs(COVER_SCREEN.getStackForm())
                .EUt(16).duration(50)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(ELECTRIC_PUMP_HV, 2)
                .inputs(new ItemStack(Items.CAULDRON))
                .input(circuit, MarkerMaterials.Tier.Advanced)
                .output(COVER_INFINITE_WATER)
                .EUt(480).duration(200)
                .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.WroughtIron, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.ULV)).circuitMeta(8).duration(25).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.Steel, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.LV)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.Aluminium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.MV)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.StainlessSteel, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.HV)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.Titanium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.EV)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.TungstenSteel, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.IV)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.Chrome, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.LuV)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.Iridium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.ZPM)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.Osmium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.UV)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.Neutronium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.MAX)).circuitMeta(8).duration(50).buildAndRegister();

        if (ConfigHolder.U.GT5u.harderHeatingCoils) {
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(30).input(OrePrefix.wireGtDouble, Materials.Cupronickel, 8).input(OrePrefix.foil, Materials.Bronze, 8).fluidInputs(Materials.TinAlloy.getFluid(GTValues.L)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(CoilType.CUPRONICKEL)).duration(200).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(120).input(OrePrefix.wireGtDouble, Materials.Kanthal, 8).input(OrePrefix.foil, Materials.Aluminium, 8).fluidInputs(Materials.Copper.getFluid(GTValues.L)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(CoilType.KANTHAL)).duration(300).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(480).input(OrePrefix.wireGtDouble, Materials.Nichrome, 8).input(OrePrefix.foil, Materials.StainlessSteel, 8).fluidInputs(Materials.Aluminium.getFluid(GTValues.L)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(CoilType.NICHROME)).duration(400).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(1920).input(OrePrefix.wireGtDouble, Materials.TungstenSteel, 8).input(OrePrefix.foil, Materials.VanadiumSteel, 8).fluidInputs(Materials.Nichrome.getFluid(GTValues.L)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(CoilType.TUNGSTENSTEEL)).duration(500).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(7680).input(OrePrefix.wireGtDouble, Materials.HSSG, 8).input(OrePrefix.foil, Materials.TungstenCarbide, 8).fluidInputs(Materials.Tungsten.getFluid(GTValues.L)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(CoilType.HSS_G)).duration(600).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(30720).input(OrePrefix.wireGtDouble, Materials.Naquadah, 8).input(OrePrefix.foil, Materials.Osmium, 8).fluidInputs(Materials.TungstenSteel.getFluid(GTValues.L)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(CoilType.NAQUADAH)).duration(700).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(122880).input(OrePrefix.wireGtDouble, Materials.NaquadahAlloy, 8).input(OrePrefix.foil, Materials.Osmiridium, 8).fluidInputs(Materials.Naquadah.getFluid(GTValues.L)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(CoilType.NAQUADAH_ALLOY)).duration(800).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(500000).input(OrePrefix.wireGtDouble, Materials.FluxedElectrum, 8).input(OrePrefix.foil, Materials.Naquadria, 8).fluidInputs(Materials.NaquadahAlloy.getFluid(GTValues.L)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(CoilType.FLUXED_ELECTRUM)).duration(900).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(2000000).input(OrePrefix.wireGtDouble, Materials.DiamericiumTitanium, 8).input(OrePrefix.foil, Materials.Trinium, 8).fluidInputs(Materials.Neutronium.getFluid(GTValues.L)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(CoilType.DIAMERICIUM_TITANIUM)).duration(1000).buildAndRegister();

        } else {
            for (CoilType coilType : CoilType.values()) {
                if (coilType.getMaterial() != null) {
                    ItemStack outputStack = MetaBlocks.WIRE_COIL.getItemVariant(coilType);
                    ASSEMBLER_RECIPES.recipeBuilder()
                            .circuitMeta(8)
                            .input(wireGtDouble, coilType.getMaterial(), 8)
                            .outputs(outputStack)
                            .duration(50).EUt(16).buildAndRegister();
                }
            }
        }

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.Invar, 6).input(OrePrefix.frameGt, Materials.Invar, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(MetalCasingType.INVAR_HEATPROOF, 2)).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.Steel, 6).input(OrePrefix.frameGt, Materials.Steel, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(MetalCasingType.STEEL_SOLID, 2)).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.Aluminium, 6).input(OrePrefix.frameGt, Materials.Aluminium, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(MetalCasingType.ALUMINIUM_FROSTPROOF, 2)).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.TungstenSteel, 6).input(OrePrefix.frameGt, Materials.TungstenSteel, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(MetalCasingType.TUNGSTENSTEEL_ROBUST, 2)).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.StainlessSteel, 6).input(OrePrefix.frameGt, Materials.StainlessSteel, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(MetalCasingType.STAINLESS_CLEAN, 2)).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.Titanium, 6).input(OrePrefix.frameGt, Materials.Titanium, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(MetalCasingType.TITANIUM_STABLE, 2)).duration(50).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).inputs(MetaBlocks.METAL_CASING.getItemVariant(MetalCasingType.STEEL_SOLID)).fluidInputs(Materials.Polytetrafluoroethylene.getFluid(216)).notConsumable(new IntCircuitIngredient(6)).outputs(MetaBlocks.METAL_CASING.getItemVariant(MetalCasingType.PTFE_INERT_CASING)).duration(50).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.LuV)).input(OrePrefix.plate, Materials.TungstenSteel, 6).outputs(MetaBlocks.MULTIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING)).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).inputs(MetaBlocks.MULTIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING)).input(OrePrefix.plate, Materials.Americium, 6).outputs(MetaBlocks.MULTIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING_MK2)).duration(50).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.Magnalium, 6).input(OrePrefix.frameGt, Materials.BlueSteel, 1).outputs(MetaBlocks.TURBINE_CASING.getItemVariant(TurbineCasingType.STEEL_TURBINE_CASING, 2)).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).inputs(MetaBlocks.TURBINE_CASING.getItemVariant(TurbineCasingType.STEEL_TURBINE_CASING)).input(OrePrefix.plate, Materials.StainlessSteel, 6).outputs(MetaBlocks.TURBINE_CASING.getItemVariant(TurbineCasingType.STAINLESS_TURBINE_CASING, 2)).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).inputs(MetaBlocks.TURBINE_CASING.getItemVariant(TurbineCasingType.STEEL_TURBINE_CASING)).input(OrePrefix.plate, Materials.Titanium, 6).outputs(MetaBlocks.TURBINE_CASING.getItemVariant(TurbineCasingType.TITANIUM_TURBINE_CASING, 2)).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).inputs(MetaBlocks.TURBINE_CASING.getItemVariant(TurbineCasingType.STEEL_TURBINE_CASING)).input(OrePrefix.plate, Materials.TungstenSteel, 6).outputs(MetaBlocks.TURBINE_CASING.getItemVariant(TurbineCasingType.TUNGSTENSTEEL_TURBINE_CASING, 2)).duration(50).buildAndRegister();


        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(25).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.ULV)).input(OrePrefix.cableGtSingle, Materials.Lead, 2).fluidInputs(Materials.Polyethylene.getFluid(L * 2)).outputs(MetaTileEntities.HULL[0].getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.LV)).input(OrePrefix.cableGtSingle, Materials.Tin, 2).fluidInputs(Materials.Polyethylene.getFluid(L * 2)).outputs(MetaTileEntities.HULL[1].getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.MV)).input(OrePrefix.cableGtSingle, Materials.Copper, 2).fluidInputs(Materials.Polyethylene.getFluid(L * 2)).outputs(MetaTileEntities.HULL[2].getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.MV)).input(OrePrefix.cableGtSingle, Materials.AnnealedCopper, 2).fluidInputs(Materials.Polyethylene.getFluid(L * 2)).outputs(MetaTileEntities.HULL[2].getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.HV)).input(OrePrefix.cableGtSingle, Materials.Gold, 2).fluidInputs(Materials.Polyethylene.getFluid(L * 2)).outputs(MetaTileEntities.HULL[3].getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.EV)).input(OrePrefix.cableGtSingle, Materials.Aluminium, 2).fluidInputs(Materials.Polyethylene.getFluid(L * 2)).outputs(MetaTileEntities.HULL[4].getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.IV)).input(OrePrefix.cableGtSingle, Materials.Tungsten, 2).fluidInputs(Polytetrafluoroethylene.getFluid(L * 2)).outputs(MetaTileEntities.HULL[5].getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.LuV)).input(OrePrefix.cableGtSingle, Materials.VanadiumGallium, 2).fluidInputs(Polytetrafluoroethylene.getFluid(L * 2)).outputs(MetaTileEntities.HULL[6].getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.ZPM)).input(OrePrefix.cableGtSingle, Materials.Naquadah, 2).fluidInputs(Polybenzimidazole.getFluid(L * 2)).outputs(MetaTileEntities.HULL[7].getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.UV)).input(OrePrefix.wireGtQuadruple, Materials.NaquadahAlloy, 2).fluidInputs(Polybenzimidazole.getFluid(L * 2)).outputs(MetaTileEntities.HULL[8].getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.MAX)).input(OrePrefix.wireGtSingle, Materials.RutheniumTriniumAmericiumNeutronate, 2).fluidInputs(Polybenzimidazole.getFluid(L * 2)).outputs(MetaTileEntities.HULL[14].getStackForm()).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(2).inputs(new ItemStack(Blocks.CHEST, 1, GTValues.W)).input(plate, Iron, 5).outputs(new ItemStack(Blocks.HOPPER)).duration(800).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(2).inputs(new ItemStack(Blocks.TRAPPED_CHEST, 1, GTValues.W)).input(plate, Iron, 5).outputs(new ItemStack(Blocks.HOPPER)).duration(800).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(2).input(gear, CobaltBrass).input(dust, Diamond).output(COMPONENT_SAW_BLADE_DIAMOND).duration(1600).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).input(foil, Polycaprolactam, 4).input(CARBON_MESH).fluidInputs(Polyethylene.getFluid(288)).output(DUCT_TAPE).duration(100).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).input(foil, SiliconeRubber, 4).input(CARBON_MESH).fluidInputs(Polyethylene.getFluid(288)).output(DUCT_TAPE, 2).duration(100).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).input(foil, StyreneButadieneRubber, 2).input(CARBON_MESH).fluidInputs(Polyethylene.getFluid(144)).output(DUCT_TAPE, 4).duration(100).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).input(foil, Polybenzimidazole).input(CARBON_MESH).fluidInputs(Polyethylene.getFluid(72)).output(DUCT_TAPE, 8).duration(100).buildAndRegister();

    }

    private static void registerBlastFurnaceRecipes() {
        BLAST_RECIPES.recipeBuilder().duration((int) Math.max(TungstenSteel.getAverageMass() / 80, 1) * TungstenSteel.getBlastTemperature()).EUt(480).input(ingot, Tungsten).input(ingot, Steel).outputs(OreDictUnifier.get(OrePrefix.ingotHot, Materials.TungstenSteel, 2), OreDictUnifier.get(OrePrefix.dustSmall, Materials.DarkAsh)).blastFurnaceTemp(TungstenSteel.getBlastTemperature()).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration((int) Math.max(TungstenCarbide.getAverageMass() / 40, 1) * TungstenCarbide.getBlastTemperature()).EUt(480).input(ingot, Tungsten).input(dust, Carbon).outputs(OreDictUnifier.get(OrePrefix.ingotHot, Materials.TungstenCarbide, 2), OreDictUnifier.get(OrePrefix.dustSmall, Materials.DarkAsh)).blastFurnaceTemp(TungstenCarbide.getBlastTemperature()).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration((int) Math.max(VanadiumGallium.getAverageMass() / 40, 1) * VanadiumGallium.getBlastTemperature()).EUt(480).input(ingot, Vanadium, 3).input(ingot, Gallium).outputs(OreDictUnifier.get(OrePrefix.ingotHot, Materials.VanadiumGallium, 4), OreDictUnifier.get(OrePrefix.dustSmall, Materials.DarkAsh, 2)).blastFurnaceTemp(VanadiumGallium.getBlastTemperature()).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration((int) Math.max(NiobiumTitanium.getAverageMass() / 80, 1) * NiobiumTitanium.getBlastTemperature()).EUt(480).input(ingot, Niobium).input(ingot, Titanium).outputs(OreDictUnifier.get(OrePrefix.ingotHot, Materials.NiobiumTitanium, 2), OreDictUnifier.get(OrePrefix.dustSmall, Materials.DarkAsh)).blastFurnaceTemp(NiobiumTitanium.getBlastTemperature()).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration((int) Math.max(Nichrome.getAverageMass() / 32, 1) * Nichrome.getBlastTemperature()).EUt(480).input(ingot, Nickel, 4).input(ingot, Chrome).outputs(OreDictUnifier.get(OrePrefix.ingotHot, Materials.Nichrome, 5), OreDictUnifier.get(OrePrefix.dustSmall, Materials.DarkAsh, 2)).blastFurnaceTemp(Nichrome.getBlastTemperature()).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(400).EUt(100).input(dust, Ruby).output(nugget, Aluminium, 3).output(dustTiny, DarkAsh).blastFurnaceTemp(1200).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(320).EUt(100).input(gem, Ruby).output(nugget, Aluminium, 3).output(dustTiny, DarkAsh).blastFurnaceTemp(1200).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(400).EUt(100).input(dust, GreenSapphire).output(nugget, Aluminium, 3).output(dustTiny, DarkAsh).blastFurnaceTemp(1200).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(320).EUt(100).input(gem, GreenSapphire).output(nugget, Aluminium, 3).output(dustTiny, DarkAsh).blastFurnaceTemp(1200).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(400).EUt(100).input(dust, Sapphire).output(nugget, Aluminium, 3).blastFurnaceTemp(1200).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(320).EUt(100).input(gem, Sapphire).output(nugget, Aluminium, 3).blastFurnaceTemp(1200).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(800).EUt(500).input(dust, Ilmenite, 5).outputs(OreDictUnifier.get(ingot, WroughtIron), OreDictUnifier.get(dust, Rutile, 3)).blastFurnaceTemp(1700).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(800).EUt(480).input(dust, Magnesium, 2).fluidInputs(TitaniumTetrachloride.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.ingotHot, Materials.Titanium), OreDictUnifier.get(OrePrefix.dust, Materials.MagnesiumChloride, 6)).blastFurnaceTemp(Materials.Titanium.getBlastTemperature() + 200).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(500).EUt(120).input(ingot, Iron).fluidInputs(Oxygen.getFluid(1000)).output(ingot, Steel).output(dustTiny, Ash).blastFurnaceTemp(1000).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(100).EUt(120).input(ingot, PigIron).fluidInputs(Oxygen.getFluid(1000)).output(ingot, Steel).output(dustTiny, Ash).blastFurnaceTemp(1000).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(100).EUt(120).input(ingot, WroughtIron).fluidInputs(Oxygen.getFluid(1000)).output(ingot, Steel).output(dustTiny, Ash).blastFurnaceTemp(1000).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(200).EUt(120).input(dust, Copper).fluidInputs(Oxygen.getFluid(1000)).output(ingot, AnnealedCopper).blastFurnaceTemp(1200).notConsumable(new IntCircuitIngredient(1)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(500).EUt(120).input(ingot, Copper).fluidInputs(Oxygen.getFluid(1000)).output(ingot, AnnealedCopper).blastFurnaceTemp(1200).notConsumable(new IntCircuitIngredient(1)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(200).EUt(1920).input(dust, Osmiridium).fluidInputs(Helium.getFluid(1000)).output(ingotHot, Osmiridium).blastFurnaceTemp(2900).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(500).EUt(30720).input(dust, NaquadahAlloy).fluidInputs(Argon.getFluid(1000)).output(ingotHot, NaquadahAlloy).blastFurnaceTemp(NaquadahAlloy.getBlastTemperature()).buildAndRegister();

        registerBlastFurnaceMetallurgyRecipes();
    }

    private static void registerBlastFurnaceMetallurgyRecipes() {
        createSulfurDioxideRecipe(Stibnite, AntimonyTrioxide, 1500);
        createSulfurDioxideRecipe(Sphalerite, Zincite, 1000);
        createSulfurDioxideRecipe(Pyrite, BandedIron, 2000);
        createSulfurDioxideRecipe(Pentlandite, Garnierite, 1000);

        BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200)
                .input(dust, Tetrahedrite)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, CupricOxide)
                .output(dustTiny, AntimonyTrioxide, 3)
                .fluidOutputs(SulfurDioxide.getFluid(2000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200)
                .input(dust, Cobaltite)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, CobaltOxide)
                .output(dust, ArsenicTrioxide)
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200)
                .input(dust, Galena)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, Massicot)
                .output(nugget, Silver, 6)
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200)
                .input(dust, Chalcopyrite)
                .input(dust, SiliconDioxide)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, CupricOxide)
                .output(dust, Ferrosilite)
                .fluidOutputs(SulfurDioxide.getFluid(2000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200)
                .input(dust, SiliconDioxide)
                .input(dust, Carbon, 2)
                .output(ingot, Silicon)
                .output(dustTiny, Ash)
                .fluidOutputs(CarbonMonoxide.getFluid(2000))
                .buildAndRegister();
    }

    private static void createSulfurDioxideRecipe(Material inputMaterial, Material outputMaterial, int sulfurDioxideAmount) {
        BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200)
                .input(dust, inputMaterial)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, outputMaterial)
                .output(dustTiny, Ash)
                .fluidOutputs(SulfurDioxide.getFluid(sulfurDioxideAmount))
                .buildAndRegister();
    }

    private static void registerDecompositionRecipes() {


        EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(RUBBER_DROP.getStackForm())
                .output(dust, RawRubber, 3)
                .duration(150).EUt(2)
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .inputs(MetaBlocks.LEAVES.getItem(LogVariant.RUBBER_WOOD, 16))
                .output(dust, RawRubber)
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .inputs(MetaBlocks.LOG.getItem(LogVariant.RUBBER_WOOD))
                .output(dust, RawRubber)
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .inputs(MetaBlocks.SAPLING.getItem(LogVariant.RUBBER_WOOD))
                .output(dust, RawRubber)
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().duration(150).EUt(2)
                .inputs(new ItemStack(Items.SLIME_BALL))
                .output(dust, RawRubber, 2)
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().duration(20).EUt(2)
                .inputs(MetaItems.FLUID_CELL.getStackForm())
                .outputs(MetaItems.FLUID_CELL.getStackForm())
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().duration(20).EUt(2)
                .inputs(MetaItems.UNIVERSAL_FLUID_CELL.getStackForm())
                .outputs(MetaItems.UNIVERSAL_FLUID_CELL.getStackForm())
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().duration(20).EUt(2)
                .inputs(MetaItems.LARGE_FLUID_CELL_STEEL.getStackForm())
                .outputs(MetaItems.LARGE_FLUID_CELL_STEEL.getStackForm())
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().duration(20).EUt(2)
                .inputs(MetaItems.LARGE_FLUID_CELL_ALUMINIUM.getStackForm())
                .outputs(MetaItems.LARGE_FLUID_CELL_ALUMINIUM.getStackForm())
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().duration(20).EUt(2)
                .inputs(MetaItems.LARGE_FLUID_CELL_STAINLESS_STEEL.getStackForm())
                .outputs(MetaItems.LARGE_FLUID_CELL_STAINLESS_STEEL.getStackForm())
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().duration(20).EUt(2)
                .inputs(MetaItems.LARGE_FLUID_CELL_TITANIUM.getStackForm())
                .outputs(MetaItems.LARGE_FLUID_CELL_TITANIUM.getStackForm())
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().duration(20).EUt(2)
                .inputs(MetaItems.LARGE_FLUID_CELL_TUNGSTEN_STEEL.getStackForm())
                .outputs(MetaItems.LARGE_FLUID_CELL_TUNGSTEN_STEEL.getStackForm())
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().duration(20).EUt(2)
                .inputs(MetaItems.LARGE_FLUID_CELL_CHROME.getStackForm())
                .outputs(MetaItems.LARGE_FLUID_CELL_CHROME.getStackForm())
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().duration(20).EUt(2)
                .inputs(MetaItems.LARGE_FLUID_CELL_IRIDIUM.getStackForm())
                .outputs(MetaItems.LARGE_FLUID_CELL_IRIDIUM.getStackForm())
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().duration(20).EUt(2)
                .inputs(MetaItems.LARGE_FLUID_CELL_OSMIUM.getStackForm())
                .outputs(MetaItems.LARGE_FLUID_CELL_OSMIUM.getStackForm())
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().duration(20).EUt(2)
                .inputs(MetaItems.LARGE_FLUID_CELL_NEUTRONIUM.getStackForm())
                .outputs(MetaItems.LARGE_FLUID_CELL_NEUTRONIUM.getStackForm())
                .buildAndRegister();


        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).input("treeSapling", 8).output(PLANT_BALL).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.WHEAT, 8)).output(PLANT_BALL).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.POTATO, 8)).output(PLANT_BALL).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.CARROT, 8)).output(PLANT_BALL).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Blocks.CACTUS, 8)).output(PLANT_BALL).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.REEDS, 8)).output(PLANT_BALL).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Blocks.BROWN_MUSHROOM, 8)).output(PLANT_BALL).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Blocks.RED_MUSHROOM, 8)).output(PLANT_BALL).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.BEETROOT, 8)).output(PLANT_BALL).buildAndRegister();

    }

    private static void registerCutterRecipes() {
        CUTTER_RECIPES.recipeBuilder().duration(100).EUt(16).inputs(new ItemStack(Blocks.GLOWSTONE)).output(plate, Glowstone, 4).buildAndRegister();
    }

    private static void registerRecyclingRecipes() {

        MACERATOR_RECIPES.recipeBuilder()
                .input(stone, Endstone)
                .output(dust, Endstone)
                .chancedOutput(dustTiny, Tungstate, 1200, 280)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .input(stone, Netherrack)
                .output(dust, Netherrack)
                .chancedOutput(nugget, Gold, 500, 120)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .input(stone, Soapstone)
                .output(dustImpure, Talc)
                .chancedOutput(dustTiny, Chromite, 1000, 280)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .input(stone, Redrock)
                .output(dust, Redrock)
                .chancedOutput(dust, Redrock, 1000, 380)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .input(stone, Marble)
                .output(dust, Marble)
                .chancedOutput(dust, Marble, 1000, 380)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .input(stone, Basalt)
                .output(dust, Basalt)
                .chancedOutput(dust, Basalt, 1000, 380)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .input(stone, GraniteBlack)
                .output(dust, GraniteBlack)
                .chancedOutput(dust, Thorium, 100, 40)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .input(stone, GraniteRed)
                .output(dust, GraniteRed)
                .chancedOutput(dustSmall, Uranium238, 100, 40)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .input(stone, Andesite)
                .output(dust, Andesite)
                .chancedOutput(dustSmall, Stone, 100, 40)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .input(stone, Diorite)
                .output(dust, Diorite)
                .chancedOutput(dustSmall, Stone, 100, 40)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .input(stone, Granite)
                .output(dust, Granite)
                .chancedOutput(dustSmall, Stone, 100, 40)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Items.PORKCHOP))
                .output(dustSmall, Meat, 6)
                .output(dustTiny, Bone)
                .duration(102).EUt(4).buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Items.FISH, 1, GTValues.W))
                .output(dustSmall, Meat, 6)
                .output(dustTiny, Bone)
                .duration(102).EUt(4).buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Items.CHICKEN))
                .output(dust, Meat)
                .output(dustTiny, Bone)
                .duration(102).EUt(4).buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Items.BEEF))
                .output(dustSmall, Meat, 6)
                .output(dustTiny, Bone)
                .duration(102).EUt(4).buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Items.RABBIT))
                .output(dustSmall, Meat, 6)
                .output(dustTiny, Bone)
                .duration(102).EUt(4).buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Items.MUTTON))
                .output(dust, Meat)
                .output(dustTiny, Bone)
                .duration(102).EUt(4).buildAndRegister();


    }

    private static void registerFluidRecipes() {


        FLUID_HEATER_RECIPES.recipeBuilder().duration(32).EUt(4)
                .fluidInputs(Ice.getFluid(L))
                .circuitMeta(1)
                .fluidOutputs(Water.getFluid(L)).buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(100).EUt(16).notConsumable(SHAPE_MOLD_BALL).fluidInputs(Toluene.getFluid(100)).output(GELLED_TOLUENE).buildAndRegister();

        FLUID_HEATER_RECIPES.recipeBuilder().duration(30).EUt(32).fluidInputs(Water.getFluid(6)).circuitMeta(1).fluidOutputs(Steam.getFluid(960)).buildAndRegister();
        FLUID_HEATER_RECIPES.recipeBuilder().duration(30).EUt(32).fluidInputs(DistilledWater.getFluid(6)).circuitMeta(1).fluidOutputs(Steam.getFluid(960)).buildAndRegister();
    }

    private static <T extends Enum<T> & IStringSerializable> void registerBrickRecipe(StoneBlock<T> stoneBlock, T normalVariant, T brickVariant) {
        ModHandler.addShapedRecipe(stoneBlock.getRegistryName().getNamespace() + "_" + normalVariant + "_bricks",
                stoneBlock.getItemVariant(brickVariant, ChiselingVariant.NORMAL, 4),
                "XX", "XX", 'X',
                stoneBlock.getItemVariant(normalVariant, ChiselingVariant.NORMAL));
    }

    private static <T extends Enum<T> & IStringSerializable> void registerChiselingRecipes(StoneBlock<T> stoneBlock) {
        for (T variant : stoneBlock.getVariantValues()) {
            boolean isBricksVariant = variant.getName().endsWith("_bricks");
            if (!isBricksVariant) {
                ModHandler.addSmeltingRecipe(stoneBlock.getItemVariant(variant, ChiselingVariant.CRACKED), stoneBlock.getItemVariant(variant, ChiselingVariant.NORMAL));
                FORGE_HAMMER_RECIPES.recipeBuilder().duration(12).EUt(4)
                        .inputs(stoneBlock.getItemVariant(variant, ChiselingVariant.NORMAL))
                        .outputs(stoneBlock.getItemVariant(variant, ChiselingVariant.CRACKED))
                        .buildAndRegister();
            } else {
                ModHandler.addSmeltingRecipe(stoneBlock.getItemVariant(variant, ChiselingVariant.NORMAL), stoneBlock.getItemVariant(variant, ChiselingVariant.CRACKED));
            }
            CHEMICAL_BATH_RECIPES.recipeBuilder().duration(12).EUt(4)
                    .inputs(stoneBlock.getItemVariant(variant, !isBricksVariant ? ChiselingVariant.CRACKED : ChiselingVariant.NORMAL))
                    .fluidInputs(Materials.Water.getFluid(144))
                    .outputs(stoneBlock.getItemVariant(variant, ChiselingVariant.MOSSY))
                    .buildAndRegister();
            ModHandler.addShapelessRecipe(stoneBlock.getRegistryName().getPath() + "_chiseling_" + variant,
                    stoneBlock.getItemVariant(variant, ChiselingVariant.CHISELED),
                    stoneBlock.getItemVariant(variant, ChiselingVariant.NORMAL));
        }
    }

    private static void registerNBTRemoval() {
        for (MetaTileEntityQuantumChest chest : MetaTileEntities.QUANTUM_CHEST)
            if (chest != null) {
                ModHandler.addShapelessRecipe("quantum_chest_nbt_" + chest.getTier() + chest.getMetaName(), chest.getStackForm(), chest.getStackForm());
            }

        for (MetaTileEntityQuantumTank tank : MetaTileEntities.QUANTUM_TANK)
            if (tank != null) {
                ModHandler.addShapelessRecipe("quantum_tank_nbt_" + tank.getTier() + tank.getMetaName(), tank.getStackForm(), tank.getStackForm());
            }

        //Drums
        ModHandler.addShapelessRecipe("drum_nbt_wood", MetaTileEntities.WOODEN_DRUM.getStackForm(), MetaTileEntities.WOODEN_DRUM.getStackForm());
        ModHandler.addShapelessRecipe("drum_nbt_bronze", MetaTileEntities.BRONZE_DRUM.getStackForm(), MetaTileEntities.BRONZE_DRUM.getStackForm());
        ModHandler.addShapelessRecipe("drum_nbt_steel", MetaTileEntities.STEEL_DRUM.getStackForm(), MetaTileEntities.STEEL_DRUM.getStackForm());
        ModHandler.addShapelessRecipe("drum_nbt_aluminium", MetaTileEntities.ALUMINIUM_DRUM.getStackForm(), MetaTileEntities.ALUMINIUM_DRUM.getStackForm());
        ModHandler.addShapelessRecipe("drum_nbt_stainless_steel", MetaTileEntities.STAINLESS_STEEL_DRUM.getStackForm(), MetaTileEntities.STAINLESS_STEEL_DRUM.getStackForm());
        ModHandler.addShapelessRecipe("drum_nbt_titanium", MetaTileEntities.TITANIUM_DRUM.getStackForm(), MetaTileEntities.TITANIUM_DRUM.getStackForm());
        ModHandler.addShapelessRecipe("drum_nbt_tungstensteel", MetaTileEntities.TUNGSTENSTEEL_DRUM.getStackForm(), MetaTileEntities.TUNGSTENSTEEL_DRUM.getStackForm());
        //Tanks
        ModHandler.addShapelessRecipe("tank_nbt_wood", MetaTileEntities.WOODEN_TANK.getStackForm(), MetaTileEntities.WOODEN_TANK.getStackForm());
        ModHandler.addShapelessRecipe("tank_nbt_bronze", MetaTileEntities.BRONZE_TANK.getStackForm(), MetaTileEntities.BRONZE_TANK.getStackForm());
        ModHandler.addShapelessRecipe("tank_nbt_steel", MetaTileEntities.STEEL_TANK.getStackForm(), MetaTileEntities.STEEL_TANK.getStackForm());
        ModHandler.addShapelessRecipe("tank_nbt_aluminium", MetaTileEntities.ALUMINIUM_TANK.getStackForm(), MetaTileEntities.ALUMINIUM_TANK.getStackForm());
        ModHandler.addShapelessRecipe("tank_nbt_stainless_steel", MetaTileEntities.STAINLESS_STEEL_TANK.getStackForm(), MetaTileEntities.STAINLESS_STEEL_TANK.getStackForm());
        ModHandler.addShapelessRecipe("tank_nbt_titanium", MetaTileEntities.TITANIUM_TANK.getStackForm(), MetaTileEntities.TITANIUM_TANK.getStackForm());
        ModHandler.addShapelessRecipe("tank_nbt_tungstensteel", MetaTileEntities.TUNGSTENSTEEL_TANK.getStackForm(), MetaTileEntities.TUNGSTENSTEEL_TANK.getStackForm());

        //Jetpacks
        ModHandler.addShapelessRecipe("fluid_jetpack_clear", SEMIFLUID_JETPACK.getStackForm(), SEMIFLUID_JETPACK.getStackForm());

    }
}
