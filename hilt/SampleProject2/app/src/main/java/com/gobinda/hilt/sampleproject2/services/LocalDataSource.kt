package com.gobinda.hilt.sampleproject2.services

import javax.inject.Inject

class LocalDataSource @Inject constructor(): DataSource {
    override fun fetchData(): List<Int> {
        return listOf(1, 2, 3, 4, 5)
    }
}