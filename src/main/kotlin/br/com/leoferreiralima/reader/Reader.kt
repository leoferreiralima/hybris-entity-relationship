package br.com.leoferreiralima.reader

interface Reader<T> {
    fun read(path:String): T
}