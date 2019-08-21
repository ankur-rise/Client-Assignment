package com.llm.ui.adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import com.llm.R
import com.llm.data.models.DeliveryItemDataModel

class DeliveryAdapter : PagedListAdapter<DeliveryItemDataModel, DeliveryAdapter.DeliveryViewHolder>(DIFF_CALLBACK) {

    var itemSelectListener:OnItemSelectListener?=null


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
            return DeliveryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.delivery_list_item, parent, false))

        }

        override fun onBindViewHolder(holder: DeliveryViewHolder, position: Int) {
            val concert: DeliveryItemDataModel = getItem(position)!!

            // Note that "concert" is a placeholder if it's null.
            holder.bindTo(concert)
        }

        override fun getItemCount(): Int {
            val itemCount = super.getItemCount()
            Log.i(DeliveryAdapter::class.java.simpleName, itemCount.toString())
            return itemCount
        }

        inner class DeliveryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private var tvDesc: TextView = itemView.findViewById(R.id.tv_desc)
            private var imageView: SimpleDraweeView = itemView.findViewById(R.id.iv) as SimpleDraweeView


            fun bindTo(item: DeliveryItemDataModel) {
                itemView.setOnClickListener {
                    itemSelectListener!!.onSelect(item)

                }

                tvDesc.text = item?.description
                imageView.setImageURI(Uri.parse(item?.imageUrl?:""))
            }

        }


        companion object {
            private val DIFF_CALLBACK = object :
                DiffUtil.ItemCallback<DeliveryItemDataModel>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                override fun areItemsTheSame(
                    oldDelivery: DeliveryItemDataModel,
                    newDelivery: DeliveryItemDataModel
                ) = oldDelivery.id == newDelivery.id

                override fun areContentsTheSame(
                    oldDelivery: DeliveryItemDataModel,
                    newDelivery: DeliveryItemDataModel
                ) = oldDelivery == newDelivery
            }
        }

    interface OnItemSelectListener {
        fun onSelect(model: DeliveryItemDataModel)
    }

    }