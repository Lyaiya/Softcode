package com.lyaiya.softcode.mixin.industrialforegoing;

import com.buuz135.industrial.jei.extractor.ExtractorRecipeCategory;
import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.Util;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(
        value = ExtractorRecipeCategory.class,
        remap = false
)
abstract class MixinExtractorRecipeCategory {
    @ModifyConstant(
            method = "getTooltipStrings",
            constant = @Constant(stringValue = "Production rate")
    )
    String modifyConstantDrawInfo0(String constant) {
        String key = Util.getKey(ModIdConstant.INDUSTRIAL_FOREGOING, KeyConstant.GUI, "extractor", "production_rate");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "getTooltipStrings",
            constant = @Constant(stringValue = "Average numbers aren't real numbers")
    )
    String modifyConstantDrawInfo1(String constant) {
        String key = Util.getKey(ModIdConstant.INDUSTRIAL_FOREGOING, KeyConstant.GUI, "extractor", "average_tooltip");
        return I18n.format(key);
    }
}
