package com.socksfactory.naeyangkku.domain.letter.domain.mapper

import com.socksfactory.naeyangkku.domain.letter.domain.LetterEntity
import com.socksfactory.naeyangkku.domain.letter.domain.model.Letter
import com.socksfactory.naeyangkku.domain.letter.presentation.dto.response.LetterResponse
import com.socksfactory.naeyangkku.global.common.Mapper
import org.springframework.stereotype.Component

@Component
class LetterMapper : Mapper<Letter, LetterEntity> {

    override fun toDomain(entity: LetterEntity): Letter {
        return Letter(
            id = entity.id,
            nickname = entity.nickname,
            content = entity.content,
            ownerId = entity.ownerId!!
        )
    }

    override fun toEntity(domain: Letter): LetterEntity {
        return LetterEntity(
            id = domain.id,
            nickname = domain.nickname,
            content = domain.content,
            ownerId = domain.ownerId
        )
    }

    fun transferLetterResponse(letter: Letter): LetterResponse {
        return LetterResponse(
            id = letter.id,
            nickname = letter.nickname,
            content = letter.content,
            ownerId = letter.ownerId!!
        )
    }

}
