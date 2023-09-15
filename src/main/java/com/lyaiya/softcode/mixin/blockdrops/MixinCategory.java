package com.lyaiya.softcode.mixin.blockdrops;

import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslationKeyKt;
import mrriegel.blockdrops.Category;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(
        value = Category.class,
        remap = false
)
abstract class MixinCategory {
    @Inject(
            method = "getTitle",
            at = @At("RETURN"),
            cancellable = true
    )
    private void injectGetTitle(CallbackInfoReturnable<String> cir) {
        String key = TranslationKeyKt.create(ModIdConstant.BLOCK_DROPS, KeyConstant.GUI, KeyConstant.RECIPE_CATEGORY, "block_drops");
        cir.setReturnValue(I18n.format(key));
    }
}
