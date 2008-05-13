package com.bitbakery.plugin.arc.sdk;

import com.bitbakery.plugin.arc.ArcIcons;
import com.bitbakery.plugin.arc.ArcStrings;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.projectRoots.*;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.vfs.VirtualFile;
import org.jdom.Element;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;

public class ArcSdkType extends SdkType implements ApplicationComponent {
    @NonNls
    private static final String BASE_DIR = "/base";

    @NonNls
    private static final String WIN_LISP_EXE = "lisp.exe";
    @NonNls
    private static final String LINUX_LISP_EXE = "lisp";
    @NonNls
    private static final String MAC_LISP_EXE = "lisp";
    @NonNls
    private static final String UNIX_LISP_EXE = "lisp";

    @NonNls
    private static String LISP_EXE;

    static {
        if (SystemInfo.isWindows) {
            LISP_EXE = WIN_LISP_EXE;
        } else if (SystemInfo.isLinux) {
            LISP_EXE = LINUX_LISP_EXE;
        } else if (SystemInfo.isMac) {
            LISP_EXE = MAC_LISP_EXE;
        } else if (SystemInfo.isUnix) {
            LISP_EXE = UNIX_LISP_EXE;
        } else {
            throw new IllegalStateException(ArcStrings.message("os.not.supported"));
        }
    }

    @NonNls
    private static final String EXE_PATH = BASE_DIR + "/" + LISP_EXE;

    @NonNls
    private static final String WIN32_SUGGESTED_HOME_PATH = "C:/ruby";
    @NonNls
    private static final String LINUX_SUGGESTED_HOME_PATH = "/usr/local";
    @NonNls
    private static final String MAC_SUGGESTED_HOME_PATH = "/usr/local";
    @NonNls
    private static final String UNIX_SUGGESTED_HOME_PATH = "/usr/local";
    @NonNls
    private static final String ARC_SDK_TYPE = "ARC_SDK_TYPE";


    protected ArcSdkType() {
        super(ARC_SDK_TYPE);
    }

    public String suggestHomePath() {
        return suggestSdkHomePath();
    }

    public static String suggestSdkHomePath() {
        if (SystemInfo.isWindows) {
            return WIN32_SUGGESTED_HOME_PATH;
        }
        if (SystemInfo.isLinux) {
            return LINUX_SUGGESTED_HOME_PATH;
        }
        if (SystemInfo.isMac) {
            return MAC_SUGGESTED_HOME_PATH;
        }
        if (SystemInfo.isUnix) {
            return UNIX_SUGGESTED_HOME_PATH;
        }
        throw new IllegalStateException(ArcStrings.message("os.not.supported"));
    }

    public boolean isValidSdkHome(final String path) {
        return (new File(path + EXE_PATH)).exists();
    }

    @Nullable
    public String getVersionString(final String sdkHome) {
        return "1.0"; // getFullVersion(sdkHome);
    }

    public String suggestSdkName(final String currentSdkName, final String sdkHome) {
        return getPresentableName();
    }

    /**
     * Adds pathes from $LOAD_PATH into classpath
     *
     * @param sdk current SDK
     */
    public void setupSdkPaths(final Sdk sdk) {
/*
        LocalFileSystem lfs = LocalFileSystem.getInstance();
        ArrayList<File> files = new ArrayList<File>();
        Output result = RubyScriptRunner.runScriptFromSource(getVMExecutablePath(sdk), new String[]{}, GET_LOAD_PATH_SCRIPT, new String[]{});
        String loadPaths[] = TextUtils.splitByLines(result.getStdout());
        for (String s : loadPaths){
            if (!s.trim().equals(".")){
                files.add(new File(s));
            }
        }
// WARNING: not all ruby LOAD_PATH may exist!
        final SdkModificator sdkModificator = sdk.getSdkModificator();
        for (File file : files) {
            if (file.exists()){
                sdkModificator.addRoot(lfs.findFileByIoFile(file), ProjectRootType.CLASS);
            }
        }

        sdkModificator.commitChanges();
*/
    }

    @Nullable
    public AdditionalDataConfigurable createAdditionalDataConfigurable(final SdkModel sdkModel, final SdkModificator sdkModificator) {
        return null;
    }

    @Nullable
    public String getBinPath(final Sdk sdk) {
        return sdk.getHomePath() + BASE_DIR;
    }

    @Nullable
    public String getToolsPath(final Sdk sdk) {
        return getBinPath(sdk);
    }

    @Nullable
    public String getVMExecutablePath(final Sdk sdk) {
        return sdk.getHomePath() + EXE_PATH;
    }

    @Nullable
    public String getRtLibraryPath(final Sdk sdk) {
        return null;
    }

    public void saveAdditionalData(final SdkAdditionalData additionalData, final Element additional) {
    }

    @Nullable
    public SdkAdditionalData loadAdditionalData(final Element additional) {
        return null;
    }

    public String getPresentableName() {
        return ArcStrings.message("sdk.title");
    }

    @NotNull
    public String getComponentName() {
        return ARC_SDK_TYPE;
    }

    public void initComponent() {
    }

    public void disposeComponent() {
    }

    public Icon getIcon() {
        // TODO - Get the right icon for this...
        return ArcIcons.ARC_LARGE_ICON;
    }

    public Icon getIconForExpandedTreeNode() {
        // TODO - Get the right icon for this...
        return ArcIcons.ARC_LARGE_ICON;
    }

    public Icon getIconForAddAction() {
        // TODO - Get the right icon for this...
        return ArcIcons.ARC_LARGE_ICON;
    }

    public static boolean isArcSDK(@Nullable final ProjectJdk sdk) {
        return sdk != null && sdk.getSdkType() instanceof ArcSdkType;
    }

    public static boolean isSDKValid(@Nullable final ProjectJdk sdk) {
        return isArcSDK(sdk) && isSDKHomeExist(sdk);
    }

    public static boolean isSDKHomeExist(@Nullable final ProjectJdk sdk) {
        if (sdk == null) {
            return false;
        }
        final VirtualFile sdkHomeDir = sdk.getHomeDirectory();
        return sdkHomeDir != null
                && sdk.getSdkType().isValidSdkHome(sdkHomeDir.getPath());
    }
}
