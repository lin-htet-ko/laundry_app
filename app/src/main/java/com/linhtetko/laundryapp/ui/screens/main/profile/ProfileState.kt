package com.linhtetko.laundryapp.ui.screens.main.profile

import com.linhtetko.laundryapp.data.vos.UserVO

data class ProfileState(
    val profile: UserVO
){
    companion object{
        fun idle() = ProfileState(profile = UserVO.idle())
    }
}
