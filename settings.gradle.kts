pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "INICIO DEL PROYECTO FINAL INTEGRADOR"
include(":app")
include(":practica02")
include(":practica02:practica02")
include(":practica03")
include(":practica04")
include(":practica05")
include(":practica06")
include(":practica07")
include(":practica08")
include(":practica09")
include(":practica10")
include(":practica11")
