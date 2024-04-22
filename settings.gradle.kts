rootProject.name = "spring-cloud-example"

fileTree(rootDir) {
    include("**/*.gradle.kts")
    exclude("build", "**/gradle", "settings.gradle.kts", "buildSrc", "/build.gradle.kts", ".", "out")
}.forEach {
    val projectPath = it.parentFile.absolutePath
        .replace(rootDir.absolutePath, "")
        .replace(File.separator, ":")
    include(projectPath)
    project(projectPath).projectDir = it.parentFile
    project(projectPath).buildFileName = it.name
}

gradle.settingsEvaluated {
    if (JavaVersion.current() < JavaVersion.VERSION_21) {
        throw GradleException("This build requires JDK 21. It's currently ${JavaVersion.current()}. You can ignore this check by passing '-Dorg.gradle.ignoreBuildJavaVersionCheck'.")
    }
}
