package com.bitbakery.plugin.arc.repl;

import com.bitbakery.plugin.arc.ArcConstants;
import com.bitbakery.plugin.arc.ArcIcons;
import com.bitbakery.plugin.arc.config.ArcConfigurationForm;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.options.*;
import com.intellij.openapi.util.JDOMExternalizable;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.openapi.util.DefaultJDOMExternalizer;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;
import org.jdom.Element;

import javax.swing.*;

public class ReplApplicationComponent extends BaseConfigurable implements ApplicationComponent, Configurable, JDOMExternalizable {

    // TODO - Update to use PersistentStateComponent
    public String replExecutable;
    public String mzSchemeHome;
    public String arcHome;

    private volatile ArcConfigurationForm form;


    public void initComponent() {
        // Do nothing
    }

    public void disposeComponent() {
        // Do nothing
    }

    public String getComponentName() {
        return "ReplApplicationComponent";
    }

    
    public String getReplExecutable() {
        return replExecutable;
    }

    public void setReplExecutable(String replExecutable) {
        this.replExecutable = replExecutable;
    }

    public String getMzSchemeHome() {
        return mzSchemeHome;
    }

    public void setMzSchemeHome(final String mzSchemeHome) {
        this.mzSchemeHome = mzSchemeHome;
    }

    public String getArcHome() {
        return arcHome;
    }

    public void setArcHome(final String arcHome) {
        this.arcHome = arcHome;
    }


    @Nls
    public String getDisplayName() {
        return ArcConstants.ARC;
    }

    public Icon getIcon() {
        return ArcIcons.ARC_CONFIG_ICON;
    }

    @Nullable
    @NonNls
    public String getHelpTopic() {
        // TODO - How does one get a help topic created and loaded? Is this it?
        return null;
    }

    public JComponent createComponent() {
        if (form == null) {
            form = new ArcConfigurationForm();
            form.setData(this);
        }
        return form.getRootComponent();
    }

    public boolean isModified() {
        return form != null && form.isModified(this);
    }

    // TODO - This might be wrong...
    public void apply() throws ConfigurationException {
        if (form != null) {
            form.getData(this);
        }
    }

    // TODO - This might be wrong...
    public void reset() {
        if (form != null) {
            form.setData(this);
        }
    }

    public void disposeUIResources() {
        form = null;
    }


/*
    public Object getState() {
        return this;
    }

    public void loadState(Object state) {
        if (state != null) {
            ReplApplicationComponent that = (ReplApplicationComponent) state;
            this.mzSchemeHome = that.mzSchemeHome;
            this.arcHome = that.arcHome;
        }
    }
*/

    public void readExternal(Element element) throws InvalidDataException {
        DefaultJDOMExternalizer.readExternal(this, element);
    }

    public void writeExternal(Element element) throws WriteExternalException {
        DefaultJDOMExternalizer.writeExternal(this, element);
    }
}
