package com.bitbakery.plugin.arc;

import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: Describe the role(s) and responsibilit(ies)
 */
public class ArcFindUsagesProvider implements FindUsagesProvider {
    @Nullable
    public WordsScanner getWordsScanner() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Nullable
    public String getHelpId(@NotNull PsiElement psiElement) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public String getType(@NotNull PsiElement element) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public String getDescriptiveName(@NotNull PsiElement element) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
