repositories {
    mavenCentral()
}

apply {
    plugin("org.jetbrains.kotlin.plugin.spring")
    plugin("org.springframework.boot")
    plugin("io.spring.dependency-management")
}

dependencies {
    implementation(project(":application"))
    implementation(project(":domain"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.jar {
    enabled = true
}
