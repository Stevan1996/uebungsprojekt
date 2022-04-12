package lise.uebungsprojekt.gamePlatform.game.entity

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import java.time.LocalDate

enum class GameRating(val numStar: Int) {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5)
}

object Games : Table("Games") {
    val id = integer("id").autoIncrement()
    val name = varchar("Titel", 40)
    val releaseDate = date("Erscheinungsdatum")
    val developer = varchar("Entwickler", 20)

    override val primaryKey = PrimaryKey(id)
}

data class Game(val id: Int, val title: String, val releaseDate: LocalDate, val developer: String)

object Ratings : IntIdTable("Ratings") {
    val rating = enumeration("Bewertung", GameRating::class)
    val comment = varchar("Kommentar", 100)
    val ratedGame = (integer("Spiele_ID").references(Games.id))
}

data class Rating(val id: Int, val rating: GameRating, val comment: String, val game_id : Int)