package com.github.mreram.bazaartemplateplugin.module

import com.intellij.ui.wizard.WizardModel
import com.intellij.ui.wizard.WizardNavigationState
import com.intellij.ui.wizard.WizardStep
import org.jdesktop.swingx.VerticalLayout
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel

class ModuleWizardStep3 : WizardStep<WizardModel>() {

    override fun prepare(state: WizardNavigationState?): JComponent {
        val dialogPanel = JPanel(VerticalLayout().apply { gap = 5 })
        dialogPanel.add(JLabel("Amadas bezanam besaze??"))
        return dialogPanel
    }
}