package com.example.falabellatest.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.falabellatest.utils.AESUtils
import com.example.falabellatest.adapters.DataAdapter
import com.example.falabellatest.data.Data
import com.example.falabellatest.data.DataPojo
import com.example.falabellatest.interfaces.APIService
import com.example.falabellatest.MainActivity
import com.example.falabellatest.R
import com.example.falabellatest.viewmodels.DataViewModel
import kotlinx.android.synthetic.main.fragment_data_list.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DataListFragment : Fragment() {
    lateinit var mContext: Context
    private lateinit var dataViewModel: DataViewModel
    private lateinit var adapter : DataAdapter
    lateinit var sharedPref: SharedPreferences
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "userAndPassword"

    companion object {
        @JvmStatic
        fun newInstance(username:String) = DataListFragment().apply {
            arguments = Bundle().apply {
                putString("username", username)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext= this.requireContext()
        sharedPref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_data_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel::class.java)
        val user:String= AESUtils.decrypt(
            sharedPref.getString(
                "user",
                " "
            )
        )
        welcome_msg.text="Hola $user"
        logout.setOnClickListener{
            val builder = AlertDialog.Builder(mContext)
            builder.setTitle(getString(R.string.confirmation))
            builder.setMessage(getString(R.string.confirmation_text))
            //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                sharedPref.edit().putString("login","0").apply()
                (activity as MainActivity).selectFragment("initial")
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->

            }
            builder.show()
        }
        tvSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                getFiltredDataFromDb(p0.toString())
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        searchData()
    }

    private fun searchData() {
        doAsync {
            try {
                val call: Response<DataPojo> = getRetrofit().create(APIService::class.java).getData().execute()
                val data = call.body() as DataPojo
                uiThread {
                    initTemplate(data)
                }
            }catch (e:Exception){
                e.printStackTrace()
            }


        }
    }

    private fun initTemplate(data: DataPojo) {
        dataViewModel.deleteAllData()
        for (x in data.getData()) {
            val i: Data =
                Data(
                    x.codigo,
                    x.nombre,
                    x.unidad_medida,
                    x.fecha,
                    x.valor
                )
            dataViewModel.insert(i)
        }
        adapter = DataAdapter() { data ->

            tvSearch.text.clear()
            (activity as MainActivity).selectDetailFragment(data)

        }

        val recyclerView = recycler_view
        recyclerView.layoutManager = LinearLayoutManager(mContext)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        dataViewModel.getAllData().observe(viewLifecycleOwner,
            Observer<List<Data>> { t -> adapter.setData(t!!) })
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(getString(R.string.baseUrl))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getFiltredDataFromDb(searchText: String) {
        val searchTextQuery = "%$searchText%"
        dataViewModel.getDataByFilter(searchTextQuery).observe(viewLifecycleOwner,
            Observer<List<Data>> { t -> adapter.setData(t!!) })
    }

}