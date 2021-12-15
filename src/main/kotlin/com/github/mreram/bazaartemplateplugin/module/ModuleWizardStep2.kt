package com.github.mreram.bazaartemplateplugin.module

import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.wizard.WizardModel
import com.intellij.ui.wizard.WizardNavigationState
import com.intellij.ui.wizard.WizardStep
import icons.Icons.dagger_icon
import org.jdesktop.swingx.HorizontalLayout
import org.jdesktop.swingx.VerticalLayout
import java.awt.BorderLayout
import java.awt.event.ItemListener
import javax.swing.Box
import javax.swing.ButtonGroup
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JRadioButton

class ModuleWizardStep2 : WizardStep<WizardModel>() {

    private var component: JComponent? = null
    private var daggerUi: JComponent? = null

    override fun prepare(state: WizardNavigationState?): JComponent {
        if (component == null) {
            component = createUi()
        }
        return requireNotNull(component)
    }

    private fun createUi(): JComponent {
        val dialogPanel = JPanel(VerticalLayout().apply { gap = 5 })
        with(dialogPanel) {
            add(JPanel(HorizontalLayout().apply { gap = 5 }).also {
                val daggerCheckBox = JBCheckBox("Dagger is needed")
                daggerCheckBox.addItemListener(onChangeHasDagger(dialogPanel))
                it.add(daggerCheckBox)
                it.add(JBLabel(dagger_icon))
            })
            return dialogPanel
        }
    }

    private fun onChangeHasDagger(panel: JPanel) = ItemListener {
        if ((it.source as JBCheckBox).isSelected) {
            daggerUi = createDaggerUi(panel)
        } else {
            panel.remove(daggerUi)
        }
        panel.updateUI()
    }

    private fun createDaggerUi(panel: JPanel): JComponent {
        val layoutManager = VerticalLayout().apply { gap = 5 }
        return JPanel(layoutManager).apply {
            add(Box.createVerticalStrut(10))
            add(JBLabel("Dagger Component type:"), BorderLayout.LINE_START)
            val buttonGroup = ButtonGroup()
            buttonGroup.add(JRadioButton("Expose Component"))
            buttonGroup.add(JRadioButton("Dispatcher Component"))
            for (radioButton in buttonGroup.elements) {
                add(radioButton)
            }
            add(Box.createVerticalStrut(10))
            add(JBLabel("Modules that you need to select:"), BorderLayout.LINE_START)
            add(JBCheckBox("ViewModel Module"))
            add(JBCheckBox("Fragment Module"))
            add(JBCheckBox("Startup Module"))
            add(JBCheckBox("Worker Module"))
            panel.add(this)
        }
    }
}