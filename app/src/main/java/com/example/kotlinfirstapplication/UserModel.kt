package com.example.kotlinfirstapplication

class UserModel() {

    var id: String = ""
    var name: String  = ""
    var email: String = ""
    var password: String = ""


    constructor(name: String,email:String,password: String) : this() {
        this.name = name
        this.email = email
        this.password = password

    }

    constructor(name: String, email: String, id:String, password: String) : this()
    {
        this.id = id
        this.name = name
        this.email = email
        this.password = password
    }




}
