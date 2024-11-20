package com.socksfactory.naeyangkku.domain.letter.domain.repository

import com.socksfactory.naeyangkku.domain.letter.domain.LetterEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LetterRepository : JpaRepository<LetterEntity, Long> {
    fun findById(id: Long, pageable: Pageable): List<LetterEntity>
    fun findAllByOwnerId(ownerId: Long, pageable: Pageable): List<LetterEntity>
    fun findAllByOwnerNickname(ownerNickname: String, pageable: Pageable): List<LetterEntity>
}
