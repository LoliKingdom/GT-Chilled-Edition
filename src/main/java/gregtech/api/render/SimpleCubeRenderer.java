package gregtech.api.render;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.texture.TextureUtils.IIconRegister;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import gregtech.api.GTValues;
import gregtech.common.ConfigHolder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nullable;

public class SimpleCubeRenderer implements ICubeRenderer, IIconRegister {

    private final String basePath;
    private final boolean hasEmissive;

    @SideOnly(Side.CLIENT)
    private TextureAtlasSprite sprite;

    @Nullable
    @SideOnly(Side.CLIENT)
    private TextureAtlasSprite spriteEmissive;

    public SimpleCubeRenderer(String basePath) {
        this(basePath, false);
    }

    public SimpleCubeRenderer(String basePath, boolean hasEmissive) {
        this.basePath = basePath;
        this.hasEmissive = hasEmissive;
        Textures.iconRegisters.add(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(TextureMap textureMap) {
        this.sprite = textureMap.registerSprite(new ResourceLocation(GTValues.MODID, "blocks/" + basePath));
        if (hasEmissive) {
            this.spriteEmissive = textureMap.registerSprite(new ResourceLocation(GTValues.MODID, "blocks/" + basePath + "_emissive"));
        }
    }

    @SideOnly(Side.CLIENT)
    public void renderSided(EnumFacing side, Matrix4 translation, Cuboid6 bounds, CCRenderState renderState, IVertexOperation[] pipeline) {
        Textures.renderFace(renderState, translation, pipeline, side, bounds, sprite);
        if (spriteEmissive != null) {
            if (ConfigHolder.U.clientConfig.emissiveTextures) {
                IVertexOperation[] lightPipeline = ArrayUtils.add(pipeline, new LightMapOperation(240, 240));
                Textures.renderFace(renderState, translation, lightPipeline, side, bounds, spriteEmissive);
            } else Textures.renderFace(renderState, translation, pipeline, side, bounds, spriteEmissive);
        }
    }

    @SideOnly(Side.CLIENT)
    public void renderSided(EnumFacing side, CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        renderSided(side, translation, Cuboid6.full, renderState, pipeline);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getParticleSprite() {
        return sprite;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void render(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline, Cuboid6 bounds) {
        for (EnumFacing side : EnumFacing.values()) {
            renderSided(side, translation, bounds, renderState, pipeline);
        }
    }
}
