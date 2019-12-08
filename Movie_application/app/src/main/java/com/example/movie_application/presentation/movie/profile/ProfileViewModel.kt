package com.example.movie_application.presentation.movie.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie_application.base.BaseViewModel
import com.example.movie_application.data.models.AccountData
import com.example.movie_application.data.repository.UserRepositoryImpl
//import com.example.movie_application.data.repository.UserRepositoryImpl
import com.example.movie_application.domain.repository.UserRepository
import com.example.movie_application.exceptions.NoConnectionException
import com.example.movie_application.extensions.launchSafe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileViewModel(
    private val userRepository: UserRepository
) : BaseViewModel() {


    private val _liveData = MutableLiveData<State>()
    val liveData: LiveData<State>
        get() = _liveData



    override fun handleError(e: Throwable) {
        _liveData.value =
            State.HideLoading
        if(e is NoConnectionException) {
            _liveData.value =
                State.IntError(
                    e.messageInt
                )
        } else {
            _liveData.value =
                State.Error(
                    e.localizedMessage
                )
        }
    }


    fun getAccountDetails(sessionId: String) {
        _liveData.value =
            State.ShowLoading
        uiScope.launchSafe(::handleError) {
            val result = withContext(Dispatchers.IO) {
                userRepository.getAccountDetails(sessionId)
            }
            _liveData.postValue(
                State.Result(result)
            )
        }
        _liveData.value =
            State.HideLoading

    }

    sealed class State {
        object ShowLoading: State()
        object HideLoading: State()
        data class Result(val account: AccountData?): State()
        data class Error(val error: String?): State()
        data class IntError(val error: Int): State()
    }

}


