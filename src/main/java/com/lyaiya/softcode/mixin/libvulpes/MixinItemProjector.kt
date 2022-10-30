package com.lyaiya.softcode.mixin.libvulpes

import com.lyaiya.softcode.constant.*
import com.lyaiya.softcode.util.*
import net.minecraft.client.resources.*
import org.spongepowered.asm.mixin.*
import org.spongepowered.asm.mixin.injection.*
import zmaster587.libVulpes.items.*

@Mixin(ItemProjector::class, remap = false)
abstract class MixinItemProjector {
    @ModifyConstant(
        method = ["addInformation"],
        constant = [Constant(stringValue = "Shift right-click: opens machine selection interface")]
    )
    private fun modifyConstantAddInformation0(text: String): String {
        return I18n.format(getTranslationKey(ModIdConstant.LIB_VULPES, KeyConstant.TOOLTIP, "projector", "shift_right_click"))
    }

    @ModifyConstant(
        method = ["addInformation"],
        constant = [Constant(stringValue = "Shift-scroll: moves cross-section")]
    )
    private fun modifyConstantAddInformation1(text: String): String {
        return I18n.format(getTranslationKey(ModIdConstant.LIB_VULPES, KeyConstant.TOOLTIP, "projector", "shift_scroll"))
    }
}