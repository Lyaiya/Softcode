package com.lyaiya.softcode.mixin.thaumicjei;

import com.buuz135.thaumicjei.util.ResearchUtils;
import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslateKeyUtil;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(
        value = ResearchUtils.class,
        remap = false
)
abstract class MixinResearchUtils {
    @ModifyConstant(
            method = "generateMissingResearchList",
            constant = @Constant(stringValue = "Missing research:")
    )
    private static String modifyConstantMissingResearch(String constant) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.THAUMIC_JEI, KeyConstant.GUI, "missing_research");
        return I18n.format(key);
    }
}
