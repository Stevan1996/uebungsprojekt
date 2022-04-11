package lise.uebungsprojekt.gamePlatform.controller

import lise.uebungsprojekt.gamePlatform.data.Game
import lise.uebungsprojekt.gamePlatform.data.Games
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class DatabaseController {
    init {
        Database.connect("jdbc:mariadb://localhost:3306/lise-mariadb", driver = "org.mariadb.jdbc.Driver",
            user = "steve", password = "test")
    }

    private fun rowToGame(row: ResultRow) : Game{
        return Game(row[Games.id], row[Games.name], row[Games.publishingDate], row[Games.developer])
    }

    @GetMapping("/Game/{id}")
    fun getGame(@PathVariable id: Int) : Game? {
        val result = transaction {
            Games.select(Games.id eq id).map {rowToGame(it)}
        }

        return result.firstOrNull()
    }
}