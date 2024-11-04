package com.socksfactory.naeyangkku.global.common

interface Mapper<D, E> {

    fun toDomain(entity: E): D

    fun toEntity(domain: D): E
}
