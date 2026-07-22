package ucne.edu.gregory_rodriguez_ap2_p2.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BorrameDto(
    val id: Int
)
