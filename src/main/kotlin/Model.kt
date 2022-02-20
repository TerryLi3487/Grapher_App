class Model {
    // simple accessor method to fetch the current state
    // the data in the model, just a counter


    // all views of this model
    private val views: ArrayList<IView> = ArrayList()

    var curDataSet: DataSet = createTestDataSet("Increasing") ?: DataSet("", "", "", ArrayList())
    var allDataSets =  mapOf<String, DataSet>()

    //private var datasetCount = 0;
    private val loremIpsum: List<String> = listOf("sorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing",
        "elit", "Mauris", "faucibus", "ultrices", "nunc", "rutrum", "sodales", "lorem", "bibendum", "vel",
        "saecenas", "sed", "dui", "pulvinar", "tincidunt", "eros", "vel", "varius", "nulla", "sed", "lacinia",
        "posuere", "rhoncus", "fusce", "sit", "amet", "nibh", "in", "sem", "faucibus", "vestibulum", "ac", "ut",
        "nibh", "integer", "placerat", "augue", "consequat", "mattis", "imperdiet", "sed", "pellentesque", "massa",
        "at", "velit", "gravida", "ac", "condimentum", "magna", "sagittis", "maecenas", "tempus", "mattis", "dui",
        "sed", "vehicula", "urna", "feugiat", "rhoncus", "aliquam", "erat", "volutpat", "donec", "semper", "quam",
        "vitae", "efficitur", "lacinia", "imperdiet", "purus", "morbi", "elit", "enim", "varius", "ac", "hendrerit",
        "eget", "lacinia", "at", "erat", "elementum", "lectus", "scelerisque", "viverra", "dignissim", "turpis",
        "ligula", "ultrices", "ligula", "id", "vehicula", "magna", "urna", "vel", "elit")

    init {
        allDataSets = allDataSets + Pair("Increasing",
            createTestDataSet("Increasing") ?: DataSet("", "", "", ArrayList()))
        allDataSets = allDataSets + Pair("Middle",
            createTestDataSet("Middle")?: DataSet("", "", "", ArrayList()))
        allDataSets = allDataSets + Pair("Large",
            createTestDataSet("Large") ?: DataSet("", "", "", ArrayList()))
        allDataSets = allDataSets + Pair("Single",
            createTestDataSet("Single") ?: DataSet("", "", "", ArrayList()))
        allDataSets = allDataSets + Pair("Range",
            createTestDataSet("Range") ?: DataSet("", "", "", ArrayList()))
        allDataSets = allDataSets + Pair("Percentage",
            createTestDataSet("Percentage") ?: DataSet("", "", "", ArrayList()))

    }

    fun capitalize(word: String): String {
        val result: String = word.take(1).uppercase() + word.drop(1).lowercase()
        return result
    }

    // method that the views can use to register themselves with the Model
    // once added, they are told to update and get state from the Model
    fun addView(view: IView) {
        views.add(view)
        view.updateView()
    }

    fun removeView(view: IView?) {
        // remove view here
    }

    // calculate number summary

    fun changedDataSet(newName: String) {
        curDataSet = allDataSets.get(newName) ?: DataSet("", "", "", ArrayList())
        //print(curDataSet.title)
        notifyAllObservers()
    }

    fun changedText(title: String, xAxis: String, yAxis: String) {
        curDataSet.title = title;
        curDataSet.xAxis = xAxis;
        curDataSet.yAxis = yAxis;
        notifyAllObservers()
    }

    fun changedIndiVal(index: Int, newVal: Int) {
        curDataSet.data[index] = newVal
        notifyAllObservers()
    }

    fun addedNewDataSet(num: Int, count:Int) {
        val name = "New${count}"

        var title = ""
        for(i in 1..3) {
            val loremNum:Int = (0..101).random()
            title += capitalize(loremIpsum[loremNum])
            title += " "
        }

        var xAxis = ""
        val loremNum:Int = (0..101).random()
        xAxis = loremIpsum[loremNum]

        val data: MutableList<Int> = ArrayList()
        for (i in 0 until num) {
            val randomNum = (0..100).random()
            data.add(randomNum)
        }

        allDataSets = allDataSets + Pair(name, DataSet(title, xAxis, "Value", data))
        notifyAllObservers()

    }


    // the model uses this method to notify one Views that the data has changed
    // the expectation is that the View will refresh themselves to display new data when appropriate
    private fun notifyObservers(view: IView) {
        view.updateView()
    }

    // the model uses this method to notify one Views that the data has changed
    // the expectation is that the View will refresh themselves to display new data when appropriate
    private fun notifyAllObservers() {
        for (view in views) {
            // println("Model: notify View")
            view.updateView()
        }
    }
}