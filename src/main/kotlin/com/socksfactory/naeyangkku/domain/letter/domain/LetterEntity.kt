package com.socksfactory.naeyangkku.domain.letter.domain

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

    @Column(nullable = false)
    val iconNm: Long? = null,

    @JoinColumn(name = "owner_id")
    val ownerId: Long? = null
) {
}
