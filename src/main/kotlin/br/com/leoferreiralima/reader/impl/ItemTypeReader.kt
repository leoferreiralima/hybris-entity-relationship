package br.com.leoferreiralima.reader.impl

import br.com.leoferreiralima.model.ItemTypeModel
import org.w3c.dom.Node

class ItemTypeReader: AbstractXmlReader<Set<ItemTypeModel>>() {

    override fun read(path:String): Set<ItemTypeModel>{
        val document = parseToDocument(path)
        val itemsType = getNodesByTag("itemtype",document)

        return itemsType.map{ itemType ->
            val name = getAttribute("code",itemType)
            val parent = getAttribute("extends",itemType)
            val attributes = getItemTypeAttributes(itemType)
            return@map ItemTypeModel(name,parent,attributes)
        }.toSet()
    }

    private fun getItemTypeAttributes(itemType: Node) =
        getNodesByTag("attribute",itemType)
            .map { attribute -> getAttribute("qualifier", attribute) }
            .toSet()
}