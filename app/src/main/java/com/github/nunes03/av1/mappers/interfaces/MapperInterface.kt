package com.github.nunes03.av1.mappers.interfaces

import android.database.Cursor

interface MapperInterface<T> {

    fun convert(cursor: Cursor): T;
}