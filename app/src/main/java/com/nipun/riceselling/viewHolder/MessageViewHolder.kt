package com.nipun.riceselling.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import com.nipun.riceselling.R
import de.hdodenhof.circleimageview.CircleImageView


class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    var sender_text_message: TextView = itemView.findViewById(R.id.senderMessageText)
    var receiver_text_message: TextView = itemView.findViewById(R.id.receiverMessageText)
    var user_profile_image: CircleImageView = itemView.findViewById(R.id.messageUserImage)
    var senderImageMsg: RoundedImageView = itemView.findViewById(R.id.messageImageVsender)
    var receiverImageMsg: RoundedImageView = itemView.findViewById(R.id.messageImageVreceiver)

}