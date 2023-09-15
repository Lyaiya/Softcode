package com.lyaiya.softcode.util;

import com.google.common.base.CaseFormat;
import com.lyaiya.softcode.constant.KeyConstant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TranslateKeyUtil {
    public static @NotNull String getKey(String modid, String keyPrefix, @NotNull String... keySections) {
        StringBuilder sb = new StringBuilder();
        sb.append(keyPrefix);
        sb.append(".");
        sb.append(modid);
        for (String keySection : keySections) {
            sb.append(".");
            sb.append(keySection);
        }
        return sb.toString();
    }

    public static String getReplacedKey(@NotNull Class<?> clazz, String modid, String classSuffix, @Nullable String keySection) {
        String finalKeySection = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, keySection != null ? keySection : classSuffix);
        String replacedString = clazz.getSimpleName().replace(classSuffix, "");
        String lowerUnderscoreString = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, replacedString);
        String key = getKey(modid, KeyConstant.GUI, finalKeySection);
        return String.format("%s.%s", key, lowerUnderscoreString);
    }

    public static String getReplacedKey(Class<?> clazz, String modid, String classSuffix) {
        return getReplacedKey(clazz, modid, classSuffix, null);
    }
}
