package gregtech.api.metatileentity.multiblock;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.capability.impl.SteamMultiblockRecipeLogic;
import gregtech.api.metatileentity.MTETrait;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.common.ConfigHolder;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class RecipeMapSteamMultiblockController extends MultiblockWithDisplayBase {

    protected static final double CONVERSION_RATE = ConfigHolder.U.multiblockSteamToEU;

    public final RecipeMap<?> recipeMap;
    protected SteamMultiblockRecipeLogic recipeMapWorkable;

    protected IItemHandlerModifiable inputInventory;
    protected IItemHandlerModifiable outputInventory;
    protected IMultipleTankHandler steamFluidTank;

    public RecipeMapSteamMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, double conversionRate) {
        super(metaTileEntityId);
        this.recipeMap = recipeMap;
        this.recipeMapWorkable = new SteamMultiblockRecipeLogic(this, recipeMap, steamFluidTank, conversionRate);
        resetTileAbilities();
    }

    public IItemHandlerModifiable getInputInventory() {
        return inputInventory;
    }

    public IItemHandlerModifiable getOutputInventory() {
        return outputInventory;
    }

    public IMultipleTankHandler getSteamFluidTank() {
        return steamFluidTank;
    }

    /**
     * Performs extra checks for validity of given recipe before multiblock
     * will start it's processing.
     */
    public boolean checkRecipe(Recipe recipe, boolean consumeIfProcess) {
        return true;
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        initializeAbilities();
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        resetTileAbilities();
    }

    @Override
    protected void updateFormedValid() {
        recipeMapWorkable.update();
    }

    private void initializeAbilities() {
        this.inputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.STEAM_IMPORT_ITEMS));
        this.outputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.STEAM_EXPORT_ITEMS));
        this.steamFluidTank = new FluidTankList(true, getAbilities(MultiblockAbility.STEAM));
    }

    private void resetTileAbilities() {
        this.inputInventory = new ItemStackHandler(0);
        this.outputInventory = new ItemStackHandler(0);
        this.steamFluidTank = new FluidTankList(true);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        if (isStructureFormed()) {
            IFluidTank steamFluidTank = recipeMapWorkable.getSteamFluidTankCombined();
            if (steamFluidTank != null && steamFluidTank.getCapacity() > 0) {
                int steamStored = steamFluidTank.getFluidAmount();
                textList.add(new TextComponentTranslation("gregtech.multiblock.steam.steam_stored", steamStored, steamFluidTank.getCapacity()));
            }

            if (!recipeMapWorkable.isWorkingEnabled()) {
                textList.add(new TextComponentTranslation("gregtech.multiblock.work_paused"));

            } else if (recipeMapWorkable.isActive()) {
                textList.add(new TextComponentTranslation("gregtech.multiblock.running"));
                int currentProgress = (int) (recipeMapWorkable.getProgressPercent() * 100);
                textList.add(new TextComponentTranslation("gregtech.multiblock.progress", currentProgress));
            } else {
                textList.add(new TextComponentTranslation("gregtech.multiblock.idling"));
            }

            if (recipeMapWorkable.isHasNotEnoughEnergy()) {
                textList.add(new TextComponentTranslation("gregtech.multiblock.steam.low_steam").setStyle(new Style().setColor(TextFormatting.RED)));
            }
        }
    }

    @Override
    protected boolean checkStructureComponents(List<IMultiblockPart> parts, Map<MultiblockAbility<Object>, List<Object>> abilities) {
        //basically check minimal requirements for inputs count
        int itemInputsCount = abilities.getOrDefault(MultiblockAbility.STEAM_IMPORT_ITEMS, Collections.emptyList())
                .stream().map(it -> (IItemHandler) it).mapToInt(IItemHandler::getSlots).sum();
        return itemInputsCount >= recipeMap.getMinInputs() &&
                abilities.containsKey(MultiblockAbility.STEAM);
    }

    @Override
    protected boolean shouldUpdate(MTETrait trait) {
        return !(trait instanceof SteamMultiblockRecipeLogic);
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        this.getFrontOverlay().render(renderState, translation, pipeline, getFrontFacing(), recipeMapWorkable.isActive());
    }
}
