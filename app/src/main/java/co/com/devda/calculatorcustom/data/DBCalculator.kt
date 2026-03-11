package co.com.devda.calculatorcustom.data

import androidx.room.Database
import androidx.room.RoomDatabase
import co.com.devda.calculatorcustom.model.PriceEntity

@Database(
    entities = [
        PriceEntity::class
    ],
    version = 1
)
abstract class DBCalculator : RoomDatabase() {
    abstract fun priceDao():PriceDAO
}