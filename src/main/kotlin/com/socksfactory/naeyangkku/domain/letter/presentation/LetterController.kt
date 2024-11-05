package com.socksfactory.naeyangkku.domain.letter.presentation

import com.socksfactory.naeyangkku.domain.letter.presentation.dto.CreateLetterRequest
import com.socksfactory.naeyangkku.domain.letter.service.LetterService
import com.socksfactory.naeyangkku.global.common.BaseResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/letter")
class LetterController (
    private val letterService: LetterService
) {

    @PostMapping("/send")
    fun sendLetter(
        @RequestBody createLetterRequest: CreateLetterRequest,
    ): BaseResponse<Unit> {
        letterService.sendLetter(createLetterRequest = createLetterRequest)
        return BaseResponse(
            message = "편지 전송 성공"
        )
    }

}
