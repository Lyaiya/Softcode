package com.lyaiya.softcode.util

import com.google.common.base.*

object TranslationKeyConstants {
    const val GUI = "gui"
    const val MATERIAL = "material"
}

object ClassConstants {
    const val RECIPE_CATEGORY = "RecipeCategory"
    const val RECIPE_WRAPPER = "RecipeWrapper"
}

fun getTranslationKey(modid: String, keyPrefix: String, keySection: String) = "$keyPrefix.$modid.$keySection"

fun Class<*>.getReplacedTranslationKey(modid: String, classSuffix: String, keySection: String? = null): String {
    val finalKeySection = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, keySection ?: classSuffix)
    val replacedString = this.simpleName.replace(classSuffix, "")
    val lowerUnderscoreString = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, replacedString)
    val key = getTranslationKey(modid, TranslationKeyConstants.GUI, finalKeySection)
    return "$key.$lowerUnderscoreString"
}
