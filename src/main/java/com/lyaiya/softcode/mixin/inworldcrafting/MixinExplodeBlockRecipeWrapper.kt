package com.lyaiya.softcode.mixin.inworldcrafting

import net.minecraft.client.resources.I18n
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.Shadow
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.ModifyArg
import xt9.inworldcrafting.common.recipe.ExplodeBlockRecipe
import xt9.inworldcrafting.integrations.jei.ExplodeBlockRecipeWrapper

@Mixin(ExplodeBlockRecipeWrapper::class, remap = false)
abstract class MixinExplodeBlockRecipeWrapper {
    @Shadow
    private lateinit var recipe: ExplodeBlockRecipe

    @ModifyArg(
        method = ["drawInfo"],
        at = At(value = "INVOKE", target = "drawStringWithShadow(Ljava/lang/String;FFI)I"),
        index = 0
    )
    private fun modifyArgDrawStringWithShadow(text: String): String {
        return I18n.format("${javaClass.recipeWrapperKey}.success_chance", recipe.itemSpawnChance)
    }
}