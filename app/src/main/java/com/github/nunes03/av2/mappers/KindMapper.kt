package com.github.nunes03.av2.mappers

import android.database.Cursor
import com.github.nunes03.av2.database.entities.KindEntity
import com.github.nunes03.av2.mappers.interfaces.KindMapperInterface

class KindMapper : KindMapperInterface {

    override fun convert(cursor: Cursor): KindEntity {
        val kindEntity = KindEntity()

        kindEntity.id = cursor.getInt(0)
        kindEntity.name = cursor.getString(1)

        return kindEntity;
    }
}