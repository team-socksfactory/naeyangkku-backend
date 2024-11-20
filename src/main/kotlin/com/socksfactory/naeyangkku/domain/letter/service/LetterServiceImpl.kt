package com.socksfactory.naeyangkku.domain.letter.service

import com.socksfactory.naeyangkku.domain.letter.domain.LetterEntity
import com.socksfactory.naeyangkku.domain.letter.domain.mapper.LetterMapper
import com.socksfactory.naeyangkku.domain.letter.domain.model.Letter
import com.socksfactory.naeyangkku.domain.letter.domain.repository.LetterRepository
import com.socksfactory.naeyangkku.domain.letter.presentation.dto.request.CreateLetterRequest
import com.socksfactory.naeyangkku.domain.letter.presentation.dto.response.LetterResponse
import com.socksfactory.naeyangkku.domain.user.domain.UserRepository
import com.socksfactory.naeyangkku.domain.user.exception.UserErrorCode
import com.socksfactory.naeyangkku.global.exception.CustomException
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LetterServiceImpl (
    private val userRepository: UserRepository,
    private val letterRepository: LetterRepository,
    private val letterMapper: LetterMapper
): LetterService {

    @Transactional
    override fun sendLetter(createLetterRequest: CreateLetterRequest): Letter {
        val ownerId = userRepository.findById(createLetterRequest.ownerId)
            .orElseThrow { CustomException(UserErrorCode.USER_NOT_FOUND) }

        val letterEntity = LetterEntity(
            nickname = createLetterRequest.nickname,
            content = createLetterRequest.content,
            iconNm = createLetterRequest.iconNm,
            ownerId = ownerId.id!!,
            ownerNickname = ownerId.nickname
        )

        val savedEntity = letterRepository.save(letterEntity)
        return savedEntity.toModel()
    }

    @Transactional
    override fun getLettersByOwnerId(
        ownerId: Long,
        pageable: Pageable
    ): List<LetterResponse> {

        return letterRepository.findAllByOwnerId(ownerId, pageable).map {
            letterMapper.transferLetterResponse(
                letterMapper.toDomain(it)
            )
        }
    }

    @Transactional
    override fun getTreeForGuest(
        ownerNickname: String,
        pageable: Pageable
    ): List<LetterResponse> {

        return letterRepository.findAllByOwnerNickname(ownerNickname, pageable).map {
            letterMapper.transferLetterResponse(
                letterMapper.toDomain(it)
            )
        }
    }

    @Transactional
    override fun getLetter(
        id: Long,
        pageable: Pageable
    ): List<LetterResponse> {

        return letterRepository.findById(id, pageable).map {
            letterMapper.transferLetterResponse(
                letterMapper.toDomain(it)
            )
        }
    }

    fun LetterEntity.toModel() = Letter(
        id = this.id,
        nickname = this.nickname,
        content = this.content,
        iconNm = this.iconNm!!,
        ownerId = this.ownerId!!,
        ownerNickname = this.nickname
    )
}
