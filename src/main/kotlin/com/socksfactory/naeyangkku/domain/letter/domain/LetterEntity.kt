package com.socksfactory.naeyangkku.domain.letter.domain

import com.socksfactory.naeyangkku.domain.user.domain.entity.UserEntity
import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity
@Table(name = "letter")
@EntityListeners(AuditingEntityListener::class)
class LetterEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(nullable = false)
    val nickname: String,

    @Column(nullable = false)
    val content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    val ownerId: UserEntity? = null
) {
}
