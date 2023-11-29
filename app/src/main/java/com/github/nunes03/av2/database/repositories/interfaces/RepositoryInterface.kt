package com.github.nunes03.av2.database.repositories.interfaces

interface RepositoryInterface<E> {

    fun create(entity: E): Long

    fun update(entity: E)

    fun findById(id: Int): E?

    fun findAll(): List<E>

    fun deleteById(id: Int)
}