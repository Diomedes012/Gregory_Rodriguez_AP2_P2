package ucne.edu.gregory_rodriguez_ap2_p2.domain.usecase

import ucne.edu.gregory_rodriguez_ap2_p2.data.remote.Resource
import ucne.edu.gregory_rodriguez_ap2_p2.data.remote.dto.GastoRequestDto
import ucne.edu.gregory_rodriguez_ap2_p2.domain.repository.GastoRepository
import javax.inject.Inject

class SaveGastoUseCase @Inject constructor(private val repository: GastoRepository) {
    suspend operator fun invoke(id: Int, request: GastoRequestDto): Resource<Unit> {
        return repository.saveGasto(id, request)
    }
}