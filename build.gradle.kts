import Setting.mixinJson
import org.jetbrains.gradle.ext.Gradle
import org.jetbrains.gradle.ext.compiler
import org.jetbrains.gradle.ext.runConfigurations
import org.jetbrains.gradle.ext.settings

plugins {
    alias(libs.plugins.idea.ext)
    alias(libs.plugins.retrofuturagradle)
    alias(libs.plugins.cursegradle)
    alias(libs.plugins.check)
}

version = Setting.MOD_VERSION
group = Setting.ROOT_PACKAGE

base {
    archivesName = Setting.MOD_ID
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.versions.jvm.get()))
        // Azul covers the most platforms for Java 8 toolchains, crucially including MacOS arm64
        vendor.set(JvmVendorSpec.AZUL)
    }
    // Generate sources and javadocs jars when building and publishing
    if (Setting.GENERATE_SOURCES_JAR) {
        withSourcesJar()
    }
    if (Setting.GENERATE_JAVADOC_JAR) {
        withJavadocJar()
    }
}

configurations {
    val embed = create("embed")
    implementation.configure {
        extendsFrom(embed)
    }
}

minecraft {
    mcVersion = "1.12.2"

    // MCP Mappings
    mcpMappingChannel = Setting.MAPPING_CHANNEL
    mcpMappingVersion = Setting.MAPPING_VERSION

    // Include and use dependencies' Access Transformer files
    useDependencyAccessTransformers = Setting.USE_DEPENDENCY_AT_FILES

    // Set username here, the UUID will be looked up automatically
    username = Setting.MINECRAFT_USERNAME

    // Add any additional tweaker classes here
    // extraTweakClasses.add("org.spongepowered.asm.launch.MixinTweaker")
    extraTweakClasses.addAll(Setting.EXTRA_TWEAK_CLASSES)

    // Add various JVM arguments here for runtime
    val args = buildList {
        add("-ea:${group}")
        if (Setting.USE_MIXINS) {
            add("-Dmixin.hotSwap=true")
            add("-Dmixin.checks.interfaces=true")
            add("-Dmixin.debug.export=true")
        }
        addAll(Setting.EXTRA_JVM_ARGS)
    }
    extraRunJvmArguments.addAll(args)

    // Add any properties you want to swap out for a dynamic value at build time here
    // Any properties here will be added to a class at build time, the name can be configured below
    if (Setting.USE_MIXINS) {
        injectedTags.put("MOD_VERSION", Setting.MOD_VERSION)
        injectedTags.put("MOD_ID", Setting.MOD_ID)
        injectedTags.put("MOD_NAME", Setting.MOD_NAME)
    }
}

repositories {
    maven {
        name = "CleanroomMC Maven"
        url = uri("https://maven.cleanroommc.com")
    }
}

dependencies {
    if (Setting.USE_ASSET_MOVER) {
        implementation(libs.assetmover)
    }

    if (Setting.USE_MIXINS) {
        val mixinbooter = libs.mixinbooter
        modUtils.enableMixins(mixinbooter, Setting.MIXIN_REFMAP)
        api(mixinbooter) {
            isTransitive = false
        }
        annotationProcessor(mixinbooter) {
            isTransitive = false
        }
        annotationProcessor(libs.asm.debug.all)
        annotationProcessor(libs.guava)
        annotationProcessor(libs.gson)
    }
}

apply(from = "gradle/scripts/dependencies.gradle")

strictNullCheck {
    packageInfo {
        imports = listOf("org.jspecify.annotations.NullMarked")
        annotations = listOf("@NullMarked")
    }
}

val resourcesPath: File = sourceSets.main.get().resources.srcDirs.first()

// Adds Access Transformer files to tasks
if (Setting.USE_ACCESS_TRANSFORMER) {
    for (location in Setting.ACCESS_TRANSORMER_LOCATIONS) {
        val fileLocation = file("${resourcesPath}/${location}")
        if (!fileLocation.exists()) {
            throw GradleException("Access Transformer file [$fileLocation] does not exist!")
        }
        tasks.deobfuscateMergedJarToSrg.get().accessTransformerFiles.from(fileLocation)
        tasks.srgifyBinpatchedJar.get().accessTransformerFiles.from(fileLocation)
    }
}

