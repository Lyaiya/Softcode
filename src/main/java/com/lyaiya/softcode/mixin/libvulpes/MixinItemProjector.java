package com.lyaiya.softcode.mixin.libvulpes;

import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslateKeyUtil;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import zmaster587.libVulpes.items.ItemProjector;

@Mixin(
        value = ItemProjector.class,
        remap = false
)
abstract class MixinItemProjector {
    @ModifyConstant(
            method = "addInformation",
            constant = @Constant(stringValue = "Shift right-click: opens machine selection interface")
    )
    private String modifyConstantShiftRightClick(String constant) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.LIB_VULPES, KeyConstant.TOOLTIP, "projector", "shift_right_click");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "addInformation",
            constant = @Constant(stringValue = "Shift-scroll: moves cross-section")
    )
    private String modifyConstantShiftScroll(String constant) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.LIB_VULPES, KeyConstant.TOOLTIP, "projector", "shift_scroll");
        return I18n.format(key);
    }
}
