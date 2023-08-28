import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler

private const val CURSE_MAVEN_GROUP = "curse.maven"

fun Project.curse(mod: String, projectId: Long, fileId: Long): String {
    require(projectId >= 0) { "The project ID '$projectId' for mod '$mod' is negative." }
    require(fileId >= 0) { "The file ID '$fileId' for mod '$mod' is negative." }
    return "${CURSE_MAVEN_GROUP}:${mod}-${projectId}:${fileId}"
}
