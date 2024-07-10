package com.example.ageminutecalculator


import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
//import android.widget.Toolbar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate: TextView? = null
    private var tvAgeInMinutes: TextView? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val btnDatePicker: Button = findViewById(R.id.button2)
        tvSelectedDate=findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes=findViewById(R.id.tvAgeInMinutes)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }
   private fun clickDatePicker(){
        val myCalendar=Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd=DatePickerDialog(this, {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->Toast.makeText(this,
            "Year was $selectedYear,Month was ${selectedMonth+1},Day of Month was $selectedDayOfMonth", Toast.LENGTH_LONG).show()

            val selectedDate="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate?.text=selectedDate


            val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate=sdf.parse(selectedDate)
            //time gives the unit milliseconds
            theDate?.let{
                val selectedDateInMinutes=theDate.time/ 60000

                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
//1 minute= 60000 milliseconds
                currentDate?.let{
                    val currentDateInMinutes= (currentDate.time ) /60000

                    val differenceInMinutes = currentDateInMinutes-selectedDateInMinutes
                    tvAgeInMinutes?.text=differenceInMinutes.toString()
                }

            }


        },
            year,
            month,
            day
        )
        //1 min=60000 milliseconds
        //1hr=60 min
        //24 hr=60*24min
        //24hr=60*24*60000ms
         dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()



    }
    }
