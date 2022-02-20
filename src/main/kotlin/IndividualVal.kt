import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.scene.control.Spinner

internal class IndividualSpinnerBox(val labelNum: Int, val initial: Int, model: Model): HBox() {
    val label = Label("$labelNum:")
    val spinner = Spinner<Int>(1, 100, initial, 1)

    init {
        spinner.prefWidth = 75.0
        label.prefWidth = 25.0
        label.alignment = Pos.BASELINE_RIGHT
        this.spacing = 7.0
        this.alignment = Pos.BASELINE_CENTER
        this.padding = Insets(10.0)
        this.children.addAll(label, spinner)

        spinner.valueProperty().addListener { obj, old, new ->
            // .curDataSet!!.data[labelNum-1] = new
            model.changedIndiVal(labelNum-1, new.toInt())
        }
    }
}


internal class IndividualVal(private val model: Model): VBox(), IView {


    override fun updateView() {
        this.children.clear()
        for (i in 0 until model.curDataSet.data.size) {
            this.children.add(IndividualSpinnerBox(i+1, model.curDataSet.data[i], model))
        }
    }

    init {
        this.alignment = Pos.CENTER
        this.prefWidth = 150.0
        this.spacing = -20.0


        model.addView(this)
    }
}