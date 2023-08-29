package com.lyaiya.softcode.mixin.thaumicjei

import com.buuz135.thaumicjei.category.ArcaneWorkbenchCategory
import com.lyaiya.softcode.constant.KeyConstant
import com.lyaiya.softcode.constant.ModIdConstant
import com.lyaiya.softcode.util.createTranslationKey
import net.minecraft.client.resources.I18n
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.Constant
import org.spongepowered.asm.mixin.injection.ModifyConstant

@Mixin(ArcaneWorkbenchCategory.ArcaneWorkbenchWrapper::class, remap = false)
abstract class MixinArcaneWorkbenchWrapper {
    @ModifyConstant(
        method = ["getTooltipStrings"],
        constant = [Constant(stringValue = "Vis Cost")]
    )
    private fun injectGetTooltipStrings(text: String): String {
        return I18n.format(createTranslationKey(ModIdConstant.THAUMIC_JEI, KeyConstant.GUI, "vis_cost"))
    }
}