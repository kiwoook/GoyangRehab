import com.ewerk.gradle.plugins.tasks.QuerydslCompile


plugins {
    java
    id("org.springframework.boot") version "3.1.2"
    id("io.spring.dependency-management") version "1.1.2"
    id("com.ewerk.gradle.plugins.querydsl") version "1.0.10"
}

group = "com.study"
version = "0.0.1-SNAPSHOT"
val queryDslVersion = "5.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    implementation("com.querydsl:querydsl-jpa:${queryDslVersion}")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
    compileOnly("org.projectlombok:lombok")
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.0.0")
    runtimeOnly("com.mysql:mysql-connector-j")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")

    annotationProcessor("com.querydsl:querydsl-apt:${queryDslVersion}")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")
    annotationProcessor("jakarta.annotation:jakarta.annotation-api")


}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.getByName<Jar>("jar") {
    enabled = false
}
val querydslDir = "$buildDir/generated/querydsl"

querydsl {
    jpa = true
    querydslSourcesDir = querydslDir
}
sourceSets.getByName("main") {
    java.srcDir(querydslDir)
}
configurations {
    named("querydsl") {
        extendsFrom(configurations.compileClasspath.get())
    }
}
tasks.withType<QuerydslCompile> {
    options.annotationProcessorPath = configurations.querydsl.get()
}

