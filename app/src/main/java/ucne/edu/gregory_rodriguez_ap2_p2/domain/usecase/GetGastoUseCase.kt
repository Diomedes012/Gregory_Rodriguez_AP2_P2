package ucne.edu.gregory_rodriguez_ap2_p2.domain.usecase

import kotlinx.coroutines.flow.Flow
import ucne.edu.gregory_rodriguez_ap2_p2.data.remote.Resource
import ucne.edu.gregory_rodriguez_ap2_p2.domain.model.Gasto
import ucne.edu.gregory_rodriguez_ap2_p2.domain.repository.GastoRepository
import javax.inject.Inject

class GetGastoUseCase @Inject constructor(private val repository: GastoRepository) {
    operator fun invoke(id: Int): Flow<Resource<Gasto>> = repository.getGasto(id)
}

