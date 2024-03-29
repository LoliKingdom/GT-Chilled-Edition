package gregtech.loaders.recipe;

import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.ConfigHolder;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;

import static gregtech.common.metatileentities.MetaTileEntities.*;

public class MetaTileEntityMachineRecipeLoader {

    public static void init() {
        if (ConfigHolder.U.GT5u.harderEnergyHatches) {

            // Energy Output Hatches

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.ULV].getStackForm())
                    .input(OrePrefix.spring, Materials.Lead)
                    .input(OrePrefix.circuit, MarkerMaterials.Tier.Primitive, 2)
                    .inputs(MetaItems.VOLTAGE_COIL_ULV.getStackForm(2))
                    .input(OrePrefix.rotor, Materials.Lead)
                    .fluidInputs(Materials.Lubricant.getFluid(2000))
                    .outputs(MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.ULV].getStackForm())
                    .duration(200).EUt(7).buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.LV].getStackForm())
                    .input(OrePrefix.spring, Materials.Tin)
                    .input(OrePrefix.circuit, MarkerMaterials.Tier.Basic, 2)
                    .inputs(MetaItems.VOLTAGE_COIL_LV.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_LV.getStackForm())
                    .fluidInputs(Materials.Lubricant.getFluid(2000))
                    .outputs(MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.LV].getStackForm())
                    .duration(200).EUt(30).buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.MV].getStackForm())
                    .input(OrePrefix.spring, Materials.Copper)
                    .inputs(MetaItems.SMALL_COIL.getStackForm(2))
                    .inputs(MetaItems.VOLTAGE_COIL_MV.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_MV.getStackForm())
                    .fluidInputs(Materials.Lubricant.getFluid(2000))
                    .outputs(MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.MV].getStackForm())
                    .duration(200).EUt(120).buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.HV].getStackForm())
                    .input(OrePrefix.spring, Materials.Gold)
                    .inputs(MetaItems.ULTRA_LOW_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                    .inputs(MetaItems.VOLTAGE_COIL_HV.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_HV.getStackForm())
                    .fluidInputs(Materials.Helium.getFluid(1000))
                    .outputs(MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.HV].getStackForm())
                    .duration(200).EUt(480).buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.HV].getStackForm())
                    .input(OrePrefix.spring, Materials.Gold)
                    .inputs(MetaItems.ULTRA_LOW_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                    .inputs(MetaItems.VOLTAGE_COIL_HV.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_HV.getStackForm())
                    .fluidInputs(Materials.SodiumPotassium.getFluid(1000))
                    .outputs(MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.HV].getStackForm())
                    .duration(200).EUt(480).buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.EV].getStackForm())
                    .input(OrePrefix.spring, Materials.Aluminium)
                    .inputs(MetaItems.LOW_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                    .inputs(MetaItems.VOLTAGE_COIL_EV.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_EV.getStackForm())
                    .fluidInputs(Materials.Helium.getFluid(2000))
                    .outputs(MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.EV].getStackForm())
                    .duration(200).EUt(1920).buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.EV].getStackForm())
                    .input(OrePrefix.spring, Materials.Aluminium)
                    .inputs(MetaItems.LOW_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                    .inputs(MetaItems.VOLTAGE_COIL_EV.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_EV.getStackForm())
                    .fluidInputs(Materials.SodiumPotassium.getFluid(2000))
                    .outputs(MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.EV].getStackForm())
                    .duration(200).EUt(1920).buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.IV].getStackForm())
                    .input(OrePrefix.spring, Materials.Tungsten)
                    .inputs(MetaItems.POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                    .inputs(MetaItems.VOLTAGE_COIL_IV.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_IV.getStackForm())
                    .fluidInputs(Materials.Helium.getFluid(3000))
                    .outputs(MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.IV].getStackForm())
                    .duration(200).EUt(7680).buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.IV].getStackForm())
                    .input(OrePrefix.spring, Materials.Tungsten)
                    .inputs(MetaItems.POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                    .inputs(MetaItems.VOLTAGE_COIL_IV.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_IV.getStackForm())
                    .fluidInputs(Materials.SodiumPotassium.getFluid(3000))
                    .outputs(MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.IV].getStackForm())
                    .duration(200).EUt(7680).buildAndRegister();

            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.LuV].getStackForm())
                    .input(OrePrefix.spring, Materials.YttriumBariumCuprate, 2)
                    .inputs(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                    .input(OrePrefix.circuit, MarkerMaterials.Tier.Master, 2)
                    .inputs(MetaItems.VOLTAGE_COIL_LUV.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_LUV.getStackForm())
                    .fluidInputs(Materials.Helium.getFluid(6000))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(720))
                    .outputs(MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.LuV].getStackForm())
                    .duration(400).EUt(30720).buildAndRegister();

            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.LuV].getStackForm())
                    .input(OrePrefix.spring, Materials.YttriumBariumCuprate, 2)
                    .inputs(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                    .input(OrePrefix.circuit, MarkerMaterials.Tier.Master, 2)
                    .inputs(MetaItems.VOLTAGE_COIL_LUV.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_LUV.getStackForm())
                    .fluidInputs(Materials.SodiumPotassium.getFluid(6000))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(720))
                    .outputs(MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.LuV].getStackForm())
                    .duration(400).EUt(30720).buildAndRegister();

            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.ZPM].getStackForm())
                    .input(OrePrefix.spring, Materials.VanadiumGallium, 4)
                    .inputs(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                    .input(OrePrefix.circuit, MarkerMaterials.Tier.Ultimate, 2)
                    .inputs(MetaItems.VOLTAGE_COIL_ZPM.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_ZPM.getStackForm())
                    .fluidInputs(Materials.Helium.getFluid(6000))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(1440))
                    .outputs(MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.ZPM].getStackForm())
                    .duration(600).EUt(122880).buildAndRegister();

            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.ZPM].getStackForm())
                    .input(OrePrefix.spring, Materials.VanadiumGallium, 4)
                    .inputs(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                    .input(OrePrefix.circuit, MarkerMaterials.Tier.Ultimate, 2)
                    .inputs(MetaItems.VOLTAGE_COIL_ZPM.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_ZPM.getStackForm())
                    .fluidInputs(Materials.SodiumPotassium.getFluid(6000))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(1440))
                    .outputs(MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.ZPM].getStackForm())
                    .duration(600).EUt(122880).buildAndRegister();

            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.UV].getStackForm())
                    .input(OrePrefix.spring, Materials.NiobiumTitanium, 4)
                    .inputs(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                    .input(OrePrefix.circuit, MarkerMaterials.Tier.Super, 2)
                    .inputs(MetaItems.VOLTAGE_COIL_UV.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_UV.getStackForm())
                    .fluidInputs(Materials.Helium.getFluid(12000))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(2880))
                    .outputs(MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.UV].getStackForm())
                    .duration(800).EUt(491520).buildAndRegister();

            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.UV].getStackForm())
                    .input(OrePrefix.spring, Materials.NiobiumTitanium, 4)
                    .inputs(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                    .input(OrePrefix.circuit, MarkerMaterials.Tier.Super, 2)
                    .inputs(MetaItems.VOLTAGE_COIL_UV.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_UV.getStackForm())
                    .fluidInputs(Materials.SodiumPotassium.getFluid(12000))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(2880))
                    .outputs(MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.UV].getStackForm())
                    .duration(800).EUt(491520).buildAndRegister();

            // Energy Input Hatches

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.ULV].getStackForm())
                    .input(OrePrefix.cableGtSingle, Materials.Lead, 2)
                    .input(OrePrefix.circuit, MarkerMaterials.Tier.Primitive)
                    .inputs(MetaItems.VOLTAGE_COIL_ULV.getStackForm(2))
                    .input(OrePrefix.rotor, Materials.Lead)
                    .fluidInputs(Materials.Lubricant.getFluid(2000))
                    .outputs(MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.ULV].getStackForm())
                    .duration(200).EUt(7).buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.LV].getStackForm())
                    .input(OrePrefix.cableGtSingle, Materials.Tin, 2)
                    .input(OrePrefix.circuit, MarkerMaterials.Tier.Basic)
                    .inputs(MetaItems.VOLTAGE_COIL_LV.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_LV.getStackForm())
                    .fluidInputs(Materials.Lubricant.getFluid(2000))
                    .outputs(MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.LV].getStackForm())
                    .duration(200).EUt(30).buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.MV].getStackForm())
                    .input(OrePrefix.cableGtSingle, Materials.Copper)
                    .inputs(MetaItems.SMALL_COIL.getStackForm(2))
                    .inputs(MetaItems.VOLTAGE_COIL_MV.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_MV.getStackForm())
                    .fluidInputs(Materials.Lubricant.getFluid(2000))
                    .outputs(MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.MV].getStackForm())
                    .duration(200).EUt(120).buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.HV].getStackForm())
                    .input(OrePrefix.cableGtSingle, Materials.Gold)
                    .inputs(MetaItems.ULTRA_LOW_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                    .inputs(MetaItems.VOLTAGE_COIL_HV.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_HV.getStackForm())
                    .fluidInputs(Materials.Helium.getFluid(1000))
                    .outputs(MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV].getStackForm())
                    .duration(200).EUt(480).buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.HV].getStackForm())
                    .input(OrePrefix.cableGtSingle, Materials.Gold)
                    .inputs(MetaItems.ULTRA_LOW_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                    .inputs(MetaItems.VOLTAGE_COIL_HV.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_HV.getStackForm())
                    .fluidInputs(Materials.SodiumPotassium.getFluid(1000))
                    .outputs(MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV].getStackForm())
                    .duration(200).EUt(480).buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.EV].getStackForm())
                    .input(OrePrefix.cableGtSingle, Materials.Aluminium)
                    .inputs(MetaItems.LOW_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                    .inputs(MetaItems.VOLTAGE_COIL_EV.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_EV.getStackForm())
                    .fluidInputs(Materials.Helium.getFluid(2000))
                    .outputs(MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.EV].getStackForm())
                    .duration(200).EUt(1920).buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.EV].getStackForm())
                    .input(OrePrefix.cableGtSingle, Materials.Aluminium)
                    .inputs(MetaItems.LOW_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                    .inputs(MetaItems.VOLTAGE_COIL_EV.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_EV.getStackForm())
                    .fluidInputs(Materials.SodiumPotassium.getFluid(2000))
                    .outputs(MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.EV].getStackForm())
                    .duration(200).EUt(1920).buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.IV].getStackForm())
                    .input(OrePrefix.cableGtSingle, Materials.Tungsten)
                    .inputs(MetaItems.POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                    .inputs(MetaItems.VOLTAGE_COIL_IV.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_IV.getStackForm())
                    .fluidInputs(Materials.Helium.getFluid(3000))
                    .outputs(MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.IV].getStackForm())
                    .duration(200).EUt(7680).buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.IV].getStackForm())
                    .input(OrePrefix.cableGtSingle, Materials.Tungsten)
                    .inputs(MetaItems.POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                    .inputs(MetaItems.VOLTAGE_COIL_IV.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_IV.getStackForm())
                    .fluidInputs(Materials.SodiumPotassium.getFluid(3000))
                    .outputs(MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.IV].getStackForm())
                    .duration(200).EUt(7680).buildAndRegister();

            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.LuV].getStackForm())
                    .input(OrePrefix.cableGtSingle, Materials.YttriumBariumCuprate, 2)
                    .inputs(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                    .input(OrePrefix.circuit, MarkerMaterials.Tier.Master, 2)
                    .inputs(MetaItems.VOLTAGE_COIL_LUV.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_LUV.getStackForm())
                    .fluidInputs(Materials.Helium.getFluid(6000))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(720))
                    .outputs(MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.LuV].getStackForm())
                    .duration(400).EUt(30720).buildAndRegister();

            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.LuV].getStackForm())
                    .input(OrePrefix.cableGtSingle, Materials.YttriumBariumCuprate, 2)
                    .inputs(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                    .input(OrePrefix.circuit, MarkerMaterials.Tier.Master, 2)
                    .inputs(MetaItems.VOLTAGE_COIL_LUV.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_LUV.getStackForm())
                    .fluidInputs(Materials.SodiumPotassium.getFluid(6000))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(720))
                    .outputs(MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.LuV].getStackForm())
                    .duration(400).EUt(30720).buildAndRegister();

            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.ZPM].getStackForm())
                    .input(OrePrefix.cableGtDouble, Materials.VanadiumGallium, 2)
                    .inputs(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                    .input(OrePrefix.circuit, MarkerMaterials.Tier.Ultimate, 2)
                    .inputs(MetaItems.VOLTAGE_COIL_ZPM.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_ZPM.getStackForm())
                    .fluidInputs(Materials.Helium.getFluid(6000))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(1440))
                    .outputs(MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.ZPM].getStackForm())
                    .duration(600).EUt(122880).buildAndRegister();

            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.ZPM].getStackForm())
                    .input(OrePrefix.cableGtDouble, Materials.VanadiumGallium, 2)
                    .inputs(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                    .input(OrePrefix.circuit, MarkerMaterials.Tier.Ultimate, 2)
                    .inputs(MetaItems.VOLTAGE_COIL_ZPM.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_ZPM.getStackForm())
                    .fluidInputs(Materials.SodiumPotassium.getFluid(6000))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(1440))
                    .outputs(MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.ZPM].getStackForm())
                    .duration(600).EUt(122880).buildAndRegister();

            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.UV].getStackForm())
                    .input(OrePrefix.cableGtDouble, Materials.NiobiumTitanium, 2)
                    .inputs(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                    .input(OrePrefix.circuit, MarkerMaterials.Tier.Super, 2)
                    .inputs(MetaItems.VOLTAGE_COIL_UV.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_UV.getStackForm())
                    .fluidInputs(Materials.Helium.getFluid(12000))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(2880))
                    .outputs(MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.UV].getStackForm())
                    .duration(800).EUt(491520).buildAndRegister();

            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .inputs(HULL[GTValues.UV].getStackForm())
                    .input(OrePrefix.cableGtDouble, Materials.NiobiumTitanium, 2)
                    .inputs(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                    .input(OrePrefix.circuit, MarkerMaterials.Tier.Super, 2)
                    .inputs(MetaItems.VOLTAGE_COIL_UV.getStackForm(2))
                    .inputs(MetaItems.ELECTRIC_PUMP_UV.getStackForm())
                    .fluidInputs(Materials.SodiumPotassium.getFluid(12000))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(2880))
                    .outputs(MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.UV].getStackForm())
                    .duration(800).EUt(491520).buildAndRegister();

        }

        // Adjustable Transformers

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(TRANSFORMER[GTValues.ULV].getStackForm())
                .inputs(MetaItems.ELECTRIC_PUMP_LV.getStackForm())
                .input(OrePrefix.wireGtQuadruple, Materials.Tin)
                .input(OrePrefix.wireGtOctal, Materials.Lead)
                .input(OrePrefix.springSmall, Materials.Lead)
                .input(OrePrefix.spring, Materials.Tin)
                .fluidInputs(Materials.Lubricant.getFluid(2000))
                .outputs(ADJUSTABLE_TRANSFORMER[GTValues.ULV].getStackForm())
                .duration(200).EUt(7).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(TRANSFORMER[GTValues.LV].getStackForm())
                .inputs(MetaItems.ELECTRIC_PUMP_LV.getStackForm())
                .input(OrePrefix.wireGtQuadruple, Materials.Copper)
                .input(OrePrefix.wireGtOctal, Materials.Tin)
                .input(OrePrefix.springSmall, Materials.Tin)
                .input(OrePrefix.spring, Materials.Copper)
                .fluidInputs(Materials.Lubricant.getFluid(2000))
                .outputs(ADJUSTABLE_TRANSFORMER[GTValues.LV].getStackForm())
                .duration(200).EUt(30).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(TRANSFORMER[GTValues.MV].getStackForm())
                .inputs(MetaItems.ELECTRIC_PUMP_MV.getStackForm())
                .input(OrePrefix.wireGtQuadruple, Materials.Gold)
                .input(OrePrefix.wireGtOctal, Materials.Copper)
                .input(OrePrefix.springSmall, Materials.Copper)
                .input(OrePrefix.spring, Materials.Gold)
                .fluidInputs(Materials.Lubricant.getFluid(2000))
                .outputs(ADJUSTABLE_TRANSFORMER[GTValues.MV].getStackForm())
                .duration(200).EUt(120).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(TRANSFORMER[GTValues.HV].getStackForm())
                .inputs(MetaItems.ELECTRIC_PUMP_MV.getStackForm())
                .input(OrePrefix.wireGtQuadruple, Materials.Aluminium)
                .input(OrePrefix.wireGtOctal, Materials.Gold)
                .input(OrePrefix.springSmall, Materials.Gold)
                .input(OrePrefix.spring, Materials.Aluminium)
                .fluidInputs(Materials.Lubricant.getFluid(2000))
                .outputs(ADJUSTABLE_TRANSFORMER[GTValues.HV].getStackForm())
                .duration(200).EUt(480).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(TRANSFORMER[GTValues.EV].getStackForm())
                .inputs(MetaItems.ELECTRIC_PUMP_HV.getStackForm())
                .input(OrePrefix.wireGtQuadruple, Materials.Tungsten)
                .input(OrePrefix.wireGtOctal, Materials.Aluminium)
                .input(OrePrefix.springSmall, Materials.Aluminium)
                .input(OrePrefix.spring, Materials.Tungsten)
                .fluidInputs(Materials.Lubricant.getFluid(2000))
                .outputs(ADJUSTABLE_TRANSFORMER[GTValues.EV].getStackForm())
                .duration(200).EUt(1920).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(TRANSFORMER[GTValues.IV].getStackForm())
                .inputs(MetaItems.ELECTRIC_PUMP_HV.getStackForm())
                .input(OrePrefix.wireGtQuadruple, Materials.YttriumBariumCuprate)
                .input(OrePrefix.wireGtOctal, Materials.Tungsten)
                .input(OrePrefix.springSmall, Materials.Tungsten)
                .input(OrePrefix.spring, Materials.YttriumBariumCuprate)
                .fluidInputs(Materials.Lubricant.getFluid(2000))
                .outputs(ADJUSTABLE_TRANSFORMER[GTValues.IV].getStackForm())
                .duration(200).EUt(7680).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(TRANSFORMER[GTValues.LuV].getStackForm())
                .inputs(MetaItems.ELECTRIC_PUMP_EV.getStackForm())
                .input(OrePrefix.wireGtQuadruple, Materials.VanadiumGallium)
                .input(OrePrefix.wireGtOctal, Materials.YttriumBariumCuprate)
                .input(OrePrefix.springSmall, Materials.YttriumBariumCuprate)
                .input(OrePrefix.spring, Materials.VanadiumGallium)
                .fluidInputs(Materials.Lubricant.getFluid(2000))
                .outputs(ADJUSTABLE_TRANSFORMER[GTValues.LuV].getStackForm())
                .duration(200).EUt(32768).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(TRANSFORMER[GTValues.ZPM].getStackForm())
                .inputs(MetaItems.ELECTRIC_PUMP_EV.getStackForm())
                .input(OrePrefix.wireGtQuadruple, Materials.NiobiumTitanium)
                .input(OrePrefix.wireGtOctal, Materials.VanadiumGallium)
                .input(OrePrefix.springSmall, Materials.VanadiumGallium)
                .input(OrePrefix.spring, Materials.NiobiumTitanium)
                .fluidInputs(Materials.Lubricant.getFluid(2000))
                .outputs(ADJUSTABLE_TRANSFORMER[GTValues.ZPM].getStackForm())
                .duration(200).EUt(131072).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(TRANSFORMER[GTValues.UV].getStackForm())
                .inputs(MetaItems.ELECTRIC_PUMP_IV.getStackForm())
                .input(OrePrefix.wireGtQuadruple, Materials.Europium)
                .input(OrePrefix.wireGtOctal, Materials.NiobiumTitanium)
                .input(OrePrefix.springSmall, Materials.NiobiumTitanium)
                .input(OrePrefix.spring, Materials.Europium)
                .fluidInputs(Materials.Lubricant.getFluid(2000))
                .outputs(ADJUSTABLE_TRANSFORMER[GTValues.UV].getStackForm())
                .duration(200).EUt(524288).buildAndRegister();

        // Adjustable Input Hatches

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(ADJUSTABLE_TRANSFORMER[GTValues.ULV].getStackForm())
                .inputs(ENERGY_INPUT_HATCH[GTValues.ULV].getStackForm())
                .inputs(MetaItems.VOLTAGE_COIL_ULV.getStackForm(2))
                .input(OrePrefix.cableGtSingle, Materials.Lead, 2)
                .input(OrePrefix.wireGtQuadruple, Materials.RedAlloy, 4)
                .input(OrePrefix.plateDouble, Materials.WroughtIron)
                .outputs(ENERGY_INPUT_HATCH_ADJUSTABLE[GTValues.ULV].getStackForm())
                .duration(100).EUt(7).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(ADJUSTABLE_TRANSFORMER[GTValues.LV].getStackForm())
                .inputs(ENERGY_INPUT_HATCH[GTValues.LV].getStackForm())
                .inputs(MetaItems.VOLTAGE_COIL_LV.getStackForm(2))
                .input(OrePrefix.cableGtSingle, Materials.Tin, 2)
                .input(OrePrefix.wireGtQuadruple, Materials.Copper, 4)
                .input(OrePrefix.plateDouble, Materials.Steel)
                .outputs(ENERGY_INPUT_HATCH_ADJUSTABLE[GTValues.LV].getStackForm())
                .duration(100).EUt(30).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(ADJUSTABLE_TRANSFORMER[GTValues.MV].getStackForm())
                .inputs(ENERGY_INPUT_HATCH[GTValues.MV].getStackForm())
                .inputs(MetaItems.VOLTAGE_COIL_MV.getStackForm(2))
                .inputs(MetaItems.SMALL_COIL.getStackForm(2))
                .input(OrePrefix.wireGtQuadruple, Materials.Cupronickel, 4)
                .input(OrePrefix.plateDouble, Materials.Aluminium)
                .outputs(ENERGY_INPUT_HATCH_ADJUSTABLE[GTValues.MV].getStackForm())
                .duration(100).EUt(120).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(ADJUSTABLE_TRANSFORMER[GTValues.HV].getStackForm())
                .inputs(ENERGY_INPUT_HATCH[GTValues.HV].getStackForm())
                .inputs(MetaItems.VOLTAGE_COIL_HV.getStackForm(2))
                .inputs(MetaItems.ULTRA_LOW_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                .input(OrePrefix.wireGtQuadruple, Materials.Kanthal, 4)
                .input(OrePrefix.plateDouble, Materials.StainlessSteel)
                .outputs(ENERGY_INPUT_HATCH_ADJUSTABLE[GTValues.HV].getStackForm())
                .duration(100).EUt(480).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(ADJUSTABLE_TRANSFORMER[GTValues.EV].getStackForm())
                .inputs(ENERGY_INPUT_HATCH[GTValues.EV].getStackForm())
                .inputs(MetaItems.VOLTAGE_COIL_EV.getStackForm(2))
                .inputs(MetaItems.LOW_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                .input(OrePrefix.wireGtQuadruple, Materials.Nichrome, 4)
                .input(OrePrefix.plateDouble, Materials.Titanium)
                .outputs(ENERGY_INPUT_HATCH_ADJUSTABLE[GTValues.EV].getStackForm())
                .duration(100).EUt(1920).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(ADJUSTABLE_TRANSFORMER[GTValues.IV].getStackForm())
                .inputs(ENERGY_INPUT_HATCH[GTValues.IV].getStackForm())
                .inputs(MetaItems.VOLTAGE_COIL_IV.getStackForm(2))
                .inputs(MetaItems.POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                .input(OrePrefix.wireGtQuadruple, Materials.TungstenSteel, 4)
                .input(OrePrefix.plateDouble, Materials.TungstenSteel)
                .outputs(ENERGY_INPUT_HATCH_ADJUSTABLE[GTValues.IV].getStackForm())
                .duration(100).EUt(7680).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(ADJUSTABLE_TRANSFORMER[GTValues.LuV].getStackForm())
                .inputs(ENERGY_INPUT_HATCH[GTValues.LuV].getStackForm())
                .inputs(MetaItems.VOLTAGE_COIL_LUV.getStackForm(2))
                .inputs(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                .input(OrePrefix.wireGtQuadruple, Materials.HSSG, 4)
                .input(OrePrefix.plateDouble, Materials.Chrome)
                .outputs(ENERGY_INPUT_HATCH_ADJUSTABLE[GTValues.LuV].getStackForm())
                .duration(100).EUt(32768).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(ADJUSTABLE_TRANSFORMER[GTValues.ZPM].getStackForm())
                .inputs(ENERGY_INPUT_HATCH[GTValues.ZPM].getStackForm())
                .inputs(MetaItems.VOLTAGE_COIL_ZPM.getStackForm(2))
                .inputs(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                .input(OrePrefix.wireGtQuadruple, Materials.Naquadah, 4)
                .input(OrePrefix.plateDouble, Materials.Iridium)
                .outputs(ENERGY_INPUT_HATCH_ADJUSTABLE[GTValues.ZPM].getStackForm())
                .duration(100).EUt(131072).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(ADJUSTABLE_TRANSFORMER[GTValues.UV].getStackForm())
                .inputs(ENERGY_INPUT_HATCH[GTValues.UV].getStackForm())
                .inputs(MetaItems.VOLTAGE_COIL_UV.getStackForm(2))
                .inputs(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                .input(OrePrefix.wireGtQuadruple, Materials.NaquadahAlloy, 4)
                .input(OrePrefix.plateDouble, Materials.Osmium)
                .outputs(ENERGY_INPUT_HATCH_ADJUSTABLE[GTValues.UV].getStackForm())
                .duration(100).EUt(524288).buildAndRegister();

        // Adjustable Output Hatches

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(ADJUSTABLE_TRANSFORMER[GTValues.ULV].getStackForm())
                .inputs(ENERGY_OUTPUT_HATCH[GTValues.ULV].getStackForm())
                .inputs(MetaItems.VOLTAGE_COIL_ULV.getStackForm(2))
                .input(OrePrefix.cableGtSingle, Materials.Lead, 2)
                .input(OrePrefix.wireGtQuadruple, Materials.RedAlloy, 4)
                .input(OrePrefix.plateDouble, Materials.WroughtIron)
                .outputs(ENERGY_OUTPUT_HATCH_ADJUSTABLE[GTValues.ULV].getStackForm())
                .duration(100).EUt(7).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(ADJUSTABLE_TRANSFORMER[GTValues.LV].getStackForm())
                .inputs(ENERGY_OUTPUT_HATCH[GTValues.LV].getStackForm())
                .inputs(MetaItems.VOLTAGE_COIL_LV.getStackForm(2))
                .input(OrePrefix.cableGtSingle, Materials.Tin, 2)
                .input(OrePrefix.wireGtQuadruple, Materials.Copper, 4)
                .input(OrePrefix.plateDouble, Materials.Steel)
                .outputs(ENERGY_OUTPUT_HATCH_ADJUSTABLE[GTValues.LV].getStackForm())
                .duration(100).EUt(30).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(ADJUSTABLE_TRANSFORMER[GTValues.MV].getStackForm())
                .inputs(ENERGY_OUTPUT_HATCH[GTValues.MV].getStackForm())
                .inputs(MetaItems.VOLTAGE_COIL_MV.getStackForm(2))
                .inputs(MetaItems.SMALL_COIL.getStackForm(2))
                .input(OrePrefix.wireGtQuadruple, Materials.Cupronickel, 4)
                .input(OrePrefix.plateDouble, Materials.Aluminium)
                .outputs(ENERGY_OUTPUT_HATCH_ADJUSTABLE[GTValues.MV].getStackForm())
                .duration(100).EUt(120).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(ADJUSTABLE_TRANSFORMER[GTValues.HV].getStackForm())
                .inputs(ENERGY_OUTPUT_HATCH[GTValues.HV].getStackForm())
                .inputs(MetaItems.VOLTAGE_COIL_HV.getStackForm(2))
                .inputs(MetaItems.ULTRA_LOW_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                .input(OrePrefix.wireGtQuadruple, Materials.Kanthal, 4)
                .input(OrePrefix.plateDouble, Materials.StainlessSteel)
                .outputs(ENERGY_OUTPUT_HATCH_ADJUSTABLE[GTValues.HV].getStackForm())
                .duration(100).EUt(480).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(ADJUSTABLE_TRANSFORMER[GTValues.EV].getStackForm())
                .inputs(ENERGY_OUTPUT_HATCH[GTValues.EV].getStackForm())
                .inputs(MetaItems.VOLTAGE_COIL_EV.getStackForm(2))
                .inputs(MetaItems.LOW_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                .input(OrePrefix.wireGtQuadruple, Materials.Nichrome, 4)
                .input(OrePrefix.plateDouble, Materials.Titanium)
                .outputs(ENERGY_OUTPUT_HATCH_ADJUSTABLE[GTValues.EV].getStackForm())
                .duration(100).EUt(1920).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(ADJUSTABLE_TRANSFORMER[GTValues.IV].getStackForm())
                .inputs(ENERGY_OUTPUT_HATCH[GTValues.IV].getStackForm())
                .inputs(MetaItems.VOLTAGE_COIL_IV.getStackForm(2))
                .inputs(MetaItems.POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                .input(OrePrefix.wireGtQuadruple, Materials.TungstenSteel, 4)
                .input(OrePrefix.plateDouble, Materials.TungstenSteel)
                .outputs(ENERGY_OUTPUT_HATCH_ADJUSTABLE[GTValues.IV].getStackForm())
                .duration(100).EUt(7680).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(ADJUSTABLE_TRANSFORMER[GTValues.LuV].getStackForm())
                .inputs(ENERGY_OUTPUT_HATCH[GTValues.LuV].getStackForm())
                .inputs(MetaItems.VOLTAGE_COIL_LUV.getStackForm(2))
                .inputs(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                .input(OrePrefix.wireGtQuadruple, Materials.HSSG, 4)
                .input(OrePrefix.plateDouble, Materials.Chrome)
                .outputs(ENERGY_OUTPUT_HATCH_ADJUSTABLE[GTValues.LuV].getStackForm())
                .duration(100).EUt(32768).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(ADJUSTABLE_TRANSFORMER[GTValues.ZPM].getStackForm())
                .inputs(ENERGY_OUTPUT_HATCH[GTValues.ZPM].getStackForm())
                .inputs(MetaItems.VOLTAGE_COIL_ZPM.getStackForm(2))
                .inputs(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                .input(OrePrefix.wireGtQuadruple, Materials.Naquadah, 4)
                .input(OrePrefix.plateDouble, Materials.Iridium)
                .outputs(ENERGY_OUTPUT_HATCH_ADJUSTABLE[GTValues.ZPM].getStackForm())
                .duration(100).EUt(131072).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(ADJUSTABLE_TRANSFORMER[GTValues.UV].getStackForm())
                .inputs(ENERGY_OUTPUT_HATCH[GTValues.UV].getStackForm())
                .inputs(MetaItems.VOLTAGE_COIL_UV.getStackForm(2))
                .inputs(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                .input(OrePrefix.wireGtQuadruple, Materials.NaquadahAlloy, 4)
                .input(OrePrefix.plateDouble, Materials.Osmium)
                .outputs(ENERGY_OUTPUT_HATCH_ADJUSTABLE[GTValues.UV].getStackForm())
                .duration(100).EUt(524288).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(HULL[GTValues.LV].getStackForm())
                .circuitMeta(1)
                .outputs(MAINTENANCE_HATCH.getStackForm())
                .duration(100).EUt(30).buildAndRegister();
    }
}
