package com.example.runningapp.repositories

import com.example.runningapp.db.Run
import com.example.runningapp.db.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(
   val runDAO: RunDAO
) {

    suspend fun insertRun(run: Run) = runDAO.insertRun(run)

    suspend fun deleteRun(run: Run) = runDAO.deleteRun(run)

    fun getAllRunsSortedByDay() = runDAO.getAllRunsSortedByDate()

    fun getAllRunsSortedByDistanceInMeters() = runDAO.getAllRunsSortedByDistanceInMeters()

    fun getAllRunsSortedByTimeInMiles() = runDAO.getAllRunsSortedByTimeInMillis()

    fun getAllRunsSortedByAVGSpeedInKMH() = runDAO.getAllRunsSortedByAVGSpeedInKMH()

    fun getAllRunsSortedByCaloriesBurned() = runDAO.getAllRunsSortedByCaloriesBurned()

    fun getTotalTimeInMiles() = runDAO.getTotalTimeInMillis()

    fun getTotalDistance() = runDAO.getTotalDistanceInMeters()

    fun getTotalCaloriesBurned() = runDAO.getTotalCaloriesBurned()

    fun getTotalAVGSpeed() = runDAO.getTotalAVGSpeedInKMH()
}