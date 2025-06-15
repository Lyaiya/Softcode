package com.lyaiya.softcode.mixin.exnihilocreatio;

import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.Util;
import exnihilocreatio.items.ore.ItemOre;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Locale;

@Mixin(
        value = ItemOre.class,
        remap = false
)
abstract class MixinItemOre {
    @Inject(
            method = "getItemStackDisplayName",
            at = @At("RETURN"),
            cancellable = true
    )
    private void injectGetItemStackDisplayName(CallbackInfoReturnable<String> cir) {
        String[] splitStr = cir.getReturnValue().split(" ", 2);
        String forwardStr = splitStr[0].toLowerCase(Locale.ENGLISH);
        String backStr = splitStr[1];

        String formatKey = Util.getKey(ModIdConstant.EX_NIHILO_CREATIO, KeyConstant.FORMAT, "material");
        String materialKey = Util.getKey(ModIdConstant.EX_NIHILO_CREATIO, KeyConstant.MATERIAL, forwardStr);

        String materialStr = I18n.format(materialKey);

        cir.setReturnValue(I18n.format(formatKey, materialStr, backStr));
    }
}
