package gui

/**
 * Created by Harmitage on 3/10/2017.
 */

import javafx.application.Application
import javafx.scene.layout.Pane
import tornadofx.*

fun main(args: Array<String>) {
    Application.launch(GUIApp::class.java, *args)
}

class GUIApp : App() {
    override val primaryView = GUIView::class

}

class GUIView : View() {
    override val root: Pane by fxml()
}