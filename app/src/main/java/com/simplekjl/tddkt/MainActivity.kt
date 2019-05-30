package com.simplekjl.tddkt

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.simplekjl.tddkt.fragments.UsersFragment

class MainActivity : AppCompatActivity() , UsersFragment.UsersFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun setFragment(newInstance: UsersFragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment, newInstance, "TEST")
            .commit()
    }
}
