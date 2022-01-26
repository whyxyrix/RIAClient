package com.whyxyrix.ria

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatDelegate
import android.widget.Switch
import java.io.File

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val path = getExternalFilesDir(null)
        var filename = "config"
        val file = File(path, filename)
        var darkMode_Switch: Switch = findViewById(R.id.darkMode)
        if(file.exists())
        {
            if(file.readText() == "dark")
            {
                darkMode_Switch.isChecked = true;
            }
        }
        darkMode_Switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                if(!file.exists()) {
                    file.createNewFile()
                }
                file.writeText("dark")
            }
            if(!isChecked)
            {
                val path = getExternalFilesDir(null)
                var filename = "config"
                val file = File(path, filename)
                if(!file.exists()) {
                    file.createNewFile()
                }
                file.writeText("light")
            }
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Внимание!")
            alert.setMessage("Приложение будет закрыто для применения темы.")
            alert.setPositiveButton("OK") {
                dialog, id -> finishAffinity()
            }
            alert.show()
        }
    }
}