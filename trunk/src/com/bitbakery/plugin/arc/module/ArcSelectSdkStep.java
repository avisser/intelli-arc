package com.bitbakery.plugin.arc.module;

import com.bitbakery.plugin.arc.sdk.ArcSdkType;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.projectRoots.ProjectJdk;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.ui.Messages;

import javax.swing.*;

public class ArcSelectSdkStep extends ModuleWizardStep {
    private Icon myIcon;
    private ArcSdkChooserPanel myPanel;
    private String myHelp;
    private ArcModuleBuilder myBuilder;

    /*



Error during dispatching of java.awt.event.MouseEvent[MOUSE_RELEASED,(442,610),button=1,modifiers=Button1,clickCount=1] on dialog2: com.bitbakery.plugin.arc.module.ArcModuleBuilder
java.lang.ClassCastException: com.bitbakery.plugin.arc.module.ArcModuleBuilder
   at com.intellij.ide.util.newProjectWizard.ProjectNameWithTypeStep.updateDataModel(ProjectNameWithTypeStep.java:8)
   at com.intellij.ide.util.newProjectWizard.AddModuleWizard.a(AddModuleWizard.java:82)
   at com.intellij.ide.util.newProjectWizard.AddModuleWizard.doNextAction(AddModuleWizard.java:102)
   at com.intellij.ide.wizard.AbstractWizard$5.actionPerformed(AbstractWizard.java:123)
   at javax.swing.AbstractButton.fireActionPerformed(AbstractButton.java:1882)
   at javax.swing.AbstractButton$Handler.actionPerformed(AbstractButton.java:2202)
   at javax.swing.DefaultButtonModel.fireActionPerformed(DefaultButtonModel.java:420)
   at javax.swing.DefaultButtonModel.setPressed(DefaultButtonModel.java:258)
   at javax.swing.plaf.basic.BasicButtonListener.mouseReleased(BasicButtonListener.java:236)
   at java.awt.Component.processMouseEvent(Component.java:5602)
   at javax.swing.JComponent.processMouseEvent(JComponent.java:3135)
   at java.awt.Component.processEvent(Component.java:5367)
   at java.awt.Container.processEvent(Container.java:2010)
   at java.awt.Component.dispatchEventImpl(Component.java:4068)
   at java.awt.Container.dispatchEventImpl(Container.java:2068)
   at java.awt.Component.dispatchEvent(Component.java:3903)
   at java.awt.LightweightDispatcher.retargetMouseEvent(Container.java:4256)
   at java.awt.LightweightDispatcher.processMouseEvent(Container.java:3936)
   at java.awt.LightweightDispatcher.dispatchEvent(Container.java:3866)
   at java.awt.Container.dispatchEventImpl(Container.java:2054)
   at java.awt.Window.dispatchEventImpl(Window.java:1791)
   at java.awt.Component.dispatchEvent(Component.java:3903)
   at java.awt.EventQueue.dispatchEvent(EventQueue.java:463)
   at com.intellij.ide.IdeEventQueue.c(IdeEventQueue.java:179)
   at com.intellij.ide.IdeEventQueue.b(IdeEventQueue.java:62)
   at com.intellij.ide.IdeEventQueue.dispatchEvent(IdeEventQueue.java:119)
   at java.awt.EventDispatchThread.pumpOneEventForHierarchy(EventDispatchThread.java:269)
   at java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java:190)
   at java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java:180)
   at java.awt.Dialog$1.run(Dialog.java:535)
   at java.awt.Dialog$2.run(Dialog.java:563)
   at java.security.AccessController.doPrivileged(Native Method)
   at java.awt.Dialog.show(Dialog.java:561)
   at com.intellij.openapi.ui.impl.DialogWrapperPeerImpl$MyDialog.show(DialogWrapperPeerImpl.java:72)
   at com.intellij.openapi.ui.impl.DialogWrapperPeerImpl.show(DialogWrapperPeerImpl.java:5)
   at com.intellij.openapi.ui.DialogWrapper.show(DialogWrapper.java:813)
   at com.intellij.openapi.roots.ui.configuration.actions.NewModuleAction.actionPerformed(NewModuleAction.java:7)
   at com.intellij.openapi.actionSystem.impl.ActionMenuItem$ActionTransmitter.actionPerformed(ActionMenuItem.java:11)
   at javax.swing.AbstractButton.fireActionPerformed(AbstractButton.java:1882)
   at com.intellij.openapi.actionSystem.impl.ActionMenuItem.fireActionPerformed(ActionMenuItem.java:32)
   at javax.swing.AbstractButton$Handler.actionPerformed(AbstractButton.java:2202)
   at javax.swing.DefaultButtonModel.fireActionPerformed(DefaultButtonModel.java:420)
   at javax.swing.DefaultButtonModel.setPressed(DefaultButtonModel.java:258)
   at javax.swing.AbstractButton.doClick(AbstractButton.java:334)
   at apple.laf.ScreenMenuItem.actionPerformed(ScreenMenuItem.java:96)
   at java.awt.MenuItem.processActionEvent(MenuItem.java:597)
   at java.awt.MenuItem.processEvent(MenuItem.java:556)
   at java.awt.MenuComponent.dispatchEventImpl(MenuComponent.java:298)
   at java.awt.MenuComponent.dispatchEvent(MenuComponent.java:286)
   at java.awt.EventQueue.dispatchEvent(EventQueue.java:466)
   at com.intellij.ide.IdeEventQueue.c(IdeEventQueue.java:179)
   at com.intellij.ide.IdeEventQueue.b(IdeEventQueue.java:37)
   at com.intellij.ide.IdeEventQueue.dispatchEvent(IdeEventQueue.java:119)
   at java.awt.EventDispatchThread.pumpOneEventForHierarchy(EventDispatchThread.java:269)
   at java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java:190)
   at java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:184)
   at java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:176)
   at java.awt.EventDispatchThread.run(EventDispatchThread.java:110)





    */


