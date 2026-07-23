package ucne.edu.gregory_rodriguez_ap2_p2.domain.repository

import kotlinx.coroutines.flow.Flow
import ucne.edu.gregory_rodriguez_ap2_p2.data.remote.Resource
import ucne.edu.gregory_rodriguez_ap2_p2.data.remote.dto.GastoRequestDto
import ucne.edu.gregory_rodriguez_ap2_p2.domain.model.Gasto

interface GastoRepository {
    fun getGastos(): Flow<Resource<List<Gasto>>>
    fun getGasto(id: Int): Flow<Resource<Gasto>>
    suspend fun saveGasto(id: Int, gastoRequest: GastoRequestDto): Resource<Unit>
}