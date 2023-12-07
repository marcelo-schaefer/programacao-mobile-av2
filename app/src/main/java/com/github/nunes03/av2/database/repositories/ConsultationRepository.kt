package com.github.nunes03.av2.database.repositories;

import android.content.ContentValues
import android.content.Context
import com.github.nunes03.av2.database.DatabaseConnection
import com.github.nunes03.av2.database.entities.AbstractEntity
import com.github.nunes03.av2.database.entities.ConsultationEntity
import com.github.nunes03.av2.database.repositories.interfaces.ConsultationRepositoryInterface
import com.github.nunes03.av2.mappers.ConsultationMapper

class ConsultationRepository(context: Context) : ConsultationRepositoryInterface {

    private val databaseConnection: DatabaseConnection

    private val consultationMapper = ConsultationMapper()

    private val abstractEntity: AbstractEntity = ConsultationEntity()

    init {
        this.databaseConnection = DatabaseConnection(context)
    }

    override fun create(entity: ConsultationEntity): Long {
        val contentValues = ContentValues()
        contentValues.put("scheduled_time", entity.scheduledTime)
        contentValues.put("description", entity.description)
        contentValues.put("animal_id", entity.animal?.id)

        return databaseConnection.insert(
            abstractEntity,
            contentValues
        )
    }

    override fun updateById(entity: ConsultationEntity) {
        val contentValues = ContentValues()
        contentValues.put("description", entity.description)
        contentValues.put("scheduled_time", entity.scheduledTime)
        contentValues.put("animal_id", entity.animal?.id)

        databaseConnection.update(
            abstractEntity,
            contentValues,
            "id = ${entity.id}"
        )
    }

    override fun findById(id: Int?): ConsultationEntity? {
        return databaseConnection.queryOne(
            abstractEntity,
            consultationMapper,
            "id = $id",
            null,
            null
        )
    }

    override fun findAll(): List<ConsultationEntity> {
        return databaseConnection.query(
            abstractEntity,
            consultationMapper,
            null,
            null,
            null
        )
    }

    override fun deleteById(id: Int?) {
        databaseConnection.delete(abstractEntity, "id = $id")
    }
}
