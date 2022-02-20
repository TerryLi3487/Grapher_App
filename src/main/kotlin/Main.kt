import javafx.application.Application
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.*
import javafx.stage.Stage
import java.awt.event.MouseEvent

class Main : Application()  {

    @Throws(Exception::class)
    override fun start(stage: Stage) {

        val model = Model()

        val layout = BorderPane()
        val graphPane = BorderPane()

        val toolBar = ToolBar(model)
        val axisBar = AxisBar(model)
        val numberSummary = NumberSummary(model)
        val individualVal = IndividualVal(model)
        val statusBar = StatusBar(model)
        val graph = Graph(model)

        val scrollPane = ScrollPane()
        scrollPane.content = individualVal
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER)

        layout.top = toolBar

        layout.center = graphPane
        graphPane.top = axisBar
        graphPane.right = numberSummary
        graphPane.left = scrollPane
        graphPane.center = graph
        layout.bottom = statusBar

        // Add grid to a scene (and the scene to the stage)
        val scene = Scene(layout, 800.0, 600.0)
        stage.minWidth = 600.0
        stage.minHeight = 400.0
        stage.scene = scene
        stage.title = "A2 Grapher (z995li)"
        stage.show()
    }
}