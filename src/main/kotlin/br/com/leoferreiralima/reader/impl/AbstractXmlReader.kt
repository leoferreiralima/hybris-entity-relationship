package br.com.leoferreiralima.reader.impl

import br.com.leoferreiralima.reader.Reader
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

abstract class AbstractXmlReader<T>: Reader<T> {

    fun parseToDocument(path: String): Document {
        val file = File(path)
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        return builder.parse(file)
    }

    fun getNodesByTag(tag:String, document: Document):List<Node>{
        val nodes = document.getElementsByTagName(tag)
        return getNodes(nodes)
    }
    fun getNodesByTag(tag:String, document: Element):List<Node>{
        val nodes = document.getElementsByTagName(tag)
        return getNodes(nodes)
    }
    fun getNodesByTag(tag:String, node: Node):List<Node>{
        if(node is Element) return getNodesByTag(tag, node)
        else if(node is Document) return getNodesByTag(tag, node)

        throw RuntimeException("Case not found")

    }

    fun getNodes(nodeList:NodeList) = IntRange(0,nodeList.length-1).map { index -> nodeList.item(index) }

    fun getAttribute(attribute:String, node:Node): String = (node as Element).getAttribute(attribute)

}