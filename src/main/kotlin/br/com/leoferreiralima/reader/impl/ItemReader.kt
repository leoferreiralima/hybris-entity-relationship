package br.com.leoferreiralima.reader.impl

import br.com.leoferreiralima.model.ItemModel
import br.com.leoferreiralima.model.ItemTypeModel
import br.com.leoferreiralima.model.RelationModel
import br.com.leoferreiralima.reader.Reader

class ItemReader(
    private val itemTypeReader: Reader<Set<ItemTypeModel>>,
    private val relationReader: Reader<Set<RelationModel>>
): Reader<ItemModel> {

    override fun read(path:String): ItemModel{
        val itemsType = itemTypeReader.read(path)
        val relations = relationReader.read(path)
        return ItemModel(itemsType,relations);
    }
}