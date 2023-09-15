package com.lyaiya.softcode.mixin.libvulpes;

import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslateKeyUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import zmaster587.libVulpes.api.material.Material;
import zmaster587.libVulpes.items.ItemOreProduct;

import javax.annotation.Nonnull;
import java.util.HashMap;

@Mixin(
        value = ItemOreProduct.class,
        remap = false
)
abstract class MixinItemOreProduct extends Item {
    @Shadow
    public HashMap<Integer, Material> properties;

    @Shadow
    String outputType;

    /**
     * Get item stack display name
     *
     * @author Lyaiya
     * @reason Kill the space
     */
    @Nonnull
    @Overwrite
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        String forwardSection;
        if (this.properties.containsKey(stack.getItemDamage())) {
            Material material = this.properties.get(stack.getItemDamage());
            forwardSection = material.getUnlocalizedName();
        } else {
            forwardSection = "";
        }
        String backSection = this.outputType;

        String key = String.format("item.%s.%s.name", forwardSection, backSection);

        if (I18n.hasKey(key) && !forwardSection.isEmpty()) {
            return I18n.format(key);
        } else {
            String forwardStr = I18n.format(String.format("material.%s.name", forwardSection));
            String backStr = I18n.format(String.format("type.%s.name", backSection));
            String finalKey = TranslateKeyUtil.getKey(ModIdConstant.LIB_VULPES, KeyConstant.FORMAT, "item_ore_product");
            return I18n.format(finalKey, forwardStr, backStr);
        }
    }
}
