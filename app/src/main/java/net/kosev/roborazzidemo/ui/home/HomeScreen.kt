package net.kosev.roborazzidemo.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import net.kosev.roborazzidemo.R
import net.kosev.roborazzidemo.ui.theme.RoborazziDemoTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    when (val state = viewModel.state.collectAsStateWithLifecycle().value) {
        HomeState.Loading -> HomeLoading(modifier = modifier)
        is HomeState.Success -> HomeContent(state = state, modifier = modifier)
    }
}

@Composable
fun HomeLoading(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Italy top 3", style = MaterialTheme.typography.displayLarge, color = MaterialTheme.colorScheme.primary)
        repeat(3) {
            Card(
                modifier = Modifier.fillMaxWidth().height(333.dp).alpha(0.4f),
            ) {

            }
        }
    }
}

@Composable
fun HomeContent(
    state: HomeState.Success,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Italy top 3", style = MaterialTheme.typography.displayLarge, color = MaterialTheme.colorScheme.primary)
        state.data.forEach { article ->
            ArticleCard(
                title = article.title,
                text = article.text,
                cover = article.cover
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeLoadingPreview() {
    RoborazziDemoTheme {
        HomeLoading()
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    RoborazziDemoTheme {
        HomeContent(
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
        )
    }
}
