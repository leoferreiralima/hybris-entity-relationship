package br.com.leoferreiralima.model

data class ItemModel(
    val itemsType: Set<ItemTypeModel>,
    val relations: Set<RelationModel>
)