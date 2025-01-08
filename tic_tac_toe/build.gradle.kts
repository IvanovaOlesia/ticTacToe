
plugins {
    java
    id("org.springframework.boot") version "3.4.0"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("jvm") version "1.9.23"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
//./gradlew bootRun
dependencies {
    implementation("jakarta.platform:jakarta.jakartaee-web-api:10.0.0") // Для веб-приложений
    implementation("jakarta.persistence:jakarta.persistence-api:3.0.0") // JPA
    implementation("jakarta.servlet:jakarta.servlet-api:5.0.0")         // Servlet API
    implementation("jakarta.websocket:jakarta.websocket-api:2.0.0")     // WebSocket API
    implementation("jakarta.json:jakarta.json-api:2.0.0")               // JSON Processing
    implementation("jakarta.inject:jakarta.inject-api:2.0.0")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}