    public ArcSelectSdkStep(WizardContext wizardCtx, ArcModuleBuilder builder, ModulesProvider provider, Icon icon, String helpId) {
        super();
        myIcon = icon;
        myBuilder = builder;
        myPanel = new ArcSdkChooserPanel(wizardCtx.getProject());
        myHelp = helpId;
    }

    public String getHelpId() {
        return myHelp;
    }

    public JComponent getPreferredFocusedComponent() {
        return myPanel.getPreferredFocusedComponent();
    }

    public JComponent getComponent() {
        return myPanel;
    }


    public void updateDataModel() {
        myBuilder.setModuleJdk(getSdk());
    }

    private ProjectJdk getSdk() {
        return myPanel.getChosenJdk();
    }

    public Icon getIcon() {
        return myIcon;
    }


    public boolean validate() {
        ProjectJdk jdk = myPanel.getChosenJdk();
        if (jdk == null) {
            int result = Messages.showYesNoDialog(
                    "prompt.confirm.project.no.jdk.error",
                    "jdk.type.incorrect",
                    Messages.getWarningIcon()
            );
            return result == 0;
        }
        if (!ArcSdkType.isArcSDK(jdk)) {
            int result = Messages.showYesNoDialog(
                    "prompt.confirm.project.jdk.error",
                    "jdk.type.incorrect",
                    Messages.getWarningIcon()
            );
            return result == 0;
        }

/* TODO - This is here to confirm if we want to override the project JDK... We may want this if we have a Arc project...?
final ProjectRootManager projectRootManager = ProjectRootManager.getInstance(myProject);
        if (!jdk.equals(projectRootManager.getProjectJdk())) {
            int result = Messages.showYesNoDialog(
                    RBundle.message("prompt.confirm.default.sdk.change"),
                    RBundle.message("title.default.sdk.change"),
                    Messages.getQuestionIcon()
            );
            if (result == 1) {
                projectRootManager.setProjectJdk(jdk);
            }
        }*/
        return true;
    }
}
