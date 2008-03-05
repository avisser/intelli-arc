package com.bitbakery.plugin.arc.structure;

import com.bitbakery.plugin.arc.psi.Def;
import com.bitbakery.plugin.arc.psi.Mac;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.TextEditorBasedStructureViewModel;
import com.intellij.ide.util.treeView.smartTree.Filter;
import com.intellij.ide.util.treeView.smartTree.Grouper;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: Describe the role(s) and responsibilit(ies)
 */
public class ArcStructureViewModel extends TextEditorBasedStructureViewModel {
    private PsiFile myFile;

    public ArcStructureViewModel(final PsiFile file) {
        super(file);
        myFile = file;
    }

    @NotNull
    public StructureViewTreeElement getRoot() {
        return new ArcStructureViewElement(myFile);
    }

    @NotNull
    public Grouper[] getGroupers() {
        // TODO - Enable grouping based on defs, macs, fns, []s, etc...
        return Grouper.EMPTY_ARRAY;
    }

    @NotNull
    public Sorter[] getSorters() {
        // TODO - Enable sorting based on defs, macs, fns, []s, etc...
        return new Sorter[]{Sorter.ALPHA_SORTER};
    }

    @NotNull
    public Filter[] getFilters() {
        // TODO - Enable filtering based on defs, macs, fns, []s, etc...
        return Filter.EMPTY_ARRAY;
    }

    protected PsiFile getPsiFile() {
        return myFile;
    }

    @NotNull
    protected Class[] getSuitableClasses() {
        // TODO - We'll want to add top-level variable definitions... anything else?
        return new Class[]{Def.class, Mac.class};
    }
}
