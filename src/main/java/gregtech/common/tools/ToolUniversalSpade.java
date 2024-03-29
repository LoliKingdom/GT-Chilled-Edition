package gregtech.common.tools;

import com.google.common.collect.ImmutableSet;
import gregtech.api.items.metaitem.MetaItem.MetaValueItem;
import gregtech.common.items.behaviors.CrowbarBehaviour;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

import java.util.Set;

public class ToolUniversalSpade extends ToolBase {

    private static final Set<String> SPADE_TOOL_CLASSES = ImmutableSet.of("shovel", "axe", "saw", "sword", "crowbar");

    @Override
    public int getToolDamagePerBlockBreak(ItemStack stack) {
        return 1;
    }

    @Override
    public int getToolDamagePerContainerCraft(ItemStack stack) {
        return 4;
    }

    @Override
    public int getToolDamagePerEntityAttack(ItemStack stack) {
        return 1;
    }

    @Override
    public float getBaseDamage(ItemStack stack) {
        return 3.0F;
    }

    @Override
    public float getDigSpeedMultiplier(ItemStack stack) {
        return 0.75F;
    }

    @Override
    public boolean canMineBlock(IBlockState block, ItemStack stack) {
        String tool = block.getBlock().getHarvestTool(block);
        return (tool != null && SPADE_TOOL_CLASSES.contains(tool)) ||
                block.getMaterial() == Material.SAND ||
                block.getMaterial() == Material.GRASS ||
                block.getMaterial() == Material.GROUND ||
                block.getMaterial() == Material.SNOW ||
                block.getMaterial() == Material.CLAY ||
                block.getMaterial() == Material.CRAFTED_SNOW ||
                block.getMaterial() == Material.LEAVES ||
                block.getMaterial() == Material.VINE ||
                block.getMaterial() == Material.WOOD ||
                block.getMaterial() == Material.CACTUS ||
                block.getMaterial() == Material.CIRCUITS ||
                block.getMaterial() == Material.GOURD ||
                block.getMaterial() == Material.WEB ||
                block.getMaterial() == Material.CLOTH ||
                block.getMaterial() == Material.CARPET ||
                block.getMaterial() == Material.PLANTS ||
                block.getMaterial() == Material.CAKE ||
                block.getMaterial() == Material.TNT ||
                block.getMaterial() == Material.SPONGE;
    }

    @Override
    public void onStatsAddedToTool(MetaValueItem item) {
        item.addComponents(new CrowbarBehaviour(DamageValues.DAMAGE_FOR_UNIVERSAL_SPADE));
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return SPADE_TOOL_CLASSES;
    }
}
