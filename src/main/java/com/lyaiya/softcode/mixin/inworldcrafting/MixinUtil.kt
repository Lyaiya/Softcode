package com.lyaiya.softcode.mixin.inworldcrafting

import com.lyaiya.softcode.constant.*
import com.lyaiya.softcode.util.*

inline val Class<*>.recipeWrapperKey
    get() = createReplacedTranslationKey(ModIdConstant.IN_WORLD_CRAFTING, ClassConstant.RECIPE_WRAPPER, ClassConstant.RECIPE_CATEGORY)