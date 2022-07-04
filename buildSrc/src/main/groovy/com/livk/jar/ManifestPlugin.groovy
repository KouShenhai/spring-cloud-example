package com.livk.jar

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.bundling.Jar
import org.gradle.util.GradleVersion

/**
 * <p>
 * JarPlugin
 * </p>
 *
 * @author livk
 * @date 2022/6/28
 */
abstract class ManifestPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.pluginManager.apply(JavaPlugin.class)
        (project.tasks.getByName(JavaPlugin.JAR_TASK_NAME) as Jar)
                .manifest.attributes(
                [
                        "Implementation-Group"  : project.group,
                        "Implementation-Title"  : project.name,
                        "Implementation-Version": project.version,
                        "Created-By"            : System.getProperty("java.version") + " (" +
                                (System.getProperty("java.specification.vendor")) + ")",
                        "Gradle-Version"        : GradleVersion.current()
                ]
        )
    }
}
