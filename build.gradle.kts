val ktor_version: String by project
val logback_version: String by project
val hikari_version: String by project
val mysql_connector_version: String by project
val exposed_version: String by project
plugins {
    kotlin("jvm") version "1.9.22"
    id("io.ktor.plugin") version "2.3.12"
    kotlin("plugin.serialization") version "2.0.20"
}

group = "ru.shvetsov.todoList"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //Logback
    implementation("ch.qos.logback:logback-classic:$logback_version")
    //Ktor server
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-server-http-redirect:$ktor_version")
    implementation("io.ktor:ktor-server-cors:$ktor_version")
    //Serialization
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    //Exposed database
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("mysql:mysql-connector-java:$mysql_connector_version")
    implementation("com.zaxxer:HikariCP:$hikari_version")
    //JWT
    implementation("io.ktor:ktor-server-auth:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jwt:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}