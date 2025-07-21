pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    
}
rootProject.name = "minibank"

include("core")
include("api")
include("consumer")
include("external-system")