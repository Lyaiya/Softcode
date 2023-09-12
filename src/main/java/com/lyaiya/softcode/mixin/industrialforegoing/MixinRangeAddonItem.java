package com.lyaiya.softcode.mixin.industrialforegoing;

import com.buuz135.industrial.item.addon.RangeAddonItem;
import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslationKeyKt;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = RangeAddonItem.class, remap = false)
public class MixinRangeAddonItem {
    @ModifyConstant(method = "addInformation", constant = @Constant(stringValue = "Range: +"))
    private String modifyConstantAddInformation(String constant) {
        return I18n.format(TranslationKeyKt.create(ModIdConstant.INDUSTRIAL_FOREGOING, KeyConstant.TOOLTIP, "range"));
    }
}
