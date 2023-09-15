package com.lyaiya.softcode.mixin.libvulpes;

import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslationKeyKt;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import zmaster587.libVulpes.items.ItemLinker;

@Mixin(
        value = ItemLinker.class,
        remap = false
)
abstract class MixinItemLinker {
    @ModifyConstant(
            method = "addInformation",
            constant = @Constant(stringValue = "Coords unset!")
    )
    private String modifyConstantCoordsUnset(String constant) {
        String key = TranslationKeyKt.create(ModIdConstant.LIB_VULPES, KeyConstant.TOOLTIP, "linker", "coords_unset");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "addInformation",
            constant = @Constant(stringValue = "X: ")
    )
    private String modifyConstantX(String constant) {
        String key = TranslationKeyKt.create(ModIdConstant.LIB_VULPES, KeyConstant.TOOLTIP, "linker", "x");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "addInformation",
            constant = @Constant(stringValue = "Y: ")
    )
    private String modifyConstantY(String constant) {
        String key = TranslationKeyKt.create(ModIdConstant.LIB_VULPES, KeyConstant.TOOLTIP, "linker", "y");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "addInformation",
            constant = @Constant(stringValue = "Z: ")
    )
    private String modifyConstantZ(String constant) {
        String key = TranslationKeyKt.create(ModIdConstant.LIB_VULPES, KeyConstant.TOOLTIP, "linker", "z");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "addInformation",
            constant = @Constant(stringValue = "Dim: ")
    )
    private String modifyConstantDim(String constant) {
        String key = TranslationKeyKt.create(ModIdConstant.LIB_VULPES, KeyConstant.TOOLTIP, "linker", "dim");
        return I18n.format(key);
    }
}
