package com.livk

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin

/**
 * <p>
 * 创建dependency效果的BOM引入器
 * </p>
 *
 * @author livk
 * @date 2022/6/1
 */
abstract class DependencyBomPlugin implements Plugin<Project> {

    public static final String DEPENDENCY_BOM = "dependencyBom"

    @Override
    void apply(Project project) {
        project.pluginManager.apply(JavaPlugin.class)
        def dependencyBom = project.configurations.create(DEPENDENCY_BOM)
        dependencyBom.visible = false
        dependencyBom.canBeResolved = false
        dependencyBom.canBeConsumed = false
        project.plugins.withType(JavaPlugin.class) { javaPlugin ->
            project.configurations.getByName(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME).extendsFrom(dependencyBom)
            project.configurations.getByName(JavaPlugin.API_ELEMENTS_CONFIGURATION_NAME).extendsFrom(dependencyBom)
            project.configurations.getByName(JavaPlugin.COMPILE_ONLY_CONFIGURATION_NAME).extendsFrom(dependencyBom)
            project.configurations.getByName(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME).extendsFrom(dependencyBom)
            project.configurations.getByName(JavaPlugin.TEST_IMPLEMENTATION_CONFIGURATION_NAME).extendsFrom(dependencyBom)
            project.configurations.getByName(JavaPlugin.TEST_COMPILE_ONLY_CONFIGURATION_NAME).extendsFrom(dependencyBom)
            project.configurations.getByName(JavaPlugin.TEST_ANNOTATION_PROCESSOR_CONFIGURATION_NAME).extendsFrom(dependencyBom)
        }
    }
}
