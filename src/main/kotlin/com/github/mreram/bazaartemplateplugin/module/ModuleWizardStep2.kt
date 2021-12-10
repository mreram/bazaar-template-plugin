package com.github.mreram.bazaartemplateplugin.module

import com.intellij.ui.components.JBTextField
import com.intellij.ui.wizard.WizardModel
import com.intellij.ui.wizard.WizardNavigationState
import com.intellij.ui.wizard.WizardStep
import com.intellij.vcs.log.ui.frame.WrappedFlowLayout
import org.jdesktop.swingx.VerticalLayout
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JCheckBox
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JScrollPane

class ModuleWizardStep2 : WizardStep<WizardModel>() {

    override fun prepare(state: WizardNavigationState?): JComponent {
        val dialogPanel = JPanel(VerticalLayout().apply { gap = 5 })
        with(dialogPanel) {
            add(JLabel("Dependencies:"), BorderLayout.LINE_START)
            val dependenciesPane = JPanel(WrappedFlowLayout(5,5))
            for (i in 0..100) {
                val checkBox = JCheckBox("ConstrainLayout$i")
                checkBox.isSelected = true
                dependenciesPane.add(checkBox, BorderLayout.CENTER)
            }
            val dependenciesScrollPane = JScrollPane(dependenciesPane).also {
                it.autoscrolls = true
                it.preferredSize = Dimension(500, 500)
            }
            return dialogPanel.also {
                it.add(dependenciesScrollPane)
            }
        }
    }
}