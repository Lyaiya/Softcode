package com.lyaiya.softcode.mixin.industrialforegoing;

import com.buuz135.industrial.jei.extractor.ExtractorRecipeWrapper;
import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.Util;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(
        value = ExtractorRecipeWrapper.class,
        remap = false
)
abstract class MixinExtractorRecipeWrapper {
    @ModifyConstant(
            method = "drawInfo",
            constant = @Constant(stringValue = "Production: ")
    )
    String modifyConstantDrawInfo0(String constant) {
        String key = Util.getKey(ModIdConstant.INDUSTRIAL_FOREGOING, KeyConstant.GUI, "extractor", "production");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "drawInfo",
            constant = @Constant(stringValue = "Average: ")
    )
    String modifyConstantDrawInfo1(String constant) {
        String key = Util.getKey(ModIdConstant.INDUSTRIAL_FOREGOING, KeyConstant.GUI, "extractor", "average");
        return I18n.format(key);
    }
}
