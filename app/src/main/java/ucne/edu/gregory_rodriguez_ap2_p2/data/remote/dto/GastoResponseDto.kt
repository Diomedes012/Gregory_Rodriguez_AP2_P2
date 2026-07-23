package ucne.edu.gregory_rodriguez_ap2_p2.data.remote.dto

import ucne.edu.gregory_rodriguez_ap2_p2.domain.model.Gasto

data class GastoResponseDto(
    val gastoId: Int,
    val fecha: String,
    val suplidor: String,
    val ncf: String?,
    val itbis: Double?,
    val monto: Double
) {
    fun toDomain() = Gasto(
        gastoId = gastoId,
        fecha = fecha,
        suplidor = suplidor,
        ncf = ncf ?: "",
        itbis = itbis ?: 0.0,
        monto = monto
    )
}