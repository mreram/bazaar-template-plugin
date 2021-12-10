package com.github.mreram.bazaartemplateplugin.actions

import com.github.mreram.bazaartemplateplugin.base.Module
import com.github.mreram.bazaartemplateplugin.module.ModuleBuilderDialog
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.module.ModuleUtil
import com.intellij.openapi.ui.Messages

class NewModuleAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        val currentPath = event.getData(PlatformDataKeys.VIRTUAL_FILE)?.let {
            val moduleName = ModuleUtil.findModuleForFile(it, event.project!!)?.name ?: ""
            val module = Module(
                moduleName,
                moduleName.replace("${event.project!!.name}.", "")
            )
        }
        ModuleBuilderDialog().showAndGet()
    }
}