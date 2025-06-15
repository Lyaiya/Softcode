package com.lyaiya.softcode.mixin.industrialforegoing;

import com.buuz135.industrial.api.book.gui.GUIBookBase;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.Util;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(
        value = GUIBookBase.class,
        remap = false
)
abstract class MixinGUIBookBase {
    @ModifyConstant(
            method = "initGui",
            constant = @Constant(stringValue = "Go back")
    )
    private String modifyConstantInitGui0(String constant) {
        String key = Util.getKey(ModIdConstant.INDUSTRIAL_FOREGOING, "manual", "go_back");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "initGui",
            constant = @Constant(stringValue = "Previous page")
    )
    private String modifyConstantInitGui1(String constant) {
        String key = Util.getKey(ModIdConstant.INDUSTRIAL_FOREGOING, "manual", "previous_page");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "initGui",
            constant = @Constant(stringValue = "Next page")
    )
    private String modifyConstantInitGui2(String constant) {
        String key = Util.getKey(ModIdConstant.INDUSTRIAL_FOREGOING, "manual", "next_page");
        return I18n.format(key);
    }
}
