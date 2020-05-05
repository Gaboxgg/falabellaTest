package com.example.falabellatest.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.falabellatest.data.Data
import com.example.falabellatest.R
import com.example.falabellatest.viewmodels.DataViewModel
import kotlinx.android.synthetic.main.fragment_data_detail.*

class DataDetailFragment : Fragment(){
    lateinit var mContext: Context
    private lateinit var dataViewModel: DataViewModel
    lateinit var data: Data


    companion object {
        @JvmStatic
        fun newInstance(data: Data) = DataDetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable("data", data)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext= this.requireContext()
        arguments?.getSerializable("data")?.let {
            data = it as Data
        }

    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_data_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        name.text=data.name
        unit.text=data.unit
        date.text=data.date
        value.text=data.value
        template_code.text=data.code
    }
}