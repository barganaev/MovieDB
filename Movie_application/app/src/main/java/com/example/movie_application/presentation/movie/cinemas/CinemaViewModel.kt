package com.example.movie_application.presentation.movie.cinemas

import androidx.lifecycle.LiveData
import com.example.movie_application.base.BaseViewModel
import com.example.movie_application.data.repository.CinemaRepositoryImpl
import com.example.movie_application.data.room.Cinema
import com.example.movie_application.data.room.CinemaDao
import com.example.movie_application.domain.repository.CinemaRepository
import com.example.movie_application.exceptions.NoConnectionException
//import com.example.movieapp.base.BaseViewModel
//import com.example.movieapp.data.room.Cinema
//import com.example.movieapp.data.room.CinemaDao
//import com.example.movieapp.exceptions.NoConnectionException

class CinemaViewModel(
    private val cinemaDao: CinemaDao
): BaseViewModel() {


    private val repository: CinemaRepository

    var liveData : LiveData<List<Cinema>>

    init {
        repository = CinemaRepositoryImpl(cinemaDao)
        liveData = repository.getAllCinemas()
    }

    override fun handleError(e: Throwable) {
        if (e is NoConnectionException) {
            //ToDo
        }
    }
}