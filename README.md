# eitri-android

## Importing the eitri-android Maven artifact

### Using Maven Central (3.7.0 and above)

The `eitri-android` artifact is available on Maven Central starting with version **3.7.0**. You can view it here:  
[eitri-android on Maven Central](https://central.sonatype.com/artifact/tech.eitri/eitri-android/overview)

#### Gradle Groovy DSL (`build.gradle`)
```groovy
dependencies {
    implementation "tech.eitri:eitri-android:$version"
}
```

#### Gradle Kotlin DSL (`build.gradle.kts`)
```kotlin
dependencies {
    implementation("tech.eitri:eitri-android:$version")
}
```

#### Maven (`pom.xml`)
```xml
<dependencies>
  <dependency>
    <groupId>tech.eitri</groupId>
    <artifactId>eitri-android</artifactId>
    <version>${version}</version>
  </dependency>
</dependencies>
```

---

### Using GitHub Packages (versions prior to 3.7.0)

For versions **before 3.7.0**, the artifact is published to GitHub Maven Packages. You must configure your repositories accordingly.

#### Gradle Groovy DSL (`settings.gradle`)
```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenLocal()
        google()
        mavenCentral()
        // Eitri Android GitHub Packages repository
        maven {
            name = "GitHubPackages"
            url  = uri("https://maven.pkg.github.com/Calindra/eitri-android")
            credentials {
                // Store these in gradle.properties for security:
                username = project.findProperty("eitri.github.username") ?: "YOUR_GITHUB_USERNAME"
                password = project.findProperty("eitri.github.token")    ?: "YOUR_GITHUB_TOKEN"
            }
        }
    }
}
```

#### Gradle Kotlin DSL (`settings.gradle.kts`)
```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenLocal()
        google()
        mavenCentral()
        maven {
            name = "GitHubPackages"
            url  = uri("https://maven.pkg.github.com/Calindra/eitri-android")
            credentials {
                username = (project.findProperty("eitri.github.username") as String?) ?: "YOUR_GITHUB_USERNAME"
                password = (project.findProperty("eitri.github.token")    as String?) ?: "YOUR_GITHUB_TOKEN"
            }
        }
    }
}
```

> **Note:** you can set properties in **`~/.gradle/gradle.properties`**:
> ```properties
> eitri.github.username=eitri-android
> eitri.github.token=<get your key using eitri-console>
> ```
