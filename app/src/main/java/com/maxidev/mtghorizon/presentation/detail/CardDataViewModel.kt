package com.maxidev.mtghorizon.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.mtghorizon.domain.usecase.CardDataUseCase
import com.maxidev.mtghorizon.domain.usecase.SymbologyUseCase
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
class CardDataViewModel @Inject constructor(
    private val cardDataUseCase: CardDataUseCase,
    private val symbologyUseCase: SymbologyUseCase
): ViewModel() {

    private val _state: MutableStateFlow<Resource<CardDataUiState>> =
        MutableStateFlow(Resource.Loading())
    val state: StateFlow<Resource<CardDataUiState>> = _state.asStateFlow()

//    init {
//        getSymbology()
//    }

    fun getCardData(id: String) {
        viewModelScope.launch {
            _state.value = Resource.Loading()

            _state.update {
                try {
                    Resource.Success(
                        data = CardDataUiState().copy(
                            cardData = cardDataUseCase.invoke(id)
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

//    private fun getSymbology() {
//        viewModelScope.launch {
//            _state.update {
//                try {
//                    Resource.Success(
//                        data = CardDataUiState().copy(
//                            symbology = symbologyUseCase.invoke()
//                        )
//                    )
//                } catch (e: HttpException) {
//                    Resource.Error(message = e.message())
//                } catch (e: IOException) {
//                    Resource.Error(message = e.toString())
//                }
//            }
//        }
//    }
}