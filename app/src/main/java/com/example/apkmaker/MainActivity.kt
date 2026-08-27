package com.example.apkmaker

import android.graphics.Typeface
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var preview: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 24, 24, 24)
        }

        root.addView(TextView(this).apply {
            text = "APK Maker"
            textSize = 28f
            typeface = Typeface.DEFAULT_BOLD
        })

        val appName = EditText(this).apply {
            hint = "App Name"
            setSingleLine(true)
        }

        val packageName = EditText(this).apply {
            hint = "Package Name"
            setSingleLine(true)
            setText("com.example.myapp")
        }

        root.addView(appName)
        root.addView(packageName)

        val row = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
        }

        val addText = Button(this).apply {
            text = "Add Text"
        }

        val addButton = Button(this).apply {
            text = "Add Button"
        }

        row.addView(addText, LinearLayout.LayoutParams(0, -2, 1f))
        row.addView(addButton, LinearLayout.LayoutParams(0, -2, 1f))
        root.addView(row)

        val clear = Button(this).apply {
            text = "Clear Preview"
        }

        root.addView(clear)

        root.addView(TextView(this).apply {
            text = "Preview"
            textSize = 20f
            typeface = Typeface.DEFAULT_BOLD
        })

        preview = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }

        root.addView(
            preview,
            LinearLayout.LayoutParams(-1, 0, 1f)
        )

        val build = Button(this).apply {
            text = "Prepare APK Build"
        }

        root.addView(build)

        addText.setOnClickListener {
            preview.addView(TextView(this).apply {
                text = appName.text.toString().ifBlank { "Sample Text" }
                textSize = 18f
                setPadding(8, 16, 8, 16)
            })
        }

        addButton.setOnClickListener {
            preview.addView(Button(this).apply {
                text = "My Button"
            })
        }

        clear.setOnClickListener {
            preview.removeAllViews()
        }

        build.setOnClickListener {
            Toast.makeText(
                this,
                "Project: ${appName.text}\nPackage: ${packageName.text}\nBuild with GitHub Actions.",
                Toast.LENGTH_LONG
            ).show()
        }

        setContentView(root)
    }
}
