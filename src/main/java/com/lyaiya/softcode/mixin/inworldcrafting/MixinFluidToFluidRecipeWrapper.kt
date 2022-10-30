package com.lyaiya.softcode.mixin.inworldcrafting

import net.minecraft.client.resources.*
import org.spongepowered.asm.mixin.*
import org.spongepowered.asm.mixin.injection.*
import org.spongepowered.asm.mixin.injection.invoke.arg.*
import xt9.inworldcrafting.integrations.jei.*

@Mixin(FluidToFluidRecipeWrapper::class, remap = false)
abstract class MixinFluidToFluidRecipeWrapper {
    @ModifyArgs(
        method = ["drawInfo"],
        at = At(value = "INVOKE", target = "drawStringWithShadow(Ljava/lang/String;FFI)I")
    )
    private fun modifyArgsDrawStringWithShadow(args: Args) {
        args.apply {
            set(0, I18n.format("${javaClass.recipeWrapperKey}.consumes_item"))
            set(1, I18n.format("${javaClass.recipeWrapperKey}.consumes_item.yes"))
            set(2, I18n.format("${javaClass.recipeWrapperKey}.consumes_item.no"))
        }
    }
}