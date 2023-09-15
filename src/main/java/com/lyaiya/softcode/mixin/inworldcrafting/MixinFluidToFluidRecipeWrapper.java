package com.lyaiya.softcode.mixin.inworldcrafting;

import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import xt9.inworldcrafting.integrations.jei.FluidToFluidRecipeWrapper;

@Mixin(
        value = FluidToFluidRecipeWrapper.class,
        remap = false
)
abstract class MixinFluidToFluidRecipeWrapper {
    @ModifyArgs(
            method = "drawInfo",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/FontRenderer;drawStringWithShadow(Ljava/lang/String;FFI)I"
            )
    )
    private void modifyArgsDrawStringWithShadow(Args args) {
        String key = InWorldCraftingKt.getRecipeWrapperKey(getClass());
        args.set(0, I18n.format(key + ".consumes_item"));
        args.set(1, I18n.format(key + ".consumes_item.yes"));
        args.set(2, I18n.format(key + ".consumes_item.no"));
    }

}
