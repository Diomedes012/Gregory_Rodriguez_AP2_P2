package ucne.edu.gregory_rodriguez_ap2_p2.presentation.detail

sealed interface GastoFormEvent {
    data class OnSuplidorChanged(val suplidor: String) : GastoFormEvent
    data class OnNcfChanged(val ncf: String) : GastoFormEvent
    data class OnItbisChanged(val itbis: String) : GastoFormEvent
    data class OnMontoChanged(val monto: String) : GastoFormEvent
    data object SaveGasto : GastoFormEvent
    data class LoadGasto(val id: Int) : GastoFormEvent
}