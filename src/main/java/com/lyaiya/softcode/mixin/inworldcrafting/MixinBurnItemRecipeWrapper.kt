package com.lyaiya.softcode.mixin.inworldcrafting

import net.minecraft.client.resources.*
import org.spongepowered.asm.mixin.*
import org.spongepowered.asm.mixin.injection.*
import xt9.inworldcrafting.common.recipe.*
import xt9.inworldcrafting.integrations.jei.*

@Mixin(BurnItemRecipeWrapper::class, remap = false)
abstract class MixinBurnItemRecipeWrapper {
    @Shadow
    private lateinit var recipe: BurnItemRecipe

    @ModifyArg(
        method = ["drawInfo"],
        at = At(value = "INVOKE", target = "drawStringWithShadow(Ljava/lang/String;FFI)I"),
        index = 0
    )
    private fun modifyArgDrawStringWithShadow(text: String): String {
        return I18n.format("${javaClass.recipeWrapperKey}.recipe_time", recipe.ticks)
    }
}