package com.lyaiya.softcode.util

import com.google.common.base.*
import com.lyaiya.softcode.constant.*

fun getTranslationKey(modid: String, keyPrefix: String, vararg keySections: String) = buildString {
    append(keyPrefix)
    append(".$modid")
    for (keySection in keySections) {
        append(".$keySection")
    }
}

fun Class<*>.getReplacedTranslationKey(modid: String, classSuffix: String, keySection: String? = null): String {
    val finalKeySection = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, keySection ?: classSuffix)
    val replacedString = this.simpleName.replace(classSuffix, "")
    val lowerUnderscoreString = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, replacedString)
    val key = getTranslationKey(modid, KeyConstant.GUI, finalKeySection)
    return "$key.$lowerUnderscoreString"
}
