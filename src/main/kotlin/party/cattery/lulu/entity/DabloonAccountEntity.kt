package party.cattery.lulu.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "dabloon_accounts")
data class DabloonAccountEntity(
    @Id
    val userId: String = "",
    @Column(nullable = false)
    var balance: Long = 1000,
)
