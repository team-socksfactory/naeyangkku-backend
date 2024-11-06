package com.socksfactory.naeyangkku.domain.letter.domain.repository

import com.socksfactory.naeyangkku.domain.letter.domain.LetterEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LetterRepository : JpaRepository<LetterEntity, Long> {
    fun findAllByOwnerId(workspaceId: Long, pageable: Pageable): List<LetterEntity>
}
