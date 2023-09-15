package com.lyaiya.softcode.mixin.industrialforegoing;

import com.buuz135.industrial.item.MeatFeederItem;
import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslateKeyUtil;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(
        value = MeatFeederItem.class,
        remap = false
)
abstract class MixinMeatFeederItem {
    @ModifyConstant(
            method = "addInformation",
            constant = @Constant(stringValue = "mb of Meat")
    )
    private String modifyConstantAddInformation(String constant) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.INDUSTRIAL_FOREGOING, KeyConstant.TOOLTIP, "meat_feeder", "meat");
        return "mb" + I18n.format(key);
    }
}
