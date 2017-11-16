package com.wtransnet.training.kotlin.trainingkotlinapp.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wtransnet.training.kotlin.trainingkotlinapp.R
import com.wtransnet.training.kotlin.trainingkotlinapp.model.LegoItem
import com.wtransnet.training.kotlin.trainingkotlinapp.util.loadUrl
import com.wtransnet.training.kotlin.trainingkotlinapp.util.mapLegoType
import kotlinx.android.synthetic.main.lego_item.view.*

/**
* Created by davidmartin on 23/10/17.
*/

class LegoAdapter(var data: MutableList<LegoItem> = mutableListOf(), private val onClickDetail: (Int) -> Unit,
                  private val onClickDelete: (LegoItem) -> Unit): RecyclerView.Adapter<LegoAdapter.LegoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LegoViewHolder {
        return LegoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lego_item, parent, false))
    }

    override fun onBindViewHolder(holder: LegoViewHolder, position: Int) {
        holder.onBind(data[position], onClickDetail, onClickDelete)
    }

    override fun getItemCount() = data.size

    class LegoViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun onBind(data: LegoItem, onClickDetail: (Int) -> Unit, onClickDelete: (LegoItem) -> Unit) {
            with(view) {
                lego_text.text = data.text
                lego_image.loadUrl(data.url)
                lego_type.text = context.mapLegoType(data.type)
                lego_delete.setOnClickListener { onClickDelete(data) }
                setOnClickListener { onClickDetail(data.id) }
            }
        }
    }
}