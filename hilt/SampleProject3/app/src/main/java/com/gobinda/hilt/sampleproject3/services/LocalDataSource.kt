package com.gobinda.hilt.sampleproject3.services

class LocalDataSource : DataSource {
    override fun fetchData(): List<Int> {
        return listOf(1, 2, 3, 4, 5)
    }
}