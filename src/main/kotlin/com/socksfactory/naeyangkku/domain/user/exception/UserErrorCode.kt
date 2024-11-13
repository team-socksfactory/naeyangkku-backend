package com.socksfactory.naeyangkku.domain.user.exception

import com.socksfactory.naeyangkku.global.exception.CustomErrorCode
import org.springframework.http.HttpStatus

enum class UserErrorCode (
    override val status: HttpStatus,
    override val state: String,
    override val message: String,
) : CustomErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "NOT_FOUND", "유저를 찾을 수 없습니다."),
    USER_ALREADY_EXIST(HttpStatus.CONFLICT, "CONFLICT", "유저가(이메일이) 이미 존재합니다."),
    USER_NOT_MATCH(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "이메일 또는 비밀번호가 잘못되었습니다."),
    INVAILD_ID_TOKEN(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", "유효하지 않은 토큰입니다."),
    INVAILD_OAUTH_CODE(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", "유효하지 Oauth2 코드 입니다."),
    INVAILD_PLATFORM_TYPE(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "지원하지 않는 소셜 로그인 방식입니다.")

}
