package gregtech.common.metatileentities.multi.electric;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.common.blocks.BlockMetalCasing.MetalCasingType;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.electric.multiblockpart.MetaTileEntityMultiFluidHatch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static gregtech.api.util.RelativeDirection.*;

public class MetaTileEntityDistillationTower extends RecipeMapMultiblockController {

    public static MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.INPUT_ENERGY,
            MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.MAINTENANCE_HATCH
    };

    public MetaTileEntityDistillationTower(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.DISTILLATION_RECIPES);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityDistillationTower(metaTileEntityId);
    }

    @Override
    protected Function<BlockPos, Integer> multiblockPartSorter() {
        return BlockPos::getY; // todo this needs to be "relative up" with Freedom Wrench
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (isStructureFormed()) {
            FluidStack stackInTank = importFluids.drain(Integer.MAX_VALUE, false);
            if (stackInTank != null && stackInTank.amount > 0) {
                TextComponentTranslation fluidName = new TextComponentTranslation(stackInTank.getFluid().getUnlocalizedName(stackInTank));
                textList.add(new TextComponentTranslation("gregtech.multiblock.distillation_tower.distilling_fluid", fluidName));
            }
        }
        super.addDisplayText(textList);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        Predicate<PatternMatchContext> exactlyOneHatch = context -> context.getInt("HatchesAmount") == 1;
        return FactoryBlockPattern.start(RIGHT, FRONT, UP)
                .aisle("YSY", "YYY", "YYY")
                .aisle("XXX", "X#X", "XXX").setRepeatable(0, 11)
                .aisle("XXX", "XXX", "XXX")
                .where('S', selfPredicate())
                .where('Y', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('X', dtAbilityPartPredicate().or(maintenancePredicate(getCasingState())))
                .where('#', isAirPredicate())
                .validateLayer(1, exactlyOneHatch)
                .validateLayer(2, exactlyOneHatch)
                .build();
    }

    private Predicate<BlockWorldState> dtAbilityPartPredicate() {
        return countMatch("HatchesAmount", abilityPartPredicate(MultiblockAbility.EXPORT_FLUIDS)).and(tilePredicate((state, tile) -> {
            if (tile instanceof IMultiblockAbilityPart<?>) {
                MultiblockAbility<?> ability = ((IMultiblockAbilityPart<?>) tile).getAbility();
                return ability == MultiblockAbility.EXPORT_FLUIDS && !(tile instanceof MetaTileEntityMultiFluidHatch);
            }
            return false;
        }));
    }

    @Override
    protected boolean allowSameFluidFillForOutputs() {
        return false;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.CLEAN_STAINLESS_STEEL_CASING;
    }

    protected IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(MetalCasingType.STAINLESS_CLEAN);
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return Textures.DISTILLATION_TOWER_OVERLAY;
    }
}
