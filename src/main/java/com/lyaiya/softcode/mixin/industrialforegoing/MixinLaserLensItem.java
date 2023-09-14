package com.lyaiya.softcode.mixin.industrialforegoing;

import com.buuz135.industrial.item.IFCustomItem;
import com.buuz135.industrial.item.LaserLensItem;
import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslationKeyKt;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(
        value = LaserLensItem.class,
        remap = false
)
abstract class MixinLaserLensItem extends IFCustomItem {
    public MixinLaserLensItem(String name) {
        super(name);
    }

    /**
     * @author Lyaiya
     * @reason kill the space
     */
    @Overwrite
    public @NotNull String getItemStackDisplayName(ItemStack stack) {
        String formatKey = TranslationKeyKt.create(ModIdConstant.INDUSTRIAL_FOREGOING, KeyConstant.FORMAT, "laser");
        String forward = new TextComponentTranslation("item.fireworksCharge." + EnumDyeColor.byMetadata(stack.getMetadata()).getTranslationKey()
                .replaceAll("_", "")).getFormattedText();
        String back = super.getItemStackDisplayName(stack);
        return I18n.format(formatKey, forward, back);
    }
}
