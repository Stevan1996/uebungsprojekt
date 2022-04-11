package lise.uebungsprojekt.gamePlatform

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
class GamePlatformApplication

fun main(args: Array<String>) {
	runApplication<GamePlatformApplication>(*args)
}