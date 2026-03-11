package co.com.devda.calculatorcustom.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prices")
data class PriceEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_price")
    val idPrice: Long = 0,

    @ColumnInfo(name = "price")
    val price: Long = 0,

    @ColumnInfo(name = "description")
    val description: String?
)