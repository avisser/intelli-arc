package com.bitbakery.plugin.arc.module;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.module.ModuleTypeManager;
import org.jetbrains.annotations.NotNull;

public class ArcModuleLoader implements ApplicationComponent {
    public ArcModuleLoader(ModuleTypeManager moduleTypeManager) {
        moduleTypeManager.registerModuleType(ArcModuleType.ARC);
    }

    @NotNull
    public String getComponentName() {
        return "ArcModuleLoader"; // ArcConstants.ARC;
    }

    public void initComponent() {
    }

    public void disposeComponent() {
    }
}
