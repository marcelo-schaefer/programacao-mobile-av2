package com.github.nunes03.av2.database.repositories

import android.content.ContentValues
import android.content.Context
import com.github.nunes03.av2.database.DatabaseConnection
import com.github.nunes03.av2.database.repositories.interfaces.KindRepositoryInterface
import com.github.nunes03.av2.entities.KindEntity
import com.github.nunes03.av2.mappers.KindMapper

class KindRepository(context: Context) : KindRepositoryInterface {

    private val databaseConnection: DatabaseConnection

    private val kindMapper: KindMapper = KindMapper()

    init {
        this.databaseConnection = DatabaseConnection(context)
    }

    override fun create(entity: KindEntity): Long {
        val contentValues = ContentValues()
        contentValues.put("name", entity.name)

        return databaseConnection.insert(
            KindEntity(),
            contentValues
        )
    }

    override fun update(entity: KindEntity) {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): KindEntity? {
        return databaseConnection.queryOne(
            KindEntity(),
            kindMapper,
            "id = $id",
            null,
            null
        )
    }

    override fun findAll(): List<KindEntity> {
        return databaseConnection.query(
            KindEntity(),
            kindMapper,
            null,
            null,
            null
        )
    }

    override fun deleteById(id: Int) {
        TODO("Not yet implemented")
    }
}