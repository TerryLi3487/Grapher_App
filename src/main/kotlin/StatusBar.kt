import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.control.Label
import javafx.scene.control.Separator
import javafx.scene.layout.*
import javafx.scene.paint.Color

internal class StatusBar(private val model: Model): HBox(), IView {

    private val datasetNumLabel = Label("datasets")

    override fun updateView() {
        datasetNumLabel.text = "${model.allDataSets.size}" + " datasets"
    }

    init {
        this.padding = Insets(10.0)
        this.alignment = Pos.BASELINE_LEFT
        this.prefHeight = 25.0
        children.add(datasetNumLabel)

        this.background = Background(
            BackgroundFill(
                Color.LIGHTGRAY,
            CornerRadii.EMPTY, Insets.EMPTY)
        )

        model.addView(this)
    }
}