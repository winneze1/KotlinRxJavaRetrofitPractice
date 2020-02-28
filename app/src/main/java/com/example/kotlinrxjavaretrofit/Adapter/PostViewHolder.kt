package com.example.kotlinrxjavaretrofit.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_layout.view.*

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val txt_author = itemView.txt_author
    val txt_title = itemView.txt_title
    val txt_content = itemView.txt_content
}