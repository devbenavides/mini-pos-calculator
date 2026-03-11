package co.com.devda.calculatorcustom.view

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.com.devda.calculatorcustom.R
import co.com.devda.calculatorcustom.viewmodel.PriceViewModel
import java.lang.Exception


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorView(navController: NavController, viewModel: PriceViewModel) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(text = "CALCULADORA", color = Color.White, fontWeight = FontWeight.Bold)
        }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),

            actions = {
                IconButton(onClick = { viewModel.loadData() }) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = stringResource(R.string.btn_load_data),
                        tint = Color.White
                    )
                }
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = stringResource(R.string.btn_list_prices),
                        tint = Color.White
                    )
                }
            })
    }) {
        ContentCalculatorView(it, viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentCalculatorView(
    it: PaddingValues, viewModel: PriceViewModel
) {
    val state = viewModel.state
    val listData = remember {
        mutableStateMapOf<String, String>()
    }
    val listPrices = remember {
        mutableStateMapOf<String, Long>()
    }
    val subTotal = remember {
        mutableStateMapOf<String, String>()
    }
    var total by remember {
        mutableStateOf("")
    }
    var dat by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.padding(it)) {
        Box(
            modifier = Modifier
                .height(550.dp)
                .padding(bottom = 5.dp)
                .padding(top = 10.dp)
                .fillMaxWidth()
        ) {
            /*LazyColumn {
                items(state.listPrice) { data ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        //listData.set(data.description.toString(), "")

                        IconButton(onClick = {
                            try {
                                val keyList: String = data.description.toString()
                                val valueList: Int = listData.getValue(keyList).toInt()
                                if (valueList > 1) listData[data.description.toString()] =
                                    (valueList - 1).toString()
                                else listData.remove(keyList)
                            } catch (e: Exception) {
                                Log.e("Error ", "Error al restar $e")
                            }

                        }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Restar")
                        }

                        TextField(
                            value = listData[data.description] ?: "",
                            label = { Text(text = data.description.toString()) },
                            onValueChange = {
                                if (it.isEmpty()) {
                                    listData.remove(data.description.toString())
                                } else {
                                    listData[data.description.toString()] = it
                                }

                            },
                            modifier = Modifier
                                .width(100.dp)
                                .padding(horizontal = 5.dp)
                                .padding(bottom = 5.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        IconButton(onClick = {

                            try {
                                val cont =
                                    listData?.containsKey(data.description.toString()) == true
                                if (!cont) listData[data.description.toString()] = "1"
                                else {
                                    val keyList: String = data.description.toString()
                                    val valueList: Int = listData.getValue(keyList).toInt()

                                    Log.e("Sumar ", "$keyList ${valueList}}")

                                    listData[keyList] = (valueList + 1).toString()
                                }
                            } catch (e: Exception) {
                                Log.e("Error ", "Error al sumar $e")
                            }


                        }) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = stringResource(
                                    R.string.btn_delete
                                )
                            )
                        }

                        //if (subTotal.) Text(text = "$ ${subTotal.getValue(data.description.toString())}")


                    }

                }

            }*/
            LazyColumn {
                items(state.listPrice) { data ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        //listData.set(data.description.toString(), "")

                        IconButton(onClick = {
                            try {
                                val keyList: String = data.description.toString()
                                val valueList: Int = listData.getValue(keyList).toInt()
                                if (valueList > 1) listData[data.description.toString()] =
                                    (valueList - 1).toString()
                                else listData.remove(keyList)
                            } catch (e: Exception) {
                                Log.e("Error ", "Error al restar $e")
                            }

                        }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Restar")
                        }

                        TextField(
                            value = listData[data.description] ?: "",
                            label = { Text(text = data.description.toString()) },
                            onValueChange = {
                                if (it.isEmpty()) {
                                    listData.remove(data.description.toString())
                                } else {
                                    listData[data.description.toString()] = it
                                }

                            },
                            modifier = Modifier
                                .width(300.dp)
                                .padding(horizontal = 5.dp)
                                .padding(bottom = 5.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        IconButton(onClick = {

                            try {
                                val cont =
                                    listData?.containsKey(data.description.toString()) == true
                                if (!cont) listData[data.description.toString()] = "1"
                                else {
                                    val keyList: String = data.description.toString()
                                    val valueList: Int = listData.getValue(keyList).toInt()

                                    Log.e("Sumar ", "$keyList ${valueList}}")

                                    listData[keyList] = (valueList + 1).toString()
                                }
                            } catch (e: Exception) {
                                Log.e("Error ", "Error al sumar $e")
                            }


                        }) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = stringResource(
                                    R.string.btn_delete
                                )
                            )
                        }

                        //if (subTotal.) Text(text = "$ ${subTotal.getValue(data.description.toString())}")


                    }

                }

            }
        }

        Box(
            modifier = Modifier
                .height(65.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = dat,
                label = { Text(text = "valor extra") },
                onValueChange = {
                    dat = it

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        Box(
            modifier = Modifier
                .height(35.dp)
                .fillMaxWidth()
        ) {

            Text(
                text = "Total a pagar $${total}",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
                //color = Color.White,

            )
        }

        Box(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    var pay: Long = 0

                    state.listPrice.forEach {
                        listPrices[it.description.toString()] = it.price
                    }


                    if (listData.isNotEmpty()) {
                        listData.forEach {
                            val price = listPrices.getValue(it.key)
                            val cant = it.value.toLong()

                            subTotal[it.key] = (cant * price).toString()
                            pay += price * cant
                        }
                    }



                    if (dat.trim().isNotEmpty()) {
                        val extraPrices = dat.split(",")

                        Log.e("Extra prices >> ", "${extraPrices.toList()}")
                        extraPrices.forEach {
                            pay += it.toLong()
                        }
                    }

                    total = pay.toString()
                    Log.e("Subtotal>>", "${subTotal.toMap()}")
                }, modifier = Modifier.align(Alignment.Center)
            ) {
                Text(text = "Sumar")
            }
        }
    }
}

