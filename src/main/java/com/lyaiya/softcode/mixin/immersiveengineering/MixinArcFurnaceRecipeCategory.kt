package com.lyaiya.softcode.mixin.immersiveengineering

import blusunrize.immersiveengineering.common.util.compat.jei.*
import blusunrize.immersiveengineering.common.util.compat.jei.arcfurnace.*
import com.google.common.base.*
import com.lyaiya.softcode.constant.*
import com.lyaiya.softcode.util.*
import mezz.jei.api.gui.*
import mezz.jei.api.recipe.*
import net.minecraft.client.resources.*
import org.spongepowered.asm.mixin.*
import org.spongepowered.asm.mixin.injection.*
import org.spongepowered.asm.mixin.injection.callback.*

@Mixin(ArcFurnaceRecipeCategory::class, remap = false)
abstract class MixinArcFurnaceRecipeCategory<T, W : IRecipeWrapper>(
    uniqueName: String, localKey: String, background: IDrawable, recipeClass: Class<T>
) : IERecipeCategory<T, W>(uniqueName, localKey, background, recipeClass) {
    @Inject(method = ["<init>"], at = [At("RETURN")])
    private fun injectInit(ci: CallbackInfo) {
        if ("." in uniqueName) {
            val splitString = uniqueName.split(".", limit = 2)
            val firstString = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, splitString[0])
            localizedName = I18n.format(
                getTranslationKey(
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