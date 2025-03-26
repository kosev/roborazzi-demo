package net.kosev.roborazzidemo.ui.home

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import com.github.takahirom.roborazzi.RoborazziRule
import com.github.takahirom.roborazzi.captureRoboImage
import net.kosev.roborazzidemo.R
import net.kosev.roborazzidemo.ui.theme.RoborazziDemoTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.annotation.LooperMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35], qualifiers = "w360dp-h1000dp-480dpi")
@LooperMode(LooperMode.Mode.PAUSED)
class HomeScreenTest {

    @get:Rule(order = 0)
    val composeRule = createComposeRule()

    @get:Rule(order = 1)
    val roborazziRule = RoborazziRule(
        RoborazziRule.Options(outputDirectoryPath = "src/test/snapshots")
    )

    @Test
    fun testHomeContent() {
        composeRule.setContent {
            HomeContentPreview()
        }
        composeRule.onRoot().captureRoboImage()
    }

    @Test
    fun testHomeLoading() {
        composeRule.setContent {
            HomeLoadingPreview()
        }
        composeRule.onRoot().captureRoboImage()
    }

    @Test
    fun testHomeScreenSuccess() {
        val viewModel = HomeViewModel()
        viewModel.setStateForTesting(
            HomeState.Success(
                listOf(
                    Article(
                        title = "Lake Como",
                        text = "The glittering shores of Lake Como have long beguiled generations of visitors with beautiful vistas of deep blue waters, wooded hillsides and elegant villas. From European aristocrats to the Hollywood glitterati it's easy to see why Como became their summer destination of choice. Shaped like an upside-down Y, the three arms of the lake converge at the attractive town of Bellagio, often referred to as the pearl of the lake with its café lined waterfront and port of bobbing fishing boats. Other lakeside towns such as Tremezzo, Varenna and Menaggio are particularly picturesque, boasting luxurious lakeside villas and spectacular gardens that are sure to delight. Finally, at the southwestern tip the prosperous town of Como boasts a delightful medieval core complete with winding streets and charming boutiques.",
                        cover = R.drawable.como
                    )
                )
            )
        )
        composeRule.setContent {
            RoborazziDemoTheme {
                HomeScreen(viewModel = viewModel)
            }
        }
        composeRule.onRoot().captureRoboImage()
    }

    @Test
    fun testHomeScreenLoading() {
        val viewModel = HomeViewModel()
        viewModel.setStateForTesting(HomeState.Loading)
        composeRule.setContent {
            RoborazziDemoTheme {
                HomeScreen(viewModel = viewModel)
            }
        }
        composeRule.onRoot().captureRoboImage()
    }
}
