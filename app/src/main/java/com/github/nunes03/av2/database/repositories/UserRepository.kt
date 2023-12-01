package com.github.nunes03.av2.database.repositories

import com.github.nunes03.av2.database.repositories.interfaces.UserRepositoryInterface
import com.github.nunes03.av2.database.entities.UserEntity

class UserRepository : UserRepositoryInterface {

    override fun create(entity: UserEntity): Long {
        TODO("Not yet implemented")
    }

    override fun updateById(entity: UserEntity) {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): UserEntity {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<UserEntity> {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int) {
        TODO("Not yet implemented")
    }
}