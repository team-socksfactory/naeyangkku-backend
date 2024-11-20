package com.socksfactory.naeyangkku.domain.share.presentation

import com.socksfactory.naeyangkku.domain.letter.presentation.dto.response.LetterResponse
import com.socksfactory.naeyangkku.domain.letter.service.LetterService
import com.socksfactory.naeyangkku.global.common.BaseResponse
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/share")
class ShareController (
    private val letterService: LetterService
) {

    @GetMapping("{ownerNickname}")
    fun getTreeForGuest(
        @PathVariable ownerNickname: String,
        @PageableDefault(sort = ["id"], direction = Sort.Direction.DESC, size = 5) pageable: Pageable
    ): BaseResponse<List<LetterResponse>> {

        return BaseResponse(
            message = "공유용 링크 조회 성공",
            data = letterService.getTreeForGuest(
                ownerNickname = ownerNickname,
                pageable = pageable
            )
        )
    }

}
