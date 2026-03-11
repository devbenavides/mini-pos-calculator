package co.com.devda.calculatorcustom.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.com.devda.calculatorcustom.data.PriceDAO
import co.com.devda.calculatorcustom.model.PriceEntity
import co.com.devda.calculatorcustom.state.PriceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PriceViewModel(
    private val dao: PriceDAO
) : ViewModel() {
    var state by mutableStateOf(PriceState())
        private set

    init {
        viewModelScope.launch {
            dao.getAllPrices().collectLatest {
                state = state.copy(
                    listPrice = it
                )
            }
        }
    }

    fun loadData() = viewModelScope.launch(Dispatchers.IO) {
        val listData = ArrayList<PriceEntity>()
        listData.add(PriceEntity(1,500,"Arepa sola"))
        listData.add(PriceEntity(2,1800,"Chocolate"))
        listData.add(PriceEntity(3,1200,"Cafe solo"))
        listData.add(PriceEntity(4,1600,"Cafe con leche"))
        listData.add(PriceEntity(5,1600,"Arepa con queso"))
        listData.add(PriceEntity(6,2800,"Arepa rellena de huevos"))
        listData.add(PriceEntity(7, 4500,"Arepa con carne"))
        listData.add(PriceEntity(8, 1500,"Aromatica"))
        listData.add(PriceEntity(9, 4500,"Porción de huevos"))
        listData.add(PriceEntity(10, 5500,"Desayuno con huevos"))

        dao.insertAll(listData)
    }

    fun addPrice(price: PriceEntity) = viewModelScope.launch(Dispatchers.IO) {
        dao.insertPrice(price)
    }

    fun editPrice(price: PriceEntity) = viewModelScope.launch(Dispatchers.IO) {
        dao.updateAll(price)
    }

    fun deletePrice(price: PriceEntity) = viewModelScope.launch(Dispatchers.IO) {
        dao.delete(price)
    }

    fun deletePriceById(id: String) = viewModelScope.launch(Dispatchers.IO) {
        dao.deleteById(id)
    }
}