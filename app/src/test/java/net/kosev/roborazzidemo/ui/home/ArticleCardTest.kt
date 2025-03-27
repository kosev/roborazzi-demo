package net.kosev.roborazzidemo.ui.home

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import com.github.takahirom.roborazzi.RoborazziRule
import com.github.takahirom.roborazzi.captureRoboImage
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
class ArticleCardTest {

    @get:Rule(order = 0)
    val composeRule = createComposeRule()

    @get:Rule(order = 1)
    val roborazziRule = RoborazziRule(
        RoborazziRule.Options(outputDirectoryPath = "src/test/snapshots")
    )

    @Test
    fun testArticleCard() {
        composeRule.setContent {
            ArticleCardPreview()
        }
        composeRule.onRoot().captureRoboImage()
    }
}
