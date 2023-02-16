package com.ukadovlad21.testtaskteamsdevit.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ukadovlad21.testtaskteamsdevit.MainViewModelProviderFactory
import com.ukadovlad21.testtaskteamsdevit.R
import com.ukadovlad21.testtaskteamsdevit.data.repository.MainRepository
import com.ukadovlad21.testtaskteamsdevit.vm.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    private var mainRepository = MainRepository()
    val mainViewModel: MainViewModel by viewModels {
        MainViewModelProviderFactory(application, mainRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_container)

    }
}


