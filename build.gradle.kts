plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.allopen") version "2.0.21"
    id("io.quarkus")
}

repositories {
    maven("https://repo.fintlabs.no/releases")
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project
val fintVersion: String = "3.19.0"

dependencies {
    implementation ("no.fint:fint-utdanning-resource-model-java:${fintVersion}")
    implementation ("no.fint:fint-administrasjon-resource-model-java:${fintVersion}")
    implementation ("no.fint:fint-personvern-resource-model-java:${fintVersion}")
    implementation ("no.fint:fint-okonomi-resource-model-java:${fintVersion}")
    implementation ("no.fint:fint-ressurs-resource-model-java:${fintVersion}")
    implementation ("no.fint:fint-arkiv-resource-model-java:${fintVersion}")

    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-config-yaml")
    implementation("io.quarkus:quarkus-resteasy-mutiny")
    testImplementation("io.quarkus:quarkus-junit5")
}

group = "no.fintlabs"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}
allOpen {
    annotation("jakarta.ws.rs.Path")
    annotation("jakarta.enterprise.context.ApplicationScoped")
    annotation("jakarta.persistence.Entity")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21
        javaParameters = true
    }
}
