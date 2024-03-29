package gregtech.common.blocks.surfacerock;

import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TileEntitySurfaceRock extends TileEntity {

    private Material material = Materials.Aluminium;
    private List<Material> undergroundMaterials = new ArrayList<>();

    @SideOnly(Side.CLIENT)
    public Object cachedModel;
    @SideOnly(Side.CLIENT)
    public Object cacheKey;

    public Material getMaterial() {
        return material;
    }

    public List<Material> getUndergroundMaterials() {
        return undergroundMaterials;
    }

    public void setData(Material material, Collection<Material> undergroundMaterials) {
        this.material = material;
        this.undergroundMaterials = new ArrayList<>(undergroundMaterials);
        markDirty();
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(getPos(), 0, writeToNBT(new NBTTagCompound()));
    }

    @Override
    public void onDataPacket(@Nonnull NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    @Nonnull
    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    public void readFromNBT(@Nonnull NBTTagCompound compound) {
        super.readFromNBT(compound);
        Material material = GregTechAPI.MATERIAL_REGISTRY.getObject(compound.getString("Material"));
        this.material = material == null ? Materials.Aluminium : material;

        for (NBTBase undergroundMaterialNBTBase : compound.getTagList("UndergroundMaterials", NBT.TAG_STRING)) {
            undergroundMaterials.add(GregTechAPI.MATERIAL_REGISTRY.getObject(((NBTTagString) undergroundMaterialNBTBase).getString()));
        }
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(@Nonnull NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setString("Material", GregTechAPI.MATERIAL_REGISTRY.getNameForObject(material));
        NBTTagList tagList = new NBTTagList();
        this.undergroundMaterials.forEach(it ->
                tagList.appendTag(new NBTTagString(GregTechAPI.MATERIAL_REGISTRY.getNameForObject(it))));
        compound.setTag("UndergroundMaterials", tagList);
        return compound;
    }
}
