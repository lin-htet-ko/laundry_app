package com.linhtetko.laundryapp.data.vos

import com.linhtetko.laundryapp.R

data class UserVO(
    val id: Long,
    val name: String,
    val image: Any
) {
    companion object {
        fun idle() = UserVO(
            id = System.currentTimeMillis(),
            name = "Lin Htet Ko",
            image = R.drawable.placeholder_person
        )
    }
}
