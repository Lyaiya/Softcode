package com.lyaiya.softcode.mixin.industrialforegoing;

import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.Util;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(
        targets = "com.buuz135.industrial.proxy.block.BlockConveyor$ConveyorItem",
        remap = false
)
abstract class MixinConveyorItem extends ItemBlock {
    public MixinConveyorItem(Block block) {
        super(block);
    }

    /**
     * @author Lyaiya
     * @reason kill the space
     */
    @Overwrite
    public @NonNull String getItemStackDisplayName(@NonNull ItemStack stack) {
        String formatKey = Util.getKey(ModIdConstant.INDUSTRIAL_FOREGOING, KeyConstant.FORMAT, "conveyor");
        String forward = new TextComponentTranslation("item.fireworksCharge." + EnumDyeColor.byMetadata(stack.getMetadata()).getTranslationKey()
                .replaceAll("_", "")).getFormattedText();
        String back = super.getItemStackDisplayName(stack);
        return I18n.format(formatKey, forward, back);
    }
}
