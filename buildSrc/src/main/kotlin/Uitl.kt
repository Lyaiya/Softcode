private const val CURSE_MAVEN_GROUP = "curse.maven"

fun curse(modName: String, projectId: Long, fileId: Long, sourcesFileId: Long? = null, deobfFileId: Long? = null, apiFileId: Long? = null): String {
    val otherFileIdList = buildList {
        if (sourcesFileId != null) {
            add("sources-$sourcesFileId")
        }
        if (deobfFileId != null) {
            add("deobf-$deobfFileId")
        }
        if (apiFileId != null) {
            add("api-$apiFileId")
        }
    }
    val finalStr = if (otherFileIdList.isNotEmpty()) {
        otherFileIdList.joinToString(separator = "-", prefix = "-")
    } else {
        ""
    }
    return "${CURSE_MAVEN_GROUP}:${modName}-${projectId}:${fileId}${finalStr}"
}
