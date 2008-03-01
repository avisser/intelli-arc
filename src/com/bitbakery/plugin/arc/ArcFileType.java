package com.bitbakery.plugin.arc;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ArcFileType extends LanguageFileType {

    public static final ArcLanguage ARC = new ArcLanguage();

    public ArcFileType() {
        super(ARC);
    }

    @NotNull
    public String getName() {
        return "Arc";
    }

    @NotNull
    public String getDescription() {
        return "Arc source file";
        // return JSBundle.message("javascript.filetype.description");
    }

    @NotNull
    public String getDefaultExtension() {
        return "arc";
    }

    public Icon getIcon() {
        return null;
        // return IconLoader.getIcon("/fileTypes/javaScript.png");
        // return LispIcons.LISP_FILE_ICON;
    }

/*
    @NotNull
    public SyntaxHighlighter getHighlighter(@Nullable final Project project, @Nullable final VirtualFile virtualFile) {
        Language lang = null;
        if (virtualFile != null) {
            lang = JavaScriptSupportLoader.getLanguageDialect(virtualFile);
        }
        if (lang == null && project != null && virtualFile != null) {
            PsiFile psiFile = ApplicationManager.getApplication().runReadAction(new Computable<PsiFile>() {
                public PsiFile compute() {
                    return PsiManager.getInstance(project).findFile(virtualFile);
                }
            });

            if (psiFile != null) lang = psiFile.getLanguageDialect();
        }
        if (lang == null) {
            lang = getLanguage();
        }
        return lang.getSyntaxHighlighter(project, virtualFile);
    }
*/
}

