package br.com.leoferreiralima.model

import java.lang.RuntimeException

enum class Cardinality() {
    ONE, MANY;

    companion object{
        fun getFromName(name:String) = valueOf(name.toUpperCase())
    }
}