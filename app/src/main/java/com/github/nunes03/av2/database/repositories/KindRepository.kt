package com.github.nunes03.av2.database.repositories

import android.content.ContentValues
import android.content.Context
import com.github.nunes03.av2.database.DatabaseConnection
import com.github.nunes03.av2.database.repositories.interfaces.KindRepositoryInterface
import com.github.nunes03.av2.database.entities.AbstractEntity
import com.github.nunes03.av2.database.entities.KindEntity
import com.github.nunes03.av2.mappers.KindMapper

class KindRepository(context: Context) : KindRepositoryInterface {

    private val databaseConnection: DatabaseConnection

    private val kindMapper: KindMapper = KindMapper()

    private val abstractEntity: AbstractEntity = KindEntity()

    init {
        this.databaseConnection = DatabaseConnection(context)
    }

    override fun create(entity: KindEntity): Long {
        val contentValues = ContentValues()
        contentValues.put("name", entity.name)

        return databaseConnection.insert(
            abstractEntity,
            contentValues
        )
    }

    override fun updateById(entity: KindEntity) {
        val contentValues = ContentValues()
        contentValues.put("name", entity.name)

        databaseConnection.update(
            abstractEntity,
            contentValues,
            "id = ${entity.id}"
        )
    }

    override fun findById(id: Int): KindEntity? {
        return databaseConnection.queryOne(
            abstractEntity,
            kindMapper,
            "id = $id",
            null,
            null
        )
    }

    override fun findAll(): List<KindEntity> {
        return databaseConnection.query(
            abstractEntity,
            kindMapper,
            null,
            null,
            null
        )
    }

    override fun deleteById(id: Int) {
        databaseConnection.delete(abstractEntity, "id = $id")
    }
}