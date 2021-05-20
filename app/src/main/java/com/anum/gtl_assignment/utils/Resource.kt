package com.anum.gtl_assignment.utils

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
}