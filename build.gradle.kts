plugins {
    id("java")
    id("org.springframework.boot") version "2.6.0-SNAPSHOT"
    id("io.spring.dependency-management") version "1.1.0"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}
group = "com.lmchecker"
version = "0.0.1-SNAPSHOT"
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    maven("https://repo.spring.io/milestone")
    maven("https://repo.spring.io/snapshot")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
}

tasks {
    jar {
        destinationDirectory
                .set(file("D:\\Codes\\PluginTop\\LM-Checker\\out\\artifacts\\untitled_jar"))
        manifest {
            attributes["Main-Class"] = "com.lmchecker.MainApplication"
        }
    }
    compileJava {
        options.encoding = "UTF-8"
    }
    shadowJar {
        archiveVersion.set("1.0")
        archiveBaseName.set("LM-Checker")
        destinationDirectory.set(
                file("D:\\Codes\\PluginTop\\LM-Checker\\out\\artifacts\\untitled_jar")
        )
    }

}