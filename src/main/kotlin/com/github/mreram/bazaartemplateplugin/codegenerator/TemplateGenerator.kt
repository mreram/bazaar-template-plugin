package com.github.mreram.bazaartemplateplugin.codegenerator

import java.io.File
import java.io.FileOutputStream

class TemplateGenerator {

    fun createTemplateFromResources(
        fileNames: Array<String>,
        destination: String,
        moduleName: String
    ) {
        fileNames.forEach { fileName ->
            val fileDestination = getFinalDestination(destination, fileName).replace(
                "module",
                moduleName
            )
            fileDestination.replace("module", "")
            createDirectoryIfNotExists(fileDestination)
            if (fileName.last() == '/') {
                return@forEach
            }
            createFileIfNotExists(fileDestination)
            val inputSteam = javaClass.classLoader.getResourceAsStream(fileName)
            val fileOutputStream = FileOutputStream(fileDestination)
            val buffer = ByteArray(BUFFER_SIZE)
            while (true) {
                val byteCount = inputSteam?.read(buffer) ?: break
                if (byteCount < 0) break
                fileOutputStream.write(buffer, 0, byteCount)
            }
            fileOutputStream.flush()
        }
    }

    private fun getFinalDestination(destination: String, fileName: String): String {
        return destination + fileName.replace(
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