package gregtech.common.metatileentities;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SimpleGeneratorMetaTileEntity;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.GTLog;
import gregtech.api.util.GTUtility;
import gregtech.common.ConfigHolder;
import gregtech.common.metatileentities.electric.*;
import gregtech.common.metatileentities.electric.multiblockpart.*;
import gregtech.common.metatileentities.multi.*;
import gregtech.common.metatileentities.multi.MetaTileEntityLargeBoiler.BoilerType;
import gregtech.common.metatileentities.multi.electric.*;
import gregtech.common.metatileentities.multi.electric.generator.MetaTileEntityLargeCombustionEngine;
import gregtech.common.metatileentities.multi.electric.generator.MetaTileEntityLargeTurbine;
import gregtech.common.metatileentities.multi.electric.generator.MetaTileEntityLargeTurbine.TurbineType;
import gregtech.common.metatileentities.multi.steam.MetaTileEntitySteamGrinder;
import gregtech.common.metatileentities.multi.steam.MetaTileEntitySteamOven;
import gregtech.common.metatileentities.steam.*;
import gregtech.common.metatileentities.steam.boiler.SteamCoalBoiler;
import gregtech.common.metatileentities.steam.boiler.SteamLavaBoiler;
import gregtech.common.metatileentities.steam.boiler.SteamSolarBoiler;
import gregtech.common.metatileentities.steam.multiblockpart.MetaTileEntitySteamHatch;
import gregtech.common.metatileentities.steam.multiblockpart.MetaTileEntitySteamItemBus;
import gregtech.common.metatileentities.storage.*;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class MetaTileEntities {

    //HULLS
    public static final MetaTileEntityHull[] HULL = new MetaTileEntityHull[GTValues.V.length];
    public static final MetaTileEntityTransformer[] TRANSFORMER = new MetaTileEntityTransformer[GTValues.V.length - 1]; // no ULV, no MAX
    public static final MetaTileEntityAdjustableTransformer[] ADJUSTABLE_TRANSFORMER = new MetaTileEntityAdjustableTransformer[GTValues.V.length - 1]; // no ULV, no MAX
    public static final MetaTileEntityDiode[] DIODES = new MetaTileEntityDiode[GTValues.V.length];
    public static final MetaTileEntityBatteryBuffer[][] BATTERY_BUFFER = new MetaTileEntityBatteryBuffer[GTValues.V.length][];
    public static final MetaTileEntityCharger[] CHARGER = new MetaTileEntityCharger[GTValues.V.length];

    //STEAM AGE SECTION
    public static SteamCoalBoiler STEAM_BOILER_COAL_BRONZE;
    public static SteamCoalBoiler STEAM_BOILER_COAL_STEEL;
    public static SteamSolarBoiler STEAM_BOILER_SOLAR_BRONZE;
    public static SteamSolarBoiler STEAM_BOILER_SOLAR_STEEL;
    public static SteamLavaBoiler STEAM_BOILER_LAVA_BRONZE;
    public static SteamLavaBoiler STEAM_BOILER_LAVA_STEEL;
    public static SteamExtractor STEAM_EXTRACTOR_BRONZE;
    public static SteamExtractor STEAM_EXTRACTOR_STEEL;
    public static SteamMacerator STEAM_MACERATOR_BRONZE;
    public static SteamMacerator STEAM_MACERATOR_STEEL;
    public static SteamCompressor STEAM_COMPRESSOR_BRONZE;
    public static SteamCompressor STEAM_COMPRESSOR_STEEL;
    public static SteamHammer STEAM_HAMMER_BRONZE;
    public static SteamHammer STEAM_HAMMER_STEEL;
    public static SteamFurnace STEAM_FURNACE_BRONZE;
    public static SteamFurnace STEAM_FURNACE_STEEL;
    public static SteamAlloySmelter STEAM_ALLOY_SMELTER_BRONZE;
    public static SteamAlloySmelter STEAM_ALLOY_SMELTER_STEEL;
    public static SteamRockBreaker STEAM_ROCK_BREAKER_BRONZE;
    public static SteamRockBreaker STEAM_ROCK_BREAKER_STEEL;

    public static MetaTileEntityPumpHatch PUMP_OUTPUT_HATCH;
    public static MetaTileEntityPrimitiveWaterPump PRIMITIVE_WATER_PUMP;

    //SIMPLE MACHINES SECTION
    public static final SimpleMachineMetaTileEntity[] ELECTRIC_FURNACE = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final MetaTileEntityMacerator[] MACERATOR = new MetaTileEntityMacerator[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] ALLOY_SMELTER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] ARC_FURNACE = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] ASSEMBLER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] AUTOCLAVE = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] BENDER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] BREWERY = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] CANNER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] CENTRIFUGE = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] CHEMICAL_BATH = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] CHEMICAL_REACTOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] COMPRESSOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] CUTTER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] DISTILLERY = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] ELECTROLYZER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] ELECTROMAGNETIC_SEPARATOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] EXTRACTOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] EXTRUDER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] FERMENTER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] FLUID_HEATER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] FLUID_SOLIDIFIER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] FORGE_HAMMER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] FORMING_PRESS = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] LATHE = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] MIXER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] ORE_WASHER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] PACKER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] UNPACKER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] POLARIZER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] LASER_ENGRAVER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] SIFTER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] THERMAL_CENTRIFUGE = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] WIREMILL = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] CIRCUIT_ASSEMBLER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] MASS_FABRICATOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] REPLICATOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] SCANNER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] GAS_COLLECTOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final MetaTileEntityRockBreaker[] ROCK_BREAKER = new MetaTileEntityRockBreaker[GTValues.V.length - 1];
    public static MetaTileEntitySimpleOreWasher SIMPLE_ORE_WASHER;

    //GENERATORS SECTION
    public static final SimpleGeneratorMetaTileEntity[] COMBUSTION_GENERATOR = new SimpleGeneratorMetaTileEntity[4];
    public static final SimpleGeneratorMetaTileEntity[] STEAM_TURBINE = new SimpleGeneratorMetaTileEntity[4];
    public static final SimpleGeneratorMetaTileEntity[] GAS_TURBINE = new SimpleGeneratorMetaTileEntity[4];
    public static MetaTileEntityMagicEnergyAbsorber MAGIC_ENERGY_ABSORBER;

    //MULTIBLOCK PARTS SECTION
    public static final MetaTileEntityItemBus[] ITEM_IMPORT_BUS = new MetaTileEntityItemBus[GTValues.UHV + 1]; // ULV-UHV
    public static final MetaTileEntityItemBus[] ITEM_EXPORT_BUS = new MetaTileEntityItemBus[GTValues.UHV + 1];
    public static final MetaTileEntityFluidHatch[] FLUID_IMPORT_HATCH = new MetaTileEntityFluidHatch[GTValues.UHV + 1];
    public static final MetaTileEntityFluidHatch[] FLUID_EXPORT_HATCH = new MetaTileEntityFluidHatch[GTValues.UHV + 1];
    public static final MetaTileEntityMultiFluidHatch[] MULTI_FLUID_IMPORT_HATCH = new MetaTileEntityMultiFluidHatch[2];
    public static final MetaTileEntityMultiFluidHatch[] MULTI_FLUID_EXPORT_HATCH = new MetaTileEntityMultiFluidHatch[2];
    public static final MetaTileEntityEnergyHatch[] ENERGY_INPUT_HATCH = new MetaTileEntityEnergyHatch[GTValues.V.length];
    public static final MetaTileEntityAdjustableEnergyHatch[] ENERGY_INPUT_HATCH_ADJUSTABLE = new MetaTileEntityAdjustableEnergyHatch[GTValues.V.length];
    public static final MetaTileEntityEnergyHatch[] ENERGY_OUTPUT_HATCH = new MetaTileEntityEnergyHatch[GTValues.V.length];
    public static final MetaTileEntityAdjustableEnergyHatch[] ENERGY_OUTPUT_HATCH_ADJUSTABLE = new MetaTileEntityAdjustableEnergyHatch[GTValues.V.length];
    public static MetaTileEntityCokeOvenHatch COKE_OVEN_HATCH;
    public static MetaTileEntitySteamItemBus STEAM_EXPORT_BUS;
    public static MetaTileEntitySteamItemBus STEAM_IMPORT_BUS;
    public static MetaTileEntitySteamHatch STEAM_HATCH;
    public static final MetaTileEntityRotorHolder[] ROTOR_HOLDER = new MetaTileEntityRotorHolder[3]; //HV, LuV, MAX
    public static MetaTileEntityMaintenanceHatch MAINTENANCE_HATCH;
    public static MetaTileEntityAutoMaintenanceHatch AUTO_MAINTENANCE_HATCH;
    public static final MetaTileEntityMufflerHatch[] MUFFLER_HATCH = new MetaTileEntityMufflerHatch[GTValues.UV]; // LV-UV

    //MULTIBLOCKS SECTION
    public static MetaTileEntityPrimitiveBlastFurnace PRIMITIVE_BLAST_FURNACE;
    public static MetaTileEntityCokeOven COKE_OVEN;
    public static MetaTileEntityElectricBlastFurnace ELECTRIC_BLAST_FURNACE;
    public static MetaTileEntityVacuumFreezer VACUUM_FREEZER;
    public static MetaTileEntityImplosionCompressor IMPLOSION_COMPRESSOR;
    public static MetaTileEntityPyrolyseOven PYROLYSE_OVEN;
    public static MetaTileEntityDistillationTower DISTILLATION_TOWER;
    public static MetaTileEntityCrackingUnit CRACKER;
    public static MetaTileEntityMultiSmelter MULTI_FURNACE;
    public static MetaTileEntityLargeCombustionEngine LARGE_COMBUSTION_ENGINE;

    public static MetaTileEntityLargeTurbine LARGE_STEAM_TURBINE;
    public static MetaTileEntityLargeTurbine LARGE_GAS_TURBINE;
    public static MetaTileEntityLargeTurbine LARGE_PLASMA_TURBINE;

    public static MetaTileEntityLargeBoiler LARGE_BRONZE_BOILER;
    public static MetaTileEntityLargeBoiler LARGE_STEEL_BOILER;
    public static MetaTileEntityLargeBoiler LARGE_TITANIUM_BOILER;
    public static MetaTileEntityLargeBoiler LARGE_TUNGSTENSTEEL_BOILER;

    public static MetaTileEntityAssemblyLine ASSEMBLY_LINE;
    public static final MetaTileEntityFusionReactor[] FUSION_REACTOR = new MetaTileEntityFusionReactor[3];

    public static MetaTileEntityLargeChemicalReactor LARGE_CHEMICAL_REACTOR;

    public static MetaTileEntitySteamOven STEAM_OVEN;
    public static MetaTileEntitySteamGrinder STEAM_GRINDER;

    //STORAGE SECTION
    public static MetaTileEntityLockedSafe LOCKED_SAFE;

    public static MetaTileEntityTank WOODEN_TANK;
    public static MetaTileEntityTank BRONZE_TANK;
    public static MetaTileEntityTank ALUMINIUM_TANK;
    public static MetaTileEntityTank STEEL_TANK;
    public static MetaTileEntityTank STAINLESS_STEEL_TANK;
    public static MetaTileEntityTank TITANIUM_TANK;
    public static MetaTileEntityTank TUNGSTENSTEEL_TANK;

    public static MetaTileEntityDrum WOODEN_DRUM;
    public static MetaTileEntityDrum BRONZE_DRUM;
    public static MetaTileEntityDrum ALUMINIUM_DRUM;
    public static MetaTileEntityDrum STEEL_DRUM;
    public static MetaTileEntityDrum STAINLESS_STEEL_DRUM;
    public static MetaTileEntityDrum TITANIUM_DRUM;
    public static MetaTileEntityDrum TUNGSTENSTEEL_DRUM;

    public static MetaTileEntityCrate WOODEN_CRATE;
    public static MetaTileEntityCrate BRONZE_CRATE;
    public static MetaTileEntityCrate ALUMINIUM_CRATE;
    public static MetaTileEntityCrate STEEL_CRATE;
    public static MetaTileEntityCrate STAINLESS_STEEL_CRATE;
    public static MetaTileEntityCrate TITANIUM_CRATE;
    public static MetaTileEntityCrate TUNGSTENSTEEL_CRATE;

    public static final MetaTileEntityQuantumChest[] QUANTUM_CHEST = new MetaTileEntityQuantumChest[10];
    public static final MetaTileEntityQuantumTank[] QUANTUM_TANK = new MetaTileEntityQuantumTank[10];
    public static final MetaTileEntityBuffer[] BUFFER = new MetaTileEntityBuffer[3];

    //MISC MACHINES SECTION
    public static MetaTileEntityWorkbench WORKBENCH;
    public static final MetaTileEntityPump[] PUMP = new MetaTileEntityPump[8];
    public static final MetaTileEntityBlockBreaker[] BLOCK_BREAKER = new MetaTileEntityBlockBreaker[4];
    public static final MetaTileEntityItemCollector[] ITEM_COLLECTOR = new MetaTileEntityItemCollector[4];
    public static final MetaTileEntityFisher[] FISHER = new MetaTileEntityFisher[4];
    public static final MetaTileEntityWorldAccelerator[] WORLD_ACCELERATOR = new MetaTileEntityWorldAccelerator[8]; // no ULV, no MAX

    public static MetaTileEntityCreativeEnergy CREATIVE_ENERGY;

    public static MetaTileEntityClipboard CLIPBOARD_TILE;

    public static void init() {
        GTLog.logger.info("Registering MetaTileEntities");

        STEAM_BOILER_COAL_BRONZE = registerMetaTileEntity(1, new SteamCoalBoiler(gregtechId("steam_boiler_coal_bronze"), false));
        STEAM_BOILER_COAL_STEEL = registerMetaTileEntity(2, new SteamCoalBoiler(gregtechId("steam_boiler_coal_steel"), true));

        STEAM_BOILER_SOLAR_BRONZE = registerMetaTileEntity(3, new SteamSolarBoiler(gregtechId("steam_boiler_solar_bronze"), false));
        STEAM_BOILER_SOLAR_STEEL = registerMetaTileEntity(4, new SteamSolarBoiler(gregtechId("steam_boiler_solar_steel"), true));

        STEAM_BOILER_LAVA_BRONZE = registerMetaTileEntity(5, new SteamLavaBoiler(gregtechId("steam_boiler_lava_bronze"), false));
        STEAM_BOILER_LAVA_STEEL = registerMetaTileEntity(6, new SteamLavaBoiler(gregtechId("steam_boiler_lava_steel"), true));

        STEAM_EXTRACTOR_BRONZE = registerMetaTileEntity(7, new SteamExtractor(gregtechId("steam_extractor_bronze"), false));
        STEAM_EXTRACTOR_STEEL = registerMetaTileEntity(8, new SteamExtractor(gregtechId("steam_extractor_steel"), true));

        STEAM_MACERATOR_BRONZE = registerMetaTileEntity(9, new SteamMacerator(gregtechId("steam_macerator_bronze"), false));
        STEAM_MACERATOR_STEEL = registerMetaTileEntity(10, new SteamMacerator(gregtechId("steam_macerator_steel"), true));

        STEAM_COMPRESSOR_BRONZE = registerMetaTileEntity(11, new SteamCompressor(gregtechId("steam_compressor_bronze"), false));
        STEAM_COMPRESSOR_STEEL = registerMetaTileEntity(12, new SteamCompressor(gregtechId("steam_compressor_steel"), true));

        STEAM_HAMMER_BRONZE = registerMetaTileEntity(13, new SteamHammer(gregtechId("steam_hammer_bronze"), false));
        STEAM_HAMMER_STEEL = registerMetaTileEntity(14, new SteamHammer(gregtechId("steam_hammer_steel"), true));

        STEAM_FURNACE_BRONZE = registerMetaTileEntity(15, new SteamFurnace(gregtechId("steam_furnace_bronze"), false));
        STEAM_FURNACE_STEEL = registerMetaTileEntity(16, new SteamFurnace(gregtechId("steam_furnace_steel"), true));

        STEAM_ALLOY_SMELTER_BRONZE = registerMetaTileEntity(17, new SteamAlloySmelter(gregtechId("steam_alloy_smelter_bronze"), false));
        STEAM_ALLOY_SMELTER_STEEL = registerMetaTileEntity(18, new SteamAlloySmelter(gregtechId("steam_alloy_smelter_steel"), true));

        STEAM_ROCK_BREAKER_BRONZE = registerMetaTileEntity(19, new SteamRockBreaker(gregtechId("steam_rock_breaker_bronze"), false));
        STEAM_ROCK_BREAKER_STEEL = registerMetaTileEntity(20, new SteamRockBreaker(gregtechId("steam_rock_breaker_steel"), true));

        // Electric Furnace, IDs 50-64
        registerSimpleMetaTileEntity(ELECTRIC_FURNACE, 50, "electric_furnace", RecipeMaps.FURNACE_RECIPES, Textures.ELECTRIC_FURNACE_OVERLAY, true);

        // Macerator, IDs 65-79
        MACERATOR[0] = registerMetaTileEntity(65, new MetaTileEntityMacerator(gregtechId("macerator.lv"), RecipeMaps.MACERATOR_RECIPES, 1, Textures.MACERATOR_OVERLAY, 1));
        MACERATOR[1] = registerMetaTileEntity(66, new MetaTileEntityMacerator(gregtechId("macerator.mv"), RecipeMaps.MACERATOR_RECIPES, 1, Textures.MACERATOR_OVERLAY, 2));
        MACERATOR[2] = registerMetaTileEntity(67, new MetaTileEntityMacerator(gregtechId("macerator.hv"), RecipeMaps.MACERATOR_RECIPES, 3, Textures.PULVERIZER_OVERLAY, 3));
        MACERATOR[3] = registerMetaTileEntity(68, new MetaTileEntityMacerator(gregtechId("macerator.ev"), RecipeMaps.MACERATOR_RECIPES, 3, Textures.PULVERIZER_OVERLAY, 4));
        MACERATOR[4] = registerMetaTileEntity(69, new MetaTileEntityMacerator(gregtechId("macerator.iv"), RecipeMaps.MACERATOR_RECIPES, 3, Textures.PULVERIZER_OVERLAY, 5));
        if (getMidTier("macerator")) {
            MACERATOR[5] = registerMetaTileEntity(70, new MetaTileEntityMacerator(gregtechId("macerator.luv"), RecipeMaps.MACERATOR_RECIPES, 3, Textures.PULVERIZER_OVERLAY, 6));
            MACERATOR[6] = registerMetaTileEntity(71, new MetaTileEntityMacerator(gregtechId("macerator.zpm"), RecipeMaps.MACERATOR_RECIPES, 3, Textures.PULVERIZER_OVERLAY, 7));
            MACERATOR[7] = registerMetaTileEntity(72, new MetaTileEntityMacerator(gregtechId("macerator.uv"), RecipeMaps.MACERATOR_RECIPES, 3, Textures.PULVERIZER_OVERLAY, 8));
        }
        if (getHighTier("macerator")) {
            MACERATOR[8] = registerMetaTileEntity(73, new MetaTileEntityMacerator(gregtechId("macerator.uhv"), RecipeMaps.MACERATOR_RECIPES, 3, Textures.PULVERIZER_OVERLAY, 9));
            MACERATOR[9] = registerMetaTileEntity(74, new MetaTileEntityMacerator(gregtechId("macerator.uev"), RecipeMaps.MACERATOR_RECIPES, 3, Textures.PULVERIZER_OVERLAY, 10));
            MACERATOR[10] = registerMetaTileEntity(75, new MetaTileEntityMacerator(gregtechId("macerator.uiv"), RecipeMaps.MACERATOR_RECIPES, 3, Textures.PULVERIZER_OVERLAY, 11));
            MACERATOR[11] = registerMetaTileEntity(76, new MetaTileEntityMacerator(gregtechId("macerator.umv"), RecipeMaps.MACERATOR_RECIPES, 3, Textures.PULVERIZER_OVERLAY, 12));
            MACERATOR[12] = registerMetaTileEntity(77, new MetaTileEntityMacerator(gregtechId("macerator.uxv"), RecipeMaps.MACERATOR_RECIPES, 3, Textures.PULVERIZER_OVERLAY, 13));
        }

        // Alloy Smelter, IDs 80-94
        registerSimpleMetaTileEntity(ALLOY_SMELTER, 80, "alloy_smelter", RecipeMaps.ALLOY_SMELTER_RECIPES, Textures.ALLOY_SMELTER_OVERLAY, true);

        // Free Range, IDs 95-109

        // Arc Furnace, IDs 110-124
        registerSimpleMetaTileEntity(ARC_FURNACE, 110, "arc_furnace", RecipeMaps.ARC_FURNACE_RECIPES, Textures.ARC_FURNACE_OVERLAY, false, GTUtility.hvCappedTankSizeFunction);

        // Assembler, IDs 125-139
        registerSimpleMetaTileEntity(ASSEMBLER, 125, "assembler", RecipeMaps.ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, true, GTUtility.hvCappedTankSizeFunction);

        // Autoclave, IDs 140-154
        registerSimpleMetaTileEntity(AUTOCLAVE, 140, "autoclave", RecipeMaps.AUTOCLAVE_RECIPES, Textures.AUTOCLAVE_OVERLAY, false, GTUtility.hvCappedTankSizeFunction);

        // Bender, IDs 155-169
        registerSimpleMetaTileEntity(BENDER, 155, "bender", RecipeMaps.BENDER_RECIPES, Textures.BENDER_OVERLAY, true);

        // Brewery, IDs 170-184
        registerSimpleMetaTileEntity(BREWERY, 170, "brewery", RecipeMaps.BREWING_RECIPES, Textures.BREWERY_OVERLAY, true, GTUtility.hvCappedTankSizeFunction);

        // Canner, IDs 185-199
        registerSimpleMetaTileEntity(CANNER, 185, "canner", RecipeMaps.CANNER_RECIPES, Textures.CANNER_OVERLAY, true);

        // Centrifuge, IDs 200-214
        registerSimpleMetaTileEntity(CENTRIFUGE, 200, "centrifuge", RecipeMaps.CENTRIFUGE_RECIPES, Textures.CENTRIFUGE_OVERLAY, false);

        // Chemical Bath, IDs 215-229
        registerSimpleMetaTileEntity(CHEMICAL_BATH, 215, "chemical_bath", RecipeMaps.CHEMICAL_BATH_RECIPES, Textures.CHEMICAL_BATH_OVERLAY, true, GTUtility.hvCappedTankSizeFunction);

        // Chemical Reactor, IDs 230-244
        registerSimpleMetaTileEntity(CHEMICAL_REACTOR, 230, "chemical_reactor", RecipeMaps.CHEMICAL_RECIPES, Textures.CHEMICAL_REACTOR_OVERLAY, true, GTUtility.hvCappedTankSizeFunction);

        // Compressor, IDs 245-259
        registerSimpleMetaTileEntity(COMPRESSOR, 245, "compressor", RecipeMaps.COMPRESSOR_RECIPES, Textures.COMPRESSOR_OVERLAY, true);

        // Cutter, IDs 260-274
        registerSimpleMetaTileEntity(CUTTER, 260, "cutter", RecipeMaps.CUTTER_RECIPES, Textures.CUTTER_OVERLAY, true);

        // Distillery, IDs 275-289
        registerSimpleMetaTileEntity(DISTILLERY, 275, "distillery", RecipeMaps.DISTILLERY_RECIPES, Textures.DISTILLERY_OVERLAY, true, GTUtility.hvCappedTankSizeFunction);

        // Electrolyzer, IDs 290-304
        registerSimpleMetaTileEntity(ELECTROLYZER, 290, "electrolyzer", RecipeMaps.ELECTROLYZER_RECIPES, Textures.ELECTROLYZER_OVERLAY, false);

        // Electromagnetic Separator, IDs 305-319
        registerSimpleMetaTileEntity(ELECTROMAGNETIC_SEPARATOR, 305, "electromagnetic_separator", RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES, Textures.ELECTROMAGNETIC_SEPARATOR_OVERLAY, true);

        // Extractor, IDs 320-334
        registerSimpleMetaTileEntity(EXTRACTOR, 320, "extractor", RecipeMaps.EXTRACTOR_RECIPES, Textures.EXTRACTOR_OVERLAY, true);

        // Extruder, IDs 335-349
        registerSimpleMetaTileEntity(EXTRUDER, 335, "extruder", RecipeMaps.EXTRUDER_RECIPES, Textures.EXTRUDER_OVERLAY, true);

        // Fermenter, IDs 350-364
        registerSimpleMetaTileEntity(FERMENTER, 350, "fermenter", RecipeMaps.FERMENTING_RECIPES, Textures.FERMENTER_OVERLAY, true, GTUtility.hvCappedTankSizeFunction);

        // Mass Fabricator, IDs 365-379
        registerSimpleMetaTileEntity(MASS_FABRICATOR, 365, "mass_fabricator", RecipeMaps.MASS_FABRICATOR_RECIPES, Textures.MASS_FABRICATOR_OVERLAY, true);

        // Replicator, IDs 380-394
        registerSimpleMetaTileEntity(REPLICATOR, 380, "replicator", RecipeMaps.REPLICATOR_RECIPES, Textures.REPLICATOR_OVERLAY, true);

        // Fluid Heater, IDs 395-409
        registerSimpleMetaTileEntity(FLUID_HEATER, 395, "fluid_heater", RecipeMaps.FLUID_HEATER_RECIPES, Textures.FLUID_HEATER_OVERLAY, true, GTUtility.hvCappedTankSizeFunction);

        // Fluid Solidifier, IDs 410-424
        registerSimpleMetaTileEntity(FLUID_SOLIDIFIER, 410, "fluid_solidifier", RecipeMaps.FLUID_SOLIDFICATION_RECIPES, Textures.FLUID_SOLIDIFIER_OVERLAY, true, GTUtility.hvCappedTankSizeFunction);

        // Forge Hammer, IDs 425-439
        registerSimpleMetaTileEntity(FORGE_HAMMER, 425, "forge_hammer", RecipeMaps.FORGE_HAMMER_RECIPES, Textures.FORGE_HAMMER_OVERLAY, true);

        // Forming Press, IDs 440-454
        registerSimpleMetaTileEntity(FORMING_PRESS, 440, "forming_press", RecipeMaps.FORMING_PRESS_RECIPES, Textures.FORMING_PRESS_OVERLAY, true);

        // Lathe, IDs 455-469
        registerSimpleMetaTileEntity(LATHE, 455, "lathe", RecipeMaps.LATHE_RECIPES, Textures.LATHE_OVERLAY, true);

        // Scanner, IDs 470-484
        registerSimpleMetaTileEntity(SCANNER, 470, "scanner", RecipeMaps.SCANNER_RECIPES, Textures.SCANNER_OVERLAY, true);

        // Mixer, IDs 485-499
        registerSimpleMetaTileEntity(MIXER, 485, "mixer", RecipeMaps.MIXER_RECIPES, Textures.MIXER_OVERLAY, false, GTUtility.hvCappedTankSizeFunction);

        // Ore Washer, IDs 500-514
        registerSimpleMetaTileEntity(ORE_WASHER, 500, "ore_washer", RecipeMaps.ORE_WASHER_RECIPES, Textures.ORE_WASHER_OVERLAY, true);

        // Packer, IDs 515-529
        registerSimpleMetaTileEntity(PACKER, 515, "packer", RecipeMaps.PACKER_RECIPES, Textures.PACKER_OVERLAY, true);

        // Unpacker, IDs 530-544
        registerSimpleMetaTileEntity(UNPACKER, 530, "unpacker", RecipeMaps.UNPACKER_RECIPES, Textures.UNPACKER_OVERLAY, true);

        // Gas Collectors, IDs 545-559
        registerSimpleMetaTileEntity(GAS_COLLECTOR, 545, "gas_collector", RecipeMaps.GAS_COLLECTOR_RECIPES, Textures.GAS_COLLECTOR_OVERLAY, false);

        // Polarizer, IDs 560-574
        registerSimpleMetaTileEntity(POLARIZER, 560, "polarizer", RecipeMaps.POLARIZER_RECIPES, Textures.POLARIZER_OVERLAY, true);

        // Laser Engraver, IDs 575-589
        registerSimpleMetaTileEntity(LASER_ENGRAVER, 575, "laser_engraver", RecipeMaps.LASER_ENGRAVER_RECIPES, Textures.LASER_ENGRAVER_OVERLAY, true);

        // Sifter, IDs 590-604
        registerSimpleMetaTileEntity(SIFTER, 590, "sifter", RecipeMaps.SIFTER_RECIPES, Textures.SIFTER_OVERLAY, true);

        // Thermal Centrifuge, IDs 605-619
        registerSimpleMetaTileEntity(THERMAL_CENTRIFUGE, 605, "thermal_centrifuge", RecipeMaps.THERMAL_CENTRIFUGE_RECIPES, Textures.THERMAL_CENTRIFUGE_OVERLAY, true);

        // Wire Mill, IDs 620-634
        registerSimpleMetaTileEntity(WIREMILL, 620, "wiremill", RecipeMaps.WIREMILL_RECIPES, Textures.WIREMILL_OVERLAY, true);

        // Circuit Assembler, IDs 650-664
        registerSimpleMetaTileEntity(CIRCUIT_ASSEMBLER, 635, "circuit_assembler", RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, true, GTUtility.hvCappedTankSizeFunction);

        // Rock Breaker, IDs 665-679
        ROCK_BREAKER[0] = registerMetaTileEntity(665, new MetaTileEntityRockBreaker(gregtechId("rock_breaker.lv"), RecipeMaps.ROCK_BREAKER_RECIPES, Textures.ROCK_BREAKER_OVERLAY, 1));
        ROCK_BREAKER[1] = registerMetaTileEntity(666, new MetaTileEntityRockBreaker(gregtechId("rock_breaker.mv"), RecipeMaps.ROCK_BREAKER_RECIPES, Textures.ROCK_BREAKER_OVERLAY, 2));
        ROCK_BREAKER[2] = registerMetaTileEntity(667, new MetaTileEntityRockBreaker(gregtechId("rock_breaker.hv"), RecipeMaps.ROCK_BREAKER_RECIPES, Textures.ROCK_BREAKER_OVERLAY, 3));
        ROCK_BREAKER[3] = registerMetaTileEntity(668, new MetaTileEntityRockBreaker(gregtechId("rock_breaker.ev"), RecipeMaps.ROCK_BREAKER_RECIPES, Textures.ROCK_BREAKER_OVERLAY, 4));
        ROCK_BREAKER[4] = registerMetaTileEntity(669, new MetaTileEntityRockBreaker(gregtechId("rock_breaker.iv"), RecipeMaps.ROCK_BREAKER_RECIPES, Textures.ROCK_BREAKER_OVERLAY, 5));
        if (getMidTier("rock_breaker")) {
            ROCK_BREAKER[5] = registerMetaTileEntity(670, new MetaTileEntityRockBreaker(gregtechId("rock_breaker.luv"), RecipeMaps.ROCK_BREAKER_RECIPES, Textures.ROCK_BREAKER_OVERLAY, 6));
            ROCK_BREAKER[6] = registerMetaTileEntity(671, new MetaTileEntityRockBreaker(gregtechId("rock_breaker.zpm"), RecipeMaps.ROCK_BREAKER_RECIPES, Textures.ROCK_BREAKER_OVERLAY, 7));
            ROCK_BREAKER[7] = registerMetaTileEntity(672, new MetaTileEntityRockBreaker(gregtechId("rock_breaker.uv"), RecipeMaps.ROCK_BREAKER_RECIPES, Textures.ROCK_BREAKER_OVERLAY, 8));
        }
        if (getHighTier("rock_breaker")) {
            ROCK_BREAKER[8] = registerMetaTileEntity(673, new MetaTileEntityRockBreaker(gregtechId("rock_breaker.uhv"), RecipeMaps.ROCK_BREAKER_RECIPES, Textures.ROCK_BREAKER_OVERLAY, 9));
            ROCK_BREAKER[9] = registerMetaTileEntity(674, new MetaTileEntityRockBreaker(gregtechId("rock_breaker.uev"), RecipeMaps.ROCK_BREAKER_RECIPES, Textures.ROCK_BREAKER_OVERLAY, 10));
            ROCK_BREAKER[10] = registerMetaTileEntity(675, new MetaTileEntityRockBreaker(gregtechId("rock_breaker.uiv"), RecipeMaps.ROCK_BREAKER_RECIPES, Textures.ROCK_BREAKER_OVERLAY, 11));
            ROCK_BREAKER[11] = registerMetaTileEntity(676, new MetaTileEntityRockBreaker(gregtechId("rock_breaker.umv"), RecipeMaps.ROCK_BREAKER_RECIPES, Textures.ROCK_BREAKER_OVERLAY, 12));
            ROCK_BREAKER[12] = registerMetaTileEntity(677, new MetaTileEntityRockBreaker(gregtechId("rock_breaker.uxv"), RecipeMaps.ROCK_BREAKER_RECIPES, Textures.ROCK_BREAKER_OVERLAY, 13));
        }

        // Some space here for more SimpleMachines

        // Space left for these just in case
        // Diesel Generator, IDs 935-949
        COMBUSTION_GENERATOR[0] = registerMetaTileEntity(935, new SimpleGeneratorMetaTileEntity(gregtechId("combustion_generator.lv"), RecipeMaps.COMBUSTION_GENERATOR_FUELS, Textures.COMBUSTION_GENERATOR_OVERLAY, 1));
        COMBUSTION_GENERATOR[1] = registerMetaTileEntity(936, new SimpleGeneratorMetaTileEntity(gregtechId("combustion_generator.mv"), RecipeMaps.COMBUSTION_GENERATOR_FUELS, Textures.COMBUSTION_GENERATOR_OVERLAY, 2));
        COMBUSTION_GENERATOR[2] = registerMetaTileEntity(937, new SimpleGeneratorMetaTileEntity(gregtechId("combustion_generator.hv"), RecipeMaps.COMBUSTION_GENERATOR_FUELS, Textures.COMBUSTION_GENERATOR_OVERLAY, 3));

        // Steam Turbine, IDs 950-964
        STEAM_TURBINE[0] = registerMetaTileEntity(950, new SimpleGeneratorMetaTileEntity(gregtechId("steam_turbine.lv"), RecipeMaps.STEAM_TURBINE_FUELS, Textures.STEAM_TURBINE_OVERLAY, 1));
        STEAM_TURBINE[1] = registerMetaTileEntity(951, new SimpleGeneratorMetaTileEntity(gregtechId("steam_turbine.mv"), RecipeMaps.STEAM_TURBINE_FUELS, Textures.STEAM_TURBINE_OVERLAY, 2));
        STEAM_TURBINE[2] = registerMetaTileEntity(952, new SimpleGeneratorMetaTileEntity(gregtechId("steam_turbine.hv"), RecipeMaps.STEAM_TURBINE_FUELS, Textures.STEAM_TURBINE_OVERLAY, 3));

        // Gas Turbine, IDs 965-979
        GAS_TURBINE[0] = registerMetaTileEntity(965, new SimpleGeneratorMetaTileEntity(gregtechId("gas_turbine.lv"), RecipeMaps.GAS_TURBINE_FUELS, Textures.GAS_TURBINE_OVERLAY, 1));
        GAS_TURBINE[1] = registerMetaTileEntity(966, new SimpleGeneratorMetaTileEntity(gregtechId("gas_turbine.mv"), RecipeMaps.GAS_TURBINE_FUELS, Textures.GAS_TURBINE_OVERLAY, 2));
        GAS_TURBINE[2] = registerMetaTileEntity(967, new SimpleGeneratorMetaTileEntity(gregtechId("gas_turbine.hv"), RecipeMaps.GAS_TURBINE_FUELS, Textures.GAS_TURBINE_OVERLAY, 3));

        // Item Collector, IDs 980-983
        ITEM_COLLECTOR[0] = registerMetaTileEntity(980, new MetaTileEntityItemCollector(gregtechId("item_collector.lv"), 1, 8));
        ITEM_COLLECTOR[1] = registerMetaTileEntity(981, new MetaTileEntityItemCollector(gregtechId("item_collector.mv"), 2, 16));
        ITEM_COLLECTOR[2] = registerMetaTileEntity(982, new MetaTileEntityItemCollector(gregtechId("item_collector.hv"), 3, 32));
        ITEM_COLLECTOR[3] = registerMetaTileEntity(983, new MetaTileEntityItemCollector(gregtechId("item_collector.ev"), 4, 64));

        MAGIC_ENERGY_ABSORBER = registerMetaTileEntity(984, new MetaTileEntityMagicEnergyAbsorber(gregtechId("magic_energy_absorber")));

        // Hulls, IDs 985-999
        int endPos = GTValues.HT ? HULL.length - 1 : Math.min(HULL.length - 1, GTValues.UV + 1);
        for (int i = 0; i < endPos; i++) {
            HULL[i] = new MetaTileEntityHull(gregtechId("hull." + GTValues.VN[i].toLowerCase()), i);
            registerMetaTileEntity(985 + i, HULL[i]);
        }
        // MAX Hull
        HULL[HULL.length - 1] = new MetaTileEntityHull(gregtechId("hull.max"), HULL.length - 1);
        registerMetaTileEntity(985 + HULL.length - 1, HULL[HULL.length - 1]);

        // MULTIBLOCK START: IDs 1000-1149. Space left for addons to register Multiblocks grouped with the rest in JEI
        PRIMITIVE_BLAST_FURNACE = registerMetaTileEntity(1000, new MetaTileEntityPrimitiveBlastFurnace(gregtechId("primitive_blast_furnace.bronze")));
        ELECTRIC_BLAST_FURNACE = registerMetaTileEntity(1001, new MetaTileEntityElectricBlastFurnace(gregtechId("electric_blast_furnace")));
        VACUUM_FREEZER = registerMetaTileEntity(1002, new MetaTileEntityVacuumFreezer(gregtechId("vacuum_freezer")));
        IMPLOSION_COMPRESSOR = registerMetaTileEntity(1003, new MetaTileEntityImplosionCompressor(gregtechId("implosion_compressor")));
        PYROLYSE_OVEN = registerMetaTileEntity(1004, new MetaTileEntityPyrolyseOven(gregtechId("pyrolyse_oven")));
        DISTILLATION_TOWER = registerMetaTileEntity(1005, new MetaTileEntityDistillationTower(gregtechId("distillation_tower")));
        MULTI_FURNACE = registerMetaTileEntity(1006, new MetaTileEntityMultiSmelter(gregtechId("multi_furnace")));
        LARGE_COMBUSTION_ENGINE = registerMetaTileEntity(1007, new MetaTileEntityLargeCombustionEngine(gregtechId("large_combustion_engine")));
        CRACKER = registerMetaTileEntity(1008, new MetaTileEntityCrackingUnit(gregtechId("cracker")));

        LARGE_STEAM_TURBINE = registerMetaTileEntity(1009, new MetaTileEntityLargeTurbine(gregtechId("large_turbine.steam"), TurbineType.STEAM));
        LARGE_GAS_TURBINE = registerMetaTileEntity(1010, new MetaTileEntityLargeTurbine(gregtechId("large_turbine.gas"), TurbineType.GAS));
        LARGE_PLASMA_TURBINE = registerMetaTileEntity(1011, new MetaTileEntityLargeTurbine(gregtechId("large_turbine.plasma"), TurbineType.PLASMA));

        LARGE_BRONZE_BOILER = registerMetaTileEntity(1012, new MetaTileEntityLargeBoiler(gregtechId("large_boiler.bronze"), BoilerType.BRONZE));
        LARGE_STEEL_BOILER = registerMetaTileEntity(1013, new MetaTileEntityLargeBoiler(gregtechId("large_boiler.steel"), BoilerType.STEEL));
        LARGE_TITANIUM_BOILER = registerMetaTileEntity(1014, new MetaTileEntityLargeBoiler(gregtechId("large_boiler.titanium"), BoilerType.TITANIUM));
        LARGE_TUNGSTENSTEEL_BOILER = registerMetaTileEntity(1015, new MetaTileEntityLargeBoiler(gregtechId("large_boiler.tungstensteel"), BoilerType.TUNGSTENSTEEL));

        COKE_OVEN = registerMetaTileEntity(1016, new MetaTileEntityCokeOven(gregtechId("coke_oven")));
        COKE_OVEN_HATCH = registerMetaTileEntity(1017, new MetaTileEntityCokeOvenHatch(gregtechId("coke_oven_hatch")));

        ASSEMBLY_LINE = registerMetaTileEntity(1018, new MetaTileEntityAssemblyLine(gregtechId("assembly_line")));
        FUSION_REACTOR[0] = registerMetaTileEntity(1019, new MetaTileEntityFusionReactor(gregtechId("fusion_reactor.luv"), 6));
        FUSION_REACTOR[1] = registerMetaTileEntity(1020, new MetaTileEntityFusionReactor(gregtechId("fusion_reactor.zpm"), 7));
        FUSION_REACTOR[2] = registerMetaTileEntity(1021, new MetaTileEntityFusionReactor(gregtechId("fusion_reactor.uv"), 8));

        LARGE_CHEMICAL_REACTOR = registerMetaTileEntity(1022, new MetaTileEntityLargeChemicalReactor(gregtechId("large_chemical_reactor")));

        STEAM_OVEN = registerMetaTileEntity(1023, new MetaTileEntitySteamOven(gregtechId("steam_oven")));
        STEAM_GRINDER = registerMetaTileEntity(1024, new MetaTileEntitySteamGrinder(gregtechId("steam_grinder")));

        // MISC MTE's START: IDs 1150-2000

        // Import/Export Buses/Hatches, IDs 1150-1209
        for (int i = 0; i < ITEM_IMPORT_BUS.length - 1; i++) {
            String voltageName = GTValues.VN[i].toLowerCase();
            ITEM_IMPORT_BUS[i] = new MetaTileEntityItemBus(gregtechId("item_bus.import." + voltageName), i, false);
            ITEM_EXPORT_BUS[i] = new MetaTileEntityItemBus(gregtechId("item_bus.export." + voltageName), i, true);
            FLUID_IMPORT_HATCH[i] = new MetaTileEntityFluidHatch(gregtechId("fluid_hatch.import." + voltageName), i, false);
            FLUID_EXPORT_HATCH[i] = new MetaTileEntityFluidHatch(gregtechId("fluid_hatch.export." + voltageName), i, true);

            registerMetaTileEntity(1150 + i, ITEM_IMPORT_BUS[i]);
            registerMetaTileEntity(1165 + i, ITEM_EXPORT_BUS[i]);
            registerMetaTileEntity(1180 + i, FLUID_IMPORT_HATCH[i]);
            registerMetaTileEntity(1195 + i, FLUID_EXPORT_HATCH[i]);
        }

        // Max Hatches/Buses
        ITEM_IMPORT_BUS[9] = new MetaTileEntityItemBus(gregtechId("item_bus.import.max"), 14, false);
        ITEM_EXPORT_BUS[9] = new MetaTileEntityItemBus(gregtechId("item_bus.export.max"), 14, true);
        FLUID_IMPORT_HATCH[9] = new MetaTileEntityFluidHatch(gregtechId("fluid_hatch.import.max"), 14, false);
        FLUID_EXPORT_HATCH[9] = new MetaTileEntityFluidHatch(gregtechId("fluid_hatch.export.max"), 14, true);

        registerMetaTileEntity(1150 + 9, ITEM_IMPORT_BUS[9]);
        registerMetaTileEntity(1165 + 9, ITEM_EXPORT_BUS[9]);
        registerMetaTileEntity(1180 + 9, FLUID_IMPORT_HATCH[9]);
        registerMetaTileEntity(1195 + 9, FLUID_EXPORT_HATCH[9]);

        // Multi-Fluid Hatches
        MULTI_FLUID_IMPORT_HATCH[0] = registerMetaTileEntity(1190, new MetaTileEntityMultiFluidHatch(gregtechId("fluid_hatch.import_4x"), 2, false));
        MULTI_FLUID_IMPORT_HATCH[1] = registerMetaTileEntity(1191, new MetaTileEntityMultiFluidHatch(gregtechId("fluid_hatch.import_9x"), 3, false));
        MULTI_FLUID_EXPORT_HATCH[0] = registerMetaTileEntity(1205, new MetaTileEntityMultiFluidHatch(gregtechId("fluid_hatch.export_4x"), 2, true));
        MULTI_FLUID_EXPORT_HATCH[1] = registerMetaTileEntity(1206, new MetaTileEntityMultiFluidHatch(gregtechId("fluid_hatch.export_9x"), 3, true));

        // Energy Input/Output Hatches, IDs 1210-1269
        endPos = GTValues.HT ? ENERGY_INPUT_HATCH.length - 1 : Math.min(ENERGY_INPUT_HATCH.length - 1, GTValues.UV + 1);
        for (int i = 0; i < endPos; i++) {
            String voltageName = GTValues.VN[i].toLowerCase();
            ENERGY_INPUT_HATCH[i] = new MetaTileEntityEnergyHatch(gregtechId("energy_hatch.input." + voltageName), i, false);
            ENERGY_INPUT_HATCH_ADJUSTABLE[i] = new MetaTileEntityAdjustableEnergyHatch(gregtechId("energy_hatch.adjustable.input." + voltageName), i, false);
            ENERGY_OUTPUT_HATCH[i] = new MetaTileEntityEnergyHatch(gregtechId("energy_hatch.output." + voltageName), i, true);
            ENERGY_OUTPUT_HATCH_ADJUSTABLE[i] = new MetaTileEntityAdjustableEnergyHatch(gregtechId("energy_hatch.adjustable.output." + voltageName), i, true);

            registerMetaTileEntity(1210 + i, ENERGY_INPUT_HATCH[i]);
            registerMetaTileEntity(1225 + i, ENERGY_OUTPUT_HATCH[i]);
            registerMetaTileEntity(1240 + i, ENERGY_INPUT_HATCH_ADJUSTABLE[i]);
            registerMetaTileEntity(1255 + i, ENERGY_OUTPUT_HATCH_ADJUSTABLE[i]);
        }
        // MAX Hatches
        ENERGY_INPUT_HATCH[ENERGY_INPUT_HATCH.length - 1] = new MetaTileEntityEnergyHatch(gregtechId("energy_hatch.input.max"), ENERGY_INPUT_HATCH.length - 1, false);
        ENERGY_OUTPUT_HATCH[ENERGY_OUTPUT_HATCH.length - 1] = new MetaTileEntityEnergyHatch(gregtechId("energy_hatch.output.max"), ENERGY_OUTPUT_HATCH.length - 1, true);
        ENERGY_INPUT_HATCH_ADJUSTABLE[ENERGY_INPUT_HATCH_ADJUSTABLE.length - 1] = new MetaTileEntityAdjustableEnergyHatch(gregtechId("energy_hatch.adjustable.input.max"), ENERGY_INPUT_HATCH_ADJUSTABLE.length - 1, false);
        ENERGY_OUTPUT_HATCH_ADJUSTABLE[ENERGY_OUTPUT_HATCH_ADJUSTABLE.length - 1] = new MetaTileEntityAdjustableEnergyHatch(gregtechId("energy_hatch.adjustable.output.max"), ENERGY_OUTPUT_HATCH_ADJUSTABLE.length - 1, true);

        registerMetaTileEntity(1210 + ENERGY_INPUT_HATCH.length - 1, ENERGY_INPUT_HATCH[ENERGY_INPUT_HATCH.length - 1]);
        registerMetaTileEntity(1225 + ENERGY_INPUT_HATCH.length - 1, ENERGY_INPUT_HATCH[ENERGY_INPUT_HATCH.length - 1]);
        registerMetaTileEntity(1240 + ENERGY_INPUT_HATCH_ADJUSTABLE.length - 1, ENERGY_INPUT_HATCH_ADJUSTABLE[ENERGY_INPUT_HATCH_ADJUSTABLE.length - 1]);
        registerMetaTileEntity(1255 + ENERGY_OUTPUT_HATCH_ADJUSTABLE.length - 1, ENERGY_OUTPUT_HATCH_ADJUSTABLE[ENERGY_OUTPUT_HATCH_ADJUSTABLE.length - 1]);

        // Transformer, IDs 1270-1299
        endPos = GTValues.HT ? TRANSFORMER.length - 1 : Math.min(TRANSFORMER.length - 1, GTValues.UV);
        for (int i = 0; i <= endPos; i++) {
            MetaTileEntityTransformer transformer = new MetaTileEntityTransformer(gregtechId("transformer." + GTValues.VN[i].toLowerCase()), i);
            TRANSFORMER[i] = registerMetaTileEntity(1270 + (i), transformer);
            MetaTileEntityAdjustableTransformer adjustableTransformer = new MetaTileEntityAdjustableTransformer(gregtechId("transformer.adjustable." + GTValues.VN[i].toLowerCase()), i);
            ADJUSTABLE_TRANSFORMER[i] = registerMetaTileEntity(1285 + (i), adjustableTransformer);
        }

        // Diode, IDs 1300-1314
        endPos = GTValues.HT ? DIODES.length - 1 : Math.min(DIODES.length - 1, GTValues.UV + 1);
        for (int i = 0; i < endPos; i++) {
            String diodeId = "diode." + GTValues.VN[i].toLowerCase();
            MetaTileEntityDiode diode = new MetaTileEntityDiode(gregtechId(diodeId), i);
            DIODES[i] = registerMetaTileEntity(1300 + i, diode);
        }
        // MAX Diode
        MetaTileEntityDiode diode = new MetaTileEntityDiode(gregtechId("diode.max"), DIODES.length - 1);
        DIODES[DIODES.length - 1] = registerMetaTileEntity(1300 + DIODES.length - 1, diode);

        // Battery Buffer, IDs 1315-1374
        endPos = GTValues.HT ? BATTERY_BUFFER.length - 1 : Math.min(BATTERY_BUFFER.length - 1, GTValues.UV + 1);
        int[] batteryBufferSlots = new int[]{1, 4, 9, 16};
        for (int i = 0; i < endPos; i++) {
            BATTERY_BUFFER[i] = new MetaTileEntityBatteryBuffer[batteryBufferSlots.length];
            for (int slot = 0; slot < batteryBufferSlots.length; slot++) {
                String bufferId = "battery_buffer." + GTValues.VN[i].toLowerCase() + "." + batteryBufferSlots[slot];
                MetaTileEntityBatteryBuffer batteryBuffer = new MetaTileEntityBatteryBuffer(gregtechId(bufferId), i, batteryBufferSlots[slot]);
                BATTERY_BUFFER[i][slot] = registerMetaTileEntity(1315 + batteryBufferSlots.length * i + slot, batteryBuffer);
            }
        }
        // MAX Battery Buffer
        BATTERY_BUFFER[BATTERY_BUFFER.length - 1] = new MetaTileEntityBatteryBuffer[batteryBufferSlots.length];
        for (int slot = 0; slot < batteryBufferSlots.length; slot++) {
            MetaTileEntityBatteryBuffer batteryBuffer = new MetaTileEntityBatteryBuffer(gregtechId("battery_buffer.max." + batteryBufferSlots[slot]), BATTERY_BUFFER.length - 1, batteryBufferSlots[slot]);
            BATTERY_BUFFER[BATTERY_BUFFER.length - 1][slot] = registerMetaTileEntity(1315 + batteryBufferSlots.length * (BATTERY_BUFFER.length - 1) + slot, batteryBuffer);
        }

        // Charger, IDs 1375-1389
        endPos = GTValues.HT ? CHARGER.length - 1 : Math.min(CHARGER.length - 1, GTValues.UV + 1);
        for (int i = 0; i < endPos; i++) {
            String chargerId = "charger." + GTValues.VN[i].toLowerCase();
            MetaTileEntityCharger charger = new MetaTileEntityCharger(gregtechId(chargerId), i, 4);
            CHARGER[i] = registerMetaTileEntity(1375 + i, charger);
        }
        // MAX Charger
        MetaTileEntityCharger charger = new MetaTileEntityCharger(gregtechId("charger.max"), CHARGER.length - 1, 4);
        CHARGER[CHARGER.length - 1] = registerMetaTileEntity(1375 + CHARGER.length - 1, charger);

        // World Accelerators, IDs 1390-1404
        if (ConfigHolder.U.GT5u.enableWorldAccelerators) {
            WORLD_ACCELERATOR[0] = registerMetaTileEntity(1390, new MetaTileEntityWorldAccelerator(gregtechId("world_accelerator.lv"), 1));
            WORLD_ACCELERATOR[1] = registerMetaTileEntity(1391, new MetaTileEntityWorldAccelerator(gregtechId("world_accelerator.mv"), 2));
            WORLD_ACCELERATOR[2] = registerMetaTileEntity(1392, new MetaTileEntityWorldAccelerator(gregtechId("world_accelerator.hv"), 3));
            WORLD_ACCELERATOR[3] = registerMetaTileEntity(1393, new MetaTileEntityWorldAccelerator(gregtechId("world_accelerator.ev"), 4));
            WORLD_ACCELERATOR[4] = registerMetaTileEntity(1394, new MetaTileEntityWorldAccelerator(gregtechId("world_accelerator.iv"), 5));
            WORLD_ACCELERATOR[5] = registerMetaTileEntity(1395, new MetaTileEntityWorldAccelerator(gregtechId("world_accelerator.luv"), 6));
            WORLD_ACCELERATOR[6] = registerMetaTileEntity(1396, new MetaTileEntityWorldAccelerator(gregtechId("world_accelerator.zpm"), 7));
            WORLD_ACCELERATOR[7] = registerMetaTileEntity(1397, new MetaTileEntityWorldAccelerator(gregtechId("world_accelerator.uv"), 8));
        }

        // Free Range: 1405-1509

        // Buffers, IDs 1510-1512
        BUFFER[0] = registerMetaTileEntity(1510, new MetaTileEntityBuffer(gregtechId("buffer.lv"), 1));
        BUFFER[1] = registerMetaTileEntity(1511, new MetaTileEntityBuffer(gregtechId("buffer.mv"), 2));
        BUFFER[2] = registerMetaTileEntity(1512, new MetaTileEntityBuffer(gregtechId("buffer.hv"), 3));

        // Free Range: 1513-1514

        // Fishers, IDs 1515-1529
        FISHER[0] = registerMetaTileEntity(1515, new MetaTileEntityFisher(gregtechId("fisher.lv"), 1));
        FISHER[1] = registerMetaTileEntity(1516, new MetaTileEntityFisher(gregtechId("fisher.mv"), 2));
        FISHER[2] = registerMetaTileEntity(1517, new MetaTileEntityFisher(gregtechId("fisher.hv"), 3));
        FISHER[3] = registerMetaTileEntity(1518, new MetaTileEntityFisher(gregtechId("fisher.ev"), 4));

        // Pumps, IDs 1530-1544
        PUMP[0] = registerMetaTileEntity(1530, new MetaTileEntityPump(gregtechId("pump.lv"), 1));
        PUMP[1] = registerMetaTileEntity(1531, new MetaTileEntityPump(gregtechId("pump.mv"), 2));
        PUMP[2] = registerMetaTileEntity(1532, new MetaTileEntityPump(gregtechId("pump.hv"), 3));
        PUMP[3] = registerMetaTileEntity(1533, new MetaTileEntityPump(gregtechId("pump.ev"), 4));

        // Super / Quantum Chests, IDs 1560-1574
        for (int i = 0; i < 5; i++) {
            String voltageName = GTValues.VN[i + 1].toLowerCase();
            QUANTUM_CHEST[i] = new MetaTileEntityQuantumChest(gregtechId("super_chest." + voltageName), i + 1, 4000000L * (int) Math.pow(2, i));
            registerMetaTileEntity(1560 + i, QUANTUM_CHEST[i]);
        }

        for (int i = 5; i < QUANTUM_CHEST.length; i++) {
            String voltageName = GTValues.VN[i].toLowerCase();
            long capacity = i == GTValues.UHV ? Integer.MAX_VALUE : 4000000L * (int) Math.pow(2, i);
            QUANTUM_CHEST[i] = new MetaTileEntityQuantumChest(gregtechId("quantum_chest." + voltageName), i, capacity);
            registerMetaTileEntity(1565 + i, QUANTUM_CHEST[i]);
        }

        // Super / Quantum Tanks, IDs 1575-1589
        for (int i = 0; i < 5; i++) {
            String voltageName = GTValues.VN[i + 1].toLowerCase();
            QUANTUM_TANK[i] = new MetaTileEntityQuantumTank(gregtechId("super_tank." + voltageName), i + 1, 4000000 * (int) Math.pow(2, i));
            registerMetaTileEntity(1575 + i, QUANTUM_TANK[i]);
        }

        for (int i = 5; i < QUANTUM_TANK.length; i++) {
            String voltageName = GTValues.VN[i].toLowerCase();
            int capacity = i == GTValues.UHV ? Integer.MAX_VALUE : 4000000 * (int) Math.pow(2, i);
            QUANTUM_TANK[i] = new MetaTileEntityQuantumTank(gregtechId("quantum_tank." + voltageName), i, capacity);
            registerMetaTileEntity(1580 + i, QUANTUM_TANK[i]);
        }

        // Block Breakers, IDs 1590-1594
        for (int i = 0; i < BLOCK_BREAKER.length; i++) {
            String voltageName = GTValues.VN[i + 1].toLowerCase();
            BLOCK_BREAKER[i] = new MetaTileEntityBlockBreaker(gregtechId("block_breaker." + voltageName), i + 1);
            registerMetaTileEntity(1590 + i, BLOCK_BREAKER[i]);
        }

        // Tanks, IDs 1595-1609
        WOODEN_TANK = registerMetaTileEntity(1595, new MetaTileEntityTank(gregtechId("wooden_tank"), Materials.Wood, 4000, 1, 3));
        BRONZE_TANK = registerMetaTileEntity(1596, new MetaTileEntityTank(gregtechId("bronze_tank"), Materials.Bronze, 8000, 4, 3));
        STEEL_TANK = registerMetaTileEntity(1597, new MetaTileEntityTank(gregtechId("steel_tank"), Materials.Steel, 16000, 7, 5));
        ALUMINIUM_TANK = registerMetaTileEntity(1598, new MetaTileEntityTank(gregtechId("aluminium_tank"), Materials.Aluminium, 32000, 8, 5));
        STAINLESS_STEEL_TANK = registerMetaTileEntity(1599, new MetaTileEntityTank(gregtechId("stainless_steel_tank"), Materials.StainlessSteel, 64000, 9, 7));
        TITANIUM_TANK = registerMetaTileEntity(1600, new MetaTileEntityTank(gregtechId("titanium_tank"), Materials.Titanium, 128000, 12, 9));
        TUNGSTENSTEEL_TANK = registerMetaTileEntity(1601, new MetaTileEntityTank(gregtechId("tungstensteel_tank"), Materials.TungstenSteel, 512000, 16, 9));

        // Drums, IDs 1610-1624
        WOODEN_DRUM = registerMetaTileEntity(1610, new MetaTileEntityDrum(gregtechId("drum.wood"), Materials.Wood, 4000));
        BRONZE_DRUM = registerMetaTileEntity(1611, new MetaTileEntityDrum(gregtechId("drum.bronze"), Materials.Bronze, 8000));
        STEEL_DRUM = registerMetaTileEntity(1612, new MetaTileEntityDrum(gregtechId("drum.steel"), Materials.Steel, 16000));
        ALUMINIUM_DRUM = registerMetaTileEntity(1613, new MetaTileEntityDrum(gregtechId("drum.aluminium"), Materials.Aluminium, 32000));
        STAINLESS_STEEL_DRUM = registerMetaTileEntity(1614, new MetaTileEntityDrum(gregtechId("drum.stainless_steel"), Materials.StainlessSteel, 64000));
        TITANIUM_DRUM = registerMetaTileEntity(1615, new MetaTileEntityDrum(gregtechId("drum.titanium"), Materials.Titanium, 128000));
        TUNGSTENSTEEL_DRUM = registerMetaTileEntity(1616, new MetaTileEntityDrum(gregtechId("drum.tungstensteel"), Materials.TungstenSteel, 512000));

        // Crates, IDs 1625-1639
        WOODEN_CRATE = registerMetaTileEntity(1625, new MetaTileEntityCrate(gregtechId("crate.wood"), Materials.Wood, 27));
        BRONZE_CRATE = registerMetaTileEntity(1626, new MetaTileEntityCrate(gregtechId("crate.bronze"), Materials.Bronze, 54));
        STEEL_CRATE = registerMetaTileEntity(1627, new MetaTileEntityCrate(gregtechId("crate.steel"), Materials.Steel, 72));
        ALUMINIUM_CRATE = registerMetaTileEntity(1628, new MetaTileEntityCrate(gregtechId("crate.aluminium"), Materials.Aluminium, 90));
        STAINLESS_STEEL_CRATE = registerMetaTileEntity(1629, new MetaTileEntityCrate(gregtechId("crate.stainless_steel"), Materials.StainlessSteel, 108));
        TITANIUM_CRATE = registerMetaTileEntity(1630, new MetaTileEntityCrate(gregtechId("crate.titanium"), Materials.Titanium, 126));
        TUNGSTENSTEEL_CRATE = registerMetaTileEntity(1631, new MetaTileEntityCrate(gregtechId("crate.tungstensteel"), Materials.TungstenSteel, 144));

        // Rotor Holder, IDs 1640-1644
        ROTOR_HOLDER[0] = registerMetaTileEntity(1640, new MetaTileEntityRotorHolder(gregtechId("rotor_holder.hv"), GTValues.HV, 1.0f));
        ROTOR_HOLDER[1] = registerMetaTileEntity(1641, new MetaTileEntityRotorHolder(gregtechId("rotor_holder.luv"), GTValues.LuV, 1.15f));
        ROTOR_HOLDER[2] = registerMetaTileEntity(1642, new MetaTileEntityRotorHolder(gregtechId("rotor_holder.uhv"), GTValues.UHV, 1.25f));

        // Misc, IDs 1645-1999
        LOCKED_SAFE = registerMetaTileEntity(1645, new MetaTileEntityLockedSafe(gregtechId("locked_safe")));
        WORKBENCH = registerMetaTileEntity(1646, new MetaTileEntityWorkbench(gregtechId("workbench")));
        PRIMITIVE_WATER_PUMP = registerMetaTileEntity(1647, new MetaTileEntityPrimitiveWaterPump(gregtechId("primitive_water_pump")));
        PUMP_OUTPUT_HATCH = registerMetaTileEntity(1648, new MetaTileEntityPumpHatch(gregtechId("pump_hatch")));

        CREATIVE_ENERGY = registerMetaTileEntity(1649, new MetaTileEntityCreativeEnergy());
        // Steam Hatches/Buses
        STEAM_EXPORT_BUS = registerMetaTileEntity(1650, new MetaTileEntitySteamItemBus(gregtechId("steam_export_bus"), true));
        STEAM_IMPORT_BUS = registerMetaTileEntity(1651, new MetaTileEntitySteamItemBus(gregtechId("steam_import_bus"), false));
        STEAM_HATCH = registerMetaTileEntity(1652, new MetaTileEntitySteamHatch(gregtechId("steam_hatch")));
        SIMPLE_ORE_WASHER = registerMetaTileEntity(1653, new MetaTileEntitySimpleOreWasher(gregtechId("ore_washer.simple"), RecipeMaps.SIMPLE_WASHER_RECIPES, Textures.ORE_WASHER_OVERLAY, 0));

        // Maintenance Hatches, IDs 1654-1655
        MAINTENANCE_HATCH = registerMetaTileEntity(1654, new MetaTileEntityMaintenanceHatch(gregtechId("maintenance_hatch")));
        AUTO_MAINTENANCE_HATCH = registerMetaTileEntity(1655, new MetaTileEntityAutoMaintenanceHatch(gregtechId("maintenance_hatch_full_auto")));

        // Muffler Hatches, IDs 1656-
        for (int i = 0; i < MUFFLER_HATCH.length; i++) {
            String voltageName = GTValues.VN[i + 1].toLowerCase();
            MUFFLER_HATCH[i] = new MetaTileEntityMufflerHatch(gregtechId("muffler_hatch." + voltageName), i + 1);

            registerMetaTileEntity(1656 + i, MUFFLER_HATCH[i]);
        }

        CLIPBOARD_TILE = registerMetaTileEntity(1666, new MetaTileEntityClipboard(gregtechId("clipboard")));

        /*
         * FOR ADDON DEVELOPERS:
         *
         * GTCEu will not take more than 2000 IDs. Anything past ID 1999
         * is considered FAIR GAME, take whatever you like.
         *
         * If you would like to reserve IDs, feel free to reach out to the
         * development team and claim a range of IDs! We will mark any
         * claimed ranges below this comment. Max value is 32767.
         *
         * - Gregicality / Shadows of Greg: 2000-3999
         * - Gregification: 4000-4499
         * - GregTech Food Option: 8500-8999
         * - HtmlTech: 9000-9499
         * - PCM's Ore Addon: 9500-9999
         * - GCM: 10000-10099
         * - MechTech: 10100-10499
         * - FREE RANGE 10500-32767
         */
    }

    private static void registerSimpleMetaTileEntity(SimpleMachineMetaTileEntity[] machines,
                                                     int startId,
                                                     String name,
                                                     RecipeMap<?> map,
                                                     OrientedOverlayRenderer texture,
                                                     boolean hasFrontFacing,
                                                     Function<Integer, Integer> tankScalingFunction) {
        registerSimpleMetaTileEntity(machines, startId, name, map, texture, hasFrontFacing, MetaTileEntities::gregtechId, tankScalingFunction);
    }

    private static void registerSimpleMetaTileEntity(SimpleMachineMetaTileEntity[] machines,
                                                     int startId,
                                                     String name,
                                                     RecipeMap<?> map,
                                                     OrientedOverlayRenderer texture,
                                                     boolean hasFrontFacing) {
        registerSimpleMetaTileEntity(machines, startId, name, map, texture, hasFrontFacing, GTUtility.defaultTankSizeFunction);
    }

    public static void registerSimpleMetaTileEntity(SimpleMachineMetaTileEntity[] machines,
                                                    int startId,
                                                    String name,
                                                    RecipeMap<?> map,
                                                    OrientedOverlayRenderer texture,
                                                    boolean hasFrontFacing,
                                                    Function<String, ResourceLocation> resourceId,
                                                    Function<Integer, Integer> tankScalingFunction) {
        for (int i = 0; i < machines.length - 1; i++) {
            if (i > 4 && !getMidTier(name)) continue;
            if (i > 7 && !getHighTier(name)) break;

            String voltageName = GTValues.VN[i + 1].toLowerCase();
            machines[i] = registerMetaTileEntity(startId + i,
                    new SimpleMachineMetaTileEntity(resourceId.apply(String.format("%s.%s", name, voltageName)), map, texture, i + 1, hasFrontFacing, tankScalingFunction));
        }
    }

    public static <T extends MetaTileEntity> T registerMetaTileEntity(int id, T sampleMetaTileEntity) {
        GregTechAPI.MTE_REGISTRY.register(id, sampleMetaTileEntity.metaTileEntityId, sampleMetaTileEntity);
        return sampleMetaTileEntity;
    }

    private static ResourceLocation gregtechId(String name) {
        return new ResourceLocation(GTValues.MODID, name);
    }

    // Used for addons if they wish to disable certain tiers of machines
    private static final Map<String, Boolean> MID_TIER = new HashMap<>();
    private static final Map<String, Boolean> HIGH_TIER = new HashMap<>();

    @SuppressWarnings("unused")
    public static void setMidTier(String key, boolean enabled) {
        MID_TIER.put(key, enabled);
    }

    @SuppressWarnings("unused")
    public static void setHighTier(String key, boolean enabled) {
        HIGH_TIER.put(key, enabled);
        GTValues.HT = enabled || HIGH_TIER.containsValue(true);
    }

    public static boolean getMidTier(String key) {
        return MID_TIER.getOrDefault(key, true);
    }

    public static boolean getHighTier(String key) {
        return HIGH_TIER.getOrDefault(key, false);
    }
}
