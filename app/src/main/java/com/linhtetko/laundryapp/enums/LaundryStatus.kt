package com.linhtetko.laundryapp.enums

sealed class LaundryStatus(val finishingStatus: FinishingStatus, val label: String) {
    data class Washing(private val status: FinishingStatus,private val name: String) : LaundryStatus(status, name)
    data class Cleaning(private val status: FinishingStatus,private val name: String) : LaundryStatus(status, name)
    data class Drying(private val status: FinishingStatus,private val name: String) : LaundryStatus(status, name)
    data class Deliver(private val status: FinishingStatus,private val name: String) : LaundryStatus(status, name)
}
