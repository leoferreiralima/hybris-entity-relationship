package br.com.leoferreiralima.model

data class ItemTypeModel(
    val name:String,
    val parent:String,
    val properties:Set<String>
){

    override fun toString(): String {
        return "$name -> $parent[label=\"extends\",color=red];"
    }
}
