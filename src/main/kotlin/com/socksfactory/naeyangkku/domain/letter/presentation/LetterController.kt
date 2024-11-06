package com.socksfactory.naeyangkku.domain.letter.presentation

import com.socksfactory.naeyangkku.domain.letter.presentation.dto.request.CreateLetterRequest
import com.socksfactory.naeyangkku.domain.letter.service.LetterService
import com.socksfactory.naeyangkku.global.common.BaseResponse
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
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
        letterService.sendLetter(createLetterRequest)
        return BaseResponse(
            message = "편지 전송 성공"
        )
    }

    @GetMapping("/list/{ownerId}")
    fun getLetters(
        @PathVariable ownerId: Long,
        @PageableDefault(sort = ["id"], direction = Sort.Direction.DESC, size = 5) pageable: Pageable
        ): BaseResponse<Unit> {
        letterService.getLettersByOwnerId(ownerId, pageable)
        return BaseResponse(
            message = "해당 유저의 편지 리스트 조회 성공"
        )
    }

}
