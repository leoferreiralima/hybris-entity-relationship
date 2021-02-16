package br.com.leoferreiralima

import br.com.leoferreiralima.model.ItemTypeModel
import br.com.leoferreiralima.model.RelationModel
import br.com.leoferreiralima.reader.impl.ItemReader
import br.com.leoferreiralima.reader.impl.ItemTypeReader
import br.com.leoferreiralima.reader.impl.RelationReader
import br.com.leoferreiralima.search.ItemSearch


fun main() {
    val itemTypeReader = ItemTypeReader()
    val relationReader = RelationReader()
    val itemReader = ItemReader(itemTypeReader, relationReader)

    val itemSearch = ItemSearch("^.*[A-Za-z]+-items\\.xml$")

    val itemsFile = itemSearch.search("C:\\b2b\\core-customize\\hybris\\bin")
    val itemsTypeMap = HashMap<String,ItemTypeModel>()
    val relations = HashSet<RelationModel>()

    println("${itemsFile.size} items found")

    itemsFile.forEach{ itemFile ->
        val item = itemReader.read(itemFile)
        relations.addAll(item.relations)
        item.itemsType.forEach { itemType ->
            itemsTypeMap.merge(itemType.name,itemType)
        }
    }

    val digraphBuilder = StringBuilder("digraph G {\nrankdir=LR\npackmode=\"graph\"\n")

    itemsTypeMap.values.forEach{itemType->
        if(!itemType.parent.isBlank() && itemType.parent != "GenericItem"){
            digraphBuilder.append(itemType)
            digraphBuilder.append("\n")
        }
    }

    relations.iterator().forEach { relation->
        digraphBuilder.append(relation)
        digraphBuilder.append("\n")
    }

    digraphBuilder.append("}")

    println(digraphBuilder.toString())

}

private fun HashMap<String,ItemTypeModel>.merge(key:String, value:ItemTypeModel){

    if(contains(key)){
        merge(key,value){ savedValue,newValue ->
            val properties = HashSet<String>()
            properties.addAll(savedValue.properties)
            properties.addAll(newValue.properties)
            ItemTypeModel(key,newValue.parent,  properties)
        }
    }else{
        put(key,value)
    }
}
