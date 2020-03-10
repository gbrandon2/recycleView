package com.example.recycleview


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.recycleview.R
import com.example.recycleview.data.DailyWeather
import com.example.recycleview.data.RandomUser
import com.example.recycleview.data.User
import com.example.recycleview.databinding.FragmentPersonalBinding
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.coroutines.newFixedThreadPoolContext

/**
 * A simple [Fragment] subclass.
 */
class PersonalFragment : Fragment() {
    val users= mutableListOf<User>()
    lateinit var navController: NavController
    lateinit var user:User
    lateinit var  mBinding:FragmentPersonalBinding
    private var adapter:WeatherRecycleViewAdapter?=null

    private lateinit var viewModel: RandomUserViewModel
    private var days = mutableListOf<DailyWeather>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_personal, container, false)
    mBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_personal,container,false)
days.clear()
        user= arguments!!.getParcelable("user")!!
        viewModel = ViewModelProvider(this).get(RandomUserViewModel::class.java)
        viewModel.clearList();
        if(days.isEmpty()) {

            viewModel.forecast(user.nombre)
            crearsuarios()

        }
        adapter=WeatherRecycleViewAdapter(users)
        mBinding.listF.layoutManager=LinearLayoutManager(context)
        mBinding.listF.adapter= adapter;
        mBinding.user=user

        return mBinding.root
    }
    fun crearsuarios(){
        viewModel.getDays().observe(viewLifecycleOwner, Observer { obsUsers ->
            run {
                days = obsUsers as MutableList<DailyWeather>

                for (randUser in days) {

                    var user = User(
                        randUser.date,randUser.main,randUser.temp+"°C",randUser.humidity+"% (Humidity)","https://openweathermap.org/img/wn/"+randUser.picture+"@2x.png"
                    )
                    users.add(user)
                }
                adapter!!.updateData()
            } })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
}
