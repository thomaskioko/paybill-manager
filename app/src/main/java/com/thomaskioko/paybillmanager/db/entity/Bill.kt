package com.thomaskioko.paybillmanager.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId

@Entity
data class Bill(
        var billName: String,
        var paybillNumber: String,
        var accountNumber: String,
        var amount: String,
        var categoryId: Int,
        var reminderDate: OffsetDateTime?
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var dateCreated: OffsetDateTime = OffsetDateTime.now(ZoneId.of("Z"))

}
