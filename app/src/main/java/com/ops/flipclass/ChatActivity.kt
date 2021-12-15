package com.ops.flipclass

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.ops.flipclass.adapters.MessageAdapter
import com.ops.flipclass.models.Message
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.app_toolbar_one.*

class ChatActivity : AppCompatActivity() {

    private lateinit var chatRecyclerview: RecyclerView
    private lateinit var messageBox: EditText
    private lateinit var sendButton: LinearLayout
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList: ArrayList<Message>
    private lateinit var mDbRef: DatabaseReference

    var senderRoom: String? = null
    var receiverRoom: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        /*window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT*/


        val name = intent.getStringExtra("name")
        val receiverUid = intent.getStringExtra("uid")

        val senderUid = FirebaseAuth.getInstance().currentUser?.uid
        mDbRef = FirebaseDatabase.getInstance("https://flipclass-d87e8-default-rtdb.firebaseio.com/").reference

        senderRoom = receiverUid + senderUid
        receiverRoom =  senderUid + receiverUid

        tvToolbarTitle.text = name

        chatRecyclerview = findViewById(R.id.rv_chat)
        messageBox = findViewById(R.id.et_typeMessage)
        sendButton = findViewById(R.id.ll_sendMessageButton)
        messageList = ArrayList()
        messageAdapter = MessageAdapter(this, messageList)

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.stackFromEnd = true
        chatRecyclerview.layoutManager = linearLayoutManager
        chatRecyclerview.adapter = messageAdapter

        // Logic for adding data to recyclerView
        mDbRef.child("chats").child(senderRoom!!).child("messages")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    messageList.clear()

                    for (postSnapshot in snapshot.children){

                        val message = postSnapshot.getValue(Message::class.java)
                        messageList.add(message!!)
                    }
                    messageAdapter.notifyDataSetChanged()
                    chatRecyclerview.scrollToPosition(messageList.size - 1)

                }

                override fun onCancelled(p0: DatabaseError) {

                }
            })

        //adding message to database
        sendButton.setOnClickListener {
            val message = messageBox.text.toString().trim()
            val currentTimeStamp = (System.currentTimeMillis()).toString().substring(0,13)

            if (message.isNotEmpty()){
                val messageObject = Message(message, senderUid, currentTimeStamp.toLong())

                mDbRef.child("chats").child(senderRoom!!).child("messages").push()
                    .setValue(messageObject).addOnSuccessListener {
                        mDbRef.child("chats").child(receiverRoom!!).child("messages").push()
                            .setValue(messageObject)
                    }
                messageBox.setText("")
            }else{
                Toast.makeText(this@ChatActivity, "Please Type something", Toast.LENGTH_SHORT)
                    .show()
            }

        }

    }
}