package com.example.falabellatest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.falabellatest.data.Data
import com.example.falabellatest.fragments.DataDetailFragment
import com.example.falabellatest.fragments.DataListFragment
import com.example.falabellatest.fragments.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        selectFragment("initial")
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

     fun selectFragment(url:String){
         lateinit var fragment:Fragment
         if(url=="initial") {
             fragment = LoginFragment.newInstance()
             supportFragmentManager.inTransaction {
                 replace(R.id.container, fragment, url)
             }
         }else{
             fragment = DataListFragment.newInstance(url)
             supportFragmentManager.inTransaction {
                 addToBackStack(url)
                 replace(R.id.container, fragment, url)
             }
         }
    }

    fun selectDetailFragment(data: Data){
         var fragment:Fragment = DataDetailFragment.newInstance(data)
            supportFragmentManager.inTransaction {
                addToBackStack(data.code)
                replace(R.id.container, fragment, data.code)
            }
    }
}
