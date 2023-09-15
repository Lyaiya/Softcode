package com.lyaiya.softcode.mixin.ic2;

import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslateKeyUtil;
import ic2.core.util.KeyboardClient;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(
        value = KeyboardClient.class,
        remap = false
)
abstract class MixinKeyboardClient {
    @ModifyConstant(
            method = "<init>",
            constant = @Constant(stringValue = "ALT Key")
    )
    private String modifyConstantInit0(String constant) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.IC2, KeyConstant.KEY, "alt");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "<init>",
            constant = @Constant(stringValue = "Boost Key")
    )
    private String modifyConstantInit1(String constant) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.IC2, KeyConstant.KEY, "boost");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "<init>",
            constant = @Constant(stringValue = "Mode Switch Key")
    )
    private String modifyConstantInit2(String constant) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.IC2, KeyConstant.KEY, "mode_switch");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "<init>",
            constant = @Constant(stringValue = "Side Inventory Key")
    )
    private String modifyConstantInit3(String constant) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.IC2, KeyConstant.KEY, "side_inventory");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "<init>",
            constant = @Constant(stringValue = "Hub Expand Key")
    )
    private String modifyConstantInit4(String constant) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.IC2, KeyConstant.KEY, "hud_expand");
        return I18n.format(key);
    }
}
