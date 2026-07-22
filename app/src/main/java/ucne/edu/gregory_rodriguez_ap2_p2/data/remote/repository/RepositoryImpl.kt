package ucne.edu.gregory_rodriguez_ap2_p2.data.remote.repository

import ucne.edu.gregory_rodriguez_ap2_p2.data.remote.RemoteDataSource
import ucne.edu.gregory_rodriguez_ap2_p2.domain.repository.BorrameRepository
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): BorrameRepository{
}