package com.github.nunes03.av2.entities

class KindEntity : AbstractEntity() {

    var id: Int? = null;

    var name: String? = null;

    override fun getTableName(): String {
        return "kind"
    }
}
