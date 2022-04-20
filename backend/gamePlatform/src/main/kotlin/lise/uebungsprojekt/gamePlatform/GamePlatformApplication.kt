package lise.uebungsprojekt.gamePlatform

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
open class GamePlatformApplication

fun main(args: Array<String>) {
	runApplication<GamePlatformApplication>(*args)
}