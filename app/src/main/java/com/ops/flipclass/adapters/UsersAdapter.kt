package com.ops.flipclass.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ops.flipclass.R
import com.ops.flipclass.models.ListUsersModel
import kotlinx.android.synthetic.main.item_chat.view.*

class UsersAdapter(val context: Context, var users: List<ListUsersModel.Data>): RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    /*private var onClickListener: OnClickListener? = null

    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener = onClickListener
    }

    interface OnClickListener {

        fun onClick(position: Int, model: List<Featured.Data.Product>)
    }*/

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var firstName: TextView = itemView.tv_firstName
        var message: TextView = itemView.tv_lastMessage
        var timeAgo: TextView = itemView.tv_timeAgo
        var messageCount: TextView = itemView.tv_messageCount
        var image: ImageView = itemView.civ_user_image_chat
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.firstName.text = user.first_name
        holder.message.text = user.last_message
        holder.timeAgo.text = user.time_ago
        holder.messageCount.text = user.unseen_count.toString()
        Glide.with(holder.itemView.context).load(user.avatar).fitCenter().into(holder.image)

        /*holder.itemView.setOnClickListener {
            if (onClickListener != null){
                onClickListener!!.onClick(position, featured[position].product)
            }
        }*/

    }

    override fun getItemCount(): Int {
        return users.size
    }
}