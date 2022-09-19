package com.dicoding.courseschedule.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.ui.list.ListViewModelFactory
import com.dicoding.courseschedule.util.TimePickerFragment
import java.text.SimpleDateFormat
import java.util.*

class AddCourseActivity : AppCompatActivity(),TimePickerFragment.DialogTimeListener {
    private lateinit var viewModel: AddCourseViewModel
    private lateinit var spinnerDay : Spinner
    private lateinit var courseEditText : TextView
    private lateinit var startTime : TextView
    private lateinit var endTime : TextView
    private lateinit var lecturerEditText : TextView
    private lateinit var noteEditText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)
        courseEditText = findViewById(R.id.add_course_name)
        lecturerEditText = findViewById(R.id.add_course_lecturer)
        noteEditText = findViewById(R.id.add_course_note)
        spinnerDay = findViewById(R.id.spinner_day)
        startTime = findViewById(R.id.add_start_time)
        endTime = findViewById(R.id.add_end_time)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.add_course)

        val factory = ListViewModelFactory.createFactory(this)
        viewModel = ViewModelProvider(this, factory).get(AddCourseViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_insert -> {

                val course = courseEditText.text.toString().trim()
                val lecturer = lecturerEditText.text.toString().trim()
                val note = noteEditText.text.toString().trim()
                val day = spinnerDay.selectedItemPosition
                val startTimeInput = startTime.text.toString()
                val endTimeInput = endTime.text.toString()

                if (course.isNotEmpty() && lecturer.isNotEmpty() && note.isNotEmpty()) {
                    viewModel.insertCourse(
                        courseName = course,
                        day = day,
                        startTime = startTimeInput,
                        endTime = endTimeInput,
                        lecturer = lecturer,
                        note = note
                    )
                    finish()
                } else {
                    Toast.makeText(this, getString(R.string.empty_list_message), Toast.LENGTH_SHORT)
                        .show()
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showTimePicekerStart(view: View) {
        val dialogFragment = TimePickerFragment()
        dialogFragment.show(supportFragmentManager, "startPicker")
    }

    fun showTimePicekerEnd(view: View) {
        val dialogFragment = TimePickerFragment()
        dialogFragment.show(supportFragmentManager, "endPicker")
    }

    override fun onDialogTimeSet(tag: String?, hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        if (tag == "startPicker") {
            startTime.text = dateFormat.format(calendar.time)
        } else {
            endTime.text = dateFormat.format(calendar.time)
        }
    }
}