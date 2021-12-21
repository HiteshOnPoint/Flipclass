package com.ops.flipclass.ui.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.gson.Gson
import com.ops.flipclass.R
import com.ops.flipclass.adapters.UserAdapter
import com.ops.flipclass.adapters.UsersAdapter
import com.ops.flipclass.models.ListUsersModel
import com.ops.flipclass.models.User
import com.ops.flipclass.utilities.Infrastructure
import com.ops.flipclass.utilities.SharedPrefsUtils
import kotlinx.android.synthetic.main.activity_message.*
import kotlinx.android.synthetic.main.app_toolbar.*
import kotlinx.android.synthetic.main.app_toolbar_one.*
import kotlinx.android.synthetic.main.app_toolbar_one.llBackButton
import java.io.InputStream
import java.lang.Exception

class MessageActivity : AppCompatActivity(), UserAdapter.OnItemClickListener {

    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userList: ArrayList<User>
    private lateinit var adapter: UserAdapter
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    private lateinit var mSharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        Infrastructure.showProgressDialog(this@MessageActivity)

        mAuth = FirebaseAuth.getInstance()
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }*/

        civ_backButton.visibility = View.VISIBLE
        tvToolbarTitle.visibility = View.VISIBLE
        civ_loggedInUserImage.visibility = View.VISIBLE

        tvToolbarTitle.text = "Messages"
        val photo = SharedPrefsUtils.getStringPreference(this, "userPhoto")
        Glide.with(this).load((photo).toString()).into(civ_loggedInUserImage)

        civ_backButton.setOnClickListener {
            onBackPressed()
        }

        civ_loggedInUserImage.setOnClickListener {
            startActivity(Intent(this@MessageActivity, MyProfileActivity::class.java))
        }

        userList = ArrayList()
        adapter = UserAdapter(this, userList, this)

        userRecyclerView = findViewById(R.id.rv_users)

        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.adapter = adapter

        mDbRef = FirebaseDatabase.getInstance("https://flipclass-d87e8-default-rtdb.firebaseio.com/").reference

        mDbRef.child("user").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                userList.clear()
                for (postSnapshot in snapshot.children){

                    val currentUser = postSnapshot.getValue(User::class.java)

                    if (mAuth.currentUser?.uid != currentUser?.uid){

                        userList.add(currentUser!!)
                    }
                }
                adapter.notifyDataSetChanged()
                Infrastructure.dismissProgressDialog()

            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })

    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this@MessageActivity, "Item $position Clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = userList[position]
        val intent = Intent(this@MessageActivity, ChatActivity::class.java)
        intent.putExtra("name", clickedItem.name)
        intent.putExtra("uid", clickedItem.uid)
        intent.putExtra("photo", clickedItem.photo)
        startActivity(intent)
    }

    /*private fun status (status: String){

        val reference = mDbRef.child("user").child(mAuth.currentUser?.uid!!)

        val map = HashMap<String, Any>()
        map["status"] = status

        reference.updateChildren(map)
    }

    override fun onResume() {
        super.onResume()
        status("online")
    }

    override fun onPause() {
        super.onPause()
        status("offline")
    }*/


    private fun setUI() {

        val jsonStringUsers = loadJson(this)

        //Using Gson to parse json string
        val users = Gson().fromJson(jsonStringUsers, ListUsersModel::class.java)

        Log.d("USERS", "UserListSize: ${users.data.size}")

        //users list
        val usersAdapter = UsersAdapter(this, users.data)
        rv_users.adapter = usersAdapter
        rv_users.layoutManager = LinearLayoutManager(this)

        /*usersAdapter.setOnClickListener(object : FeaturedAdapter.OnClickListener{
            override fun onClick(position: Int, model: List<Featured.Data.Product>) {

                val intent = Intent(this@MainActivity, ProductsActivity::class.java)
//                intent.putExtra("ProductList", model.toString())
                intent.putExtra("ProductList", Gson().toJson(model))
                startActivity(intent)
//                Toast.makeText(this@MainActivity, model.get(0).product_name, Toast.LENGTH_SHORT).show()
            }
        })*/

        /*//shopByCategory list
        rv_shopByCategory.layoutManager = GridLayoutManager(this@MainActivity,3, GridLayoutManager.VERTICAL, false)//{ override fun canScrollVertically(): Boolean { return false } }
        rv_shopByCategory.suppressLayout(true)

        val shopByCategoryAdapter = ShopByCategoryAdapter(this, shopByCategory.data)
        rv_shopByCategory.adapter = shopByCategoryAdapter*/
    }

    private fun loadJson(context: Context): String? {
        var input: InputStream? = null
        var jsonString: String

        try {
            //Create InputStream
                input = context.assets.open("userlist.json")

            val size = input.available()

            //Create buffer with size
            val buffer = ByteArray(size)

            //Read data from InputStream into the Buffer
            input.read(buffer)

            //Create Json String
            jsonString = String(buffer)
            return jsonString

        }catch (e: Exception){
            e.printStackTrace()
        }finally {
            //Must close the stream
            input?.close()
        }
        return null
    }

}