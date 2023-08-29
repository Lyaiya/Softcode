package com.lyaiya.softcode.mixin.libvulpes

import com.lyaiya.softcode.constant.KeyConstant
import com.lyaiya.softcode.constant.ModIdConstant
import com.lyaiya.softcode.util.createTranslationKey
import net.minecraft.block.Block
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.Overwrite
import zmaster587.libVulpes.block.BlockOre
import zmaster587.libVulpes.items.ItemOre
import java.util.*

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
            I18n.format(createTranslationKey(ModIdConstant.LIB_VULPES, KeyConstant.FORMAT, "item_ore"), firstString, secondString)
        }
    }
}