package com.ops.flipclass.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ops.flipclass.ChatActivity
import com.ops.flipclass.R
import com.ops.flipclass.models.User
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.app_toolbar_one.*

class UserAdapter(val context: Context, val userList: ArrayList<User>):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var lastMessage : String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false)
        return UserAdapter.UserViewHolder(itemView)
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
                        }
                    }
                    notifyDataSetChanged()
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            })

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("name", currentUser.name)
            intent.putExtra("uid", currentUser.uid)

            context.startActivity(intent)
        }

        if (currentUser.status.equals("online")){
            holder.imgOnline.visibility = View.VISIBLE
            holder.imgOffline.visibility = View.GONE
        }else{
            holder.imgOnline.visibility = View.GONE
            holder.imgOffline.visibility = View.VISIBLE
        }

    }

    override fun getItemCount(): Int {
        return  userList.size
    }

    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textName = itemView.findViewById<TextView>(R.id.tv_firstName)
        val imgOnline = itemView.findViewById<CircleImageView>(R.id.civ_onlineBadge)
        val imgOffline = itemView.findViewById<CircleImageView>(R.id.civ_offlineBadge)
        val imgPhoto = itemView.findViewById<CircleImageView>(R.id.civ_user_image_chat)
        val textLastMessage = itemView.findViewById<TextView>(R.id.tv_lastMessage)

    }
}