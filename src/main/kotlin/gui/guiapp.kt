package gui

/**
 * Created by Harmitage on 3/10/2017.
 */

import javafx.application.Application
import tornadofx.App

/**
 * Created by ronsmits on 29/04/16.
 */

fun main(args: Array<String>) {
    Application.launch(ChartApp::class.java, *args)
}

class ChartApp : App() {
    override val primaryView = chartview::class

}