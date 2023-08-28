import org.jetbrains.gradle.ext.*
import org.jetbrains.gradle.ext.Gradle

plugins {
    kotlin("jvm") version "1.9.0"
    id("org.jetbrains.gradle.plugin.idea-ext") version "1.1.7"
    id("com.gtnewhorizons.retrofuturagradle") version "1.3.24"
}

// Project properties
group = Constant.Group
version = Version.Mod
base.archivesName.set(Constant.ModId)

// Set the toolchain version to decouple the Java we run Gradle with from the Java used to compile and run the mod
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
        // Azul covers the most platforms for Java 8 toolchains, crucially including MacOS arm64
        vendor.set(JvmVendorSpec.AZUL)
    }
    // Generate sources and javadocs jars when building and publishing
    withSourcesJar()
    // withJavadocJar()
}

// configurations {
//     embed
//     implementation.extendsFrom(embed)
// }

// Most RFG configuration lives here, see the JavaDoc for com.gtnewhorizons.retrofuturagradle.MinecraftExtension
minecraft {
    mcVersion.set("1.12.2")

    // MCP Mappings
    mcpMappingChannel.set("stable")
    mcpMappingVersion.set("39")

    // Set username here, the UUID will be looked up automatically
    username.set("Developer")

    // Add any additional tweaker classes here
    // extraTweakClasses.add("org.spongepowered.asm.launch.MixinTweaker")

    // Add various JVM arguments here for runtime
    val jvmArgs = buildList {
        add("-ea:${project.group}")
        if (Setting.UseCoremod) {
            add("-Dfml.coreMods.load=${Constant.FMLCorePlugin}")
        }
        if (Setting.UseMixin) {
            add("-Dmixin.hotSwap=true")
            add("-Dmixin.checks.interfaces=true")
            add("-Dmixin.debug.export=true")
        }
    }
    extraRunJvmArguments.addAll(jvmArgs)

    // Include and use dependencies' Access Transformer files
    useDependencyAccessTransformers.set(true)

    // Add any properties you want to swap out for a dynamic value at build time here
    // Any properties here will be added to a class at build time, the name can be configured below
    // Example:
    injectedTags.put("VERSION", project.version)
    // injectedTags.put("MOD_ID", Constant.ModId)
}

// Generates a class named rfg.examplemod.Tags with the mod version in it, you can find it at
tasks.injectTags {
    outputClassName.set("${project.group}.${Constant.ModId}.Tags")
}

// Create a new dependency type for runtime-only dependencies that don't get included in the maven publication
// val runtimeOnlyNonPublishable: Configuration by configurations.creating {
//     description = "Runtime only dependencies that are not published alongside the jar"
//     isCanBeConsumed = false
//     isCanBeResolved = false
// }
// listOf(configurations.runtimeClasspath, configurations.testRuntimeClasspath).forEach {
//     it.configure {
//         extendsFrom(
//             runtimeOnlyNonPublishable
//         )
//     }
// }

repositories {
    maven {
        name = "CleanroomMC Maven"
        url = uri("https://maven.cleanroommc.com")
    }
    maven {
        name = "SpongePowered Maven"
        url = uri("https://repo.spongepowered.org/maven")
    }
    maven {
        name = "CurseMaven"
        url = uri("https://cursemaven.com")
        content {
            includeGroup("curse.maven")
        }
    }
    maven {
        name = "BlameJared Maven"
        url = uri("https://maven.blamejared.com")
        content {
            includeGroup("CraftTweaker2")
        }
    }
    maven {
        name = "ModMaven"
        url = uri("https://modmaven.dev")
    }
}

