package com.ops.flipclass.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ops.flipclass.R
import com.ops.flipclass.models.User
import com.ops.flipclass.ui.activity.ChatActivity
import de.hdodenhof.circleimageview.CircleImageView
import java.text.SimpleDateFormat
import java.util.*

class UserAdapter(val context: Context, val userList: ArrayList<User>, val listener: OnItemClickListener):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false)
        /*val position = holder.item_container.tag as Int
        Log.e("1122",""+position)
        val currentUser = userList[position]*/
        /*holder.item_container.setOnClickListener {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("name", currentUser.name)
            intent.putExtra("uid", currentUser.uid)
            context.startActivity(intent)
        }*/
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.textName.text = currentUser.name
        Glide.with(holder.itemView.context).load(currentUser.photo).into(holder.imgPhoto)

        FirebaseDatabase.getInstance("https://flipclass-d87e8-default-rtdb.firebaseio.com/").reference
            .child("chats")
            .child(FirebaseAuth.getInstance().currentUser?.uid + currentUser.uid)
            .child("messages")
            .orderByChild("timeStamp")
            .limitToLast(1)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.hasChildren()){
                        for (snapshot1 in snapshot.children){
                            holder.textLastMessage.text = snapshot1.child("message").value.toString()

                            val timeStamp: Long? = snapshot1.child("timeStamp").value as Long?
                            val simpleTimeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
                            val time: String = simpleTimeFormat.format(timeStamp)

                            holder.textLastMessageTime.text = ("$time")
                        }
                    }
                    notifyDataSetChanged()
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            })

        /*holder.itemView.setOnClickListener {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("name", currentUser.name)
            intent.putExtra("uid", currentUser.uid)
            intent.putExtra("photo", currentUser.photo)
            context.startActivity(intent)
            *//*Toast.makeText(context,"name:"+currentUser.name+"\n\n"+"Uid"+currentUser.uid,Toast.LENGTH_LONG).show()
            Log.e("1122","name:"+currentUser.name+"\nUid"+currentUser.uid+"\nPos"+position+"\n\n")*//*
        }*/

        /*if (currentUser.status.equals("online")){
            holder.imgOnline.visibility = View.VISIBLE
            holder.imgOffline.visibility = View.GONE
        }else{
            holder.imgOnline.visibility = View.GONE
            holder.imgOffline.visibility = View.VISIBLE
        }*/

    }

    override fun getItemCount(): Int {
        return  userList.size
    }

    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{

        val textName = itemView.findViewById<TextView>(R.id.tv_firstName)
        val imgOnline = itemView.findViewById<CircleImageView>(R.id.civ_onlineBadge)
        val imgOffline = itemView.findViewById<CircleImageView>(R.id.civ_offlineBadge)
        val imgPhoto = itemView.findViewById<CircleImageView>(R.id.civ_user_image_chat)
        val textLastMessage = itemView.findViewById<TextView>(R.id.tv_lastMessage)
        val textLastMessageTime = itemView.findViewById<TextView>(R.id.tv_timeAgo)
        val item_container = itemView.findViewById<LinearLayout>(R.id.item_container)

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position  = adapterPosition
            if (position != RecyclerView.NO_POSITION)
            listener.onItemClick(position)
        }

    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}