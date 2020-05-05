package com.example.falabellatest

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_data_list.*

class DataListFragment : Fragment() {
    lateinit var mContext: Context
    private lateinit var dataViewModel: DataViewModel
    private val adapter = DataAdapter()

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

    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_data_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = recycler_view
        recyclerView.layoutManager = LinearLayoutManager(mContext)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel::class.java)
        dataViewModel.getAllData().observe(viewLifecycleOwner,
            Observer<List<Data>> { t -> adapter.setData(t!!) })
    }
}