package ucne.edu.gregory_rodriguez_ap2_p2.presentation.list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ucne.edu.gregory_rodriguez_ap2_p2.domain.repository.BorrameRepository
import javax.inject.Inject

@HiltViewModel
class BorrammeListViewModel @Inject constructor(
    private val repository: BorrameRepository
): ViewModel() {
}