package com.example.kotlinfirstapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.kotlinfirstapplication.Utils.Sqlite_Database
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    var name: String = ""
    var email: String = ""
    var pasword: String = ""


    var dbHandler  = Sqlite_Database(this)

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


            var nameEdit = findViewById(R.id.NameRegister)as EditText
            var emailEdit = findViewById(R.id.EmailRegister) as EditText
            var passEdit = findViewById(R.id.PasswordRegister) as EditText
            var RegisterBtn = findViewById(R.id.RegisterBtn) as Button



            RegisterBtn.setOnClickListener()
            {

            name = nameEdit.text.toString()
            email = emailEdit.text.toString()
            pasword = passEdit.text.toString()

            if (name.isEmpty())
            {
                nameEdit.setError("Enter Name")
                return@setOnClickListener
            }
            else if (email.isEmpty())
            {
                emailEdit.setError("Enter Email")
                return@setOnClickListener
            }
            else if (pasword.isEmpty())
            {
                passEdit.setError("Enter Password")
                return@setOnClickListener
            }


                Log.d("Register", "name: " + name)
                Log.d("Register", "email: " + email)
                Log.d("Register", "pass: " + pasword)

               dbHandler.addUser(UserModel(name, email, pasword))

                Toast.makeText(applicationContext, "addUser", Toast.LENGTH_SHORT).show()


        }

        }
}
