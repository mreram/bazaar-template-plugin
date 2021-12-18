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
        "$BASE_PATH_JAVA_FILES_MODULE/datasource/"
    )
    val networkFilePaths: Array<String> = arrayOf(
        "$BASE_PATH_JAVA_FILES_MODULE/model/",
        "$BASE_PATH_JAVA_FILES_MODULE/request/",
        "$BASE_PATH_JAVA_FILES_MODULE/api/"
    )
    val viewFilePaths: Array<String> = arrayOf(
        "$BASE_PATH_JAVA_FILES_MODULE/view/",
        "$BASE_PATH_JAVA_FILES_MODULE/viewmodel/",
        "$BASE_PATH_JAVA_FILES_MODULE/view/adapter/"
    )
    val diFilePaths: Array<String> = arrayOf(
        "$BASE_PATH_JAVA_FILES_MODULE/di/",
        "$BASE_PATH_JAVA_FILES_MODULE/di/builder/",
        "$BASE_PATH_JAVA_FILES_MODULE/di/component/",
        "$BASE_PATH_JAVA_FILES_MODULE/di/module/",
        "$BASE_PATH_JAVA_FILES_MODULE/di/qualifier/",
        "$BASE_PATH_JAVA_FILES_MODULE/di/scope/"
    )
    val actionLogFilePaths: Array<String> = arrayOf(
        "$BASE_PATH_JAVA_FILES_MODULE/actionlog/"
    )
}