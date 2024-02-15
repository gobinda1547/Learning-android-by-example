package com.gobinda.hilt.sampleproject1.services

class LocalDataSource : DataSource {
    override fun fetchData(): List<Int> {
        return listOf(1, 2, 3, 4, 5)
    }
}