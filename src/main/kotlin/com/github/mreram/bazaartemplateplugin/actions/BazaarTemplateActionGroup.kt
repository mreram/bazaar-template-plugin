package com.github.mreram.bazaartemplateplugin.actions

import icons.Icons
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.editor.Editor

class BazaarTemplateActionGroup : DefaultActionGroup() {

    override fun update(event: AnActionEvent) {
        event.presentation.icon = Icons.main_icon
    }
}