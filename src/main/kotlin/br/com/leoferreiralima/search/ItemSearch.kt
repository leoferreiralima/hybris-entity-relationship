package br.com.leoferreiralima.search

import java.io.IOException
import java.nio.file.*

import java.nio.file.attribute.BasicFileAttributes


class ItemSearch(private val regex:String) {

    fun search(path:String):Set<String>{
       val matcher = FileSystems.getDefault().getPathMatcher("regex:$regex")

        val paths = HashSet<String>()

        Files.walkFileTree(Paths.get(path), object : SimpleFileVisitor<Path>() {
            @Throws(IOException::class)
            override fun preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult {
                return FileVisitResult.CONTINUE
            }

            @Throws(IOException::class)
            override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
                if (matcher.matches(file)) {
                    paths.add(file.toString())
                }
                return FileVisitResult.CONTINUE
            }
            @Throws(IOException::class)
            override fun visitFileFailed(file: Path, exc: IOException): FileVisitResult {
                return FileVisitResult.CONTINUE
            }
        })

        return paths
    }
}