import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.layout.HBox

internal class AxisBar(private val model: Model): HBox(), IView {
    private val titleLabel = Label("Title:")
    private val titleField = TextField(model.curDataSet?.title)
    private val xAxisLabel = Label("X-Axis:")
    private val xAxisField = TextField(model.curDataSet?.xAxis)
    private val yAxisLabel = Label("Y-Axis:")
    private val yAxisField = TextField(model.curDataSet?.yAxis)

    private var oldDataSet = createTestDataSet("Increasing") ?: DataSet("", "", "", ArrayList())

    override fun updateView() {

        if (model.curDataSet != oldDataSet) {
            titleField.text = model.curDataSet.title
            xAxisField.text = model.curDataSet.xAxis
            yAxisField.text = model.curDataSet.yAxis

            oldDataSet = model.curDataSet
        }


    }

    init {
        titleField.prefWidth = 150.0
        xAxisField.prefWidth = 110.0
        yAxisField.prefWidth = 110.0
        children.addAll(titleLabel, titleField, xAxisLabel, xAxisField, yAxisLabel, yAxisField)
        this.spacing = 8.0
        this.padding = Insets(10.0)
        this.alignment = Pos.CENTER_LEFT
        model.addView(this)



        titleField.setOnKeyTyped {

            model.changedText(titleField.text, xAxisField.text, yAxisField.text);
        }

        xAxisField.setOnKeyTyped{

            //val caretPos: Int = xAxisField.getCaretPosition()
            /*print("caret pos is $caretPos\n")
            print("caret pos is ${xAxisField.text.length}\n")
            val new = xAxisField.text[caretPos]
            //print("new is $new\n")
            print("new is ${xAxisField.text[0]}\n")
            print("new is ${xAxisField.text[1]}\n")
            print("new is ${xAxisField.text[2]}\n")
            print("new is ${xAxisField.text[3]}\n")
            print("new is ${xAxisField.text[4]}\n")
            print("new is ${xAxisField.text[5]}\n")
            print("new is ${xAxisField.text[6]}\n")
            print("new is ${xAxisField.text[7]}\n")
            print("new is ${xAxisField.text[8]}\n")
            //print("new is ${xAxisField.text[9]}\n")

             */
           // xAxisField.caretPosition = xAxisField.caretPosition + 1
            model.changedText(titleField.text, xAxisField.text, yAxisField.text);
        }

        yAxisField.setOnKeyTyped {
            model.changedText(titleField.text, xAxisField.text, yAxisField.text);
        }

    }
}