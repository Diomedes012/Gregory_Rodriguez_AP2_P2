package ucne.edu.gregory_rodriguez_ap2_p2.data.remote

import retrofit2.HttpException
import ucne.edu.gregory_rodriguez_ap2_p2.data.remote.dto.GastoRequestDto
import ucne.edu.gregory_rodriguez_ap2_p2.data.remote.dto.GastoResponseDto
import javax.inject.Inject

class GastoRemoteDataSource @Inject constructor(
    private val api: GastosApi
) {
    suspend fun getGastos(): Result<List<GastoResponseDto>> {
        return try {
            val response = api.getGastos()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error de red ${response.code()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun getGasto(id: Int): Result<GastoResponseDto> {
        return try {
            val response = api.getGasto(id)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error de red ${response.code()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun addGasto(gasto: GastoRequestDto): Result<GastoResponseDto> {
        return try {
            val response = api.postGasto(gasto)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error de red ${response.code()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun updateGasto(id: Int, gasto: GastoRequestDto): Result<Unit> {
        return try {
            val response = api.putGasto(id, gasto)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error de red ${response.code()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }
}