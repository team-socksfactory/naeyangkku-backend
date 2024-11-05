package com.socksfactory.naeyangkku.domain.letter.service

import com.socksfactory.naeyangkku.domain.letter.domain.LetterEntity
import com.socksfactory.naeyangkku.domain.letter.domain.model.Letter
import com.socksfactory.naeyangkku.domain.letter.domain.repository.LetterRepository
import com.socksfactory.naeyangkku.domain.letter.presentation.dto.CreateLetterRequest
import com.socksfactory.naeyangkku.domain.user.domain.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LetterServiceImpl (
    private val userRepository: UserRepository,
    private val letterRepository: LetterRepository
): LetterService {

    @Transactional
    override fun sendLetter(createLetterRequest: CreateLetterRequest): Letter {
        val ownerId = userRepository.findById(createLetterRequest.ownerId)
            .orElseThrow { IllegalArgumentException("User not found") }

        val letterEntity = LetterEntity(
            nickname = createLetterRequest.nickname,
            content = createLetterRequest.content,
            ownerId = ownerId
        )

        val savedEntity = letterRepository.save(letterEntity)
        return savedEntity.toModel()
    }

    fun LetterEntity.toModel() = Letter(
        id = this.id,
        nickname = this.nickname,
        content = this.content,
        ownerId = this.ownerId!!
    )
}
