package com.socksfactory.naeyangkku.global.auth.jwt

import com.socksfactory.naeyangkku.domain.user.domain.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class JwtUserDetails(
    val user: User
) : UserDetails {

    val id: Long? = user.id

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities: MutableCollection<GrantedAuthority> = ArrayList()

        authorities.add(SimpleGrantedAuthority(user.role.name))

        return authorities
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.nickname
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
}
