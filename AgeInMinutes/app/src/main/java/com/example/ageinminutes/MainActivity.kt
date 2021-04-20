package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDatePicker.setOnClickListener {
            clickDatePicker(it)
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd=DatePickerDialog(this, DatePickerDialog
            .OnDateSetListener
            { view, selectedYear, selectedMonth, selectedDayOfMonth ->

                val selectedDate="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                tvSelectedDate.text=selectedDate
                val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate=sdf.parse(selectedDate)
                val selectedDateInMinutes=theDate!!.time/60000
                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes=currentDate!!.time/60000
                val differenceInMinutes=currentDateInMinutes-selectedDateInMinutes
                tvSelectedDateInMinute.text=differenceInMinutes.toString()
            }
            , year, month, day)
        dpd.datePicker.setMaxDate(Date().time-86400000)
        dpd.show()
    }
}