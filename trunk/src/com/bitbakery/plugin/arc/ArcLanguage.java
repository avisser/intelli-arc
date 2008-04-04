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
    private ArcRefactoringSupportProvider refactoringSupportProvider;
    private ArcParserDefinition parserDefinition;
    private ArcBraceMatcher braceMatcher;
    private ArcSyntaxHighlighter syntaxHighlighter;
    private ArcCommenter commenter;
    private ArcFoldingBuilder foldingBuilder;
    private ArcFindUsagesProvider findUsagesProvider;

    public ArcLanguage() {
        super("Arc");
        parserDefinition = new ArcParserDefinition();
        refactoringSupportProvider = new ArcRefactoringSupportProvider();
        braceMatcher = new ArcBraceMatcher();
        syntaxHighlighter = new ArcSyntaxHighlighter();
        commenter = new ArcCommenter();
        foldingBuilder = new ArcFoldingBuilder();
        findUsagesProvider = new ArcFindUsagesProvider();
    }

    @NotNull
    public ParserDefinition getParserDefinition() {
        return parserDefinition;
    }

    @NotNull
    public SurroundDescriptor[] getSurroundDescriptors() {
        // TODO - Enable surrounding with (), [] (fn (xxx)  ...), etc.
        return super.getSurroundDescriptors();
    }

    @Nullable
    public PsiReferenceAdjuster getReferenceAdjuster() {
        // TODO - Find out what the hell a "reference adjuster" is... (???)
        return super.getReferenceAdjuster();
    }

    @NotNull
    public NamesValidator getNamesValidator() {
        // TODO - Although legal, we should warn the user if they're about to redefine a name - I'm guessing we won't do that intentionally very often!
        return super.getNamesValidator();
    }

    @NotNull
    public RefactoringSupportProvider getRefactoringSupportProvider() {
        return refactoringSupportProvider;
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
        // TODO - Don't know if we need an *external* annotator, but something to warn about duplicate definitions would be nice!
        return super.getExternalAnnotator();
    }

    @NotNull
    public TokenSet getReadableTextContainerElements() {
        return ArcTokenTypes.READABLE_TEXT;
    }

    @NotNull
    public PairedBraceMatcher getPairedBraceMatcher() {
        return braceMatcher;
    }

    @NotNull
    public SyntaxHighlighter getSyntaxHighlighter(Project project, final VirtualFile virtualFile) {
        return syntaxHighlighter;
    }

    @NotNull
    public Commenter getCommenter() {
        return commenter;
    }

    public FoldingBuilder getFoldingBuilder() {
        return foldingBuilder;
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
        return findUsagesProvider;
    }

}

