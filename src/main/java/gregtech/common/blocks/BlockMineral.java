package gregtech.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;

public class BlockMineral extends StoneBlock<BlockMineral.MineralVariant> {

    public BlockMineral() {
        super(Material.ROCK);
        setUnlocalizedName("mineral");
        setHardness(3.0f);
        setResistance(6.0f);
        setSoundType(SoundType.STONE);
        setHarvestLevel("pickaxe", 1);
        setDefaultState(withVariant(
            MineralVariant.MARBLE,
            ChiselingVariant.NORMAL));
    }

    public enum MineralVariant implements IStringSerializable {

        MARBLE("marble"),
        MARBLE_BRICKS("marble_bricks"),
        BASALT("basalt"),
        BASALT_BRICKS("basalt_bricks");

        private final String name;

        MineralVariant(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

    }

}
