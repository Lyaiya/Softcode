package com.lyaiya.softcode.mixin.industrialforegoing;

import com.buuz135.industrial.jei.extractor.ExtractorRecipeCategory;
import com.buuz135.industrial.jei.laser.LaserRecipeCategory;
import com.buuz135.industrial.jei.sludge.SludgeRefinerRecipeCategory;
import com.lyaiya.softcode.constant.ClassConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.Util;
import net.minecraft.client.resources.I18n;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(
        value = {ExtractorRecipeCategory.class, LaserRecipeCategory.class, SludgeRefinerRecipeCategory.class},
        remap = false
)
abstract class MixinRecipeCategory {
    @Inject(
            method = "getTitle",
            at = @At("RETURN"),
            cancellable = true
    )
    private void injectGetTitle(@NonNull CallbackInfoReturnable<String> cir) {
        String key = Util.getReplacedKey(this.getClass(), ModIdConstant.INDUSTRIAL_FOREGOING, ClassConstant.RECIPE_CATEGORY);
        cir.setReturnValue(I18n.format(key));
    }
}
