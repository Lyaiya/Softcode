package com.lyaiya.softcode.mixin.libvulpes

import com.lyaiya.softcode.constant.*
import com.lyaiya.softcode.util.*
import net.minecraft.client.resources.*
import org.spongepowered.asm.mixin.*
import org.spongepowered.asm.mixin.injection.*
import zmaster587.libVulpes.block.*

@Mixin(BlockMotor::class, remap = false)
abstract class MixinBlockMotor {
    @ModifyConstant(
        method = ["addInformation"],
        constant = [Constant(stringValue = "Machine Speed: %.2f")]
    )
    private fun modifyConstantAddInformation0(text: String): String {
        return I18n.format(getTranslationKey(ModIdConstant.LIB_VULPES, KeyConstant.TOOLTIP, "motor", "machine_speed")) + "%.2f"
    }
}