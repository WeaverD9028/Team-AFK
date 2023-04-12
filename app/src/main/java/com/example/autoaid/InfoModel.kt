package com.example.autoaid

data class InfoModel(// creating getter and setter methods
    // variables for our vin, id
    var carMake: String? = null,
    var carModel: String? = null,
    var carYear: String? = null,
    var carEngine: String? = null
)
{
    var id = 0

}