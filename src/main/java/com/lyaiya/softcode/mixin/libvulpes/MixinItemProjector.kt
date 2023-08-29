package com.lyaiya.softcode.mixin.libvulpes

import com.lyaiya.softcode.constant.KeyConstant
import com.lyaiya.softcode.constant.ModIdConstant
import com.lyaiya.softcode.util.createTranslationKey
import net.minecraft.client.resources.I18n
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.Constant
import org.spongepowered.asm.mixin.injection.ModifyConstant
import zmaster587.libVulpes.items.ItemProjector

@Mixin(ItemProjector::class, remap = false)
abstract class MixinItemProjector {
    @ModifyConstant(
        method = ["addInformation"],
        constant = [Constant(stringValue = "Shift right-click: opens machine selection interface")]
    )
    private fun modifyConstantAddInformation0(text: String): String {
        return I18n.format(createTranslationKey(ModIdConstant.LIB_VULPES, KeyConstant.TOOLTIP, "projector", "shift_right_click"))
    }

    @ModifyConstant(
        method = ["addInformation"],
        constant = [Constant(stringValue = "Shift-scroll: moves cross-section")]
    )
    private fun modifyConstantAddInformation1(text: String): String {
        return I18n.format(createTranslationKey(ModIdConstant.LIB_VULPES, KeyConstant.TOOLTIP, "projector", "shift_scroll"))
    }
}