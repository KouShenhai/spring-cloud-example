package com.livk.cloud

import com.livk.cloud.info.BootPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin

/**
 * <p>
 * ServicePlugin
 * </p>
 *
 * @author livk
 * @date 2022/8/10
 */
class ServicePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.pluginManager.apply(JavaPlugin::class.java)
        project.pluginManager.apply(ModulePlugin::class.java)
        project.pluginManager.apply(BootPlugin::class.java)

        val bootstrap = project.dependencies.create("org.springframework.cloud:spring-cloud-starter-bootstrap")
        val loadbalancer = project.dependencies.create("org.springframework.cloud:spring-cloud-starter-loadbalancer")

        project.dependencies.add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, bootstrap)
        project.dependencies.add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, loadbalancer)
    }
}
