private const val CURSE_MAVEN_GROUP = "curse.maven"

fun curse(modName: String, projectId: Long, fileId: Long): String = "${CURSE_MAVEN_GROUP}:${modName}-${projectId}:${fileId}"
