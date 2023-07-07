package com.linhtetko.laundryapp.data.vos

import com.linhtetko.laundryapp.R

data class LaundryVO(
    val id: Long,
    val name: String,
    val location: String,
    val distance: String,
    val pricePerCloth: Double,
    val rating: Double,
    val reviewCount: Long,
    val image: Any
) {
    companion object {
        fun idle() = LaundryVO(
            id = System.currentTimeMillis(),
            name = "Happy Laundry",
            distance = "20 km",
            location = "La Min Condo, Hlaing, Burma",
            pricePerCloth = 0.5,
            reviewCount = 458,
            rating = 4.0,
            image = R.drawable.placeholder_laundry_machine
        )
    }
}
