package com.github.nunes03.av2.database.repositories

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
        TODO("Not yet implemented")
    }

    override fun updateById(entity: UserEntity) {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): UserEntity? {
        return databaseConnection.queryOne(
            abstractEntity,
            userMapper,
            "id = $id",
            null,
            null
        )
    }

    override fun findAll(): List<UserEntity> {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int) {
        TODO("Not yet implemented")
    }
}