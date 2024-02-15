package com.gobinda.hilt.sampleproject2.services

class RemoteDataSource : DataSource {
    override fun fetchData(): List<Int> {
        return listOf(6, 7, 8, 9, 10)
    }
}