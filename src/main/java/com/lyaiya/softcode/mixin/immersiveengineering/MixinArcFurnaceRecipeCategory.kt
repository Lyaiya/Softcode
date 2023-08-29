package com.lyaiya.softcode.mixin.immersiveengineering

import blusunrize.immersiveengineering.common.util.compat.jei.IERecipeCategory
import blusunrize.immersiveengineering.common.util.compat.jei.arcfurnace.ArcFurnaceRecipeCategory
import com.google.common.base.CaseFormat
import com.lyaiya.softcode.constant.KeyConstant
import com.lyaiya.softcode.constant.ModIdConstant
import com.lyaiya.softcode.util.createTranslationKey
import mezz.jei.api.gui.IDrawable
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.client.resources.I18n
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

@Mixin(ArcFurnaceRecipeCategory::class, remap = false)
abstract class MixinArcFurnaceRecipeCategory<T, W : IRecipeWrapper>(
    uniqueName: String, localKey: String, background: IDrawable, recipeClass: Class<T>
) : IERecipeCategory<T, W>(uniqueName, localKey, background, recipeClass) {
    @Inject(
        method = ["<init>"],
        at = [At("RETURN")]
    )
    private fun injectInit(ci: CallbackInfo) {
        if ("." in uniqueName) {
            val splitString = uniqueName.split(".", limit = 2)
            val firstString = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, splitString[0])
            localizedName = I18n.format(
                createTranslationKey(
                    ModIdConstant.IMMERSIVE_ENGINEERING,
                    KeyConstant.GUI,
                    KeyConstant.RECIPE_CATEGORY,
                    firstString,
                    splitString[1]
                )
            )
        }
    }
}