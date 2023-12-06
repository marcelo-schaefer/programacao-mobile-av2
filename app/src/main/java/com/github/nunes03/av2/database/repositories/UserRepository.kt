package com.github.nunes03.av2.database.repositories

import android.content.ContentValues
import android.content.Context
import com.github.nunes03.av2.database.DatabaseConnection
import com.github.nunes03.av2.database.entities.AbstractEntity
import com.github.nunes03.av2.database.entities.UserEntity
import com.github.nunes03.av2.database.repositories.interfaces.UserRepositoryInterface
import com.github.nunes03.av2.mappers.UserMapper

class UserRepository(context: Context) : UserRepositoryInterface {

    private val databaseConnection: DatabaseConnection

    private val userMapper: UserMapper = UserMapper()

    private val abstractEntity: AbstractEntity = UserEntity()

    init {
        this.databaseConnection = DatabaseConnection(context)
    }

    override fun create(entity: UserEntity): Long {
        val contentValues = ContentValues()
        contentValues.put("name", entity.name)
        contentValues.put("email", entity.email)

        return databaseConnection.insert(
            abstractEntity,
            contentValues
        )
    }

    override fun updateById(entity: UserEntity) {
        val contentValues = ContentValues()
        contentValues.put("name", entity.name)
        contentValues.put("email", entity.email)

        databaseConnection.update(
            abstractEntity,
            contentValues,
            "id = ${entity.id}"
        )
    }

    override fun findById(id: Int?): UserEntity? {
        return databaseConnection.queryOne(
            abstractEntity,
            userMapper,
            "id = $id",
            null,
            null
        )
    }

    override fun findAll(): List<UserEntity> {
        return databaseConnection.query(
            abstractEntity,
            userMapper,
            null,
            null,
            null
        )
    }

    override fun deleteById(id: Int) {
        databaseConnection.delete(abstractEntity, "id = $id")
    }
}