package com.lyaiya.softcode.mixin.libvulpes;

import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslationKeyKt;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import zmaster587.libVulpes.block.BlockOre;
import zmaster587.libVulpes.items.ItemOre;

import java.util.Locale;

@Mixin(
        value = ItemOre.class,
        remap = false
)
abstract class MixinItemOre extends ItemBlock {
    public MixinItemOre(Block block) {
        super(block);
    }

    /**
     * Get item stack display name
     *
     * @author Lyaiya
     * @reason Kill the space
     */
    @Overwrite
    public @NotNull String getItemStackDisplayName(@NotNull ItemStack stack) {
        BlockOre blockOre = (BlockOre) this.getBlock();

        String forwardSection = this.getUnlocalizedNameInefficiently(stack);
        String backSection = blockOre.getProduct().name().toLowerCase(Locale.ENGLISH);

        String key = String.format("tile.%s.%s.name", forwardSection.substring(9), backSection);

        if (I18n.hasKey(key)) {
            return I18n.format(key);
        } else {
            String forwardStr = I18n.format(String.format("%s.name", forwardSection));
            String backStr = I18n.format(String.format("type.%s.name", backSection));
            String finalKey = TranslationKeyKt.create(ModIdConstant.LIB_VULPES, KeyConstant.FORMAT, "item_ore");
            return I18n.format(finalKey, forwardStr, backStr);
        }
    }
}
