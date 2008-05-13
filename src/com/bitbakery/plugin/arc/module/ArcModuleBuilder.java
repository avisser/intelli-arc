package com.bitbakery.plugin.arc.module;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.projectRoots.ProjectJdk;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;

public class ArcModuleBuilder extends ModuleBuilder {
    private ProjectJdk myJdk;
    private String myContentRoot;


    public String getContentRootPath() {
        return myContentRoot;
    }

    public void setContentRootPath(String contentEntryPath) {
        myContentRoot = contentEntryPath;
    }

    private void setupContentRoot(ModifiableRootModel rootModel) {
        String moduleRootPath = getContentRootPath();
        if (moduleRootPath != null) {
            LocalFileSystem lfs = LocalFileSystem.getInstance();
            VirtualFile moduleContentRoot = lfs.refreshAndFindFileByPath(FileUtil.toSystemIndependentName(moduleRootPath));
            if (moduleContentRoot != null) {
                rootModel.addContentEntry(moduleContentRoot);
            }
        }
    }

    public void setupRootModel(ModifiableRootModel rootModel) throws ConfigurationException {
        if (myJdk != null) {
            rootModel.setJdk(myJdk);
        } else {
            rootModel.inheritJdk();
        }
        setupContentRoot(rootModel);
    }

    public void setModuleJdk(ProjectJdk jdk) {
        myJdk = jdk;
    }

    public ProjectJdk getModuleJdk() {
        return myJdk;
    }


    public ModuleType getModuleType() {
        return ArcModuleType.ARC;
    }
}
