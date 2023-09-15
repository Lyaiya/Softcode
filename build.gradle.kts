import org.jetbrains.gradle.ext.Gradle
import org.jetbrains.gradle.ext.compiler
import org.jetbrains.gradle.ext.runConfigurations
import org.jetbrains.gradle.ext.settings
import kotlin.collections.set

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
        implementation(Deps.AssetMover)
    }
    if (Setting.UseMixin) {
        // implementation(Deps.MixinBooter)
        // Change your mixin refmap name here:
        // val mixin = modUtils.enableMixins("org.spongepowered:mixin:0.8.3", "mixins.${Constant.ModId}.refmap.json") as String
        val mixinBooter = modUtils.enableMixins(Deps.MixinBooter, "mixins.${Constant.ModId}.refmap.json") as String
        api(mixinBooter) {
            isTransitive = false
        }
        annotationProcessor(mixinBooter) {
            isTransitive = false
        }
        annotationProcessor("org.ow2.asm:asm-debug-all:5.2")
        annotationProcessor("com.google.guava:guava:24.1.1-jre")
        annotationProcessor("com.google.code.gson:gson:2.8.6")
    }

    if (RuntimeDebug.HadEnoughItems) {
        runtimeOnly(rfg.deobf(Deps.HadEnoughItems))
        if (RuntimeDebug.JustEnoughCharacters) {
            runtimeOnly(rfg.deobf(Deps.JustEnoughCharacters))
            runtimeOnly(rfg.deobf(Deps.HadEnoughCharacters))
        }
    }

    compileOnly(rfg.deobf(Deps.InWorldCrafting))
    if (RuntimeDebug.InWorldCrafting) {
        runtimeOnly(Deps.CraftTweaker)
        runtimeOnly(rfg.deobf(Deps.InWorldCrafting))
    }

    compileOnly(rfg.deobf(Deps.JustEnoughItemsApi))
    compileOnly(rfg.deobf(Deps.ExNihiloCreatio))
    if (RuntimeDebug.ExNihiloCreatio) {
        runtimeOnly(rfg.deobf(Deps.ShadowfactsForgelin))
        runtimeOnly(rfg.deobf(Deps.ExNihiloCreatio))
    }

    compileOnly(rfg.deobf(Deps.Baubles))
    compileOnly(rfg.deobf(Deps.Thaumcraft))
    compileOnly(rfg.deobf(Deps.ThaumicJEI))
    if (RuntimeDebug.Thaumcraft) {
        runtimeOnly(rfg.deobf(Deps.Baubles))
        runtimeOnly(rfg.deobf(Deps.Thaumcraft))
        if (RuntimeDebug.ThaumicJEI) {
            runtimeOnly(rfg.deobf(Deps.ThaumicJEI))
        }
    }

    compileOnly(rfg.deobf(Deps.ImmersiveEngineering))
    if (RuntimeDebug.ImmersiveEngineering) {
        runtimeOnly(rfg.deobf(Deps.ImmersiveEngineering))
    }

    compileOnly(rfg.deobf(Deps.BlockDrops))
    if (RuntimeDebug.BlockDrops) {
        runtimeOnly(Deps.BlockDrops)
    }

    compileOnly(Deps.IndustrialCraft)
    if (RuntimeDebug.IndustrialCraft) {
        runtimeOnly(Deps.IndustrialCraft)
    }

    compileOnly(rfg.deobf(Deps.Controlling))
    if (RuntimeDebug.Controlling) {
        runtimeOnly(rfg.deobf(Deps.Controlling))
    }

    compileOnly(rfg.deobf(Deps.LibVulpes))
    compileOnly(rfg.deobf(Deps.AdvancedRocketry))
    if (RuntimeDebug.AdvancedRocketry) {
        runtimeOnly(rfg.deobf(Deps.LibVulpes))
        runtimeOnly(rfg.deobf(Deps.AdvancedRocketry))
    }

    compileOnly(rfg.deobf(Deps.SereneSeasons))
    if (RuntimeDebug.SereneSeasons) {
        runtimeOnly(rfg.deobf(Deps.SereneSeasons))
    }

    compileOnly(Deps.JustEnoughResources)
    if (RuntimeDebug.JustEnoughResources) {
        runtimeOnly(Deps.JustEnoughResources)
    }

    compileOnly(rfg.deobf(Deps.TeslaCoreLib))
    compileOnly(rfg.deobf(Deps.IndustrialForegoing))
    if (RuntimeDebug.IndustrialForegoing) {
        runtimeOnly(rfg.deobf(Deps.ShadowfactsForgelin))
        runtimeOnly(rfg.deobf(Deps.TeslaCoreLib))
        runtimeOnly(rfg.deobf(Deps.IndustrialForegoing))
    }
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
                    this["FMLCorePluginContainsFMLMod"] = true.toString()
                    this["ForceLoadAsMod"] = (project.gradle.startParameter.taskNames[0] == "build").toString()
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
