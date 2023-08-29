package com.lyaiya.softcode.mixin.inworldcrafting

import net.minecraft.client.resources.I18n
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.ModifyArgs
import org.spongepowered.asm.mixin.injection.invoke.arg.Args
import xt9.inworldcrafting.integrations.jei.FluidToItemRecipeWrapper

@Mixin(FluidToItemRecipeWrapper::class, remap = false)
abstract class MixinFluidToItemRecipeWrapper {
    @ModifyArgs(
        method = ["drawInfo"],
        at = At(value = "INVOKE", target = "drawStringWithShadow(Ljava/lang/String;FFI)I"),
    )
    private fun modifyArgsDrawStringWithShadow(args: Args) {
        args.apply {
            set(0, I18n.format("${javaClass.recipeWrapperKey}.consumes_fluid"))
            set(1, I18n.format("${javaClass.recipeWrapperKey}.consumes_fluid.yes"))
            set(2, I18n.format("${javaClass.recipeWrapperKey}.consumes_fluid.no"))
        }
    }
}