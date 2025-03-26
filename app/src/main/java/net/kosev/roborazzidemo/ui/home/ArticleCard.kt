package net.kosev.roborazzidemo.ui.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.kosev.roborazzidemo.R
import net.kosev.roborazzidemo.ui.theme.RoborazziDemoTheme

@Composable
fun ArticleCard(
    title: String,
    text: String,
    @DrawableRes cover: Int,
    modifier: Modifier = Modifier
) {
    Card {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(painter = painterResource(cover), contentDescription = null)
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            )
        }
    }
}

@Preview
@Composable
fun ArticleCardPreview() {
    RoborazziDemoTheme {
        ArticleCard(
            title = "Florence",
            text = "Situated on the banks of the Arno amid the Tuscan hills, Florence is celebrated for its art, history and architecture. The powerhouse of the Renaissance, today the city is a living gallery. The ochre dome of the Duomo dominates the skyline, while the Accademia and Uffizi museums house works such as Michelangelo’s David and Botticelli’s Primavera.",
            cover = R.drawable.como
        )
    }
}
