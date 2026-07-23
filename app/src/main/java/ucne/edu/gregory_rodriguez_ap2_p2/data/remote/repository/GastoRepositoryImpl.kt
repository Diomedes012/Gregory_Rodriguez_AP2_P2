package ucne.edu.gregory_rodriguez_ap2_p2.data.remote.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ucne.edu.gregory_rodriguez_ap2_p2.data.remote.GastoRemoteDataSource
import ucne.edu.gregory_rodriguez_ap2_p2.data.remote.Resource
import ucne.edu.gregory_rodriguez_ap2_p2.data.remote.dto.GastoRequestDto
import ucne.edu.gregory_rodriguez_ap2_p2.domain.model.Gasto
import ucne.edu.gregory_rodriguez_ap2_p2.domain.repository.GastoRepository
import javax.inject.Inject

class GastoRepositoryImpl @Inject constructor(
    private val remoteDataSource: GastoRemoteDataSource
) : GastoRepository {

    override fun getGastos(): Flow<Resource<List<Gasto>>> = flow {
        emit(Resource.Loading())
        val response = remoteDataSource.getGastos()
        response.onSuccess { gastosResponse ->
            val gastosDominio = gastosResponse.map { it.toDomain() }
            emit(Resource.Success(gastosDominio))
        }.onFailure { exception ->
            emit(Resource.Error(exception.message ?: "Error desconocido al obtener gastos"))
        }
    }

    override fun getGasto(id: Int): Flow<Resource<Gasto>> = flow {
        emit(Resource.Loading())
        val response = remoteDataSource.getGasto(id)
        response.onSuccess { gastoDto ->
            emit(Resource.Success(gastoDto.toDomain()))
        }.onFailure { exception ->
            emit(Resource.Error(exception.message ?: "Error desconocido al obtener el detalle"))
        }
    }

    override suspend fun saveGasto(id: Int, gastoRequest: GastoRequestDto): Resource<Unit> {
        return if (id == 0) {
            val result = remoteDataSource.addGasto(gastoRequest)
            if (result.isSuccess) Resource.Success(Unit)
            else Resource.Error(result.exceptionOrNull()?.message ?: "Error al guardar")
        } else {
            val result = remoteDataSource.updateGasto(id, gastoRequest)
            if (result.isSuccess) Resource.Success(Unit)
            else Resource.Error(result.exceptionOrNull()?.message ?: "Error al actualizar")
        }
    }
}