plugins {
    kotlin("jvm") version "1.9.0" apply false
    kotlin("plugin.spring") version "1.9.0" apply false
    kotlin("plugin.serialization") version "1.9.0" apply false
    id("org.springframework.boot") version "3.2.0" apply false
    id("io.spring.dependency-management") version "1.1.3" apply false
    id("com.google.protobuf") version "0.9.4" apply false
}

allprojects {
    group = "minibank"
    version = "1.0.0"

    repositories {
        mavenCentral()
    }
}
