package com.example.apkmaker

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.*
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

        root.addView(EditText(this).apply {
            hint = "App Name"
            setSingleLine(true)
        })

        urlBox = EditText(this).apply {
            hint = "Website URL"
            setSingleLine(true)
            inputType =
                android.text.InputType.TYPE_CLASS_TEXT or
                android.text.InputType.TYPE_TEXT_VARIATION_URI
        }
        root.addView(urlBox)

        val previewButton = Button(this).apply {
            text = "PREVIEW WEBSITE"
        }
        root.addView(previewButton)

        preview = WebView(this)

        preview.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            databaseEnabled = true
            cacheMode = WebSettings.LOAD_NO_CACHE
            loadsImagesAutomatically = true
            mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
        }

        preview.webViewClient = object : WebViewClient() {

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                if (request?.isForMainFrame == true) {
                    Toast.makeText(
                        this@MainActivity,
                        "Website load হয়নি। Internet connection check করুন।",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        root.addView(
            preview,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        val browserButton = Button(this).apply {
            text = "OPEN IN BROWSER"
        }
        root.addView(browserButton)

        val buildButton = Button(this).apply {
            text = "BUILD WEBVIEW APK"
        }
        root.addView(buildButton)

        previewButton.setOnClickListener {
            loadWebsite()
        }

        browserButton.setOnClickListener {
            var url = urlBox.text.toString().trim()

            if (url.isNotEmpty()) {
                if (!url.startsWith("http://") &&
                    !url.startsWith("https://")) {
                    url = "https://$url"
                }

                startActivity(
                    Intent(Intent.ACTION_VIEW, Uri.parse(url))
                )
            }
        }

        buildButton.setOnClickListener {
            Toast.makeText(
                this,
                "Website App configuration ready.",
                Toast.LENGTH_LONG
            ).show()
        }

        preview.loadData(
            """
            <html>
            <body style="font-family:sans-serif;padding:30px">
            <h2>Website Preview</h2>
            <p>Website URL লিখে PREVIEW WEBSITE চাপুন।</p>
            </body>
            </html>
            """.trimIndent(),
            "text/html",
            "UTF-8"
        )

        setContentView(root)
    }

    private fun loadWebsite() {
        var url = urlBox.text.toString().trim()

        if (url.isEmpty()) {
            Toast.makeText(
                this,
                "Website URL দিন",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (!url.startsWith("http://") &&
            !url.startsWith("https://")) {
            url = "https://$url"
        }

        preview.clearCache(true)
        preview.clearHistory()
        preview.loadUrl(url)
    }
}
