package ucne.edu.gregory_rodriguez_ap2_p2.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ucne.edu.gregory_rodriguez_ap2_p2.data.remote.Resource
import ucne.edu.gregory_rodriguez_ap2_p2.data.remote.dto.GastoRequestDto
import ucne.edu.gregory_rodriguez_ap2_p2.domain.usecase.GetGastoUseCase
import ucne.edu.gregory_rodriguez_ap2_p2.domain.usecase.SaveGastoUseCase
import ucne.edu.gregory_rodriguez_ap2_p2.presentation.navigation.Screen
import javax.inject.Inject

@HiltViewModel
class GastoFormViewModel @Inject constructor(
    private val getGastoUseCase: GetGastoUseCase,
    private val saveGastoUseCase: SaveGastoUseCase,
    savedState: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(GastoFormUiState())
    val state = _state.asStateFlow()

    init {
        val args = savedState.toRoute<Screen.GastoForm>()
        if (args.id > 0) {
            loadGasto(args.id)
        }
    }

    fun onEvent(event: GastoFormEvent) {
        when (event) {
            is GastoFormEvent.LoadGasto -> {
                if (event.id == 0) {
                    _state.value = GastoFormUiState()
                } else if (event.id != _state.value.gastoId || _state.value.isSaved) {
                    _state.value = GastoFormUiState()
                    loadGasto(event.id)
                }
            }
            is GastoFormEvent.OnSuplidorChanged -> _state.update { it.copy(suplidor = event.suplidor, error = null) }
            is GastoFormEvent.OnNcfChanged -> _state.update { it.copy(ncf = event.ncf) }
            is GastoFormEvent.OnItbisChanged -> _state.update { it.copy(itbis = event.itbis) }
            is GastoFormEvent.OnMontoChanged -> _state.update { it.copy(monto = event.monto, error = null) }
            GastoFormEvent.SaveGasto -> saveGasto()
        }
    }

    private fun loadGasto(id: Int) {
        viewModelScope.launch {
            getGastoUseCase(id).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { gasto ->
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    gastoId = gasto.gastoId,
                                    fecha = gasto.fecha,
                                    suplidor = gasto.suplidor,
                                    ncf = gasto.ncf,
                                    itbis = gasto.itbis.toString(),
                                    monto = gasto.monto.toString()
                                )
                            }
                        }
                    }
                    is Resource.Error -> _state.update { it.copy(isLoading = false, error = result.message) }
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    private fun saveGasto() {
        val current = _state.value

        if (current.suplidor.isBlank()) {
            _state.update { it.copy(error = "El suplidor no puede estar vacío") }
            return
        }
        val montoDouble = current.monto.toDoubleOrNull() ?: 0.0
        if (montoDouble <= 0) {
            _state.update { it.copy(error = "El monto es obligatorio y debe ser mayor a 0") }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val request = GastoRequestDto(
                fecha = current.fecha,
                suplidor = current.suplidor,
                ncf = current.ncf.ifBlank { null },
                itbis = current.itbis.toDoubleOrNull(),
                monto = montoDouble
            )

            when (val result = saveGastoUseCase(current.gastoId, request)) {
                is Resource.Success -> _state.update { it.copy(isLoading = false, isSaved = true) }
                is Resource.Error -> _state.update { it.copy(isLoading = false, error = result.message) }
                is Resource.Loading -> Unit
            }
        }
    }
}