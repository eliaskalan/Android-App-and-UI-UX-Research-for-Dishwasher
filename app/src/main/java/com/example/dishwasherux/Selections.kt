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
    val demo_hour_time: Int
    val demo_second_time: Int
}

object SoftSelection: SelectionInterface {
    override  var id: String = "soft";
    override var title: String = "Ελαφρύ";
    override var temperature: Int = 40;
    override var imagePath: Int = R.drawable.plate_soft;
    override var color: String = "#4CAF50";
    override val description: String = "\"Ελαφρή\" πρόγραμμα πλυντηρίου: 40°C, 30 λεπτά. Ιδανικό για ευαίσθητα ρούχα, απαλλάσσει από ελαφριά λεκέδα. Γρήγορο και αποτελεσματικό πλύσιμο. Καθημερινή χρήση, προστατεύει τα υφάσματα.";
    override val duration_minutes: Int = 30;
    override val duration_hours: Int = 0;
    override val demo_hour_time: Int = 0;
    override val demo_second_time: Int = 30;

}
object NormalSection: SelectionInterface {
    override var id: String = "normal";
    override var title: String = "Κανονικό";
    override var temperature: Int = 60;
    override var imagePath: Int = R.drawable.plate_classic;
    override var color: String = "#FF9800";
    override val description: String = "Πρόγραμμα \"Κανονικό\": 60°C, 1 ωρα. Κατάλληλο για καθημερινά ρούχα. Γρήγορη πλύση με αποτελεσματικά αποτελέσματα. Ιδανικό για γρήγορη ανανέωση των ρούχων σας.";
    override val duration_minutes: Int = 0;
    override val duration_hours: Int = 1;
    override val demo_hour_time: Int = 1;
    override val demo_second_time: Int = 0;
}
object StrongSection: SelectionInterface {
    override var id: String = "strong";
    override var title: String = "Βαρύ";
    override var temperature: Int = 80;
    override var imagePath: Int = R.drawable.plate_strong;
    override var color: String = "#F44336";
    override val description: String = "Πρόγραμμα \"Δυνατό\": 80°C, 1,30 ώρα. Κατάλληλο για ανθεκτικά ρούχα και λεκέδες. Ισχυρή πλύση με εξαιρετικά αποτελέσματα. Εγγυάται την απομάκρυνση των επίμονων λεκέδων και τη βαθιά καθαριότητα.";
    override val duration_minutes: Int = 30;
    override val duration_hours: Int = 1;
    override val demo_hour_time: Int = 1;
    override val demo_second_time: Int = 30;
}
var Selections = arrayOf(SoftSelection, NormalSection, StrongSection)

