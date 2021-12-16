package com.ops.flipclass.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.ops.flipclass.R
import com.ops.flipclass.models.Message
import com.ops.flipclass.view.FCTextView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MessageAdapter(val context: Context, val messageList: ArrayList<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECEIVED = 1
    val ITEM_SENT = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == 1) {
            // Inflate receive
            val itemView =
                LayoutInflater.from(context).inflate(R.layout.item_received, parent, false)
            return ReceiveViewHolder(itemView)

        } else {
            // Inflate sent
            var itemView = LayoutInflater.from(context).inflate(R.layout.item_sent, parent, false)
            return SentViewHolder(itemView)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currentMessage = messageList[position]

        if (holder.javaClass == SentViewHolder::class.java) {

            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = currentMessage.message

            val timeStamp: Long? = currentMessage.timeStamp

            val simpleDateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
            val simpleTimeFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

            // you can also try these codes
            //dd-M-yyyy hh:mm:s ------       02-1-2018 06:07:59
            //dd MMMM yyyy	    ------         02 January 2018
            //dd MMMM yyyy zzzz    ------	02 January 2018 India Standard Time
            //dd MMM yyyy HH:mm:ss ------ Tue, 02 Jan 2018 18:07:59 IST

            val date: String = simpleDateFormat.format(timeStamp)
            val time: String = simpleTimeFormat.format(timeStamp)


            holder.sentMessageTime.text = ("$time : $date")

        } else {

            val viewHolder = holder as ReceiveViewHolder
            holder.receivedMessage.text = currentMessage.message

            val timeStamp: Long? = currentMessage.timeStamp

            val simpleDateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
            val simpleTimeFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

            val date: String = simpleDateFormat.format(timeStamp)
            val time: String = simpleTimeFormat.format(timeStamp)


            holder.receivedMessageTime.text = ("$time  $date")
        }

    }

    override fun getItemViewType(position: Int): Int {

        val currentMessage = messageList[position]

        if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)) {
            return ITEM_SENT
        } else {
            return ITEM_RECEIVED
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    class SentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sentMessage = itemView.findViewById<FCTextView>(R.id.tv_sentMessage)
        val sentMessageTime = itemView.findViewById<FCTextView>(R.id.tv_sentMessageTime)
    }

    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val receivedMessage = itemView.findViewById<FCTextView>(R.id.tv_receivedMessage)
        val receivedMessageTime = itemView.findViewById<FCTextView>(R.id.tv_receivedMessageTime)
    }
}