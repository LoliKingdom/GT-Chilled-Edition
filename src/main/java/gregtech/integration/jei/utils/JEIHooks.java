package gregtech.integration.jei.utils;

import com.mojang.realmsclient.gui.ChatFormatting;
import gregtech.api.util.FluidTooltipUtil;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

/**
 * Class used for code injection into JEI.
 * Not intended for normal use within GTCE.
 */
@SuppressWarnings("unused")
public class JEIHooks {

    /**
     * This method is NOT intended to be called by GTCE.
     */
    public static void addFluidTooltip(List<String> tooltip, FluidStack ingredient) {
        String formula = FluidTooltipUtil.getFluidTooltip(ingredient.getFluid());
        if (formula != null && !formula.isEmpty()) {
            tooltip.add(ChatFormatting.GRAY + formula);
        }
    }
}
