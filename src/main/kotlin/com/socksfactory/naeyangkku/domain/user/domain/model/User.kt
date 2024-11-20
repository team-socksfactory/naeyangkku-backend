package com.socksfactory.naeyangkku.domain.user.domain.model

import com.socksfactory.naeyangkku.domain.user.domain.enums.UserRoles

data class User(
    val id: Long? = null,
    val email: String = "",
    val nickname: String = "",
    val password: String = "",
    val role: UserRoles = UserRoles.ROLE_USER
)
