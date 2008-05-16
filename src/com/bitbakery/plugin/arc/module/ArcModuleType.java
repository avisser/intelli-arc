package com.bitbakery.plugin.arc.module;

import com.bitbakery.plugin.arc.ArcIcons;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.ProjectWizardStepFactory;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import org.jetbrains.annotations.NonNls;

import javax.swing.*;
import java.util.ArrayList;

public class ArcModuleType extends ModuleType<ArcModuleBuilder> {
    public static ArcModuleType ARC = new ArcModuleType();

    @NonNls
    private static final String NEW_MODULE_NAME = "untitled";
    @NonNls
    private static final String ARC_MODULE = "ARC_MODULE";

    private ArcModuleType() {
        super(ARC_MODULE);
    }

    public String getNewModuleName() {
        return NEW_MODULE_NAME;
    }

    public final boolean isJ2EE() {
        return false;
    }

    // TODO - All of this needs to be reworked for version 8... <sigh>...
    public ModuleWizardStep[] createWizardSteps(WizardContext ctx, ArcModuleBuilder builder, ModulesProvider provider) {

        final ProjectWizardStepFactory wizardFactory = ProjectWizardStepFactory.getInstance();
        ArrayList<ModuleWizardStep> steps = new ArrayList<ModuleWizardStep>();
        steps.add(new ArcSelectLocationStep(ctx, builder, provider, ArcIcons.ARC_MODULE_TYPE_ICON, null));
        //steps.add(new ArcSelectSdkStep(ctx, builder, provider, ArcIcons.ARC_MODULE_TYPE_ICON, null));
        return steps.toArray(new ModuleWizardStep[steps.size()]);
    }

    public ArcModuleBuilder createModuleBuilder() {
        return new ArcModuleBuilder();
    }


    public String getName() {
        return "Arc Module"; // LispBundle.message("module.cl.title");
    }


    public String getDescription() {
        return "Facilitates development of <b>Arc</b> projects. Supports <b>Arc</b> SDK and REPL configuration. Requires <b>MzScheme 352</b> or <b>360</b>."; // LispBundle.message("module.cl.description");
    }


    public Icon getBigIcon() {
        return ArcIcons.ARC_MODULE_TYPE_ICON;
    }


    public Icon getNodeIcon(boolean isOpened) {
        Icon icon = ArcIcons.ARC_MODULE_TYPE_ICON;
        return isOpened ? icon : icon;
    }
}


