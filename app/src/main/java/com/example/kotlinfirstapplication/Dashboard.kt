package com.example.kotlinfirstapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.solver.widgets.Helper
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import com.example.kotlinfirstapplication.Utils.SharedPreferenceManager
import com.example.kotlinfirstapplication.Utils.Sqlite_Database
import kotlin.math.log

class Dashboard : AppCompatActivity() , OnClick{

    override fun ToastCreate(message: String)
    {
        Log.d("Dashboard",message)
         Toast.makeText(this@Dashboard,message,Toast.LENGTH_SHORT).show()
    }

    var sharedPreferenceManager :SharedPreferenceManager ?= null
    var recyclerView:RecyclerView ?= null
    var searchview:EditText ?= null
    val userList = ArrayList<UserModel>()
    var logout: TextView ?= null
     var adapter: RecyclerAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        sharedPreferenceManager = SharedPreferenceManager(this@Dashboard)


        userList.add(UserModel("divyanshu","divyanshu.arora8@gmail.com","1",""))
        userList.add(UserModel("Mukund","mukund@gmail.com","2",""))
        userList.add(UserModel("Chandrapaul","chandrapaul@gmail.com","3",""))
        userList.add(UserModel("Anshul","anshul@gmail.com","4",""))

        recyclerView = findViewById(R.id.recyclerView)as RecyclerView
        searchview = findViewById(R.id.searchview) as EditText
        logout = findViewById(R.id.logout) as TextView
        loadData()


        logout!!.setOnClickListener()
        {
          sharedPreferenceManager?.clearSharedPreference()
            var intent = Intent(this@Dashboard,LoginScreen::class.java)
            startActivity(intent)
        }

        searchview?.addTextChangedListener(object: TextWatcher
        {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                filterItem(p0.toString().trim())
            }
        })



    }



    fun loadData()
    {


        recyclerView?.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        val DividerItemDecoration = DividerItemDecoration(recyclerView?.context,LinearLayout.VERTICAL)
        recyclerView?.addItemDecoration(DividerItemDecoration)

        adapter   = RecyclerAdapter(userList,this)
        recyclerView?.adapter = adapter

    }

    private fun filterItem(searchItem: String)
    {

        val FilterItemArray : ArrayList<UserModel> = ArrayList()

        val userArray : ArrayList<UserModel> = userList

        for (eachUser in userArray)
        {
            if (eachUser.name.toLowerCase().contains(searchItem.toLowerCase()))
            {
                FilterItemArray.add(eachUser)
            }
        }

        adapter?.addFilterList(FilterItemArray)
    }
}

