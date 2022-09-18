package com.example.getquotes.UI


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getquotes.Adapter.QuoteAdapter
import com.example.getquotes.Api.QuoteServices
import com.example.getquotes.Api.RetrofitHelper
import com.example.getquotes.R
import com.example.getquotes.Repository.QouteRepository
import com.example.getquotes.ViewModels.MainViewModel
import com.example.getquotes.ViewModels.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    private lateinit var mAdapter: QuoteAdapter
    private lateinit var progressBar: ProgressBar
    private var i : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar?.setCustomView(R.layout.action_bar)
        setContentView(R.layout.activity_main)
        //progress bar
        progressBar = findViewById(R.id.progreasBar)
        //recyclerview
        val mRecyclerView = findViewById<RecyclerView>(R.id.quoteView)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = QuoteAdapter()
        mRecyclerView.adapter = mAdapter

        //viewModel
        val quoteService = RetrofitHelper.getInstance().create(QuoteServices::class.java)
        val repository = QouteRepository(quoteService)

        progressBar.visibility = View.VISIBLE

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, Observer {
            progressBar.visibility = View.INVISIBLE
            mAdapter.setQuotes(it.results)
        })

        mAdapter.onItemClick = {
            i++
            val handler = Handler()
            handler.postDelayed({
                if (i==1){
            val intent = Intent(this, QuotesDetail::class.java)
            intent.putExtra("result",it)
            startActivity(intent)
                    finish()

                }else if (i==2){
                    Toast.makeText(this, "Double tap", Toast.LENGTH_LONG).show()
                }
                i = 0
            },500)
        }


//        favourite.setOnClickListener {
//            val intent = Intent(this@MainActivity, Favourite::class.java)
//            startActivity(intent)
//        }


    }

}
