package com.bitbakery.plugin.arc.module;

import com.bitbakery.plugin.arc.ArcIcons;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import org.jetbrains.annotations.NonNls;

import javax.swing.*;
import java.util.ArrayList;

public class ArcModuleType extends ArcRunnableModuleType<ArcModuleBuilder> {
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

    public ModuleWizardStep[] createWizardSteps(WizardContext ctx, ArcModuleBuilder builder, ModulesProvider provider) {
        ArrayList<ModuleWizardStep> steps = new ArrayList<ModuleWizardStep>();
        steps.add(new ArcSelectLocationStep(ctx, builder, provider, ArcIcons.ARC_LARGE_ICON, null));
        steps.add(new ArcSelectSdkStep(ctx, builder, provider, ArcIcons.ARC_LARGE_ICON, null));
        return steps.toArray(new ModuleWizardStep[steps.size()]);
    }

    public ArcModuleBuilder createModuleBuilder() {
        return new ArcModuleBuilder();
    }


    public String getName() {
        return "ArcModule"; // LispBundle.message("module.cl.title");
    }


    public String getDescription() {
        return "Arc module description"; // LispBundle.message("module.cl.description");
    }


    public Icon getBigIcon() {
        return ArcIcons.ARC_LARGE_ICON;
    }


    public Icon getNodeIcon(boolean isOpened) {
        Icon icon = ArcIcons.ARC_LARGE_ICON;
        return isOpened ? icon : icon;
    }
}
