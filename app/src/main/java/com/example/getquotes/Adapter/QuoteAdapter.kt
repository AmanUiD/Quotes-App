package com.example.getquotes.Adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.getquotes.Models.Result
import com.example.getquotes.R


class QuoteAdapter :
    RecyclerView.Adapter<QuoteAdapter.CustomViewHolder>() {
    var quoteList = ArrayList<Result>()

    var onItemClick: ((Result) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflaterView =
            LayoutInflater.from(parent.context).inflate(R.layout.quotes_data, parent, false)
        return CustomViewHolder(inflaterView)
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val items = quoteList.get(position)
        holder.quotes.text = items.content
        holder.author.text = items.author

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(items)
        }
    }

    override fun getItemCount(): Int {
        return quoteList.size
    }


    fun setQuotes(quoteList: ArrayList<Result>) {
        this.quoteList = quoteList
        notifyDataSetChanged()
    }

    class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val quotes: TextView = view.findViewById(R.id.content)
        val author: TextView = view.findViewById(R.id.author)


    }
}


