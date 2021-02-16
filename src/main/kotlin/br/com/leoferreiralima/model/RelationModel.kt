package br.com.leoferreiralima.model

data class RelationModel(
    val name:String,
    val source: RelationTypeModel,
    val target: RelationTypeModel
)