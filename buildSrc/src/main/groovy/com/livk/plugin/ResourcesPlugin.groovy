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

    private static final List<String> COMPILER_ARGS;

    static {
        COMPILER_ARGS = new ArrayList<>();
        COMPILER_ARGS.addAll(Arrays.asList(
                "-Xlint:-options", "-Xlint:rawtypes",
                "-Xlint:deprecation", "-Xlint:unchecked",));
    }

    @Override
    void apply(Project project) {
        def javaCompile = project.tasks
                .getByName(JavaPlugin.COMPILE_JAVA_TASK_NAME)
                .dependsOn(Format.NAME, JavaPlugin.PROCESS_RESOURCES_TASK_NAME) as JavaCompile
        javaCompile.getOptions().setCompilerArgs(COMPILER_ARGS)
        javaCompile.getOptions().setEncoding("UTF-8")
    }
}
