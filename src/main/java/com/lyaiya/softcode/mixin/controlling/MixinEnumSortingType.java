package com.lyaiya.softcode.mixin.controlling;

import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslateKeyUtil;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import us.getfluxed.controlsearch.client.gui.EnumSortingType;

@Mixin(
        value = EnumSortingType.class,
        remap = false
)
abstract class MixinEnumSortingType {
    @ModifyConstant(
            method = "getName",
            constant = @Constant(stringValue = "Default")
    )
    private String modifyConstantDefault(String constant) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.CONTROLLING, KeyConstant.KEY, "default");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "getName",
            constant = @Constant(stringValue = "A->Z")
    )
    private String modifyConstantAZ(String constant) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.CONTROLLING, KeyConstant.KEY, "a_z");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "getName",
            constant = @Constant(stringValue = "Z->A")
    )
    private String modifyConstantZA(String constant) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.CONTROLLING, KeyConstant.KEY, "z_a");
        return I18n.format(key);
    }
}
