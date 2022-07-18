dependencies {
    compileOnly("org.springframework:spring-webmvc")
    compileOnly("org.springframework:spring-webflux")
    compileOnly("io.projectreactor.netty:reactor-netty-http")
    compileOnly("org.springframework.boot:spring-boot-starter")
    compileOnly("com.github.pagehelper:pagehelper-spring-boot-starter")
    compileOnly("com.fasterxml.jackson.core:jackson-databind")
    compileOnly("org.apache.tomcat.embed:tomcat-embed-core")
    compileOnly("com.squareup.okhttp3:okhttp")
    api("org.apache.commons:commons-lang3")
    api("com.google.code.findbugs:annotations")
}
