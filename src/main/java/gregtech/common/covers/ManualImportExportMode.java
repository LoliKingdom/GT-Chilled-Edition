package gregtech.common.covers;

import net.minecraft.util.IStringSerializable;

public enum ManualImportExportMode implements IStringSerializable {

    DISABLED("cover.universal.manual_import_export.mode.disabled"),
    FILTERED("cover.universal.manual_import_export.mode.filtered"),
    UNFILTERED("cover.universal.manual_import_export.mode.unfiltered");

    public final String localeName;

    ManualImportExportMode(String localeName) {
        this.localeName = localeName;
    }

    @Override
    public String getName() {
        return localeName;
    }
}
