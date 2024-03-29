package sq.mayv.aladhan.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import sq.mayv.aladhan.room.dao.PrayerDao
import sq.mayv.aladhan.room.entity.DateEntity
import sq.mayv.aladhan.room.entity.TimingsEntity

@Database(
    entities = [DateEntity::class, TimingsEntity::class],
    version = 1
)
abstract class AladhanDatabase: RoomDatabase() {

    abstract val dao: PrayerDao

}