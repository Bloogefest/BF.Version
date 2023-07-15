plugins {
    id("java")
    id("maven-publish")
    id("signing")
}

group = "com.bloogefest"
version = "1.0.0-SNAPSHOT"
description = "Библиотека для работы с версиями и их форматами."

repositories {
    mavenCentral()
    maven {
        name = "OSSRH SNAPSHOT"
        url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    implementation("com.bloogefest:annotation:3.0.0-SNAPSHOT")
    implementation("com.bloogefest:common:4.0.0-SNAPSHOT")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("master") {
            artifactId = "version"

            from(components["java"])

            pom {
                name.set("BF.Version")
                description.set(project.description)
                url.set("https://github.com/Bloogefest/BF.Version")

                licenses {
                    license {
                        name.set("Mozilla Public License Version 2.0")
                        url.set("https://mozilla.org/MPL/2.0/")
                    }
                }

                developers {
                    developer {
                        id.set("Bloogefest")
                        name.set("George Sopin")
                        url.set("https://github.com/Bloogefest")
                        timezone.set("W-SU")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/Bloogefest/BF.Version.git")
                    developerConnection.set("scm:git:ssh://github.com/Bloogefest/BF.Version.git")
                    url.set("https://github.com/Bloogefest/BF.Version")
                }

                issueManagement {
                    system.set("Github")
                    url.set("https://github.com/Bloogefest/BF.Version/issues")
                }

                ciManagement {
                    system.set("Github")
                    url.set("https://github.com/Bloogefest/BF.Version/actions")
                }
            }
        }
    }

    repositories {
        maven {
            name = "OSSRH"

            val version = version.toString()
            url = uri(
                when {
                    "-SNAPSHOT" in version -> "https://s01.oss.sonatype.org/content/repositories/snapshots/"
                    "-RC" in version -> "https://s01.oss.sonatype.org/content/repositories/releases/"
                    else -> "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
                }
            )

            credentials {
                username = System.getenv("OSSRH_CREDENTIALS_USERNAME") ?: findProperty(
                    "OSSRH_CREDENTIALS_USERNAME"
                ).toString()
                password = System.getenv("OSSRH_CREDENTIALS_PASSWORD") ?: findProperty(
                    "OSSRH_CREDENTIALS_PASSWORD"
                ).toString()
            }
        }
    }
}

signing {
    useInMemoryPgpKeys(
        System.getenv("SINGING_KEY_ID") ?: findProperty("SINGING_KEY_ID").toString(),
        System.getenv("SINGING_KEY_SECRET") ?: findProperty("SINGING_KEY_SECRET").toString(),
        System.getenv("SINGING_KEY_PASSWORD") ?: findProperty("SINGING_KEY_PASSWORD").toString()
    )

    sign(publishing.publications["master"])
}

configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0, "seconds")
}

tasks.test {
    useJUnitPlatform()
}

tasks.javadoc {
    val options = options as CoreJavadocOptions

    options.encoding = "UTF-8"

    options.addStringOption("Xdoclint:none", "-quiet")
    options.addStringOption("tag", "apiNote:a:API Note:")
    options.addStringOption("tag", "implSpec:a:Implementation Requirements:")
    options.addStringOption("tag", "implNote:a:Implementation Note:")
}

tasks.compileJava {
    options.encoding = "UTF-8"
}

tasks.compileTestJava {
    options.encoding = "UTF-8"
}