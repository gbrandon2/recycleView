package com.example.recycleview


import android.app.DownloadManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.recycleview.data.RandomUser
import com.example.recycleview.data.User
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.json.JSONArray
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() ,MyUserRecycleViewAdapter.onListInter{
    override fun onListItem(item: User?) {
        var algo= bundleOf("user" to item)
       navController!!.navigate(R.id.action_mainFragment_to_personalFragment,algo)
    }

    val users= mutableListOf<User>()
    lateinit var navController: NavController;
    private var adapter:MyUserRecycleViewAdapter?=null
    private lateinit var viewModel: RandomUserViewModel
    private var userList = mutableListOf<RandomUser>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view=inflater.inflate(R.layout.fragment_main, container, false)
        viewModel = ViewModelProvider(this).get(RandomUserViewModel::class.java)
    if(users.isEmpty()) {


        viewModel.addUser()
        crearUsuarios()

    }
        adapter=MyUserRecycleViewAdapter(users,this)
        view.list.layoutManager=LinearLayoutManager(context)
        view.list.adapter=adapter
        return view
    }
   fun crearUsuarios(){
        viewModel.getUser().observe(viewLifecycleOwner, Observer { obsUsers ->
            run {
                userList = obsUsers as MutableList<RandomUser>

                for (randUser in userList) {

                    var user = User(
                        randUser.name,randUser.main,randUser.temp+"°C",randUser.humidity+"% (Humidity)","https://openweathermap.org/img/wn/"+randUser.picture+"@2x.png"
                    )
                    users.add(user)
                }
                adapter!!.updateData()
            } })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)

    }


}


