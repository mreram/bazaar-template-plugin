package com.github.mreram.bazaartemplateplugin.module

import com.intellij.ui.components.JBTextField
import com.intellij.ui.wizard.WizardModel
import com.intellij.ui.wizard.WizardNavigationState
import com.intellij.ui.wizard.WizardStep
import org.jdesktop.swingx.VerticalLayout
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.Box
import javax.swing.ButtonGroup
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JRadioButton

class ModuleWizardStep1 : WizardStep<WizardModel>() {

    override fun prepare(state: WizardNavigationState?): JComponent {
        val dialogPanel = JPanel(VerticalLayout().apply { gap = 5 })
        with(dialogPanel) {
            add(JLabel("Name:"), BorderLayout.LINE_START)
            add(JBTextField(), BorderLayout.LINE_START)
            dialogPanel.add(Box.createVerticalStrut(8))
            add(JLabel("Dagger Component type:"), BorderLayout.LINE_START)
            val buttonGroup = ButtonGroup()
            buttonGroup.add(JRadioButton("Expose Component"))
            buttonGroup.add(JRadioButton("Dispatcher Component"))
            for (radioButton in buttonGroup.elements) {
                add(radioButton)
            }
            return dialogPanel.apply {
                preferredSize = Dimension(500, 500)
            }
        }
    }
}