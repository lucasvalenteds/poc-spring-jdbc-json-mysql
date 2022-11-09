plugins {
    id("org.springframework.boot") version "3.0.0-RC1"
    id("io.spring.dependency-management") version "1.1.0"
    java
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(19))
    }
}

configurations {
    compileOnly {
        extendsFrom(annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven(url = "https://repo.spring.io/milestone")
}

ext {
    set("testcontainersVersion", "1.17.4")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-mysql")
    compileOnly("org.projectlombok:lombok")
    implementation("com.mysql:mysql-connector-j")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:mysql")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:${ext["testcontainersVersion"]}")
    }
}

tasks.test {
    useJUnitPlatform()
}
