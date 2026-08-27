package com.example.apkmaker

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
        }

        val title = TextView(this).apply {
            text = "APK Maker"
            textSize = 28f
            setPadding(0, 0, 0, 24)
        }
        root.addView(title)

        val name = EditText(this).apply {
            hint = "App Name"
            setSingleLine(true)
        }
        root.addView(name)

        val packageName = EditText(this).apply {
            hint = "Package Name (e.g. com.example.myapp)"
            setSingleLine(true)
        }
        root.addView(packageName)

        val addText = Button(this).apply {
            text = "Add Text"
        }
        root.addView(addText)

        val addButton = Button(this).apply {
            text = "Add Button"
        }
        root.addView(addButton)

        val preview = TextView(this).apply {
            text = "Preview will appear here"
            textSize = 18f
            setPadding(0, 32, 0, 16)
        }
        root.addView(preview)

        addText.setOnClickListener {
            preview.text = "Sample Text\n\nApp: ${name.text}"
        }

        addButton.setOnClickListener {
            preview.text = "Button added\n\nApp: ${name.text}\nPackage: ${packageName.text}"
        }

        val build = Button(this).apply {
            text = "Build APK (GitHub Actions)"
        }
        root.addView(build)

        build.setOnClickListener {
            Toast.makeText(
                this,
                "Project settings saved. APK build is done by GitHub Actions.",
                Toast.LENGTH_LONG
            ).show()
        }

        setContentView(root)
    }
}
