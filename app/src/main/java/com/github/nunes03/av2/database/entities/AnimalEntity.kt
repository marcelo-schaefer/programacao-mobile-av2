package com.github.nunes03.av2.database.entities

class AnimalEntity : AbstractEntity() {
    var id: Int? = null

    var name: String? = null

    var kind: KindEntity? = null

    var user: UserEntity? = null

    override fun getTableName(): String {
        return "animal"
    }
}
