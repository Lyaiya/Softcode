package com.lyaiya.softcode.mixin.industrialforegoing;

import com.buuz135.industrial.jei.sludge.SludgeRefinerRecipeWrapper;
import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.Util;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(
        value = SludgeRefinerRecipeWrapper.class,
        remap = false
)
abstract class MixinSludgeRefinerRecipeWrapper {
    @ModifyConstant(
            method = "drawInfo",
            constant = @Constant(stringValue = "Chance: ")
    )
    String modifyConstantDrawInfo(String constant) {
        String key = Util.getKey(ModIdConstant.INDUSTRIAL_FOREGOING, KeyConstant.GUI, "sludge_refiner", "chance");
        return I18n.format(key);
    }
}
