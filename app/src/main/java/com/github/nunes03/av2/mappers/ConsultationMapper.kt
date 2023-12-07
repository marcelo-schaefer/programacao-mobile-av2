package com.github.nunes03.av2.mappers

import android.database.Cursor
import com.github.nunes03.av2.database.entities.AnimalEntity
import com.github.nunes03.av2.database.entities.ConsultationEntity
import com.github.nunes03.av2.mappers.interfaces.ConsultationMapperInterface

class ConsultationMapper : ConsultationMapperInterface {

    override fun convert(cursor: Cursor): ConsultationEntity {
        val consultationEntity = ConsultationEntity()

        consultationEntity.id = cursor.getInt(0)
        consultationEntity.scheduledTime = cursor.getString(1)
        consultationEntity.description = cursor.getString(2)

        consultationEntity.animal = AnimalEntity()
        consultationEntity.animal?.id = cursor.getInt(3)

        return consultationEntity; }
}