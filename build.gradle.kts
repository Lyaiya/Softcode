import net.minecraftforge.gradle.common.util.*
import wtf.gofancy.fancygradle.script.extensions.*

val buildDeobfJar: String by extra
val buildApiJar: String by extra
val buildSourceJar: String by extra

val useMixins: String by extra
val useCoremod: String by extra
val useAssetmover: String by extra
val hasAccessTransformer: String by extra

val includeMod: String by extra
val coremodPluginClassName: String by extra

buildscript {
    repositories {
        maven {
            name = "MixinGradle"
            url = uri("https://repo.spongepowered.org/repository/maven-public")
        }
    }
    dependencies {
        classpath("org.spongepowered:mixingradle:0.7-SNAPSHOT")
    }
}

plugins {
    kotlin("jvm") version Version.Kotlin
    id("net.minecraftforge.gradle") version Version.ForgeGradle
    id("wtf.gofancy.fancygradle") version Version.FancyGradle
}

if (useMixins.toBoolean()) {
    apply(plugin = "org.spongepowered.mixin")
}

group = Constant.Group
version = Version.Mod

java.toolchain.languageVersion.set(JavaLanguageVersion.of(8))

minecraft {
    mappings("stable", "39-1.12")

    if (hasAccessTransformer.toBoolean()) {
        accessTransformer("src/main/resources/META-INF/accesstransformer.cfg")
    }

    runs {
        val clientConfig = Action<RunConfig> {
            workingDirectory = project.file("run/client").canonicalPath
            properties(
                mapOf(
                    "forge.logging.markers" to "REGISTRIES",
                    "forge.logging.console.level" to "debug",
                )
            )
            if (useCoremod.toBoolean()) {
                jvmArgs("-Dfml.coreMods.load=$coremodPluginClassName")
            }
            if (useMixins.toBoolean()) {
                jvmArgs("-Dmixin.hotSwap=true")
                jvmArgs("-Dmixin.checks.interfaces=true")
                jvmArgs("-Dmixin.debug=true")
            }
            source(sourceSets.main.get())
        }
        create("client", clientConfig)

        val serverConfig = Action<RunConfig> {
            workingDirectory = project.file("run/server").canonicalPath
            properties(
                mapOf(
                    "forge.logging.markers" to "REGISTRIES",
                    "forge.logging.console.level" to "debug",
                )
            )
            if (useCoremod.toBoolean()) {
                jvmArgs("-Dfml.coreMods.load=$coremodPluginClassName")
            }
            if (useMixins.toBoolean()) {
                jvmArgs("-Dmixin.hotSwap=true")
                jvmArgs("-Dmixin.checks.interfaces=true")
            }
            source(sourceSets.main.get())
        }
        create("server", serverConfig)
    }
}

repositories {
    mavenCentral()
    curseForge()
    maven {
        name = "CleanroomMC"
        url = uri("https://maven.cleanroommc.com")
    }
    maven {
        name = "SpongePowered"
        url = uri("https://repo.spongepowered.org/maven")
    }
    maven {
        name = "BlameJared Maven"
        url = uri("https://maven.blamejared.com")
        content {
            includeGroup("CraftTweaker2")
        }
    }
    maven {
        name = "Progwml6 maven"
        url = uri("https://dvs1.progwml6.com/files/maven")
    }
}

