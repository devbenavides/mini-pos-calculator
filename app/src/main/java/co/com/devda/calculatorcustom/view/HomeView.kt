package co.com.devda.calculatorcustom.view


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.com.devda.calculatorcustom.viewmodel.PriceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, viewModel: PriceViewModel) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Listado de Precios", color = Color.White, fontWeight = FontWeight.Bold)
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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("add")
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar")
            }
        }

    ) {
        ContentHomeView(it, navController, viewModel)
    }
}

@Composable
fun ContentHomeView(it: PaddingValues, navController: NavController, viewModel: PriceViewModel) {
    val state = viewModel.state
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var idDelete by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.padding(it)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(9.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "ID"
            )
            Text(
                text = "PRECIO"
            )
            Text(
                text = "PRODUCTO"
            )
            Text(
                text = "EDITAR"
            )
            Text(
                text = "ELIMINAR"
            )

        }
        LazyColumn {
            items(state.listPrice) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 9.dp)
                        .padding(end = 9.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    //horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Text(
                        text = it.idPrice.toString(),
                        modifier = Modifier
                            .weight(0.01f)
                    )

                    Text(
                        text = it.price.toString(),
                        modifier = Modifier
                            .weight(0.02f)
                    )
                    Text(
                        text = it.description.toString(),
                        modifier = Modifier
                            .weight(0.03f)
                    )
                    IconButton(
                        onClick = { navController.navigate("edit/${it.idPrice}/${it.price}/${it.description}") },
                        modifier = Modifier
                            .weight(0.02f)
                    ) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar")
                    }
                    IconButton(
                        onClick = {
                            showDialog = true
                            idDelete = it.idPrice.toString()
                        },
                        modifier = Modifier
                            .weight(0.02f)
                    )
                    {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Eliminar"
                        )
                    }
                }
            }
        }
    }

    deleteDialog(
        show = showDialog,
        onDismiss = { showDialog = false },
        onConfirm = {
            showDialog = false
            viewModel.deletePriceById(idDelete)
        }
    )
}

@Composable
fun deleteDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    if (show) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                TextButton(onClick = {
                    onConfirm()
                }) {
                    Text(text = "Eliminar")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "Cancelar")
                }
            },
            title = { Text(text = "Eliminar") },
            text = { Text(text = "Desea eliminar el precio de la lista") }
        )
    }
}