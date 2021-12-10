package com.github.mreram.bazaartemplateplugin.module

import com.intellij.openapi.ui.Messages
import com.intellij.ui.wizard.WizardDialog
import java.lang.reflect.Field
import javax.swing.JButton

class ModuleBuilderDialog : WizardDialog<ModuleWizardModel>(true, ModuleWizardModel()) {

    init {
        title = "New Bazaar Module"
        val myFinish: Field = javaClass.superclass.getDeclaredField("myFinish")
        myFinish.isAccessible = true
        (myFinish.get(this) as JButton).text = "Besaz haji"
    }

    override fun doOKAction() {
        super.doOKAction()
        Messages.showMessageDialog("Babaaaa vaysa", "Wait", null)
    }
}