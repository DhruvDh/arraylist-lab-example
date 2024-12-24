plugins {
    id("java-library")
    id("info.solidsoft.pitest") version "1.15.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")
    testImplementation("org.pitest:pitest-junit5-plugin:1.2.0")
}

// Custom source directories (instead of Gradle's default src/main/java, src/test/java)
sourceSets {
    main {
        java.srcDirs("src")
    }
    test {
        java.srcDirs("test")
    }
}

// Use Java 17 via Gradle Toolchains
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

// Tell JUnit to use the Jupiter engine
tasks.test {
    useJUnitPlatform()
}

// PIT Mutation Testing config
pitest {
    targetClasses.set(setOf("DataStructures.*"))
    threads.set(4)
    outputFormats.set(setOf("XML"))
    timestampedReports.set(false)
    junit5PluginVersion.set("1.2.0")
    mutators.set(setOf("STRONGER"))
}
