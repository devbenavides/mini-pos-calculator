package co.com.devda.calculatorcustom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import co.com.devda.calculatorcustom.data.DBCalculator
import co.com.devda.calculatorcustom.navigation.NavManager
import co.com.devda.calculatorcustom.ui.theme.CalculatorCustomTheme
import co.com.devda.calculatorcustom.viewmodel.PriceViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorCustomTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val db = Room.databaseBuilder(this, DBCalculator::class.java,"db_calculator").build()
                    val dao = db.priceDao()

                    val viewModel = PriceViewModel(dao)

                    NavManager(viewModel = viewModel)
                }
            }
        }
    }
}


