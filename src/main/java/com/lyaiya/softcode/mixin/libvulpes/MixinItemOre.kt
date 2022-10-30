package com.lyaiya.softcode.mixin.libvulpes

import com.lyaiya.softcode.constant.*
import net.minecraft.block.*
import net.minecraft.client.resources.*
import net.minecraft.item.*
import org.spongepowered.asm.mixin.*
import zmaster587.libVulpes.block.BlockOre
import zmaster587.libVulpes.items.*
import java.util.Locale

@Mixin(ItemOre::class, remap = false)
abstract class MixinItemOre(block: Block) : ItemBlock(block) {

    /**
     * Get item stack display name
     *
     * @author Lyaiya
     * @reason Kill the space
     */
    @Overwrite
    override fun getItemStackDisplayName(stack: ItemStack): String {
        val firstSection = this.getUnlocalizedNameInefficiently(stack)
        val secondSection = (this.getBlock() as BlockOre).getProduct().name().lowercase(Locale.ENGLISH)

        val translateKey = "tile.${firstSection.substring(9)}.${secondSection}.name"

        return if (I18n.hasKey(translateKey)) {
            I18n.format(translateKey)
        } else {
            val firstString = I18n.format("${firstSection}.name")
            val secondString = I18n.format("type.${secondSection}.name")
            I18n.format(
                com.lyaiya.softcode.util.getTranslationKey(ModIdConstant.LIB_VULPES, KeyConstant.FORMAT, "item_ore"),
                firstString,
                secondString
            )
        }
    }
}