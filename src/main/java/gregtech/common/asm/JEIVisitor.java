package gregtech.common.asm;

import gregtech.common.asm.util.ObfMapping;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class JEIVisitor extends MethodVisitor implements Opcodes {

    public static final String TARGET_CLASS_NAME = "mezz/jei/plugins/vanilla/ingredients/fluid/FluidStackRenderer";
    public static final ObfMapping TARGET_METHOD = new ObfMapping(TARGET_CLASS_NAME, "getTooltip", targetSignature());

    private static final String FLUID_TOOLTIP_OWNER = "gregtech/integration/jei/utils/JEIHooks";
    private static final String FLUID_TOOLTIP_SIGNATURE = tooltipSignature();
    private static final String FLUID_TOOLTIP_METHOD_NAME = "addFluidTooltip";

    public JEIVisitor(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

    boolean hasAddedFluidName = false;

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        super.visitMethodInsn(opcode, owner, name, desc, itf);
        if (!hasAddedFluidName && opcode == INVOKEINTERFACE && name.equals("add")) {
            mv.visitVarInsn(ALOAD, 4); // List<String> tooltip
            mv.visitVarInsn(ALOAD, 2); // FluidStack fluidStack
            mv.visitMethodInsn(INVOKESTATIC, FLUID_TOOLTIP_OWNER, FLUID_TOOLTIP_METHOD_NAME, FLUID_TOOLTIP_SIGNATURE, false);
        }
    }

    // public List<String> getTooltip(Minecraft minecraft, FluidStack fluidStack, ITooltipFlag tooltipFlag)
    private static String targetSignature() {

        return  "(" +
                "Lnet/minecraft/client/Minecraft;" + // Minecraft
                "Lnet/minecraftforge/fluids/FluidStack;" + // FluidStack
                "Lnet/minecraft/client/util/ITooltipFlag;" + // ITooltipFlag
                ")Ljava/util/List;"; // return List<String>
    }

    // public void addFluidTooltip(List<String> tooltip, Object ingredient)
    private static String tooltipSignature() {

        return  "(" +
                "Ljava/util/List;" + // List<String>
                // "Ljava/lang/Object;" + // Object
                "Lnet/minecraftforge/fluids/FluidStack;" + // FluidStack
                ")V"; // return void
    }
}
