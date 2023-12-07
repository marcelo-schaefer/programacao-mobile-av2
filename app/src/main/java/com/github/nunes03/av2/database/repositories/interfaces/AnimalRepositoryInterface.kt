package com.github.nunes03.av2.database.repositories.interfaces

import com.github.nunes03.av2.database.entities.AnimalEntity
import com.github.nunes03.av2.database.entities.KindEntity

interface AnimalRepositoryInterface : RepositoryInterface<AnimalEntity> {

    fun findByKind(kind: KindEntity): ArrayList<AnimalEntity>

    fun findByNameContainsAndKind(name: String, kind: KindEntity): ArrayList<AnimalEntity>
}