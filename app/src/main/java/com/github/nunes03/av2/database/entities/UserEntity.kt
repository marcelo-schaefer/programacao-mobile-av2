package com.github.nunes03.av2.database.entities

class UserEntity : AbstractEntity() {

    var id: Int? = null

    var name: String? = null

    var email: String? = null

    override fun getTableName(): String {
        return "user"
    }
}
