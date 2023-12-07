package com.github.nunes03.av2.mappers

import android.database.Cursor
import com.github.nunes03.av2.database.entities.UserEntity
import com.github.nunes03.av2.mappers.interfaces.UserMapperInterface

class UserMapper : UserMapperInterface {

    override fun convert(cursor: Cursor): UserEntity {
        val userEntity = UserEntity()

        userEntity.id = cursor.getInt(0)
        userEntity.name = cursor.getString(1)
        userEntity.email = cursor.getString(2)

        return userEntity;
    }
}