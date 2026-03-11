package co.com.devda.calculatorcustom.state

import co.com.devda.calculatorcustom.model.PriceEntity

data class PriceState(
    val listPrice : List<PriceEntity> = emptyList()
)
