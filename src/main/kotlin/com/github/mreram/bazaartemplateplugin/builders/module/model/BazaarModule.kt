package com.github.mreram.bazaartemplateplugin.builders.module.model

import com.intellij.openapi.project.Project

data class BazaarModule(
    val rootPath: String,
    val name: String,
    val nameCamelCase: String,
    val componentTypeName: String,
    val project: Project,
    val files: List<String>
)
