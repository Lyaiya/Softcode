package com.lyaiya.softcode.mixin.industrialforegoing;

import com.buuz135.industrial.book.GUIBookMain;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslateKeyUtil;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(
        value = GUIBookMain.class,
        remap = false
)
abstract class MixinGUIBookMain {
    @ModifyConstant(
            method = "initGui",
            constant = @Constant(stringValue = "Search")
    )
    private String modifyConstantInitGui(String constant) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.INDUSTRIAL_FOREGOING, "manual", "search");
        return I18n.format(key);
    }
}
