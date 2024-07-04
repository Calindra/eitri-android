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
                password = "get your key using eitri-console"
            }
        }
        //...
    }
}

```
