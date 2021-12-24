package com.github.mreram.bazaartemplateplugin.module

import com.github.mreram.bazaartemplateplugin.builders.module.dagger.DaggerComponentType

object ModuleConfig {

    var name: String = ""
    var nameCamelCase: String = ""
    var hasDi: Boolean = false
    var hasDataSource: Boolean = false
    var hasNetwork: Boolean = false
    var hasView: Boolean = false
    var hasActionLog: Boolean = false
    var hasViewModel: Boolean = false
    var hasStartupTask: Boolean = false
    var hasWorker: Boolean = false
    var hasFragment: Boolean = false
    var componentType: DaggerComponentType? = null
}