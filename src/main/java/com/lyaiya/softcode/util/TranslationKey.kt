package com.lyaiya.softcode.util

import com.google.common.base.CaseFormat
import com.lyaiya.softcode.constant.KeyConstant

@JvmName("create")
fun createTranslationKey(modid: String, keyPrefix: String, vararg keySections: String) = buildString {
    append(keyPrefix)
    append(".$modid")
    for (keySection in keySections) {
        append(".$keySection")
    }
}

@JvmName("createReplaced")
@JvmOverloads
fun Class<*>.createReplacedTranslationKey(modid: String, classSuffix: String, keySection: String? = null): String {
    val finalKeySection = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, keySection ?: classSuffix)
    val replacedString = this.simpleName.replace(classSuffix, "")
    val lowerUnderscoreString = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, replacedString)
    val key = createTranslationKey(modid, KeyConstant.GUI, finalKeySection)
    return "$key.$lowerUnderscoreString"
}