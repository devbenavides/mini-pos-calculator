package co.com.devda.calculatorcustom.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.com.devda.calculatorcustom.model.PriceEntity
import co.com.devda.calculatorcustom.viewmodel.PriceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPriceView(
    navController: NavController,
    viewModel: PriceViewModel,
    idPrice: Long,
    price: Long,
    description: String?
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Editar Precio", color = Color.White, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Regresar",
                            tint = Color.White
                        )
                    }
                }
            )
        }


    ) {
        ContentEditPriceView(it, navController, viewModel,idPrice,price,description)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentEditPriceView(
    it: PaddingValues,
    navController: NavController,
    viewModel: PriceViewModel, idPrice: Long, price: Long, description: String?
) {
    var price by remember { mutableStateOf(price.toString()) }
    var description by remember { mutableStateOf(description) }
    Column(
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = price?:"",
            onValueChange = { price = it },
            label = { Text(text = "Precio") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        OutlinedTextField(
            value = description?:"",
            onValueChange = { description = it },
            label = { Text(text = "Descripcion") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        Button(onClick = {
            val priceEntity = PriceEntity(idPrice=idPrice, price = price.toLong(), description = description)
            viewModel.editPrice(priceEntity)
            navController.popBackStack()
        }) {
            Text(text = "Editar")
        }
    }
}