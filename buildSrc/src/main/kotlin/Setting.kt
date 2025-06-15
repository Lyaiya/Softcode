object Setting {
    // Compilation Options
    const val GENERATE_SOURCES_JAR = true
    const val GENERATE_JAVADOC_JAR = true

    // Testing
    const val ENABLE_JUNIT_TESTING = true
    const val SHOW_TESTING_OUTPUT = true

    // Mod Information
    // HIGHLY RECOMMEND complying with SemVer for mod_version: https://semver.org/
    const val MOD_VERSION = "1.0.8"
    const val ROOT_PACKAGE = "com.lyaiya"
    const val MOD_ID = "softcode"
    const val MOD_NAME = "Softcode"

    // Mod Metadata (Optional)
    const val MOD_DESCRIPTION = ""
    const val MOD_URL = ""
    const val MOD_UPDATE_JSON = "https://github.com/Lyaiya/Softcode"

    // Delimit authors with commas
    val MOD_AUTHORS: List<String> = listOf("Lyaiya")
    const val MOD_CREDITS = ""
    const val MOD_LOGO_PATH = ""

    // Mapping Properties
    const val MAPPING_CHANNEL = "stable"
    const val MAPPING_VERSION = "39"
    const val USE_DEPENDENCY_AT_FILES = true

    // Run Configurations
    // If multiple arguments/tweak classes are stated, use spaces as the delimiter
    const val MINECRAFT_USERNAME = "Lyaiya"
    val EXTRA_JVM_ARGS: List<String> = emptyList()
    val EXTRA_TWEAK_CLASSES: List<String> = emptyList()

    // If any properties changes below this line, refresh gradle again to ensure everything is working correctly.

    // Modify Minecraft Sources
    // RetroFuturaGradle allows Minecraft sources to be edited, and have the changes reflected upon running it
    // Good for previews when coremodding, or generally seeing how behaviours can change with certain code applied/unapplied
    // Turning this on allows Minecraft sources to persist and not regenerate
    const val CHANGE_MINECRFAT_SOURCES = false

    // Tags
    // A RetroFuturaGradle concept akin to Ant ReplaceTokens
    // A class is generated at build-time for compilation, to describe properties that have values that could change at build time such as versioning
    // Class name is configurable with the `tag_class_name` property
    // Tag properties can be stated in the `tags.properties` file, references are allowed
    const val USE_TAGS = true
    const val TAG_CLASS_NAME = "${ROOT_PACKAGE}.${MOD_ID}.Tags"

    // Access Transformers
    // A way to change visibility of Minecraft's classes, methods and fields
    // An example access transformer file is given in the path: `src/main/resources/example_at.cfg`
    // AT files should be in the root of src/main/resources with the filename formatted as: `mod_id_at.cfg`
    // Use the property `access_transformer_locations` to state custom AT files if you aren't using the default `mod_id_at.cfg` location
    // If multiple locations are stated, use spaces as the delimiter
    const val USE_ACCESS_TRANSFORMER = false
    val ACCESS_TRANSORMER_LOCATIONS = listOf("${MOD_ID}_at.cfg")

    // Mixins
    // Powerful tool to do runtime description changes of classes
    // Wiki: https://github.com/SpongePowered/Mixin/wiki + https://github.com/CleanroomMC/MixinBooter/ + https://cleanroommc.com/wiki/forge-mod-development/mixin/preface
    // Only use mixins once you understand the underlying structure
    const val USE_MIXINS = true

    // A configuration defines a mixin set, and you may have as many mixin sets as you require for your application.
    // Each config can only have one and only one package root.
    // Generate missing configs, obtain from mixin_configs and generate file base on name convention: "mixins.config_name.json"
    // You should change package root once they are generated
    const val GENERATE_MIXINS_JSON = true

    // Delimit configs with spaces. Should only put configs name instead of full file name
    val MININ_CONFIGS = listOf(MOD_ID)

    // A refmap is a json that denotes mapping conversions, this json is generated automatically, with the name `mixins.mod_id.refmap.json`
    // Use the property `mixin_refmap` if you want it to use a different name, only one name is accepted
    const val MIXIN_REFMAP = "mixin.${MOD_ID}.refmap.json"

    // Coremods
    // The most powerful way to change java classes at runtime, it is however very primitive with little documentation.
    // Only make a coremod if you are absolutely sure of what you are doing
    // Change the property `coremod_includes_mod` to false if your coremod doesn't have a @Mod annotation
    // You MUST state a class name for `coremod_plugin_class_name` if you are making a coremod, the class should implement `IFMLLoadingPlugin`
    const val IS_COREMOD = false
    const val COREMOD_INCLUDES_MOD = true
    const val COREMOD_PLUGIN_CLASS_NAME = ""

    // AssetMover
    // Convenient way to allow downloading of assets from official vanilla Minecraft servers, CurseForge, or any direct links
    // Documentation: https://github.com/CleanroomMC/AssetMover
    const val USE_ASSET_MOVER = false

    fun mixinJson(text: String): String = "mixins.${text}.json"
}