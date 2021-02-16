package br.com.leoferreiralima.model

data class RelationModel(
    val name:String,
    val source: RelationTypeModel,
    val target: RelationTypeModel
){
    override fun toString(): String {
        return "$name[shape=diamond];\n$source -> $name ${source.getLabel()};\n$name -> $target ${target.getLabel()}"
    }
}
