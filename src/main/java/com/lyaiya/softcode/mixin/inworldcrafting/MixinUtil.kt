package com.lyaiya.softcode.mixin.inworldcrafting

import com.lyaiya.softcode.*
import com.lyaiya.softcode.util.*

inline val Class<*>.recipeWrapperKey
    get() = getReplacedTranslationKey(ModId.IN_WORLD_CRAFTING, ClassConstants.RECIPE_WRAPPER, ClassConstants.RECIPE_CATEGORY)