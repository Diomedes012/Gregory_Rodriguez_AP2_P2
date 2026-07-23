package ucne.edu.gregory_rodriguez_ap2_p2.data.remote.dto

data class GastoRequestDto(
    val fecha: String,
    val suplidor: String,
    val ncf: String?,
    val itbis: Double?,
    val monto: Double
)