package com.github.mreram.bazaartemplateplugin.builders.module.config

import com.github.mreram.bazaartemplateplugin.builders.module.model.BazaarModule

object ModuleArgumentConfigs {

    fun contentArguments(module: BazaarModule) = mapOf(
        "%MODULE_NAME%" to module.name,
        "%MODULE_NAME_CAMEL_CASE%" to module.nameCamelCase,
        "%COMPONENT_TYPE%" to module.componentTypeName,
    )

    fun pathArguments(module: BazaarModule) = mapOf(
        "modulename" to module.name,
        "%ModuleNameCamelCase%" to module.nameCamelCase
    )
}