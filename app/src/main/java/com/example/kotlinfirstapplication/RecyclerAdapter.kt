package com.example.kotlinfirstapplication
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast


class RecyclerAdapter (var userList: ArrayList<UserModel>,val onClick: OnClick): RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>() {


    companion object
    {
        var onClick : OnClick ?= null
    }


    class ItemViewHolder(itemView:View): RecyclerView.ViewHolder(itemView)
    {
            val user_name = itemView.findViewById(R.id.name) as TextView
            val user_email = itemView.findViewById(R.id.email) as TextView
            val user_id = itemView.findViewById(R.id.ID) as TextView
            val parent = itemView.findViewById(R.id.parent) as LinearLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ItemViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ItemViewHolder(v)
    }

    override fun getItemCount(): Int {
        return  userList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, p1: Int)
    {
            val user:UserModel = userList[p1]

        holder.user_name.text = user.name
        holder.user_email.text = user.email
        holder.user_id.text = user.id


        holder.parent.setOnClickListener()
        {
            Log.d("Adapter","itemClick"+user.name)
            onClick.ToastCreate("hello "+user.name)
        }

    }



    fun addFilterList(filter: ArrayList<UserModel>)
  {
      this.userList = filter;
      notifyDataSetChanged()

  }



}