package com.example.templateapp.core

interface BaseMapper<in A, out B> {

    fun map(type: A?): B
}