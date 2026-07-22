package ucne.edu.gregory_rodriguez_ap2_p2.data.remote.dto

import com.squareup.moshi.Json

data class CharacterResponseDto(
    @Json(name = "items") val items: List<BorrameDto>
)