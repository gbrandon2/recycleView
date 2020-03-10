package com.example.recycleview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleview.data.User

import com.example.recycleview.databinding.RowforecastBinding

class WeatherRecycleViewAdapter(private  val mValues: List<User> ):RecyclerView.Adapter<WeatherRecycleViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherRecycleViewAdapter.ViewHolder {
        var binder : RowforecastBinding
        binder = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.rowforecast, parent, false)
        return ViewHolder(binder)
    }
    public fun updateData(){
        notifyDataSetChanged()
    }
    override fun getItemCount():  Int =mValues.size

    override fun onBindViewHolder(holder: WeatherRecycleViewAdapter.ViewHolder, position: Int) {

        val item=mValues[position]
        holder.mView.user = item
        holder.mView.executePendingBindings()
    }
inner class ViewHolder(val mView:RowforecastBinding):RecyclerView.ViewHolder(mView.root){

}
}