dependencies {
    minecraft("net.minecraftforge:forge:1.12.2-${Version.Forge}")

    implementation(fileTree("lib"))

    if (useMixins.toBoolean()) {
        val mixinbooterVersion = "5.0"
        compileOnly(fg.deobf("zone.rong:mixinbooter:${mixinbooterVersion}"))
        runtimeOnly("zone.rong:mixinbooter:${mixinbooterVersion}")
    }

    // // Kotlin
    // val kotlinVersion = "1.6.21"
    // implementation(kotlin("reflect", kotlinVersion))
    //
    // // Forgelin-Continuous v1.6.21.0
    // runtimeOnly(curse("forgelin-continuous", 456403L, 3771384L))

    // CraftTweaker
    runtimeOnly("CraftTweaker2:CraftTweaker2-MC1120-Main:1.12-4.+")

    // Had Enough Item v4.24.0
    runtimeOnly(fg.deobf(curse("had-enough-items", 557549L, 3957880L)))

    // Had Enough Characters v1.3.0
    runtimeOnly(fg.deobf(curse("had-enough-characters", 640802L, 4035773L)))

    // InWorldCrafting v1.2.0
    val inworldcraftingDependency = curse("inworldcrafting", 311938L, 2683267L)
    compileOnly(fg.deobf(inworldcraftingDependency))

    // Ex Nihilo: Creatio v0.4.7.2
    val exnihilocreatioDependency = curse("ex-nihilo-creatio", 274456L, 2817545L)
    compileOnly(fg.deobf(exnihilocreatioDependency))
    // Shadowfacts' Forgelin v1.8.4
    val shadowfactsforgelinDependency = curse("shadowfacts-forgelin", 248453L, 2785465L)
    runtimeOnly(fg.deobf(shadowfactsforgelinDependency))
    // Just Enough Items v4.16.+
    compileOnly(fg.deobf("mezz.jei:jei_1.12.2:4.16.+:api"))

    // Thaumic JEI v1.6.0-27
    val thaumicjeiDependency = curse("thaumic-jei", 285492L, 2705304L)
    compileOnly(fg.deobf(thaumicjeiDependency))
    // Thaumcraft v6.1.BETA26
    val thaumcraftDependency = curse("thaumcraft", 223628L, 2629023L)
    compileOnly(fg.deobf(thaumcraftDependency))
    // Baubles v1.5.2
    val baublesDependency = curse("baubles", 227083L, 2518667L)
    compileOnly(fg.deobf(baublesDependency))

    // Just Enough Resources v0.9.2.60
    val justenoughresourcesDependency = "curse.maven:just-enough-resources-240630:2728585-deobf-sources"
    implementation(justenoughresourcesDependency)

    // Block Drops v1.4.0
    val blockdropsDependency = curse("block-drops", 244181L, 2509046L)
    compileOnly(fg.deobf(blockdropsDependency))

    // Advanced Rocketry v2.0.0-13
    val advancedrocketryDependency = curse("advanced-rocketry", 236542L, 3801020L)
    implementation(fg.deobf(advancedrocketryDependency))
    // LibVulpes v0.4.2-25
    val libvulpesDependency = curse("lib-vulpes", 236541L, 3801015L)
    implementation(fg.deobf(libvulpesDependency))

    // Industrial Foregoing v1.12.13-237
    // def industrialforegoing_dependency = "curse.maven:industrial-foregoing-266515:2745321-sources-api-debof"
    // implementation industrialforegoing_dependency
    // Tesla Core Lib
    // def teslacorelib_dependency = "curse.maven:tesla-core-lib-254602:3438487-sources-debof"
    // implementation teslacorelib_dependency

    // Immersive Engineering v0.12-98
    val immersiveengineeringDependency = curse("immersive-engineering", 231951L, 2974106L)
    compileOnly(fg.deobf(immersiveengineeringDependency))

    // Industrial Craft v2.8.222
    val industrialcraftDependency = "curse.maven:industrial-craft-242638:3838713-deobf"
    compileOnly(industrialcraftDependency)

    // Controlling v3.0.10
    val controllingDependency = curse("controlling", 250398L, 3025548L)
    compileOnly(fg.deobf(controllingDependency))

    // FTB Quests v1202.9.0.15
    val ftbquestsDependency = "curse.maven:ftb-quests-289412:3156637-sources"
    implementation(ftbquestsDependency)
    // FTB Library v5.4.7.2
    val ftblibraryDependency = "curse.maven:ftb-library-237167:2985811-sources"
    implementation(ftblibraryDependency)
    // Item Filters v1.0.4.2
    val itemfiltersDependency = "curse.maven:item-filters-309674:3003364-sources"
    implementation(itemfiltersDependency)

    // PackagedAuto v1.0.5.19
    val packagedautoDependency = curse("packagedauto", 308380L, 3614585L)
    implementation(fg.deobf(packagedautoDependency))

    // Serene Seasons v1.2.18
    val sereneseasonsDependency = curse("serene-seasons", 291874L, 2799213L)
    implementation(fg.deobf(sereneseasonsDependency))
}

fancyGradle {
    patches {
        resources
        coremods
        asm
        mergetool
    }
}

sourceSets {
    main {
        if (useMixins.toBoolean()) {
            ext.set("refMap", "mixins.${Constant.ModId}.refmap.json")
        }
    }
}

tasks {
    if (buildDeobfJar.toBoolean()) {
        // Create deobf dev jars
        register<Jar>("deobfJar") {
            from(sourceSets.main.get().java)
            archiveClassifier.set("deobf")
        }
    }

    if (buildApiJar.toBoolean()) {
        // Create API library jar
        register<Jar>("apiZip") {
            from(sourceSets.main.get().java) {
                include("name/api/**")
            }
            from(sourceSets.main.get().output) {
                include("name/api/**")
            }
            archiveClassifier.set("api")
        }
    }

    if (buildSourceJar.toBoolean()) {
        // Create source jar
        register<Jar>("sourcesJar") {
            from(sourceSets.main.get().allJava)
            archiveClassifier.set("sources")
        }
    }

    jar {
        manifest {
            val attributeMap = mutableMapOf<String, String>()
            if (useCoremod.toBoolean()) {
                attributeMap["FMLCorePlugin"] = coremodPluginClassName
                if (includeMod.toBoolean()) {
                    attributeMap["FMLCorePluginContainsFMLMod"] = true.toString()
                    attributeMap["ForceLoadAsMod"] = (project.gradle.startParameter.taskNames[0] == "build").toString()
                }
            }
            if (useMixins.toBoolean()) {
                attributeMap["TweakClass"] = "org.spongepowered.asm.launch.MixinTweaker"
            }
            attributes(attributeMap)
        }

        finalizedBy("reobfJar")
    }

    processResources {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
        inputs.property("version", project.version)
        filesMatching("mcmod.info") {
            expand("version" to project.version)
        }
    }

    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
            languageVersion = "1.6"
        }
    }
}

artifacts {
    if (buildDeobfJar.toBoolean()) {
        archives("deobfJar")
    }
    if (buildApiJar.toBoolean()) {
        archives("apiZip")
    }
    if (buildSourceJar.toBoolean()) {
        archives("sourcesJar")
    }
}
