package com.example.apkmaker

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var urlBox: EditText
    private lateinit var preview: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 20, 20, 20)
        }

        root.addView(TextView(this).apply {
            text = "APK Maker 2.0"
            textSize = 28f
        })

        val appName = EditText(this).apply {
            hint = "App Name"
            setSingleLine(true)
        }
        root.addView(appName)

        urlBox = EditText(this).apply {
            hint = "Website URL"
            setSingleLine(true)
        }
        root.addView(urlBox)

        val previewButton = Button(this).apply {
            text = "PREVIEW WEBSITE"
        }
        root.addView(previewButton)

        preview = WebView(this).apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
        }

        root.addView(
            preview,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        val buildButton = Button(this).apply {
            text = "BUILD WEBVIEW APK"
        }
        root.addView(buildButton)

        previewButton.setOnClickListener {
            var url = urlBox.text.toString().trim()

            if (url.isEmpty()) {
                Toast.makeText(
                    this,
                    "Website URL দিন",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (!url.startsWith("http://") &&
                !url.startsWith("https://")) {
                url = "https://$url"
            }

            preview.loadUrl(url)
        }

        buildButton.setOnClickListener {
            Toast.makeText(
                this,
                "APK project প্রস্তুত। GitHub Actions থেকে APK build করুন।",
                Toast.LENGTH_LONG
            ).show()
        }

        setContentView(root)
    }
}
