package ucne.edu.gregory_rodriguez_ap2_p2.presentation.list

sealed interface GastoListEvent {
    data object LoadGastos: GastoListEvent
}