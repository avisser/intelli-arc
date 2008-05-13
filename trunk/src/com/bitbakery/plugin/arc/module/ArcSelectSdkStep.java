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
