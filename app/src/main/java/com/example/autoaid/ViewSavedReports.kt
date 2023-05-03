package com.example.autoaid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewSavedReports: AppCompatActivity() {
    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private lateinit var savedreportModalArrayList: ArrayList<SavedReportModel>
    private lateinit var dbHandler: DBHandler
    private lateinit var reportRVAdapter: ReportRVAdapter
    private lateinit var reportsRV: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_saved_reports)

        // initializing our all variables.
        savedreportModalArrayList = ArrayList()
        dbHandler = DBHandler(this@ViewSavedReports)

        // getting our course array
        // list from db handler class.
        savedreportModalArrayList = dbHandler.readReports()

        // on below line passing our array list to our adapter class.
        reportRVAdapter = ReportRVAdapter(savedreportModalArrayList!!, this)
        reportsRV = findViewById(R.id.idRVReports)

        // setting layout manager for our recycler view.
        val linearLayoutManager =
            LinearLayoutManager(this@ViewSavedReports, RecyclerView.VERTICAL, false)
        reportsRV.setLayoutManager(linearLayoutManager)

        // setting our adapter to recycler view.
        reportsRV.setAdapter(reportRVAdapter)


    }
}