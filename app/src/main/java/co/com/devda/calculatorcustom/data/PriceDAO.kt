package co.com.devda.calculatorcustom.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import co.com.devda.calculatorcustom.model.PriceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PriceDAO {
    @Query("SELECT * FROM prices")
    fun getAllPrices():Flow<List<PriceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listPrice: List<PriceEntity>)

    @Insert
    fun insertPrice(vararg price: PriceEntity)

    @Update
    fun updateAll(price: PriceEntity)

    @Delete
    fun delete(price: PriceEntity)

    @Query("DELETE FROM prices  WHERE id_price= :id")
    fun deleteById(id:String)
}