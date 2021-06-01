package com.example.round

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.round.databinding.ActivityScheduleBinding

class scheduleActivity : AppCompatActivity() {
    lateinit var binding: ActivityScheduleBinding
    lateinit var DBHelper: sDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    fun clearEditText(){
        binding.apply {
            sIDEdit.text.clear()
            sNameEdit.text.clear()
            startHourEdit.text.clear()
            startMinuteEdit.text.clear()
            endHourEdit.text.clear()
            endMinuteEdit.text.clear()
        }
    }

    private fun init(){
        DBHelper = sDBHelper(this)
        val routineID: Int = getIntent().getExtras()!!.getInt("RID")

        binding.apply{
            insertBtn.setOnClickListener{
                val name = sNameEdit.text.toString()
                val start = (startHourEdit.text.toString().toInt())*60 - startMinuteEdit.text.toString().toInt()//
                val end = (endHourEdit.text.toString().toInt())*60 - endMinuteEdit.text.toString().toInt()//
                val schedule = scheduleData(routineID, 0, name, start, end)
                val result = DBHelper.insertSchedule(schedule)
                if (result){
                    Toast.makeText(this@scheduleActivity, "Data INSERT SUCCESS", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@scheduleActivity, "Data INSERT FAILED", Toast.LENGTH_SHORT).show()
                }
                clearEditText()
            }
        }
    }
}