# eitri-android

## How to import eitri-android maven repo?

```kt

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenLocal()
        google()
        mavenCentral()
        // ... eitri-android maven repo
        maven {
            url = uri("https://maven.pkg.github.com/Calindra/eitri-android")
            credentials {
                username = "eitri-android"
                password = "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"
            }
        }
        //...
    }
}

```
