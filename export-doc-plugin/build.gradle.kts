plugins {
    kotlin("jvm") version "1.9.0"
    id("java")
    id("org.jetbrains.intellij") version "1.16.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}

intellij {
    version.set("2022.3")
    type.set("IC")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}

patchPluginXml {
    changeNotes.set("Initial version")
}

