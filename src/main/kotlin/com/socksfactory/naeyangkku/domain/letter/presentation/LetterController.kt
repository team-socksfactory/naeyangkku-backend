package com.socksfactory.naeyangkku.domain.letter.presentation

import com.socksfactory.naeyangkku.domain.letter.presentation.dto.request.CreateLetterRequest
import com.socksfactory.naeyangkku.domain.letter.presentation.dto.response.LetterResponse
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
        ): BaseResponse<List<LetterResponse>> {

        return BaseResponse(
            message = "해당 유저의 편지 리스트 조회 성공",
            data = letterService.getLettersByOwnerId(
                ownerId = ownerId,
                pageable = pageable
            )
        )
    }

    @GetMapping("/{nickname}")
    fun getTreeForGuest(
        @PathVariable nickname: String,
        @PageableDefault(sort = ["id"], direction = Sort.Direction.DESC, size = 5) pageable: Pageable
    ): BaseResponse<List<LetterResponse>> {

        return BaseResponse(
            message = "공유용 링크 조회 성공",
            data = letterService.getTreeForGuest(
                nickname = nickname,
                pageable = pageable
            )
        )
    }

    @GetMapping("/{id}")
    fun getLetter(
        @PathVariable id: Long,
        @PageableDefault(sort = ["id"], direction = Sort.Direction.DESC, size = 5) pageable: Pageable
    ): BaseResponse<List<LetterResponse>> {

        return BaseResponse(
            message = "선택한 편지 조회 성공",
            data = letterService.getLetter(
                id = id,
                pageable = pageable
            )
        )
    }

}
