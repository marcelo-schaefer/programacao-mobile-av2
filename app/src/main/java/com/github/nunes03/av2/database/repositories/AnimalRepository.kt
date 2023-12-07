package com.github.nunes03.av2.database.repositories;

import android.content.ContentValues
import android.content.Context
import com.github.nunes03.av2.database.DatabaseConnection
import com.github.nunes03.av2.database.entities.AbstractEntity
import com.github.nunes03.av2.database.entities.AnimalEntity
import com.github.nunes03.av2.database.entities.KindEntity
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
        val contentValues = ContentValues()
        contentValues.put("name", entity.name)
        contentValues.put("kind_id", entity.kind?.id)
        contentValues.put("user_id", entity.user?.id)

        return databaseConnection.insert(
            abstractEntity,
            contentValues
        )
    }

    override fun updateById(entity: AnimalEntity) {
        val contentValues = ContentValues()
        contentValues.put("name", entity.name)
        contentValues.put("kind_id", entity.kind?.id)
        contentValues.put("user_id", entity.user?.id)

        databaseConnection.update(
            abstractEntity,
            contentValues,
            "id = ${entity.id}"
        )
    }

    override fun findById(id: Int?): AnimalEntity? {
        return databaseConnection.queryOne(
            abstractEntity,
            animalMapper,
            "id = $id",
            null,
            null
        )
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

    override fun findByKind(kind: KindEntity): ArrayList<AnimalEntity> {
        return ArrayList(
            databaseConnection.query(
                abstractEntity,
                animalMapper,
                "kind_id = ${kind.id}",
                null,
                null
            )
        )
    }

    override fun findByNameContainsAndKind(
        name: String,
        kind: KindEntity
    ): ArrayList<AnimalEntity> {
        return ArrayList(
            databaseConnection.query(
                abstractEntity,
                animalMapper,
                "name like '%$name%' and kind_id = ${kind.id}",
                null,
                null
            )
        )
    }

    override fun deleteById(id: Int?) {
        databaseConnection.delete(abstractEntity, "id = $id")
    }
}
