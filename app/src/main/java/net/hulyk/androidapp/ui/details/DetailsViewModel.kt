package net.hulyk.androidapp.ui.details

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.hulyk.androidapp.domain.model.BitmapWrapper
import net.hulyk.androidapp.domain.usecase.GetFaceAreaFaceSizeUseCase
import net.hulyk.androidapp.ext.navArgs
import net.hulyk.androidapp.ui.details.DetailsViewModel.State.*
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    val getFaceAreaFaceSizeUseCase: GetFaceAreaFaceSizeUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val args by savedStateHandle.navArgs<DetailsFragmentArgs>()

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    fun drawFace() = viewModelScope.launch {
        _state.value = Loading
        getFaceAreaFaceSizeUseCase(args.url)
            .fold({ _state.value = Success(it) }, { _state.value = Error })
    }

    sealed class State {
        object Loading : State()
        object Error : State()
        data class Success(val data: BitmapWrapper) : State()
    }

}