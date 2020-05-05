package com.example.falabellatest.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.falabellatest.utils.AESUtils
import com.example.falabellatest.MainActivity
import com.example.falabellatest.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(){
    lateinit var mContext: Context
    lateinit var sharedPref: SharedPreferences
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "userAndPassword"

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment().apply {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext= this.requireContext()
        sharedPref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        sharedPref.edit().putString("user",
            AESUtils.encrypt("prueba")
        ).apply()
        sharedPref.edit().putString("password",
            AESUtils.encrypt("123123")
        ).apply()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(sharedPref.getString("login"," ") == "1")
            (activity as MainActivity).selectFragment("phase01")
        login_button?.setOnClickListener{login(loginText.text.toString(),password.text.toString())}
    }

    fun login(username:String,password:String){
        if(validate(username,password)) {
            sharedPref.edit().putString("login","1").apply()
            (activity as MainActivity).selectFragment("phase01")
        }else{
            error_text.text = getString(R.string.wrong_access)
        }
    }

    private fun validate(username: String, password: String): Boolean {
        var encryptedUsername:String =
            AESUtils.encrypt(username)
        var encryptedPassword:String =
            AESUtils.encrypt(password)
        var storedUser:String = sharedPref.getString("user","")
        var storedPassword:String = sharedPref.getString("password","")

        return encryptedUsername==storedUser && encryptedPassword==storedPassword

    }
}