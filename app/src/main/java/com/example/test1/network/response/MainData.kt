package com.example.test1.network.response

data class MainData(
    private val size: Int,
    private val list: List<Name>
) {
    data class Name(
        private val first: String,
        private val last: String
    )
}