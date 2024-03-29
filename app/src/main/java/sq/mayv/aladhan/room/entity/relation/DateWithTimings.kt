package sq.mayv.aladhan.room.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import sq.mayv.aladhan.room.entity.DateEntity
import sq.mayv.aladhan.room.entity.TimingsEntity

data class DateWithTimings(
    @Embedded val date: DateEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "date_id"
    )
    val timings: TimingsEntity
)
