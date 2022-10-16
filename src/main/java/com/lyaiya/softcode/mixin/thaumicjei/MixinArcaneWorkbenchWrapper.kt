package com.lyaiya.softcode.mixin.thaumicjei

import com.buuz135.thaumicjei.category.*
import com.lyaiya.softcode.*
import com.lyaiya.softcode.util.*
import net.minecraft.client.resources.*
import org.spongepowered.asm.mixin.*
import org.spongepowered.asm.mixin.injection.*

@Mixin(ArcaneWorkbenchCategory.ArcaneWorkbenchWrapper::class, remap = false)
abstract class MixinArcaneWorkbenchWrapper {
    @ModifyConstant(
        method = ["getTooltipStrings"],
        constant = [Constant(stringValue = "Vis Cost")]
    )
    private fun injectGetTooltipStrings(text: String): String {
        return I18n.format(getTranslationKey(ModId.THAUMIC_JEI, KeyConstants.GUI, "vis_cost"))
    }
}