package com.github.mreram.bazaartemplateplugin.builders.module.config

object ModuleFileConfigs {

    private const val BASE_PATH_TEMPLATES_MODULE = "/templates/modulename"
    private const val BASE_PATH_JAVA_FILES_MODULE =
        "$BASE_PATH_TEMPLATES_MODULE/src/main/java/com/farsitel/bazaar/modulename"

    val baseModuleFilePaths: Array<String> = arrayOf(
        "$BASE_PATH_TEMPLATES_MODULE/.gitignore.ft",
        "$BASE_PATH_TEMPLATES_MODULE/build.gradle.ft",
        "$BASE_PATH_TEMPLATES_MODULE/src/main/AndroidManifest.xml.ft"
    )
    val datasourceFilePaths: Array<String> = arrayOf(
        "$BASE_PATH_JAVA_FILES_MODULE/datasource/",
        "$BASE_PATH_JAVA_FILES_MODULE/model/",
        "$BASE_PATH_JAVA_FILES_MODULE/request/",
        "$BASE_PATH_JAVA_FILES_MODULE/api/"
    )
    val daggerViewModelFilePaths: Array<String> = arrayOf(
        "$BASE_PATH_JAVA_FILES_MODULE/module/"
    )
    val daggerStartupFilePaths: Array<String> = arrayOf(
        "$BASE_PATH_JAVA_FILES_MODULE/viewmodel/"
    )
}