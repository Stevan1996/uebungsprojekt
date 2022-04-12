package lise.uebungsprojekt.gamePlatform.game.repository

import lise.uebungsprojekt.gamePlatform.game.entity.Game
import lise.uebungsprojekt.gamePlatform.game.entity.Games
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

interface GameRepository{
    fun createTable()
    fun findAll(): List<Game>
    fun findById(id: Int): Game?
    fun existsById(id: Int): Boolean
    fun count(): Long
    fun saveAll(entities: List<Game?>)
    fun delete(g: Game)
    fun deleteAll(): Int
    fun deleteAll(g: List<Game>)
    fun deleteAllById(entities: List<Int>)
    fun deleteById(id: Int)

}

@Repository
@Transactional
class GameRepositoryImpl : GameRepository {

    override fun createTable() = SchemaUtils.create(Games)

    override fun count() = Games.selectAll().count()
    fun <S : Game?> save(entity: S): S {
        if (entity == null) {
            throw IllegalArgumentException("Entity is null")
        }
        Games.insert {
            it[Games.name] = entity.title
            it[Games.developer] = entity.developer
            it[Games.releaseDate] = entity.releaseDate
        }

        return entity
    }

    override fun saveAll(entities: List<Game?>){
        for(entity in entities){
            save(entity)
        }
    }

    override fun existsById(id: Int): Boolean {
        return Games.select(Games.id eq id).map {rowToGame(it)}.isNotEmpty()
    }

    override fun findById(id: Int): Game? {
        val result = Games.select(Games.id eq id).map {rowToGame(it)}
        return result.firstOrNull()
    }

    override fun findAll() = Games.selectAll().map {rowToGame(it)}
    fun findAllById(ids: MutableIterable<Int>): MutableIterable<Game> {
        val result = mutableListOf<Game>()
        for(id in ids) {
            val tmp = findById(id)
            if(tmp != null) {
                result.add(tmp)
            }
        }

        return result
    }

    override fun deleteAll() = Games.deleteAll()

    override fun deleteById(id: Int) {
        Games.deleteWhere { Games.id eq id }
    }

    override fun delete(game: Game) {
        deleteById(game.id)
    }

    override fun deleteAllById(ids: List<Int>) {
        for(id in ids) {
            deleteById(id)
        }
    }

    override fun deleteAll(entities: List<Game>) {
        for(entity in entities) {
            delete(entity)
        }
    }

    private fun rowToGame(row: ResultRow): Game {
        return Game(row[Games.id], row[Games.name], row[Games.releaseDate], row[Games.developer])
    }
}