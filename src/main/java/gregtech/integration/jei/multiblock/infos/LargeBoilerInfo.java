package gregtech.integration.jei.multiblock.infos;

import com.google.common.collect.Lists;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.common.metatileentities.multi.MetaTileEntityLargeBoiler;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumFacing;

import java.util.List;

public class LargeBoilerInfo extends MultiblockInfoPage {

    public final MetaTileEntityLargeBoiler boiler;

    public LargeBoilerInfo(MetaTileEntityLargeBoiler boiler) {
        this.boiler = boiler;
    }

    @Override
    public MultiblockControllerBase getController() {
        return boiler;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        return Lists.newArrayList(MultiblockShapeInfo.builder()
                .aisle("FXX", "CCC", "CCC", "CCC")
                .aisle("XXH", "SPC", "MPC", "CCC")
                .aisle("IXX", "COC", "CCC", "CCC")
                .where('S', boiler, EnumFacing.WEST)
                .where('P', boiler.boilerType.pipeState)
                .where('X', boiler.boilerType.fireboxState)
                .where('C', boiler.boilerType.casingState)
                .where('O', MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.MV], EnumFacing.SOUTH)
                .where('I', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.MV], EnumFacing.WEST)
                .where('F', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.MV], EnumFacing.WEST)
                .where('H', MetaTileEntities.MUFFLER_HATCH[GTValues.LV], EnumFacing.EAST)
                .where('M', maintenanceIfEnabled(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.BRONZE_BRICKS)), EnumFacing.WEST)
                .build());
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gregtech.multiblock.large_boiler.description")};
    }
}
