package com.bitbakery.plugin.arc;


import com.intellij.formatting.FormattingModelBuilder;
import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;
import com.intellij.lang.Commenter;
import com.intellij.lang.Language;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.lang.folding.FoldingBuilder;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.bitbakery.plugin.arc.psi.ArcElement;
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


    @NotNull
    public StructureViewBuilder getStructureViewBuilder(@NotNull final ArcElement root) {
        return new TreeBasedStructureViewBuilder() {
            public StructureViewModel createStructureViewModel() {
                return new ArcStructureViewModel(root);
            }
        };
    }

    @Nullable
    public FormattingModelBuilder getFormattingModelBuilder() {
        return new ArcFormattingModelBuilder();
    }

    public Annotator getAnnotator() {
        return ARC_ANNOTATOR;
    }

    @NotNull
    public FindUsagesProvider getFindUsagesProvider() {
        return new ArcFindUsagesProvider();
    }
}

