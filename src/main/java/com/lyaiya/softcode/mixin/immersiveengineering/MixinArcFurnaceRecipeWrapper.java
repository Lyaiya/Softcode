package com.lyaiya.softcode.mixin.immersiveengineering;

import blusunrize.immersiveengineering.common.util.compat.jei.arcfurnace.ArcFurnaceRecipeWrapper;
import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslateKeyUtil;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(
        value = ArcFurnaceRecipeWrapper.class,
        remap = false
)
abstract class MixinArcFurnaceRecipeWrapper {
    @ModifyConstant(
            method = "drawInfo",
            constant = @Constant(stringValue = " IF/t")
    )
    private String modifyConstantUnitPerTick(String constant) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.IMMERSIVE_ENGINEERING, KeyConstant.GUI, "unit_per_tick");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "drawInfo",
            constant = @Constant(stringValue = " Seconds")
    )
    private String modifyConstantSeconds(String constant) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.IMMERSIVE_ENGINEERING, KeyConstant.GUI, "seconds");
        return I18n.format(key);
    }
}
