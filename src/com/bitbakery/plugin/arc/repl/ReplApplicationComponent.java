package com.bitbakery.plugin.arc.repl;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.util.DefaultJDOMExternalizer;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import com.bitbakery.plugin.arc.ArcConstants;
import org.jdom.Element;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ReplApplicationComponent implements ApplicationComponent { //, Configurable, JDOMExternalizable {
    //private ArcConfigurationForm form;

    // Common Lisp settings - these guys are persisted via JDOMExternalizable
    //public LispImplementationType type;
    public String homeDirectory;
    public String initializationFile;
    public String arcExecutable;


    public void initComponent() {
        // Do nothing
    }

    public void disposeComponent() {
        // Do nothing
    }

    public String getComponentName() {
        return "ReplApplicationComponent";
    }

    public String getHomeDirectory() {
        return homeDirectory;
    }

    public void setHomeDirectory(final String homeDirectory) {
        this.homeDirectory = homeDirectory;
    }

    public String getInitializationFile() {
        return initializationFile;
    }

    public void setInitializationFile(final String initializationFile) {
        this.initializationFile = initializationFile;
    }

    public String getArcExecutable() {
        return arcExecutable;
    }

    public void setArcExecutable(final String arcExecutable) {
        this.arcExecutable = arcExecutable;
    }

    @Nls
    public String getDisplayName() {
        return ArcConstants.ARC;
    }

    public Icon getIcon() {
        return null;
        // return ArcIcons.ARC_FILE_ICON;
    }

    @Nullable
    @NonNls
    public String getHelpTopic() {
        return null;
    }

/*
    public JComponent createComponent() {
        if (form == null) {
            form = new ArcConfigurationForm();
        }
        return form.getRootComponent();
    }
*/

/*
    public boolean isModified() {
        return form != null && form.isModified(this);
    }

    public void apply() throws ConfigurationException {
        if (form != null) {
            form.getData(this);
        }
    }

    public void reset() {
        if (form != null) {
            form.setData(this);
        }
    }

    public void disposeUIResources() {
        form = null;
    }
*/

    public void readExternal(Element element) throws InvalidDataException {
        DefaultJDOMExternalizer.readExternal(this, element);
    }

    public void writeExternal(Element element) throws WriteExternalException {
        DefaultJDOMExternalizer.writeExternal(this, element);
    }
}
