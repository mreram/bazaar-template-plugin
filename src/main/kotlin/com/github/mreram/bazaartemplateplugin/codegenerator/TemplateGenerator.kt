package com.github.mreram.bazaartemplateplugin.codegenerator

import java.io.File
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Paths

class TemplateGenerator {

    fun createTemplateFromResources(
        filePaths: Array<String>,
        rootPath: String,
        pathArguments: Map<String, String>,
        contentArguments: Map<String, String>
    ) {
        filePaths.forEach { fileName ->
            val filePath = getFilePath(rootPath, fileName).replaceArguments(pathArguments)
            createDirectoryIfNotExists(filePath)
            if (fileName.last() == '/') {
                return@forEach
            }
            createFileIfNotExists(filePath)
            val inputSteam = javaClass.classLoader.getResourceAsStream(fileName) ?: return
            val fileOutputStream = FileOutputStream(filePath)
            val buffer = ByteArray(BUFFER_SIZE)
            while (true) {
                val byteCount = inputSteam.read(buffer)
                if (byteCount < 0) break
                fileOutputStream.write(buffer, 0, byteCount)
            }
            val path = Paths.get(filePath)
            var content = String(Files.readAllBytes(path), Charsets.UTF_8)
            content = content.replaceArguments(contentArguments)
            Files.write(path, content.toByteArray(Charsets.UTF_8))
            fileOutputStream.flush()
        }
    }

    private fun String.replaceArguments(pathArguments: Map<String, String>): String {
        var replaced: String = this
        pathArguments.forEach {
            replaced = replaced.replace(it.key, it.value)
        }
        return replaced
    }

    private fun getFilePath(rootPath: String, filePath: String): String {
        return rootPath + getFilePathWithoutTemplateSpecifications(filePath)
    }

    /**
     * Removing template/ path from file path and .ft from file name
     */
    private fun getFilePathWithoutTemplateSpecifications(fileName: String): String {
        return fileName.replace(
            BASE_PATH_TEMPLATES,
            ""
        ).replace(".ft", "")
    }

    private fun createDirectoryIfNotExists(fileDestination: String) {
        val directoryPath = fileDestination.substring(
            startIndex = 0,
            fileDestination.indexOfLast { it == '/' }
        )
        val directory = File(directoryPath)
        if (directory.exists().not()) {
            directory.mkdirs()
        }
    }

    private fun createFileIfNotExists(fileDestination: String) {
        val file = File(fileDestination)
        if (file.exists().not()) {
            file.createNewFile()
        }
    }

    companion object {

        private const val BASE_PATH_TEMPLATES = "/templates"
        private const val BUFFER_SIZE = 4 * 1024
    }
}