package ucne.edu.gregory_rodriguez_ap2_p2.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import ucne.edu.gregory_rodriguez_ap2_p2.data.remote.dto.GastoRequestDto
import ucne.edu.gregory_rodriguez_ap2_p2.data.remote.dto.GastoResponseDto

interface GastosApi {
    @GET("api/Gastos")
    suspend fun getGastos(): Response<List<GastoResponseDto>>

    @GET("api/Gastos/{id}")
    suspend fun getGasto(@Path("id") id: Int): Response<GastoResponseDto>

    @POST("api/Gastos")
    suspend fun postGasto(@Body gasto: GastoRequestDto): Response<GastoResponseDto>

    @PUT("api/Gastos/{id}")
    suspend fun putGasto(@Path("id") id: Int, @Body gasto: GastoRequestDto): Response<Unit>
}