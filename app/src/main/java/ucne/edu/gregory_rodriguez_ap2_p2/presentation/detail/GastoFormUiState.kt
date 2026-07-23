package ucne.edu.gregory_rodriguez_ap2_p2.presentation.detail

data class GastoFormUiState(
    val isLoading: Boolean = false,
    val gastoId: Int = 0,
    val fecha: String = "2026-07-22T00:00:00",
    val suplidor: String = "",
    val ncf: String = "",
    val itbis: String = "",
    val monto: String = "",
    val error: String? = null,
    val isSaved: Boolean = false
)

