package com.lyaiya.softcode.mixin.industrialforegoing;

import com.buuz135.industrial.jei.JEICustomPlugin;
import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.Util;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(
        value = JEICustomPlugin.class,
        remap = false
)
abstract class MixinJEICustomPlugin {
    @ModifyConstant(
            method = "registerCategories",
            constant = @Constant(stringValue = "Bioreactor accepted items")
    )
    private String modifyConstantRegisterCategories0(String constant) {
        String key = Util.getKey(ModIdConstant.INDUSTRIAL_FOREGOING, KeyConstant.GUI, KeyConstant.RECIPE_CATEGORY, "bioreactor_accepted_items");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "registerCategories",
            constant = @Constant(stringValue = "Protein reactor accepted items")
    )
    private String modifyConstantRegisterCategories1(String constant) {
        String key = Util.getKey(ModIdConstant.INDUSTRIAL_FOREGOING, KeyConstant.GUI, KeyConstant.RECIPE_CATEGORY, "protein_reactor_accepted_items");
        return I18n.format(key);
    }
}
