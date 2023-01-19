rootProject.name = "spring-multi-auth-example"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("spring-boot", "3.0.1")
        }
    }
}