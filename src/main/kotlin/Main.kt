import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import kotlin.random.Random
import kotlin.random.nextInt

private const val WIDTH = 720
private const val HEIGHT = 480


@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
fun App() {
    var answerYes by remember { mutableStateOf(false) }
    var buttonPos by remember { mutableStateOf(IntOffset(50,0)) }
    var buttonSize by remember { mutableStateOf(IntSize.Zero) }
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize()
            .background(Color(.95f, .95f, .95f))) {
            Text("wants to date me?",
                modifier = Modifier.align(Alignment.Center)
                    .offset(y = (-50).dp)
            )

            Button(onClick = {
                             answerYes = true
            }, modifier = Modifier
                .align(Alignment.Center)
                .offset(x = (-50).dp)
            ) {
                Text("Yes")
            }


            Button(onClick = { }, modifier = Modifier
                .align(Alignment.Center)
                .offset { buttonPos }
                .onSizeChanged { buttonSize = it }
                .onPointerEvent(PointerEventType.Enter) {
                    val xo = (WIDTH / 2)
                    val yo = (HEIGHT / 2)

                    val x = Random.nextInt(-xo + buttonSize.width ..xo - buttonSize.width)
                    val y = Random.nextInt(-yo + buttonSize.height..yo - buttonSize.height)

                    buttonPos = IntOffset(x, y)
                }
            ) {
                Text("No")
            }

            if (answerYes) {
                Text("I knew you wouldn't resist!", modifier = Modifier.align(Alignment.Center).offset(y = (50).dp))
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication,
        resizable = false,
        title = "wants to date me?",
        state = rememberWindowState(size = DpSize(WIDTH.dp, HEIGHT.dp))) {
        App()
    }
}
