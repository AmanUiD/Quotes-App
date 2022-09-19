package com.example.getquotes.UI

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.getquotes.Models.Result
import com.example.getquotes.R
import kotlinx.android.synthetic.main.activity_quotes_detail.*

class QuotesDetail : AppCompatActivity() {
    lateinit var content: TextView
    lateinit var author: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.detail_bar)
        setContentView(R.layout.activity_quotes_detail)
        val backBtn: ImageView = findViewById(R.id.backBtn)

        val result = intent.getParcelableExtra<Result>("result")
        if (result != null) {
            content = findViewById(R.id.contentText)
            author = findViewById(R.id.authorName)
            content.text = result.content
            author.text = result.author

        }

        share.setOnClickListener {
            val message: String = content.text.toString()
            val i = Intent()
            i.action = Intent.ACTION_SEND
            i.putExtra(Intent.EXTRA_TEXT, message)
            i.type = "text/plain"
            startActivity(Intent.createChooser(i, "Share"))
        }

        backBtn.setOnClickListener {

            val intent = Intent(this@QuotesDetail, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }


    fun copyText(view: View) {
        Toast.makeText(this, "Text Copied", Toast.LENGTH_LONG).show()
        copyToClipBoard(content.text.toString())

    }

    fun Context.copyToClipBoard(text: CharSequence) {
        val clipBoard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Text Copy", text)
        clipBoard.setPrimaryClip(clip)

    }

}

