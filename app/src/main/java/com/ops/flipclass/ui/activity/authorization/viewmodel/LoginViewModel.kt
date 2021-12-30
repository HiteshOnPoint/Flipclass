package com.ops.flipclass.ui.activity.authorization.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ops.flipclass.APiState
import com.ops.flipclass.models.LoginResponse
import com.saint.saintfood.repository.ApiRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class LoginViewModel(private val repository: ApiRepository) : ViewModel() {

    private val loginLiveData = MutableLiveData<APiState<LoginResponse>>()
    val loginHoldLiveData: LiveData<APiState<LoginResponse>> get() = loginLiveData

    fun login(
        firstName: String,
        lastName: String,
        googleAuthKey: String,
        email: String
    ) {
        viewModelScope.launch {
            repository.login(firstName, lastName, googleAuthKey, email).collect {
                loginLiveData.value = it
            }
        }
    }
}
