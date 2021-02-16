package br.com.leoferreiralima.model

import java.lang.RuntimeException

enum class Cardinality(val code:String) {
    ONE("1"), MANY("n");

    override fun toString(): String {
        return code
    }
    companion object{
        fun getFromName(name:String) = valueOf(name.toUpperCase())
    }
}