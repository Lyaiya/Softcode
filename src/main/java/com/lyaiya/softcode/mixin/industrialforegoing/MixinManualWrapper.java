package com.lyaiya.softcode.mixin.industrialforegoing;

import com.buuz135.industrial.jei.manual.ManualWrapper;
import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslationKeyKt;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(
        value = ManualWrapper.class,
        remap = false
)
abstract class MixinManualWrapper {
    @ModifyConstant(
            method = "<init>",
            constant = @Constant(stringValue = "Open Manual Entry")
    )
    private String modifyConstantInit(String constant) {
        return I18n.format(TranslationKeyKt.create(ModIdConstant.INDUSTRIAL_FOREGOING, KeyConstant.GUI, "open_manual_entry"));
    }
}
