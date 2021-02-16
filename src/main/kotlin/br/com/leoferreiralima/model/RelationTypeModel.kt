package br.com.leoferreiralima.model

data class RelationTypeModel(
    val name: String,
    val type: String,
    val cardinality: Cardinality,
    val optional: Boolean
)