package com.example.recycleview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleview.data.User
import com.example.recycleview.databinding.RowBinding

import kotlinx.android.synthetic.main.row.view.*

class MyUserRecycleViewAdapter(private  val mValues: List<User>,private val mListner:onListInter):RecyclerView.Adapter<MyUserRecycleViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyUserRecycleViewAdapter.ViewHolder {
        var binder : RowBinding
        binder = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row, parent, false)
        return ViewHolder(binder)
    }
    public fun updateData(){
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int =mValues.size

    override fun onBindViewHolder(holder: MyUserRecycleViewAdapter.ViewHolder, position: Int) {
        val item=mValues[position]
        holder.mView.user = item
        holder.mView.executePendingBindings()
        holder.mView.cardView.setOnClickListener {
            Log.d("Usuario", item.toString())
            mListner?.onListItem(item)
        }
    }
    inner class ViewHolder(val mView: RowBinding): RecyclerView.ViewHolder(mView.root){

    }

    interface onListInter{
        fun onListItem(item:User?)
    }
}