tasks.withType<ProcessResources> {
    // This will ensure that this task is redone when the versions change
    val properties = buildMap {
        this["mod_id"] = Setting.MOD_ID
        this["mod_name"] = Setting.MOD_NAME
        this["mod_version"] = Setting.MOD_VERSION
        this["mod_description"] = Setting.MOD_DESCRIPTION
        this["mod_authors"] = Setting.MOD_AUTHORS.joinToString(", ")
        this["mod_credits"] = Setting.MOD_CREDITS
        this["mod_url"] = Setting.MOD_URL
        this["mod_update_json"] = Setting.MOD_UPDATE_JSON
        this["mod_logo_path"] = Setting.MOD_LOGO_PATH
        this["mixin_refmap"] = Setting.MIXIN_REFMAP
    }
    inputs.properties(properties)
    inputs.property("mixin_configs", Setting.MININ_CONFIGS.joinToString(" "))

    val filterList = buildList {
        add("mcmod.info")
        add("pack.mcmeta")
        addAll(Setting.MININ_CONFIGS.map { mixinJson(it) })
    }

    // Replace various properties in mcmod.info and pack.mcmeta if applicable
    filesMatching(filterList) {
        expand(properties)
    }

    if (Setting.USE_ACCESS_TRANSFORMER) {
        rename("(.+_at.cfg)", "META-INF/$1") // Make sure Access Transformer files are in META-INF folder
    }
}

tasks.withType<Jar> {
    val attributeMap = buildMap {
        if (Setting.IS_COREMOD) {
            this["FMLCorePlugin"] = Setting.COREMOD_PLUGIN_CLASS_NAME

            if (Setting.COREMOD_INCLUDES_MOD) {
                this["FMLCorePluginContainsFMLMod"] = true.toString()

                when (project.gradle.startParameter.taskNames.first()) {
                    "build", "prepareObfModsFolder", "runObfClient" -> {
                        this["ForceLoadAsMod"] = true.toString()
                    }
                }
            }
        }
        if (Setting.USE_ACCESS_TRANSFORMER) {
            this["FMLAT"] = Setting.ACCESS_TRANSORMER_LOCATIONS.joinToString(" ")
        }
    }
    manifest.attributes(attributeMap)
    // Add all embedded dependencies into the jar
    from(provider {
        configurations.getByName("embed").map {
            if (it.isDirectory()) it else zipTree(it)
        }
    })
}

idea {
    module {
        inheritOutputDirs = true
    }
    project {
        settings {
            val list = listOf(
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
            runConfigurations.addAll(list)

            compiler.javac {
                afterEvaluate {
                    javacAdditionalOptions = "-encoding utf8"
                    moduleJavacAdditionalOptions = mutableMapOf(
                        (project.name + ".main") to tasks.compileJava.get().options.compilerArgs.joinToString(" ") { "\"$it\"" }
                    )
                }
            }
        }
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

val helpersGroup = "cleanroom helpers"

val generateMixinJsonTask = tasks.register("generateMixinJson") {
    group = helpersGroup

    val mixinsJsonPath = { text: String -> "${resourcesPath}/${mixinJson(text)}" }

    val missingConfigs = Setting.MININ_CONFIGS.filterNot { file(mixinsJsonPath(it)).exists() }

    onlyIf { Setting.USE_MIXINS && Setting.GENERATE_MIXINS_JSON && !missingConfigs.isEmpty() }

    doLast {
        for (string in missingConfigs) {
            val file = file(mixinsJsonPath(string))

            if (!file.createNewFile()) {
                throw GradleException("File [$file] create error!")
            }
            val jsonContent = """
                |{
                |  "package": "",
                |  "required": true,
                |  "refmap": "${Setting.MIXIN_REFMAP}",
                |  "target": "@env(DEFAULT)",
                |  "minVersion": "0.8.5",
                |  "compatibilityLevel": "JAVA_8",
                |  "mixins": [],
                |  "server": [],
                |  "client": []
                |}
            """.trimMargin()

            file.writeText(jsonContent)
        }
    }
}

tasks.injectTags.configure {
    onlyIf { Setting.USE_TAGS && tags.get().isNotEmpty() }
    outputClassName = Setting.TAG_CLASS_NAME
}

tasks.processIdeaSettings.configure {
    dependsOn(tasks.injectTags.name, generateMixinJsonTask.name)
}

val prioritizeCoremodsTask = tasks.register("prioritizeCoremods") {
    group = helpersGroup

    doLast {
        val regex = Regex("(mixinbooter|configanytime)(-)([0-9])+\\\\.+([0-9])+(.jar)")
        fileTree("run/obfuscated").forEach {
            if (it.isFile && regex.find(it.name) != null) {
                it.renameTo(File(it.parentFile, "!${it.name}"))
            }
        }
    }
}

tasks.prepareObfModsFolder.configure {
    finalizedBy(prioritizeCoremodsTask.name)
}
