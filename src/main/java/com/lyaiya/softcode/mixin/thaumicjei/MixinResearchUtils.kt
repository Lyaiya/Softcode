@file:JvmName("MixinResearchUtils")
@file:Mixin(ResearchUtils::class, remap = false)

package com.lyaiya.softcode.mixin.thaumicjei

import com.buuz135.thaumicjei.util.ResearchUtils
import com.lyaiya.softcode.constant.KeyConstant
import com.lyaiya.softcode.constant.ModIdConstant
import com.lyaiya.softcode.util.createTranslationKey
import net.minecraft.client.resources.I18n
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.Constant
import org.spongepowered.asm.mixin.injection.ModifyConstant

@ModifyConstant(
    method = ["generateMissingResearchList"],
    constant = [Constant(stringValue = "Missing research:")]
)
private fun modifyConstantGenerateMissingResearchList(text: String): String {
    return I18n.format(createTranslationKey(ModIdConstant.THAUMIC_JEI, KeyConstant.GUI, "missing_research"))
}