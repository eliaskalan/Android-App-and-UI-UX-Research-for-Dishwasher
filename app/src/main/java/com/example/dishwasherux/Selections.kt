package com.example.dishwasherux



interface SelectionInterface {
    var id: String
    var title: String
    var temperature: Int
    var imagePath: Int
    var color: String
    val description: String
    val duration_minutes: Int
    val duration_hours: Int
}

object SoftSelection: SelectionInterface {
    override  var id: String = "soft";
    override var title: String = "Ελαφρύ";
    override var temperature: Int = 40;
    override var imagePath: Int = R.drawable.plate_soft;
    override var color: String = "#4CAF50";
    override val description: String = "description";
    override val duration_minutes: Int = 30;
    override val duration_hours: Int = 0;
}
object NormalSection: SelectionInterface {
    override var id: String = "normal";
    override var title: String = "Κανονικό";
    override var temperature: Int = 60;
    override var imagePath: Int = R.drawable.plate_classic;
    override var color: String = "#FF9800";
    override val description: String = "description";
    override val duration_minutes: Int = 0;
    override val duration_hours: Int = 1;
}
object StrongSection: SelectionInterface {
    override var id: String = "strong";
    override var title: String = "Βαρύ";
    override var temperature: Int = 80;
    override var imagePath: Int = R.drawable.plate_strong;
    override var color: String = "#F44336";
    override val description: String = "description";
    override val duration_minutes: Int = 30;
    override val duration_hours: Int = 1;
}
var Selections = arrayOf(SoftSelection, NormalSection, StrongSection)

