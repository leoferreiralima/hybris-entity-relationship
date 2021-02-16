package br.com.leoferreiralima.reader.impl

import br.com.leoferreiralima.model.*
import org.w3c.dom.Node
import java.lang.RuntimeException

class RelationReader: AbstractXmlReader<Set<RelationModel>>() {

    override fun read(path:String): Set<RelationModel>{
        val document = parseToDocument(path)
        val relations = getNodesByTag("relation",document)

        return relations.map { relation ->
            val name = getAttribute("code",relation)

            RelationModel(name,getRelationType("sourceElement",relation), getRelationType("targetElement",relation))
        }.toSet()
    }

    private fun getRelationType(tagName: String, relation:Node): RelationTypeModel {
        val element = getNodesByTag(tagName,relation).first()

        val name = getAttribute("qualifier", element)
        val type = getAttribute("type", element)
        val cardinality = Cardinality.getFromName(getAttribute("cardinality", element))
        val optional = getOptional(element)

        return RelationTypeModel(name,type,cardinality, optional)
    }
    private fun  getOptional(node: Node) =
        getNodesByTag("modifiers",node).firstOrNull()?.let { modifier->
            getAttribute("optional",modifier).let { optional ->
                optional.toBoolean()
            }
        }?:true
}