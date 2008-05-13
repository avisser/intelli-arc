package com.bitbakery.plugin.arc.module;

import com.intellij.ide.util.BrowseFilesListener;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.NamePathComponent;
import com.intellij.ide.util.projectWizard.ProjectWizardUtil;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.roots.ModuleRootModel;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.MultiLineLabelUI;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.FieldPanel;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class ArcSelectLocationStep extends ModuleWizardStep {
    @NonNls
    private static final String PROJECT_SUFFIX = ".iml";
    @NonNls
    private static final String NEW_MODULE_NAME = "untitled";
    @NonNls
    private static final String SEPARATOR = File.separator;

    private boolean wasUpdated;
    private ModulesProvider myProvider;
    private ArcModuleBuilder myBuilder;
    private WizardContext myWizardCtx;
    private Icon myIcon;
    private String myHelp;

    // UI
    private JComponent myPanel;
    private NamePathComponent mySelectPathComponent;
    private JTextField myModuleDir;

    private boolean dirChanged;

    public ArcSelectLocationStep(WizardContext wizardCtx, ArcModuleBuilder builder, ModulesProvider provider, Icon icon, String help) {
        super();
        myIcon = icon;
        myWizardCtx = wizardCtx;
        myBuilder = builder;
        myProvider = provider;
        wasUpdated = false;
        myPanel = createPanel();
        myHelp = help;
    }


    public String getHelpId() {
        return myHelp;
    }

    public void updateStep() {
        super.updateStep();
        if (!wasUpdated) {
            if (myWizardCtx.isCreatingNewProject()) {
                setSyncEnabled(false);
            } else {
                VirtualFile virtualfile = myWizardCtx.getProject().getProjectFile();
                String parentDir = virtualfile == null ? null : VfsUtil.virtualToIoFile(virtualfile).getParent();
                if (parentDir != null) {
                    String moduleName = ProjectWizardUtil.findNonExistingFileName(parentDir, newProjectName(), "");
                    setModuleName(moduleName);
                    setContentEntryPath(parentDir + SEPARATOR + moduleName);
                }
            }
        }
        if (myWizardCtx.isCreatingNewProject()) {
            if (!isPathChangedByUser()) {
                setContentEntryPath(myWizardCtx.getProjectFileDirectory());
            }
            if (!isNameChangedByUser()) {
                setModuleName(myWizardCtx.getProjectName());
            }
        }
        if (!wasUpdated) {
            mySelectPathComponent.getNameComponent().selectAll();
        }
        wasUpdated = true;
    }


    private String newProjectName() {
        ModuleType mtype = myBuilder.getModuleType();
        String newName;
        if (mtype instanceof ArcModuleType) {
            newName = ((ArcModuleType) mtype).getNewModuleName();
        } else {
            newName = NEW_MODULE_NAME;
        }
        return newName;
    }


    public Icon getIcon() {
        return myIcon;
    }


    public JComponent getPreferredFocusedComponent() {
        return mySelectPathComponent.getNameComponent();
    }


    public JComponent getComponent() {
        return myPanel;
    }


    private JComponent createPanel() {
        JPanel panel = new JPanel(new GridBagLayout());

        panel.setBorder(BorderFactory.createEtchedBorder());
        JLabel desc = new JLabel("module.common.selectlocation.title");
        desc.setUI(new MultiLineLabelUI());
        panel.add(desc, new GridBagConstraints(0, -1, 2, 1, 1.0D, 0.0D, 18, 0, new Insets(8, 10, 8, 10), 0, 0));

        mySelectPathComponent = new NamePathComponent(
                "module.common.selectlocation.module.name",
                "module.common.selectlocation.content.root",
                'M', 'r',
                "module.common.selectlocation.module.files.root.msg",
                "module.common.selectlocation.module.files.root.msg");


        panel.add(mySelectPathComponent, new GridBagConstraints(0, -1, 2, 1, 1.0D, 0.0D, 18, 2, new Insets(8, 10, 0, 10), 0, 0));
        JLabel savedIn = new JLabel("module.common.selectlocation.module.files.root.msg");
        panel.add(savedIn, new GridBagConstraints(0, -1, 2, 1, 1.0D, 1.0D, 16, 2, new Insets(30, 10, 0, 10), 0, 0));
        myModuleDir = new JTextField();
        myModuleDir.setEditable(false);

        Insets ins = myModuleDir.getBorder().getBorderInsets(myModuleDir);
        myModuleDir.setBorder(BorderFactory.createEmptyBorder(ins.top, ins.left, ins.bottom, ins.right));
        FieldPanel fp = createFieldPanel(myModuleDir, null, null);
        panel.add(fp, new GridBagConstraints(0, -1, 1, 1, 1.0D, 0.0D, 18, 2, new Insets(0, 10, 10, 0), 0, 0));
        JButton changeDir = new JButton("module.common.selectlocation.module.files.root.select");
        changeDir.addActionListener(new BrowseModuleFileDirectoryListener(myModuleDir));
        panel.add(changeDir, new GridBagConstraints(1, -1, 1, 1, 0.0D, 0.0D, 18, 0, new Insets(0, 0, 10, 10), 0, 0));
        DocumentAdapter documentadapter = new DocumentAdapter() {
            protected void textChanged(DocumentEvent documentevent) {
                fixPath();
            }
        };
        mySelectPathComponent.getNameComponent().getDocument().addDocumentListener(documentadapter);
        mySelectPathComponent.getPathComponent().getDocument().addDocumentListener(documentadapter);
        mySelectPathComponent.getPathComponent().getDocument().addDocumentListener(new DocumentAdapter() {
            public void textChanged(DocumentEvent documentevent) {
                myWizardCtx.requestWizardButtonsUpdate();
            }
        });

        return panel;
    }

    private void fixPath() {
        if (!dirChanged) {
            myModuleDir.setText(mySelectPathComponent.getPath());
        }
    }

    public void updateDataModel() {
        myBuilder.setName(getModuleName());
        myBuilder.setModuleFilePath(getModuleFilePath());
        myBuilder.setContentRootPath(getContentEntryPath());
    }

    private boolean isNameChangedByUser() {
        return mySelectPathComponent.isNameChangedByUser();
    }

    private boolean isPathChangedByUser() {
        return mySelectPathComponent.isPathChangedByUser();
    }

    private boolean moduleExists(String name) {
        Module modules[] = myProvider.getModules();
        for (Module mod : modules) {
            if (name.equals(mod.getName())) {
                return true;
            }
        }

        return false;
    }


    private String fixModulePath() {
        String text = myModuleDir.getText();
        if (text != null) {
            return text.trim();
        } else {
            return "";
        }
    }


    private String getModuleName() {
        return mySelectPathComponent.getNameValue();
    }

    private void setModuleName(String s) {
        mySelectPathComponent.setNameValue(s);
    }


    @Nullable
    private String getContentEntryPath() {
        String temp = mySelectPathComponent.getPath();
        return temp != null ? temp.trim() : null;
    }

    private void setContentEntryPath(String path) {
        mySelectPathComponent.setPath(path);
    }


    private String getModuleFilePath() {
        return fixModulePath() + SEPARATOR +
                mySelectPathComponent.getNameValue() + PROJECT_SUFFIX;
    }

    private boolean isSyncEnabled() {
        return mySelectPathComponent.isSyncEnabled();
    }

    private void setSyncEnabled(boolean enabled) {
        mySelectPathComponent.setSyncEnabled(enabled);
    }

    public boolean validate() {
        String moduleName = getModuleName();
        if (moduleName == null || moduleName.length() == 0) {
            Messages.showErrorDialog(mySelectPathComponent.getNameComponent(),
                    "module.common.selectlocation.prompt.module.name",
                    "module.common.selectlocation.module.not.specified.msg");
            return false;
        }
        if (moduleExists(moduleName)) {
            Messages.showErrorDialog(
                    "module.common.selectlocation.module.exists.text",
                    "module.common.selectlocation.module.exists.msg");
            return false;
        }
        String modulePath = fixModulePath();
        if (modulePath.length() == 0) {
            Messages.showErrorDialog(mySelectPathComponent.getPathComponent(),
                    "module.common.selectlocation.specify.module.location.prompt",
                    "module.common.selectlocation.module.location.not.specified.msg");
            return false;
        }
        String contentPath = getContentEntryPath();
        if (contentPath != null) {
            Module modules[] = myProvider.getModules();
            for (Module mod : modules) {
                ModuleRootModel rootModel = myProvider.getRootModel(mod);
                VirtualFile contentRoots[] = rootModel.getContentRoots();
                for (VirtualFile file : contentRoots) {
                    if (contentPath.equals(file.getPath())) {
                        Messages.showErrorDialog(mySelectPathComponent.getPathComponent(),
                                "module.common.selectlocation.root.already.defined.text",
                                "module.common.selectlocation.root.already.defined.title");
                        return false;
                    }
                }
            }

            if (!ProjectWizardUtil.createDirectoryIfNotExists(
                    "module.common.selectlocation.module.content.root.title",
                    contentPath, mySelectPathComponent.isPathChangedByUser())) {
                return false;
            }
        }

        return ProjectWizardUtil.createDirectoryIfNotExists(
                "module.common.selectlocation.module.file.title", fixModulePath(), dirChanged);
    }

    private class BrowseModuleFileDirectoryListener extends BrowseFilesListener {
        public BrowseModuleFileDirectoryListener(JTextField field) {
            super(field,
                    "module.common.selectlocation.select.module.file.location.prompt",
                    "module.common.selectlocation.module.file.location.text",
                    BrowseFilesListener.SINGLE_DIRECTORY_DESCRIPTOR);
        }

        public void actionPerformed(ActionEvent evt) {
            String before = myModuleDir.getText().trim();
            super.actionPerformed(evt);
            String after = myModuleDir.getText().trim();
            if (!after.equals(before)) {
                dirChanged = true;
            }
        }
    }
}
