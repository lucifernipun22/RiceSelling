package com.nipun.riceselling.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nipun.riceselling.R
import com.nipun.riceselling.model.GetMessagesModelSubList
import com.nipun.riceselling.viewHolder.MessageViewHolder

class MessagesAdapter(var getMessagesModel: GetMessagesModelSubList) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var layout: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_messages_layout, parent, false)
        return MessageViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MessageViewHolder){
            if(getMessagesModel[holder.adapterPosition].message.isNullOrEmpty()){
                holder.sender_text_message.visibility = View.GONE
                holder.receiver_text_message.visibility = View.VISIBLE
                holder.user_profile_image.visibility = View.VISIBLE
                holder.receiver_text_message.text = getMessagesModel[holder.adapterPosition].reply
            }else{
                holder.receiver_text_message.visibility = View.GONE
                holder.user_profile_image.visibility = View.GONE
                holder.sender_text_message.visibility = View.VISIBLE
                holder.sender_text_message.text = getMessagesModel[holder.adapterPosition].message
            }
        }

    }

    override fun getItemCount(): Int {
        return getMessagesModel.size
    }
}