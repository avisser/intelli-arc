package com.bitbakery.plugin.arc;


import com.intellij.ide.IconProvider;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.options.colors.ColorSettingsPages;
import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.FileTypeIndentOptionsProvider;
import com.intellij.util.PairConsumer;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ArcSupportLoader extends FileTypeFactory implements ApplicationComponent, //InspectionToolProvider,
        FileTypeIndentOptionsProvider, IconProvider {

    public static final LanguageFileType ARC = new ArcFileType();


    public void initComponent() {
        ApplicationManager.getApplication().runWriteAction(
                new Runnable() {
                    public void run() {
                        FileTypeManager.getInstance().registerFileType(ARC, "arc");

                        // TODO: Figure out the new "extensions" API that should be used here...                       
                        ColorSettingsPages.getInstance().registerPage(new ArcColorsPage());
                    }
                });

        // TODO - Is the right place for this guy??
        //ChooseByNameRegistry.getInstance().contributeToClasses(new LispClassChooser());
        //ChooseByNameRegistry.getInstance().contributeToSymbols(new LispSymbolChooser());
    }

    public void disposeComponent() {
    }

    @NotNull
    public String getComponentName() {
        return "Arc support loader";
    }

/*
    public Class[] getInspectionClasses() {
        return new Class[]{
                JSUnresolvedVariableInspection.class,
                JSUndeclaredVariableInspection.class,
                JSUntypedDeclarationInspection.class,
                JSUnresolvedFunctionInspection.class,
                JSDuplicatedDeclarationInspection.class,
                JSDeprecatedSymbolsInspection.class,
                JSUnusedLocalSymbolsInspection.class,
                JSShowOverridingMarkersInspection.class
        };
    }
*/

    public CodeStyleSettings.IndentOptions createIndentOptions() {
        return new CodeStyleSettings.IndentOptions();
    }

    public FileType getFileType() {
        return ARC;
    }

/*
    public static @Nullable LanguageDialect getLanguageDialect(VirtualFile file) {
        if (file != null) {
            final String extension = file.getExtension();
            if (ECMA_SCRIPT_L4_FILE_EXTENSION.equals(extension) || ECMA_SCRIPT_L4_FILE_EXTENSION2.equals(extension)) {
                return ECMA_SCRIPT_L4;
            } else if (JSON_FILE_EXTENSION.equals(extension)) {
                return JSON;
            } else
            if (ApplicationManager.getApplication().isUnitTestMode() && GWT_DIALECT.getFileExtension().equals(extension)) {
                return GWT_DIALECT;
            }
        }
        return null;
    }
*/

    public void createFileTypes(final @NotNull PairConsumer<FileType, String> consumer) {
        consumer.consume(ARC, "arc");
    }

    public Icon getIcon(@NotNull final PsiElement element, final int flags) {
        return null;
    }
}
