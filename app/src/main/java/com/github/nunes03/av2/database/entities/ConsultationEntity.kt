package com.github.nunes03.av2.database.entities

class ConsultationEntity : AbstractEntity() {
    var id: Int? = null

    var scheduledTime: String? = null

    var description: String? = null

    var animal: AnimalEntity? = null

    override fun getTableName(): String {
        return "consultation"
    }
}
