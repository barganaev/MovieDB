package com.example.movie_application.data.repository

import androidx.lifecycle.LiveData
import com.example.movie_application.data.room.Cinema
import com.example.movie_application.data.room.CinemaDao
import com.example.movie_application.domain.repository.CinemaRepository

class CinemaRepositoryImpl(private val cinemaDao: CinemaDao) : CinemaRepository {

    override fun getAllCinemas(): LiveData<List<Cinema>> = cinemaDao.getCinemas()

    override fun getCinema(id: Int): LiveData<Cinema> = cinemaDao.getCinema(id)
}