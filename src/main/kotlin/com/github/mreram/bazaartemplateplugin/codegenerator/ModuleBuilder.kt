package com.github.mreram.bazaartemplateplugin.codegenerator

class ModuleBuilder {

    private val fileNames: Array<String> = arrayOf(
        "$BASE_PATH_TEMPLATES_MODULE/.gitignore.ft",
        "$BASE_PATH_TEMPLATES_MODULE/build.gradle.ft",
        "$BASE_PATH_TEMPLATES_MODULE/src/main/AndroidManifest.xml.ft",
    )

    fun build(destination: String) {
        CodeGenerator().createTemplateFromResources(fileNames, destination)
    }

    companion object {

        private const val BASE_PATH_TEMPLATES_MODULE = "/templates/module"
    }
}