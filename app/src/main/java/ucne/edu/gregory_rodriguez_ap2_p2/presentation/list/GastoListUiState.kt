package ucne.edu.gregory_rodriguez_ap2_p2.presentation.list

import ucne.edu.gregory_rodriguez_ap2_p2.domain.model.Gasto

data class GastoListUiState(
    val isLoading: Boolean = false,
    val gastos: List<Gasto> = emptyList(),
    val error: String? = null
)
