@file:JvmName("MixinResearchUtils")
@file:Mixin(ResearchUtils::class, remap = false)

package com.lyaiya.softcode.mixin.thaumicjei

import com.buuz135.thaumicjei.util.*
import com.lyaiya.softcode.*
import com.lyaiya.softcode.constant.*
import com.lyaiya.softcode.util.*
import net.minecraft.client.resources.*
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.*

@ModifyConstant(
    method = ["generateMissingResearchList"],
    constant = [Constant(stringValue = "Missing research:")]
)
private fun modifyConstantGenerateMissingResearchList(text: String): String {
    return I18n.format(getTranslationKey(ModIdConstant.THAUMIC_JEI, KeyConstant.GUI, "missing_research"))
}