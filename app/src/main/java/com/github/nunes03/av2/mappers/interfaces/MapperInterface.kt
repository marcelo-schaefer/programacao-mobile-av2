package com.github.nunes03.av2.mappers.interfaces

import android.database.Cursor
import com.github.nunes03.av2.database.entities.AbstractEntity

interface MapperInterface<T : AbstractEntity> {

    fun convert(cursor: Cursor): T;
}