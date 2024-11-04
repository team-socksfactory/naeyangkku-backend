package com.socksfactory.naeyangkku.global.common.annotation

import org.springframework.security.core.annotation.AuthenticationPrincipal

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? -1L : #this.id")
annotation class GetAuthenticatedId
