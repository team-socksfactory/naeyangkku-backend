package com.socksfactory.naeyangkku.domain.letter.service

import com.socksfactory.naeyangkku.domain.letter.domain.model.Letter
import com.socksfactory.naeyangkku.domain.letter.presentation.dto.CreateLetterRequest

interface LetterService {
    fun sendLetter(createLetterRequest: CreateLetterRequest): Letter
}
