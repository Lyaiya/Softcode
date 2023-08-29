package com.lyaiya.softcode.mixin.inworldcrafting

import com.lyaiya.softcode.constant.ClassConstant
import com.lyaiya.softcode.constant.ModIdConstant
import com.lyaiya.softcode.util.createReplacedTranslationKey

inline val Class<*>.recipeWrapperKey
    get() = createReplacedTranslationKey(ModIdConstant.IN_WORLD_CRAFTING, ClassConstant.RECIPE_WRAPPER, ClassConstant.RECIPE_CATEGORY)