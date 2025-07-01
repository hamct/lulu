package party.cattery.lulu.service

import party.cattery.lulu.entity.LickEntity
import party.cattery.lulu.model.Lick

fun LickEntity.toDomain(): Lick = Lick(userId = userId, count = lickedCount)

fun Lick.toEntity(): LickEntity = LickEntity(userId = userId, lickedCount = count)
