package com.maxidev.mtghorizon.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.mtghorizon.domain.usecase.RandomCardUseCase
import com.maxidev.mtghorizon.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val randomCardUseCase: RandomCardUseCase
): ViewModel() {

    private val _state: MutableStateFlow<Resource<HomeUiState>> =
        MutableStateFlow(Resource.Loading())
    val state: StateFlow<Resource<HomeUiState>> = _state.asStateFlow()

    init {
        getRandom()
    }

    private fun getRandom() {
        viewModelScope.launch {
            _state.value = Resource.Loading()

            _state.update {
                try {
                    Resource.Success(
                        data = HomeUiState().copy(
                            randomCard = randomCardUseCase.invoke()
                        )
                    )
                } catch (e: HttpException) {
                    Resource.Error(message = e.message())
                } catch (e: IOException) {
                    Resource.Error(message = e.toString())
                }
            }
        }
    }
}