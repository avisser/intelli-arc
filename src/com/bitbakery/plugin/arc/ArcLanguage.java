package com.bitbakery.plugin.arc;


import com.bitbakery.plugin.arc.lexer.ArcTokenTypes;
import com.bitbakery.plugin.arc.structure.ArcStructureViewModel;
import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;
import com.intellij.lang.*;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.ExternalAnnotator;
import com.intellij.lang.documentation.DocumentationProvider;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.lang.folding.FoldingBuilder;
import com.intellij.lang.parameterInfo.ParameterInfoHandler;
import com.intellij.lang.refactoring.NamesValidator;
import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.lang.surroundWith.SurroundDescriptor;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class ArcLanguage extends Language {
    private static final Annotator ARC_ANNOTATOR = new ArcAnnotator();

    public ArcLanguage() {
        super("Arc");
    }

    @NotNull
    public ParserDefinition getParserDefinition() {
        return new ArcParserDefinition();
    }

    @NotNull
    public SurroundDescriptor[] getSurroundDescriptors() {
        // TODO - Enable surrounding with (), [] (fn (xxx)  ...), etc.
        return super.getSurroundDescriptors();
    }

    @Nullable
    public PsiReferenceAdjuster getReferenceAdjuster() {
        return super.getReferenceAdjuster();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @NotNull
    public NamesValidator getNamesValidator() {
        // TODO - This is actually only used by the rename refactoring, and we can actually redefine *anything* in Arc, so... return null...?
        return super.getNamesValidator();
    }

    @NotNull
    public RefactoringSupportProvider getRefactoringSupportProvider() {
        // TODO - Extend DefaultRefactoringSupportHandler in order to implement safe delete, extract method, inline, and introduce variable
        return super.getRefactoringSupportProvider();
    }

    @Nullable
    public ParameterInfoHandler[] getParameterInfoHandlers() {
        // TODO - Implement this guy to enable parameter help when writing function and macro calls
        return super.getParameterInfoHandlers();
    }

    @Nullable
    protected DocumentationProvider createDocumentationProvider() {
        // TODO - Create a documentation provider that jumps us to the arcfn documentation site
        return super.createDocumentationProvider();
    }

    @Nullable
    public ExternalAnnotator getExternalAnnotator() {
        // TODO - Anything...? Potentially powerful, but do we need it for anything??
        return super.getExternalAnnotator();
    }

    @NotNull
    public TokenSet getReadableTextContainerElements() {
        return ArcTokenTypes.READABLE_TEXT;
    }

    @NotNull
    public PairedBraceMatcher getPairedBraceMatcher() {
        return new ArcBraceMatcher();
    }

    @NotNull
    public SyntaxHighlighter getSyntaxHighlighter(Project project, final VirtualFile virtualFile) {
        return new ArcSyntaxHighlighter();
    }

    @NotNull
    public Commenter getCommenter() {
        return new ArcCommenter();
    }

    public FoldingBuilder getFoldingBuilder() {
        return new ArcFoldingBuilder();
    }

    @Nullable
    public StructureViewBuilder getStructureViewBuilder(final PsiFile psiFile) {
        return new TreeBasedStructureViewBuilder() {
            @NotNull
            public StructureViewModel createStructureViewModel() {
                return new ArcStructureViewModel(psiFile);
            }

            public boolean isRootNodeShown() {
                return false;
            }
        };
    }

/*
    @Nullable
    public FormattingModelBuilder getFormattingModelBuilder() {
        return new ArcFormattingModelBuilder();
    }
*/

    public Annotator getAnnotator() {
        return ARC_ANNOTATOR;
    }

    @NotNull
    public FindUsagesProvider getFindUsagesProvider() {
        return new ArcFindUsagesProvider();
    }
}

