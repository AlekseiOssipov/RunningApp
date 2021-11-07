package com.example.runningapp.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.example.runningapp.R
import com.example.runningapp.db.Run
import com.example.runningapp.db.RunDAO
import com.example.runningapp.ui.fragments.RunFragment
import com.example.runningapp.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val myViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        supportFragmentManager.beginTransaction().replace(R.id.flFragment, RunFragment()).commit()
        myViewModel.deleteAll()
        lifecycleScope.launch {
            myViewModel.mainRepository.insertRun(Run(caloriesBurned = 500))
            myViewModel.mainRepository.insertRun(Run(caloriesBurned = 800))
            delay(2000)
            myViewModel.mainRepository.getAllRunsSortedByCaloriesBurned().observe(this@MainActivity) { list ->
                list.forEach { Timber.d("item: ${it.caloriesBurned}") }
            }
            delay(2000)
            myViewModel.mainRepository.insertRun(Run(caloriesBurned = 1200))
        }
    }
}