package com.bitbakery.plugin.arc;

import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.FileEditorPositionListener;
import com.intellij.ide.structureView.ModelListener;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.Grouper;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.ide.util.treeView.smartTree.Filter;
import com.bitbakery.plugin.arc.psi.ArcElement;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: Describe the role(s) and responsibilit(ies)
 */
public class ArcStructureViewModel implements StructureViewModel {
    public ArcStructureViewModel(ArcElement root) {
    }

    @Nullable
    public Object getCurrentEditorElement() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addEditorPositionListener(FileEditorPositionListener listener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void removeEditorPositionListener(FileEditorPositionListener listener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addModelListener(ModelListener modelListener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void removeModelListener(ModelListener modelListener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public StructureViewTreeElement getRoot() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public Grouper[] getGroupers() {
        return new Grouper[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public Sorter[] getSorters() {
        return new Sorter[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public Filter[] getFilters() {
        return new Filter[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void dispose() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
