package com.bitbakery.plugin.arc.module;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import org.jetbrains.annotations.Nullable;

public abstract class ArcRunnableModuleType<T extends ModuleBuilder> extends ModuleType<T> {
    public ArcRunnableModuleType(String s) {
        super(s);
    }

    public static boolean checkModule(@Nullable final Module module) {
        return module != null && module.getModuleType() instanceof ArcRunnableModuleType;
    }
}
