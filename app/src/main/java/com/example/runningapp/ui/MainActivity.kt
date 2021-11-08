package com.example.runningapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.lifecycle.repeatOnLifecycle
import com.example.runningapp.R
import com.example.runningapp.db.Run
import com.example.runningapp.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val myViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        supportFragmentManager.beginTransaction().replace(R.id.flFragment, RunFragment()).commit()
        myViewModel.deleteAll()
        lifecycleScope.launchWhenStarted {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                myViewModel.mainRepository.insertRun(Run(caloriesBurned = 500))
                myViewModel.mainRepository.insertRun(Run(caloriesBurned = 800))
                delay(2000)
                myViewModel.mainRepository.getAllRunsSortedByCaloriesBurned()
                    .observe(this@MainActivity) { list ->
                        list.forEach { Timber.d("item: ${it.caloriesBurned}") }
                    }
                delay(2000)
                myViewModel.mainRepository.insertRun(Run(caloriesBurned = 1200))
            }
        }
    }
}