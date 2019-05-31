package com.simplekjl.tddkt

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.simplekjl.tddkt.data.MainViewModel
import com.simplekjl.tddkt.data.models.Comment
import com.simplekjl.tddkt.data.models.User
import com.simplekjl.tddkt.fragments.UsersFragment

class MainActivity : AppCompatActivity() , UsersFragment.UsersFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.init()
        viewModel.getUsers().observe(this, Observer<List<User>> {

        })
    }

    fun setFragment(newInstance: UsersFragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment, newInstance, "TEST")
            .commit()
    }
}
