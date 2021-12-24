package com.github.mreram.bazaartemplateplugin.module

import com.intellij.ui.components.JBLabel
import com.intellij.ui.wizard.WizardModel
import com.intellij.ui.wizard.WizardNavigationState
import com.intellij.ui.wizard.WizardStep
import org.jdesktop.swingx.VerticalLayout
import javax.swing.JComponent
import javax.swing.JPanel

class ModuleWizardFinalStep : WizardStep<WizardModel>() {

    override fun prepare(state: WizardNavigationState?): JComponent {
        val dialogPanel = JPanel(VerticalLayout().apply { gap = 5 })
        dialogPanel.add(JBLabel("Amadas bezanam besaze??"))
        return dialogPanel
    }
}