package com.socksfactory.naeyangkku.domain.letter.domain.repository

import com.socksfactory.naeyangkku.domain.letter.domain.model.Letter
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LetterRepository : JpaRepository<Letter, Long> {
}
