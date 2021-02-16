package br.com.leoferreiralima.model

data class RelationTypeModel(
    val name: String,
    val type: String,
    val cardinality: Cardinality,
    val optional: Boolean
){
    fun getLabel(): String {
        val min = if(optional) "0" else "1"
        return "[label=\"$min:$cardinality\"]"
    }
    override fun toString(): String {
        return type
    }
}