package com.lyaiya.softcode.mixin.industrialforegoing;

import com.buuz135.industrial.jei.reactor.ReactorRecipeWrapper;
import com.buuz135.industrial.proxy.BlockRegistry;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslationKeyKt;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Arrays;
import java.util.List;

@Mixin(
        value = ReactorRecipeWrapper.class,
        remap = false
)
abstract class MixinReactorRecipeWrapper {
    @ModifyReturnValue(
            method = "getTooltipStrings",
            at = @At(
                    value = "RETURN",
                    ordinal = 0
            )
    )
    private List<String> modifyArgsGetTooltipStrings(List<String> stringList) {
        String efficiencyKey = TranslationKeyKt.create(ModIdConstant.INDUSTRIAL_FOREGOING, KeyConstant.GUI, "reactor", "efficiency");
        String minKey = TranslationKeyKt.create(ModIdConstant.INDUSTRIAL_FOREGOING, KeyConstant.GUI, "reactor", "min");
        String maxKey = TranslationKeyKt.create(ModIdConstant.INDUSTRIAL_FOREGOING, KeyConstant.GUI, "reactor", "max");
        return Arrays.asList(I18n.format(efficiencyKey),
                             I18n.format(minKey, BlockRegistry.bioReactorBlock.getBaseAmount()),
                             I18n.format(maxKey, BlockRegistry.bioReactorBlock.getBaseAmount() * 2));
    }
}
