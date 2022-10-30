package com.lyaiya.softcode.mixin.libvulpes

import com.lyaiya.softcode.constant.*
import net.minecraft.client.resources.*
import net.minecraft.item.*
import org.spongepowered.asm.mixin.*
import zmaster587.libVulpes.api.material.*
import zmaster587.libVulpes.items.*

@Mixin(ItemOreProduct::class, remap = false)
abstract class MixinItemOreProduct : Item() {

    @Shadow
    private lateinit var properties: HashMap<Int, Material>

    @Shadow
    private lateinit var outputType: String

    /**
     * Get item stack display name
     *
     * @author Lyaiya
     * @reason Kill the space
     */
    @Overwrite
    override fun getItemStackDisplayName(stack: ItemStack): String {
        val firstSection = if (stack.itemDamage in this.properties) {
            (this.properties[stack.itemDamage] as Material).unlocalizedName
        } else {
            ""
        }
        val secondSection = this.outputType

        val translateKey = "item.${firstSection}.${secondSection}.name"

        return if (I18n.hasKey(translateKey) && firstSection.isNotEmpty()) {
            I18n.format(translateKey)
        } else {
            val firstString = I18n.format("material.${firstSection}.name")
            val secondString = I18n.format("type.${secondSection}.name")
            I18n.format(
                com.lyaiya.softcode.util.getTranslationKey(ModIdConstant.LIB_VULPES, KeyConstant.FORMAT, "item_ore_product"),
                firstString,
                secondString
            )
        }
    }
}