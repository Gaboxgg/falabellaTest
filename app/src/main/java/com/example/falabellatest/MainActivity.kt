package com.example.falabellatest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

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
        val fragment : Fragment = if (url=="initial") LoginFragment.newInstance()
        else DataListFragment.newInstance(url)

         supportFragmentManager.inTransaction {
             replace(R.id.container, fragment,url)
         }
    }
}
