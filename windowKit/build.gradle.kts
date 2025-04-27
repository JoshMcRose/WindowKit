import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.DokkaBaseConfiguration
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.dokka)
    alias(libs.plugins.maven.publish)
}

buildscript {
    dependencies {
        classpath(libs.dokka.base)
    }
}

group = "io.github.joshmcrose"
version = "0.1.0"

kotlin {
    jvm("desktop") {
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }

    sourceSets {
        val desktopMain by getting
        val desktopTest by getting

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(libs.aakira.napier)
            implementation(compose.components.uiToolingPreview)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
        desktopTest.dependencies {
            implementation(kotlin("test"))
            implementation(kotlin("test-junit5"))
        }
    }
}

tasks.withType<DokkaTask>().configureEach {
    moduleName = "WindowKit"
    moduleVersion = version.toString()
    outputDirectory = rootDir.resolve("docs")
    pluginConfiguration<DokkaBase, DokkaBaseConfiguration> {
        customAssets = listOf(rootDir.resolve("assets/logo-icon.svg"))
        customStyleSheets = listOf(rootDir.resolve("assets/style.css"), rootDir.resolve("assets/prism.css"), rootDir.resolve("assets/main.css"), rootDir.resolve("assets/ui.css"))
        homepageLink = "https://github.com/JoshMcRose/WindowKit"
        templatesDir = rootDir.resolve("dokka")
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
    coordinates(group.toString(), "windowkit", version.toString())

    pom {
        name = "WindowKit"
        description = "A library for creating custom Windows and Title Bars in Compose for Desktop."
        inceptionYear = "2025"
        url = "https://github.com/JoshMcRose/WindowKit"
        licenses {
            license {
                name = "MIT License"
                url = "https://opensource.org/license/MIT"
                distribution = "https://opensource.org/license/MIT"
            }
        }
        developers {
            developer {
                id = "joshmcrose"
                name = "Josh Rose"
                url = "https://github.com/JoshMcRose"
            }
        }
        scm {
            url = "https://github.com/JoshMcRose/WindowKit"
            connection = "scm:git:git://github.com/JoshMcRose/WindowKit.git"
            developerConnection = "scm:git:ssh://git@github.com/JoshMcRose/WindowKit.git"
        }
    }
}