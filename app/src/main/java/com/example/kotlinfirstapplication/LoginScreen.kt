package com.example.kotlinfirstapplication

import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.kotlinfirstapplication.Utils.SharedPreferenceManager
import com.example.kotlinfirstapplication.Utils.Sqlite_Database
import kotlinx.android.synthetic.main.login_screen.*

class LoginScreen : AppCompatActivity() {

    var Emails: String  = ""
    var Passwords: String  = ""
    var dbHandler  = Sqlite_Database(this)
//    var sharedPreferences:Shared = preferenceManager(applicationContext)
    var SharedPreferences : SharedPreferenceManager ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)

        this.SharedPreferences = SharedPreferenceManager(applicationContext)

        var emailEdit = findViewById(R.id.Email) as EditText
        var PasswordEdit = findViewById(R.id.Password) as EditText
        var Login = findViewById(R.id.login) as Button
        var RegisterIntent = findViewById(R.id.RegisterIntent) as TextView



        Login.setOnClickListener()
        {

//            val email_match = "divyanshu.arora8@gmail.com"
//            val password_match = "1234"

            Emails = emailEdit.text.toString()
            Passwords = PasswordEdit.text.toString()
            Log.d("LoginScreen", Emails)
            Log.d("LoginScreen", Passwords)

            if (Emails.isEmpty()) {
                Email.setError("Enter Mail Id")
                return@setOnClickListener
            } else if (Passwords.isEmpty()) {

                Password.setError("Enter Password")
                return@setOnClickListener
            }

            val sqLiteDatabase: SQLiteDatabase
            sqLiteDatabase = dbHandler.readableDatabase

            val loginQry = "SELECT * FROM users where user_email =" +"'"+ Emails +"'"+ " AND user_password=" +"'"+ Passwords+"'"
            Log.d("Login", "loginQry" + loginQry)

            val cursor: Cursor = sqLiteDatabase.rawQuery(loginQry, null)


            val crsrCount = cursor.count

            if (crsrCount>0)
            {

                if (cursor.moveToFirst())
                {
                    do
                    {
                        var user_id = cursor.getString(cursor.getColumnIndex("id"))
                        SharedPreferences!!.setLoginSession(user_id)
                        Log.d("Login", "id: " + user_id)

                    }
                        while (cursor.moveToNext())

                }
                Toast.makeText(applicationContext,"Success",Toast.LENGTH_SHORT).show()
                val intent = Intent(this@LoginScreen,Dashboard::class.java)
                startActivity(intent)
                finish()
            }
            else
            {
                Toast.makeText(applicationContext,"no user found",Toast.LENGTH_SHORT).show()
            }

            cursor.close()
            dbHandler.close()
        }





        RegisterIntent.setOnClickListener()
        {
            val intent = Intent(this@LoginScreen,Register::class.java)
            startActivity(intent)
        }





    }
}



