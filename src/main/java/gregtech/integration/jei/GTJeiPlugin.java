package gregtech.integration.jei;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IControllable;
import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.capability.impl.FuelRecipeLogic;
import gregtech.api.gui.impl.ModularUIGuiHandler;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.recipes.machines.FuelRecipeMap;
import gregtech.api.recipes.machines.RecipeMapFurnace;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoCategory;
import gregtech.integration.jei.recipe.*;
import gregtech.integration.jei.recipe.fuel.FuelRecipeMapCategory;
import gregtech.integration.jei.recipe.fuel.GTFuelRecipeWrapper;
import gregtech.integration.jei.recipe.primitive.*;
import gregtech.integration.jei.utils.CustomItemReturnRecipeWrapper;
import gregtech.integration.jei.utils.MachineSubtypeHandler;
import gregtech.integration.jei.utils.MetaItemSubtypeHandler;
import gregtech.loaders.recipe.CustomItemReturnShapedOreRecipeRecipe;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@JEIPlugin
public class GTJeiPlugin implements IModPlugin {

    public static IIngredientRegistry ingredientRegistry;

    @Override
    public void registerItemSubtypes(@Nonnull ISubtypeRegistry subtypeRegistry) {
        MetaItemSubtypeHandler subtype = new MetaItemSubtypeHandler();
        for (MetaItem<?> metaItem : MetaItems.ITEMS) {
            subtypeRegistry.registerSubtypeInterpreter(metaItem, subtype);
        }
        subtypeRegistry.registerSubtypeInterpreter(Item.getItemFromBlock(MetaBlocks.MACHINE), new MachineSubtypeHandler());
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new IntCircuitCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new MultiblockInfoCategory(registry.getJeiHelpers()));
        for (RecipeMap<?> recipeMap : RecipeMap.getRecipeMaps()) {
            registry.addRecipeCategories(new RecipeMapCategory(recipeMap, registry.getJeiHelpers().getGuiHelper()));
        }
        for (FuelRecipeMap fuelRecipeMap : FuelRecipeMap.getRecipeMaps()) {
            registry.addRecipeCategories(new FuelRecipeMapCategory(fuelRecipeMap, registry.getJeiHelpers().getGuiHelper()));
        }
        registry.addRecipeCategories(new OreByProductCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void register(IModRegistry registry) {
        IJeiHelpers jeiHelpers = registry.getJeiHelpers();

        registry.addRecipes(IntCircuitRecipeWrapper.create(), IntCircuitCategory.UID);
        MultiblockInfoCategory.registerRecipes(registry);
        registry.handleRecipes(CustomItemReturnShapedOreRecipeRecipe.class, recipe -> new CustomItemReturnRecipeWrapper(jeiHelpers, recipe), VanillaRecipeCategoryUid.CRAFTING);
        registry.addRecipeRegistryPlugin(new FacadeRegistryPlugin());

        ModularUIGuiHandler modularUIGuiHandler = new ModularUIGuiHandler(jeiHelpers.recipeTransferHandlerHelper());
        registry.addAdvancedGuiHandlers(modularUIGuiHandler);
        registry.addGhostIngredientHandler(modularUIGuiHandler.getGuiContainerClass(), modularUIGuiHandler);
        registry.getRecipeTransferRegistry().addRecipeTransferHandler(modularUIGuiHandler, VanillaRecipeCategoryUid.CRAFTING);

        for (RecipeMap<?> recipeMap : RecipeMap.getRecipeMaps()) {
            List<GTRecipeWrapper> recipesList = recipeMap.getRecipeList()
                .stream().filter(recipe -> !recipe.isHidden() && recipe.hasValidInputsForDisplay())
                .map(r -> new GTRecipeWrapper(recipeMap, r))
                .collect(Collectors.toList());
            registry.addRecipes(recipesList, GTValues.MODID + ":" + recipeMap.unlocalizedName);
        }

        for (FuelRecipeMap fuelRecipeMap : FuelRecipeMap.getRecipeMaps()) {
            List<GTFuelRecipeWrapper> recipeList = fuelRecipeMap.getRecipeList().stream()
                .map(GTFuelRecipeWrapper::new)
                .collect(Collectors.toList());
            registry.addRecipes(recipeList, GTValues.MODID + ":" + fuelRecipeMap.unlocalizedName);
        }

        for (ResourceLocation metaTileEntityId : GregTechAPI.META_TILE_ENTITY_REGISTRY.getKeys()) {
            MetaTileEntity metaTileEntity = GregTechAPI.META_TILE_ENTITY_REGISTRY.getObject(metaTileEntityId);
            assert metaTileEntity != null;
            if (metaTileEntity.getCapability(GregtechTileCapabilities.CAPABILITY_CONTROLLABLE, null) != null) {
                IControllable workableCapability = metaTileEntity.getCapability(GregtechTileCapabilities.CAPABILITY_CONTROLLABLE, null);

                if (workableCapability instanceof AbstractRecipeLogic) {
                    RecipeMap<?> recipeMap = ((AbstractRecipeLogic) workableCapability).recipeMap;
                    registry.addRecipeCatalyst(metaTileEntity.getStackForm(), GTValues.MODID + ":" + recipeMap.unlocalizedName);
                    if (recipeMap instanceof RecipeMapFurnace) {
                        registry.addRecipeCatalyst(metaTileEntity.getStackForm(), VanillaRecipeCategoryUid.SMELTING);
                    }
                } else if (workableCapability instanceof FuelRecipeLogic) {
                    FuelRecipeMap recipeMap = ((FuelRecipeLogic) workableCapability).recipeMap;
                    registry.addRecipeCatalyst(metaTileEntity.getStackForm(), GTValues.MODID + ":" + recipeMap.unlocalizedName);
                }
            }
        }

        for (MetaTileEntity breweryTile : MetaTileEntities.BREWERY) {
            registry.addRecipeCatalyst(breweryTile.getStackForm(), VanillaRecipeCategoryUid.BREWING);
        }

        String semiFluidMapId = GTValues.MODID + ":" + RecipeMaps.SEMI_FLUID_GENERATOR_FUELS.getUnlocalizedName();
        registry.addRecipeCatalyst(MetaTileEntities.LARGE_BRONZE_BOILER.getStackForm(), semiFluidMapId);
        registry.addRecipeCatalyst(MetaTileEntities.LARGE_STEEL_BOILER.getStackForm(), semiFluidMapId);
        registry.addRecipeCatalyst(MetaTileEntities.LARGE_TITANIUM_BOILER.getStackForm(), semiFluidMapId);
        registry.addRecipeCatalyst(MetaTileEntities.LARGE_TUNGSTENSTEEL_BOILER.getStackForm(), semiFluidMapId);

        registry.addIngredientInfo(Objects.requireNonNull(Materials.Air.getFluid(1000)), VanillaTypes.FLUID, I18n.format("gregtech.machine.air_collector.jei_description"));

        List<OreByProduct> oreByproductList = new CopyOnWriteArrayList<>();
        for (Material material : Material.MATERIAL_REGISTRY) {
            if (material instanceof DustMaterial && OreDictUnifier.get(OrePrefix.ore, material) != ItemStack.EMPTY) {
                oreByproductList.add(new OreByProduct((DustMaterial) material));
            }
        }
        String oreByProductId = GTValues.MODID + ":" + "ore_by_product";
        registry.addRecipes(oreByproductList, oreByProductId);
        for (MetaTileEntity machine : MetaTileEntities.MACERATOR)
            registry.addRecipeCatalyst(machine.getStackForm(), oreByProductId);
        for (MetaTileEntity machine : MetaTileEntities.ORE_WASHER)
            registry.addRecipeCatalyst(machine.getStackForm(), oreByProductId);
        for (MetaTileEntity machine : MetaTileEntities.CENTRIFUGE)
            registry.addRecipeCatalyst(machine.getStackForm(), oreByProductId);
        for (MetaTileEntity machine : MetaTileEntities.THERMAL_CENTRIFUGE)
            registry.addRecipeCatalyst(machine.getStackForm(), oreByProductId);
        for (MetaTileEntity machine : MetaTileEntities.CHEMICAL_BATH)
            registry.addRecipeCatalyst(machine.getStackForm(), oreByProductId);

        ingredientRegistry = registry.getIngredientRegistry();
        for (int i = 0; i <= IntCircuitIngredient.CIRCUIT_MAX; i++) {
            registry.addIngredientInfo(IntCircuitIngredient.getIntegratedCircuit(i), VanillaTypes.ITEM,
                "metaitem.circuit.integrated.jei_description");
        }

        registry.addRecipeCatalyst(MetaTileEntities.WORKBENCH.getStackForm(), VanillaRecipeCategoryUid.CRAFTING);

        for (MetaTileEntity machine : MetaTileEntities.FLUID_CANNER) {
            registry.addIngredientInfo(machine.getStackForm(), VanillaTypes.ITEM,
                "gregtech.machine.fluid_canner.jei_description");
        }
    }
}
