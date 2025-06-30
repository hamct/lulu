package party.cattery.lulu.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "licks")
data class LickEntity(
    @Id
    val userId: String = "",
    @Column(nullable = false)
    var lickedCount: Int = 0,
)
