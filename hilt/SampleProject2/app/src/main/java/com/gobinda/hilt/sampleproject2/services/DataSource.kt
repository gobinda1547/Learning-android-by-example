package com.gobinda.hilt.sampleproject2.services

interface DataSource {
    fun fetchData(): List<Int>
}