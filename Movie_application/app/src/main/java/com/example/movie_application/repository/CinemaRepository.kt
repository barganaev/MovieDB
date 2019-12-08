package com.example.movie_application.repository

import androidx.lifecycle.LiveData
import com.example.movie_application.data.room.Cinema
//import com.example.movieapp.data.room.Cinema

interface CinemaRepository {

    fun getAllCinemas(): LiveData<List<Cinema>>

    fun getCinema(id: Int): LiveData<Cinema>
}