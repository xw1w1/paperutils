plugins {
    id("java")
    id("io.papermc.paperweight.userdev") version "1.5.5"
    id("xyz.jpenilla.run-paper") version "2.2.0"
    kotlin("jvm") version "1.9.20-Beta2"
}

group = "ru.epserv"
version = "1.0-SNAPSHOT"
description = "deltabrix's utils for PaperMC minecraft servers"

dependencies {
    paperweight.paperDevBundle("1.20.2-R0.1-SNAPSHOT")
    implementation(kotlin("stdlib-jdk8"))
}

tasks {

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(19)
    }

    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }

    processResources {
        filteringCharset = Charsets.UTF_8.name()
        val props = mapOf(
                "name" to project.name,
                "version" to project.version,
                "description" to project.description,
        )
        inputs.properties(props)
        filesMatching("plugin.yml") {
            expand(props)
        }
    }
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(19)
}