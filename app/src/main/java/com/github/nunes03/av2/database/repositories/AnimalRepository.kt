package com.github.nunes03.av2.database.repositories;

import android.content.Context
import com.github.nunes03.av2.database.DatabaseConnection
import com.github.nunes03.av2.database.entities.AbstractEntity
import com.github.nunes03.av2.database.entities.AnimalEntity
import com.github.nunes03.av2.database.repositories.interfaces.AnimalRepositoryInterface
import com.github.nunes03.av2.mappers.AnimalMapper

class AnimalRepository(context: Context) : AnimalRepositoryInterface {

    private val databaseConnection: DatabaseConnection

    private val animalMapper = AnimalMapper()

    private val abstractEntity: AbstractEntity = AnimalEntity()

    init {
        this.databaseConnection = DatabaseConnection(context)
    }

    override fun create(entity: AnimalEntity): Long {
        TODO("Not yet implemented")
    }

    override fun updateById(entity: AnimalEntity) {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): AnimalEntity? {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<AnimalEntity> {
        return databaseConnection.query(
            abstractEntity,
            animalMapper,
            null,
            null,
            null
        )
    }

    override fun deleteById(id: Int) {
        TODO("Not yet implemented")
    }
}
