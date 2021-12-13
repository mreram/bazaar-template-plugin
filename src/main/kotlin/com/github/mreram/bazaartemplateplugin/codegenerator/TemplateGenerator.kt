package com.github.mreram.bazaartemplateplugin.codegenerator

import java.io.File
import java.io.FileOutputStream

class TemplateGenerator {

    fun createTemplateFromResources(fileNames: Array<String>, destination: String) {
        fileNames.forEach { fileName ->
            val fileDestination = getFinalDestination(destination, fileName)
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

    private fun getFinalDestination(destination: String, fileName: String) =
        destination + fileName.replace(
            BASE_PATH_TEMPLATES,
            ""
        ).replace(".ft", "")

    private fun createFileIfNotExists(fileDestination: String) {
        val file = File(fileDestination)
        val directory = File(
            fileDestination.substring(
                startIndex = 0,
                fileDestination.indexOfLast { it == '/' })
        )
        if (directory.exists().not()) {
            directory.mkdirs()
        }
        if (file.exists().not()) {
            file.createNewFile()
        }
    }

    companion object {

        private const val BASE_PATH_TEMPLATES = "/templates"
        private const val BUFFER_SIZE = 4 * 1024
    }
}