dependencies {
    if (Setting.UseAssetmover) {
        implementation("com.cleanroommc:assetmover:2.5")
    }
    if (Setting.UseMixin) {
        implementation("zone.rong:mixinbooter:8.3")
        // Change your mixin refmap name here:
        val mixin = modUtils.enableMixins("org.spongepowered:mixin:0.8.3", "mixins.${Constant.ModId}.refmap.json") as String
        api(mixin) {
            isTransitive = false
        }
        annotationProcessor("org.ow2.asm:asm-debug-all:5.2")
        annotationProcessor("com.google.guava:guava:24.1.1-jre")
        annotationProcessor("com.google.code.gson:gson:2.8.6")
        annotationProcessor(mixin) {
            isTransitive = false
        }
    }
    // Forgelin-Continuous v1.9.0.0
    runtimeOnly(curse("forgelin-continuous", 456403L, 4635770L))

    // CraftTweaker
    runtimeOnly("CraftTweaker2:CraftTweaker2-MC1120-Main:1.12-4.+")

    // Had Enough Item v4.25.0
    runtimeOnly(rfg.deobf(curse("had-enough-items", 557549L, 4571247L)))

    // Just Enough Characters v3.7.2
    runtimeOnly(rfg.deobf(curse("jecharacters", 250702L, 4692184L)))

    // Had Enough Characters v1.4.0
    runtimeOnly(rfg.deobf(curse("had-enough-characters", 640802L, 4692307L)))

    // InWorldCrafting v1.2.0
    compileOnly(rfg.deobf(curse("inworldcrafting", 311938L, 2683267L)))

    // Ex Nihilo: Creatio v0.4.7.2
    compileOnly(rfg.deobf(curse("ex-nihilo-creatio", 274456L, 2817545L)))
    // Shadowfacts' Forgelin v1.8.4
    runtimeOnly(rfg.deobf(curse("shadowfacts-forgelin", 248453L, 2785465L)))
    // Just Enough Items v4.16.+
    compileOnly(rfg.deobf("mezz.jei:jei_1.12.2:4.16.+:api"))

    // Thaumic JEI v1.6.0-27
    compileOnly(rfg.deobf(curse("thaumic-jei", 285492L, 2705304L)))
    // Thaumcraft v6.1.BETA26
    compileOnly(rfg.deobf(curse("thaumcraft", 223628L, 2629023L)))
    // Baubles v1.5.2
    compileOnly(rfg.deobf(curse("baubles", 227083L, 2518667L)))

    // Just Enough Resources v0.9.3.203
    val justenoughresourcesDependency = "curse.maven:just-enough-resources-240630:4440935-deobf-sources"
    implementation(justenoughresourcesDependency)

    // Block Drops v1.4.0
    compileOnly(rfg.deobf(curse("block-drops", 244181L, 2509046L)))

    // Advanced Rocketry v2.0.0-17
    val advancedrocketryDependency = curse("advanced-rocketry", 236542L, 4671856L)
    compileOnly(rfg.deobf(advancedrocketryDependency))
    // LibVulpes v0.4.2-25
    val libvulpesDependency = curse("lib-vulpes", 236541L, 3801015L)
    compileOnly(rfg.deobf(libvulpesDependency))

    // Industrial Foregoing v1.12.13-237
    // def industrialforegoing_dependency = "curse.maven:industrial-foregoing-266515:2745321-sources-api-debof"
    // implementation industrialforegoing_dependency
    // Tesla Core Lib
    // def teslacorelib_dependency = "curse.maven:tesla-core-lib-254602:3438487-sources-debof"
    // implementation teslacorelib_dependency

    // Immersive Engineering v0.12-98
    val immersiveengineeringDependency = curse("immersive-engineering", 231951L, 2974106L)
    compileOnly(rfg.deobf(immersiveengineeringDependency))

    // Industrial Craft v2.8.222
    val industrialcraftDependency = "curse.maven:industrial-craft-242638:3838713-deobf"
    compileOnly(industrialcraftDependency)

    // Controlling v3.0.12.2
    val controllingDependency = curse("controlling", 250398L, 4428378L)
    compileOnly(rfg.deobf(controllingDependency))

    // FTB Quests v1202.9.0.15
    val ftbquestsDependency = "curse.maven:ftb-quests-289412:3156637-sources"
    implementation(ftbquestsDependency)
    // FTB Library v5.4.7.2
    val ftblibraryDependency = "curse.maven:ftb-library-237167:2985811-sources"
    implementation(ftblibraryDependency)
    // Item Filters v1.0.4.2
    val itemfiltersDependency = "curse.maven:item-filters-309674:3003364-sources"
    implementation(itemfiltersDependency)

    // PackagedAuto v1.0.9.33
    val packagedautoDependency = curse("packagedauto", 308380L, 4678906L)
    implementation(rfg.deobf(packagedautoDependency))

    // Serene Seasons v1.2.18
    val sereneseasonsDependency = curse("serene-seasons", 291874L, 2799213L)
    compileOnly(rfg.deobf(sereneseasonsDependency))
}

// Adds Access Transformer files to tasks
if (Setting.UseAccessTransformer) {
    sourceSets.main.get().resources.files.forEach { file ->
        if (file.name.endsWith("_at.cfg", true)) {
            tasks.deobfuscateMergedJarToSrg.get().accessTransformerFiles.from(file)
            tasks.srgifyBinpatchedJar.get().accessTransformerFiles.from(file)
        }
    }
}

tasks.processResources {
    val properties = buildMap {
        this["version"] = project.version
        this["mcversion"] = project.minecraft.mcVersion
    }

    // This will ensure that this task is redone when the versions change
    inputs.properties(properties)

    // Replace various properties in mcmod.info and pack.mcmeta if applicable
    filesMatching(listOf("mcmod.info", "pack.mcmeta")) {
        expand(properties)
    }

    // Make sure Access Transformer files are in META-INF folder
    if (Setting.UseAccessTransformer) {
        rename("(.+_at.cfg)", "META-INF/\$1")
    }
}

tasks.jar {
    manifest {
        val attributeMap = buildMap {
            if (Setting.UseCoremod) {
                this["FMLCorePlugin"] = Constant.FMLCorePlugin
                if (Setting.IncludeMod) {
                    this["FMLCorePluginContainsFMLMod"] = true
                    this["ForceLoadAsMod"] = project.gradle.startParameter.taskNames[0] == "build"
                }
            }
            if (Setting.UseAccessTransformer) {
                this["FMLAT"] = "${Constant.ModId}_at.cfg"
            }
        }
        attributes(attributeMap)
    }
    // Add all embedded dependencies into the jar
    // from(provider{ configurations.embed.collect {it.isDirectory() ? it : zipTree(it)} })
}

// IDE Settings
idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
        inheritOutputDirs = true // Fix resources in IJ-Native runs
    }
    project.settings {
        runConfigurations {
            val gradles = listOf(
                Gradle("1. Run Client").apply {
                    taskNames = listOf("runClient")
                },
                Gradle("2. Run Server").apply {
                    taskNames = listOf("runServer")
                },
                Gradle("3. Run Obfuscated Client").apply {
                    taskNames = listOf("runObfClient")
                },
                Gradle("4. Run Obfuscated Server").apply {
                    taskNames = listOf("runObfServer")
                }
            )
            addAll(gradles)
        }
        compiler.javac {
            afterEvaluate {
                javacAdditionalOptions = "-encoding utf8"
                moduleJavacAdditionalOptions = buildMap {
                    this["${project.name}.main"] = tasks.compileJava.get().options.compilerArgs.joinToString(" ") { '"' + it + '"' }
                }
            }
        }
    }
}

tasks.processIdeaSettings {
    dependsOn(tasks.injectTags)
}
