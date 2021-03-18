package gregtech.api.capability.impl;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Efficiently delegates calls into multiple item handlers
 */
public class ItemHandlerList implements IItemHandlerModifiable {

    private final Int2ObjectMap<IItemHandler> handlerBySlotIndex = new Int2ObjectOpenHashMap<>();
    private final Reference2IntMap<IItemHandler> baseIndexOffset = new Reference2IntOpenHashMap<>();

    public ItemHandlerList(List<? extends IItemHandler> itemHandlerList) {
        int currentSlotIndex = 0;
        for (IItemHandler itemHandler : itemHandlerList) {
            if (baseIndexOffset.containsKey(itemHandler)) {
                throw new IllegalArgumentException("Attempted to add item handler " + itemHandler + " twice");
            }
            baseIndexOffset.put(itemHandler, currentSlotIndex);
            int slotsCount = itemHandler.getSlots();
            for (int slotIndex = 0; slotIndex < slotsCount; slotIndex++) {
                handlerBySlotIndex.put(currentSlotIndex + slotIndex, itemHandler);
            }
            currentSlotIndex += slotsCount;
        }
    }

    @Override
    public int getSlots() {
        return handlerBySlotIndex.size();
    }

    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
        IItemHandler itemHandler = handlerBySlotIndex.get(slot);
        if (!(itemHandler instanceof IItemHandlerModifiable))
            throw new UnsupportedOperationException("Handler " + itemHandler + " does not support this method");
        ((IItemHandlerModifiable) itemHandler).setStackInSlot(slot - baseIndexOffset.getInt(itemHandler), stack);
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        IItemHandler itemHandler = handlerBySlotIndex.get(slot);
        return itemHandler.getStackInSlot(slot - baseIndexOffset.getInt(itemHandler));
    }

    @Override
    public int getSlotLimit(int slot) {
        IItemHandler itemHandler = handlerBySlotIndex.get(slot);
        return itemHandler.getSlotLimit(slot - baseIndexOffset.getInt(itemHandler));
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        IItemHandler itemHandler = handlerBySlotIndex.get(slot);
        return itemHandler.insertItem(slot - baseIndexOffset.getInt(itemHandler), stack, simulate);
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        IItemHandler itemHandler = handlerBySlotIndex.get(slot);
        return itemHandler.extractItem(slot - baseIndexOffset.getInt(itemHandler), amount, simulate);
    }

}
