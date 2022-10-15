package com.lyaiya.softcode.util

import net.minecraft.client.Minecraft

inline val MinecraftClient: Minecraft
    get() = Minecraft.getMinecraft()