package com.github.nunes03.av2.mappers

import android.database.Cursor
import com.github.nunes03.av2.database.entities.AnimalEntity
import com.github.nunes03.av2.database.entities.KindEntity
import com.github.nunes03.av2.database.entities.UserEntity
import com.github.nunes03.av2.mappers.interfaces.AnimalMapperInterface

class AnimalMapper: AnimalMapperInterface {

    override fun convert(cursor: Cursor): AnimalEntity {
        val animalEntity = AnimalEntity()

        animalEntity.id = cursor.getInt(0)
        animalEntity.name = cursor.getString(1)

        animalEntity.kind = KindEntity()
        animalEntity.kind?.id = cursor.getInt(2)

        animalEntity.user = UserEntity()
        animalEntity.user?.id = cursor.getInt(3)

        return animalEntity;    }
}