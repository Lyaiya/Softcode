pluginManagement {
    repositories {
        gradlePluginPortal()
        maven {
            name = "FancyGradle"
            url = uri("https://maven.gofancy.wtf/releases")
        }
        maven {
            name = "MinecraftForge"
            url = uri("https://maven.minecraftforge.net")
        }
    }
}

rootProject.name = "Softcode"