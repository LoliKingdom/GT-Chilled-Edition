package gregtech.api.net;

import gregtech.common.metatileentities.MetaTileEntityClipboard;
import net.minecraft.network.PacketBuffer;

import java.util.function.Consumer;

public class PacketClipboardUIWidgetUpdate implements NetworkHandler.Packet {
    public MetaTileEntityClipboard clipboard;
    public int id;
    public Consumer<PacketBuffer> payloadWriter;
    public PacketBuffer buf;

    public PacketClipboardUIWidgetUpdate(MetaTileEntityClipboard clipboard, int id, Consumer<PacketBuffer> payloadWriter) {
        this.clipboard = clipboard;
        this.id = id;
        this.payloadWriter = payloadWriter;
    }

    public PacketClipboardUIWidgetUpdate(MetaTileEntityClipboard clipboard, PacketBuffer buf) {
        this(clipboard, buf.readVarInt(), null);
        this.buf = buf;
    }
}
