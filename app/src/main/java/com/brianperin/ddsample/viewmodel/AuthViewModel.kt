package com.brianperin.ddsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.brianperin.ddsample.network.AuthRequest
import com.brianperin.ddsample.network.Result
import com.brianperin.ddsample.network.repository.AuthRepository
import com.brianperin.ddsample.network.response.AuthResponse
import com.brianperin.ddsample.util.PrefUtils
import kotlinx.coroutines.Dispatchers

class AuthViewModel : ViewModel() {

    private val authRepository = AuthRepository()

    fun authenticate(email: String, password: String): LiveData<Result<AuthResponse>> {

        return liveData(Dispatchers.IO) {

            val request = AuthRequest(email, password)

            emit(Result.loading())

            val data = authRepository.authenticate(request)

            if (data.status == Result.Status.SUCCESS) {
                PrefUtils.saveJWT(data.data!!.token)
            }

            emit(data)
        }
    }

}