package com.example.autoaid

data class SavedReportModel// constructor
    (// creating getter and setter methods
    // variables for our vin, id
    var date : String? = null,
    var time : String? = null,
    var carMake : String? = null,
    var carModel : String? = null,
    var carYear : String? = null,
    var carVin : String? = null,
    var carDescription: String? = null,
    var carCode : String? = null,
    var carCost: String? = null,
) {
    var id = 0

}
