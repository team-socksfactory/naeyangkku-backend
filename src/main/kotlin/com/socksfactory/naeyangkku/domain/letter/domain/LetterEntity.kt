package com.socksfactory.naeyangkku.domain.letter.domain

import com.socksfactory.naeyangkku.domain.user.domain.entity.UserEntity
import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity
@EntityListeners(AuditingEntityListener::class)
class LetterEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val nickname: String,

    @Column(nullable = false)
    val content: String,

    @ManyToOne
    @JoinColumn(name = "owner_id")
    val ownerId: UserEntity? = null
) {
}
