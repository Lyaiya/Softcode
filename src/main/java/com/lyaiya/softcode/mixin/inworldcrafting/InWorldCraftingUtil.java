package com.lyaiya.softcode.mixin.inworldcrafting;

import com.lyaiya.softcode.constant.ClassConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslateKeyUtil;

public class InWorldCraftingUtil {
    public static String getRecipeWrapperKey(Class<?> clazz) {
        return TranslateKeyUtil.getReplacedKey(clazz, ModIdConstant.IN_WORLD_CRAFTING, ClassConstant.RECIPE_WRAPPER, ClassConstant.RECIPE_CATEGORY);
    }
}
