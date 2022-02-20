import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox

internal class NumberSummary(private val model: Model): HBox(), IView {
    /*private val numBox = HBox()
    private val minBox = HBox()
    private val maxBox = HBox()
    private val avgBox = HBox()
    private val sumBox = HBox()
    */

    private val labelBox = VBox()
    private val valBox = VBox()

    private val numLabel = Label("Number")
    private val minLabel = Label("Minimum")
    private val maxLabel = Label("Maximum")
    private val avgLabel = Label("Average")
    private val sumLabel = Label("Sum")

    private var num = 0
    private var min = 0
    private var max = 0
    private var avg = 0.0
    private var sum = 0

    private val numVal = Label("$num")
    private val minVal = Label("$min")
    private val maxVal = Label("$max")
    private val avgVal = Label("$avg")
    private val sumVal = Label("$sum")


    override fun updateView() {
        numVal.text = "${model.curDataSet.data.size}"
        minVal.text = "${model.curDataSet.data.minOrNull() ?: 0}"
        maxVal.text = "${model.curDataSet.data.maxOrNull() ?: 0}"
        avgVal.text = "${"%.1f".format(model.curDataSet.data.average())}"
        sumVal.text = "${model.curDataSet.data.sum()}"
    }

    init {
        /*numBox.children.addAll(numLabel, numVal)
        minBox.children.addAll(minLabel, minVal)
        maxBox.children.addAll(maxLabel, maxVal)
        avgBox.children.addAll(avgLabel, avgVal)
        sumBox.children.addAll(sumLabel, sumVal)
         */


        labelBox.children.addAll(numLabel, minLabel, maxLabel, avgLabel, sumLabel)
        valBox.children.addAll(numVal, minVal, maxVal, avgVal, sumVal)

        labelBox.spacing = 7.0
        valBox.spacing = 7.0

        labelBox.prefWidth = 70.0
        this.prefWidth = 125.0
        this.children.addAll(labelBox, valBox)
        this.padding = Insets(10.0, 20.0, 0.0, 10.0)
        model.addView(this)

    }
}