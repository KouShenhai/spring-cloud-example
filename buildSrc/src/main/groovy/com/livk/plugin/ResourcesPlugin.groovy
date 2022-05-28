package com.livk.plugin

import io.spring.javaformat.gradle.tasks.Format
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.compile.JavaCompile

/**
 * <p>
 * ResourcesPlugin
 * </p>
 *
 * @author livk
 * @date 2022/4/24
 */
class ResourcesPlugin implements Plugin<Project> {

    private static final List<String> COMPILER_ARGS = new ArrayList<>()
    private static final String UTF_8 = "UTF-8"
    private static final String SPRING_BOOT_PLUGIN_ID = "org.springframework.boot"

    static {
        COMPILER_ARGS.addAll(Arrays.asList("-Xlint:-options",
                "-Xlint:rawtypes",
                "-Xlint:deprecation",
                "-Xlint:unchecked",))
    }

    @Override
    void apply(Project project) {
        if (project.buildFile.exists()) {
            def javaCompile = project.tasks
                    .getByName(JavaPlugin.COMPILE_JAVA_TASK_NAME)
                    .dependsOn(Format.NAME) as JavaCompile
            if (!project.plugins.hasPlugin(SPRING_BOOT_PLUGIN_ID)) {
                javaCompile.dependsOn(JavaPlugin.PROCESS_RESOURCES_TASK_NAME)
            }
            javaCompile.options.compilerArgs.addAll(COMPILER_ARGS)
            javaCompile.options.encoding = UTF_8
        }
    }
}
