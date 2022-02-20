import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane

internal class ToolBar (private val model: Model) : HBox(), IView {

    private val datasetLabel = Label("Dataset: ")
    private val dropDownBtn = ChoiceBox<String>()
    //private var dropDownVal:String = "Increasing"
    private val dropDownList: MutableList<String> = ArrayList()
    private val separator = Separator(Orientation.VERTICAL)
    private val newBtn = Button("New")
    private val spinner = Spinner<Int>(1, 20, 5, 1)

    private val datasetBox = HBox()
    private val newBox = HBox()

    private var datasetCount = 0




    override fun updateView() {

        for (string in dropDownList) {
            dropDownBtn.items.add(string)
        }
        dropDownList.clear()
        //dropDownBtn.value = dropDownVal
    }



    init {
        newBtn.prefWidth = 80.0
        newBtn.maxWidth = 80.0

        spinner.prefWidth = 55.0

        datasetBox.children.addAll(datasetLabel, dropDownBtn)
        newBox.children.addAll(newBtn, spinner)

        datasetBox.alignment = Pos.CENTER_LEFT
        datasetBox.spacing = 4.0
        newBox.spacing = 4.0


        //dropDownBtn.items.addAll("Increasing", "Middle", "Large", "Single", "Range", "Percentage")
        dropDownList.addAll(listOf("Increasing", "Middle", "Large", "Single", "Range", "Percentage"))
        dropDownBtn.value = "Increasing"

        children.addAll(datasetBox, separator, newBox)
        this.spacing = 10.0
        this.padding = Insets(10.0)
        this.alignment = Pos.CENTER_LEFT


        model.addView(this)

            /*dropDownBtn.setOnAction {
            val newName = dropDownBtn.value
            model.changedDataSet(newName)
        }

             */
        dropDownBtn.valueProperty().addListener { obj, old, new ->
            model.changedDataSet(new.toString())
        }

        newBtn.setOnMouseClicked {
            val num = spinner.value
            dropDownList.add("New${datasetCount}")
            model.addedNewDataSet(num, datasetCount)
            datasetCount += 1

        }

    }

}