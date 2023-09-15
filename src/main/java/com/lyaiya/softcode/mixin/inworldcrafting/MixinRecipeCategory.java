package com.lyaiya.softcode.mixin.inworldcrafting;

import com.lyaiya.softcode.constant.ClassConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslationKeyKt;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xt9.inworldcrafting.integrations.jei.*;

@Mixin(
        value = {BurnItemRecipeCategory.class, ExplodeBlockRecipeCategory.class, ExplodeItemRecipeCategory.class, FluidToFluidRecipeCategory.class,
                FluidToItemRecipeCategory.class},
        remap = false
)
abstract class MixinRecipeCategory {
    @Inject(
            method = "getTitle",
            at = @At("RETURN"),
            cancellable = true
    )
    private void injectGetTitle(CallbackInfoReturnable<String> cir) {
        String key = TranslationKeyKt.createReplaced(this.getClass(), ModIdConstant.IN_WORLD_CRAFTING, ClassConstant.RECIPE_CATEGORY);
        cir.setReturnValue(I18n.format(key));
    }
}
