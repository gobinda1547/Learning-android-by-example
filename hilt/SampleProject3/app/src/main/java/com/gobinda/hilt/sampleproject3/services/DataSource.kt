package com.gobinda.hilt.sampleproject3.services

interface DataSource {
    fun fetchData(): List<Int>
}