package com.github.mreram.bazaartemplateplugin.actions

import com.github.mreram.bazaartemplateplugin.module.ModuleBuilderDialog
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys

class NewModuleAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        val currentPath = event.getData(PlatformDataKeys.VIRTUAL_FILE)?.path ?: return
        ModuleBuilderDialog(currentPath).show()
    }
}