package com.socksfactory.naeyangkku.global.security.auth.principal

import com.socksfactory.naeyangkku.domain.user.domain.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails: UserDetails {

    private val user: User
    private val authorities: Collection<GrantedAuthority>

    fun getUser(): User {
        return this.user
    }

    constructor(user: User, authorities: Collection<GrantedAuthority>) {
        this.user = user
        this.authorities = authorities
    }

    constructor(user: User) {
        this.user = user
        this.authorities = setOf(GrantedAuthority {
            user.role.toString()
        })
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return setOf(GrantedAuthority { user.role.toString() })
//                return authorities;
    }

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String {
        return user.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    companion object {
        fun create(user: User): CustomUserDetails {
            return CustomUserDetails(user, setOf(user.role::name as GrantedAuthority))
        }
    }

}
