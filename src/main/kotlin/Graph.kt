import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.*
import javafx.scene.shape.Rectangle
import javafx.scene.text.Text
import javafx.stage.Stage
import java.awt.event.MouseEvent
import kotlin.math.max


internal class Graph(private val model: Model): BorderPane(), IView {

    private val barsPane = StackPane()
    private val xAxis = HBox()
    private val yAxis = VBox()
    private val title = HBox()
    private val titleLabel = Label("")
    private val xAxisLabel = Label("")
    private val yAxisLabel = Label("")
    private val zeroLabel = Label("0")
    private val maxLabel = Label("")

    private val spacer1 = Region()
    private val spacer2 = Region()

    private var canvasW = 400.0
    private var canvasH = 400.0


    private val canvas = Canvas(canvasW, canvasH)
    private val gc = canvas.graphicsContext2D

    override fun updateView() {
        // bars.children
        // gc.lineWidth = 3.0
        canvas.widthProperty().bind(barsPane.widthProperty())
        canvas.heightProperty().bind(barsPane.heightProperty())

        //canvas.width = canvasW
        //canvas.height = canvasH

        spacer1.prefHeight = canvasH / 2
        spacer2.prefHeight = canvasH / 2


        gc.clearRect(0.0, 0.0, canvasW, canvasH)
        // xAxis
        gc.stroke = Color.BLACK
        gc.strokeLine(0.0,canvasH, canvasW, canvasH)
        // yAxis
        gc.strokeLine(0.0,0.0, 0.0, canvasH)
        //gc.fillRect(10.0, 0.0, 30.0, 250.0)

        gc.stroke = Color.LIGHTGREY
        gc.strokeLine(0.0,0.0, canvasW, 0.0)

        gc.strokeLine(canvasW,0.0, canvasW, canvasH)


        var numBars = model.curDataSet.data.size

        var width = (canvasW - (numBars + 1.0) *5.0) / numBars
        var xStart = 5.0
        val maxData = model.curDataSet.data.maxOrNull() ?: 0
        var ratio = canvasH / maxData
        var hueIncre = 360.0 / numBars

        maxLabel.text = "${maxData}"
        titleLabel.text = "${model.curDataSet.title}"
        xAxisLabel.text = "${model.curDataSet.xAxis}"
        yAxisLabel.text = "${model.curDataSet.yAxis}"

        //print("numBars is ${numBars}\n")
        //print("width is $width\n")
        //print("max is $maxData\n")
        //print("ration is $ratio\n")
        //print("${canvasW}\n")
        //print("${canvasH}\n")
        //print("${canvas.width}\n")
        //print("${canvas.height}\n")

        for (i in 0 until numBars) {
            //var rect = Rectangle(xStart, canvas.height, width, ratio*model.curDataSet.data[i])

            gc.fill = Color. hsb(hueIncre * i, 1.0, 1.0)
            var height = ratio*model.curDataSet.data[i]
            gc.fillRect(xStart, canvasH-height-1, width, height)
            xStart = xStart + 5.0 + width
            //bars.children.add(rect)
        }


    }

    init {
        //canvas.widthProperty().bind(this.widthProperty())
        //canvas.heightProperty().bind(this.heightProperty())
        yAxis.children.addAll(maxLabel, spacer1, yAxisLabel, spacer2, zeroLabel)
        xAxis.children.addAll(xAxisLabel)

        //yAxisLabel.textOrigin =

        xAxis.alignment = Pos.CENTER
        yAxis.alignment = Pos.BASELINE_CENTER
        title.alignment = Pos.CENTER

        yAxis.prefWidth = 50.0
        xAxis.prefHeight = 50.0


        // yAxisLabel.style = .vertical
        yAxisLabel.rotate = 270.0
        yAxisLabel.isWrapText = false
        title.children.addAll(titleLabel)



        barsPane.children.addAll(canvas)

        barsPane.widthProperty().addListener { observable, oldValue, newValue ->
            canvasW = newValue.toDouble()
            this.updateView()
        }

        barsPane.heightProperty().addListener { observable, oldValue, newValue ->
            // yAxis.spacing = newValue.toDouble() / 2
            canvasH = newValue.toDouble()
            this.updateView()
        }

        this.center = barsPane
        this.top = title
        this.left = yAxis
        this.bottom = xAxis

        this.background = Background(BackgroundFill(javafx.scene.paint.Color.WHITE,
            CornerRadii.EMPTY, Insets.EMPTY))

        this.padding = Insets(20.0)


        model.addView(this)



    }

}