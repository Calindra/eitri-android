
# eitri-android

## Importing the eitri-android Maven artifact

The `eitri-android` artifact is available on Maven Central. You can view it here:  
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

### Minimum Requirements

- minSdkVersion - Android lollipop 5.0+

---


### Migrating from legacy GitHub Packages

For users trying to update to newer versions or migrating from legacy GitHub Packages, you must configure your repositories accordingly.

#### Gradle (`settings.gradle` or `settings.gradle.kts`)

Please remove the following block where a Maven repo was declared:

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenLocal()
        google()
        mavenCentral()
        // REMOVE ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
        maven {
            name = "GitHubPackages"
            url  = uri("https://maven.pkg.github.com/Calindra/eitri-android")
            credentials {
                // Store these in gradle.properties for security:
                username = project.findProperty("eitri.github.username") ?: "YOUR_GITHUB_USERNAME"
                password = project.findProperty("eitri.github.token")    ?: "YOUR_GITHUB_TOKEN"
            }
        }
        // REMOVE.END ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    }
}
```

Then, update your implementation line by changing the group from `tech.calindra.eitri` to `tech.eitri`:

```groovy
// change this
implementation "tech.calindra.eitri:eitri-android:$version"
// to
implementation "tech.eitri:eitri-android:$version"
```

#### My App uses 2.x.y version

Change your version to the latest 2.x.y release:

[2.36.1](https://central.sonatype.com/artifact/tech.eitri/eitri-android/2.36.1)

Version 2.36.1 is backward compatible with all previously released 2.x.y versions.
