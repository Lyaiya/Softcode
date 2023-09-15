package com.lyaiya.softcode.mixin.blockdrops;

import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslationKeyKt;
import mrriegel.blockdrops.Wrapper;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(
        value = Wrapper.class,
        remap = false
)
abstract class MixinWrapper {
    @ModifyConstant(
            method = "onTooltip(IZLnet/minecraft/item/ItemStack;Ljava/util/List;)V",
            constant = @Constant(stringValue = "Min: ")
    )
    private String modifyConstantMin(String constant) {
        String key = TranslationKeyKt.create(ModIdConstant.BLOCK_DROPS, KeyConstant.GUI, "min");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "onTooltip(IZLnet/minecraft/item/ItemStack;Ljava/util/List;)V",
            constant = @Constant(stringValue = "  Max: ")
    )
    private String modifyConstantMax(String constant) {
        String key = TranslationKeyKt.create(ModIdConstant.BLOCK_DROPS, KeyConstant.GUI, "max");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "onTooltip(IZLnet/minecraft/item/ItemStack;Ljava/util/List;)V",
            constant = @Constant(stringValue = "Fortune ")
    )
    private String modifyConstantFortune(String constant) {
        return I18n.format("enchantment.lootBonusDigger") + " ";
    }

    @ModifyConstant(
            method = "onTooltip(IZLnet/minecraft/item/ItemStack;Ljava/util/List;)V",
            constant = @Constant(stringValue = "There are too many possible drops. Use left and right key to cycle.")
    )
    private String modifyConstantTooManyDrops(String constant) {
        String key = TranslationKeyKt.create(ModIdConstant.BLOCK_DROPS, KeyConstant.GUI, "too_many_drops");
        return I18n.format(key);
    }
}
