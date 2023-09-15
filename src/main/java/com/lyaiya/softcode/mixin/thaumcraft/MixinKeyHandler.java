package com.lyaiya.softcode.mixin.thaumcraft;

import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslateKeyUtil;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import thaumcraft.common.lib.events.KeyHandler;

@Mixin(
        value = KeyHandler.class,
        remap = false
)
abstract class MixinKeyHandler {
    @ModifyConstant(
            method = "<clinit>",
            constant = @Constant(stringValue = "Change Caster Focus")
    )
    private static String modifyConstantKeyF(String constant) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.THAUMCRAFT, KeyConstant.KEY, "change_caster_focus");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "<clinit>",
            constant = @Constant(stringValue = "Misc Caster Toggle")
    )
    private static String modifyConstantKeyG(String constant) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.THAUMCRAFT, KeyConstant.KEY, "misc_caster_toggle");
        return I18n.format(key);
    }
}
