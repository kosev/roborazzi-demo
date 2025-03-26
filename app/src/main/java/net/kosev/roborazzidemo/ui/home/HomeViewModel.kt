package net.kosev.roborazzidemo.ui.home

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.kosev.roborazzidemo.R

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            delay(3_000L)
            _state.update {
                HomeState.Success(
                    listOf(
                        Article(
                            title = "Florence",
                            text = "Situated on the banks of the Arno amid the Tuscan hills, Florence is celebrated for its art, history and architecture. The powerhouse of the Renaissance, today the city is a living gallery. The ochre dome of the Duomo dominates the skyline, while the Accademia and Uffizi museums house works such as Michelangelo’s David and Botticelli’s Primavera.",
                            cover = R.drawable.florence
                        ),
                        Article(
                            title = "Bologna",
                            text = "Located in Italy’s fertile heartland, Bologna is an affluent city with an attractive medieval core. Exploring the maze of historical streets, visitors will find miles and miles of porticoes or portici, lining the streets from the dramatic central Piazza Maggiore extending in all directions. Built into the structures of surrounding terracotta roofed buildings, these colonnaded passageways stretch right across the city, sheltering the routes between everything from grand Gothic and Renaissance palaces to markets, elaborate churches, and humble houses.",
                            cover = R.drawable.bologna
                        ),
                        Article(
                            title = "Lake Como",
                            text = "The glittering shores of Lake Como have long beguiled generations of visitors with beautiful vistas of deep blue waters, wooded hillsides and elegant villas. From European aristocrats to the Hollywood glitterati it's easy to see why Como became their summer destination of choice. Shaped like an upside-down Y, the three arms of the lake converge at the attractive town of Bellagio, often referred to as the pearl of the lake with its café lined waterfront and port of bobbing fishing boats. Other lakeside towns such as Tremezzo, Varenna and Menaggio are particularly picturesque, boasting luxurious lakeside villas and spectacular gardens that are sure to delight. Finally, at the southwestern tip the prosperous town of Como boasts a delightful medieval core complete with winding streets and charming boutiques.",
                            cover = R.drawable.como
                        )
                    )
                )
            }
        }
    }
}

data class Article(
    val title: String,
    val text: String,
    @DrawableRes val cover: Int
)

sealed interface HomeState {
    data object Loading : HomeState
    data class Success(val data: List<Article>) : HomeState
}
