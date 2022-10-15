package com.lyaiya.softcode.mixin.inworldcrafting

import net.minecraft.client.resources.*
import org.spongepowered.asm.mixin.*
import org.spongepowered.asm.mixin.injection.*
import xt9.inworldcrafting.integrations.jei.*

@Mixin(FluidToItemRecipeWrapper::class, remap = false)
abstract class MixinFluidToItemRecipeWrapper {
    @ModifyArg(
        method = ["drawInfo"],
        at = At(value = "INVOKE", target = "drawStringWithShadow(Ljava/lang/String;FFI)I", ordinal = 0),
        index = 0,
    )
    private fun modifyArgDrawStringWithShadow0(text: String): String {
        return I18n.format("${javaClass.recipeWrapperKey}.consumes_fluid")
    }

    @ModifyArg(
        method = ["drawInfo"],
        at = At(value = "INVOKE", target = "drawStringWithShadow(Ljava/lang/String;FFI)I", ordinal = 1),
        index = 0,
    )
    private fun modifyArgDrawStringWithShadow1(text: String): String {
        return I18n.format("${javaClass.recipeWrapperKey}.consumes_fluid.yes")
    }

    @ModifyArg(
        method = ["drawInfo"],
        at = At(value = "INVOKE", target = "drawStringWithShadow(Ljava/lang/String;FFI)I", ordinal = 2),
        index = 0,
    )
    private fun modifyArgDrawStringWithShadow2(text: String): String {
        return I18n.format("${javaClass.recipeWrapperKey}.consumes_fluid.no")
    }

}