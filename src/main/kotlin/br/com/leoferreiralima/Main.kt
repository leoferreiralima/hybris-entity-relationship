package br.com.leoferreiralima

import br.com.leoferreiralima.reader.impl.ItemReader
import br.com.leoferreiralima.reader.impl.ItemTypeReader
import br.com.leoferreiralima.reader.impl.RelationReader
import br.com.leoferreiralima.search.ItemSearch


fun main() {
    val itemTypeReader = ItemTypeReader()
    val relationReader = RelationReader()
    val itemReader = ItemReader(itemTypeReader, relationReader)

    val itemSearch = ItemSearch("^.*[A-Za-z]+-items\\.xml$")

    val itemsFile = itemSearch.search("/home/leonardo/www/hybris/HYBRISCOMM6700P_0-80003492/hybris")

    itemsFile.map { itemFile -> { itemReader.read(itemFile) } }
}