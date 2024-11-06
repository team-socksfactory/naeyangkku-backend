package com.socksfactory.naeyangkku.domain.letter.service

import com.socksfactory.naeyangkku.domain.letter.domain.model.Letter
import com.socksfactory.naeyangkku.domain.letter.presentation.dto.request.CreateLetterRequest
import com.socksfactory.naeyangkku.domain.letter.presentation.dto.response.LetterResponse
import org.springframework.data.domain.Pageable

interface LetterService {
    fun sendLetter(createLetterRequest: CreateLetterRequest): Letter
    fun getLettersByOwnerId(ownerId: Long, pageable: Pageable): List<LetterResponse>
}
