package com.intellij.lang.javascript.flex.projectStructure.model.impl;

import com.intellij.lang.javascript.flex.projectStructure.model.AirSigningOptions;
import com.intellij.lang.javascript.flex.projectStructure.model.ModifiableAirDesktopPackagingOptions;
import com.intellij.util.xmlb.annotations.AbstractCollection;
import com.intellij.util.xmlb.annotations.Attribute;
import com.intellij.util.xmlb.annotations.Property;
import com.intellij.util.xmlb.annotations.Tag;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

class AirDesktopPackagingOptionsImpl extends AirPackagingOptionsBase implements ModifiableAirDesktopPackagingOptions {

  public AirDesktopPackagingOptionsImpl getCopy() {
    AirDesktopPackagingOptionsImpl copy = new AirDesktopPackagingOptionsImpl();
    applyTo(copy);
    return copy;
  }

  public State getState() {
    final State state = new State();
    state.USE_GENERATED_DESCRIPTOR = isUseGeneratedDescriptor();
    state.CUSTOM_DESCRIPTOR_PATH = getCustomDescriptorPath();
    state.PACKAGE_FILE_NAME = getPackageFileName();
    state.FILES_TO_PACKAGE = getFilesToPackage();
    state.SIGNING_OPTIONS = getSigningOptions();
    return state;
  }

  public void loadState(@NotNull State state) {
    setUseGeneratedDescriptor(state.USE_GENERATED_DESCRIPTOR);
    setCustomDescriptorPath(state.CUSTOM_DESCRIPTOR_PATH);
    setPackageFileName(state.PACKAGE_FILE_NAME);
    setFilesToPackage(state.FILES_TO_PACKAGE);
    setSigningOptions(state.SIGNING_OPTIONS);
  }

  @Tag("packaging-air-desktop")
  public static class State {
    @Attribute("use-generated-descriptor")
    public boolean USE_GENERATED_DESCRIPTOR = true;
    @Attribute("custom-descriptor-path")
    public String CUSTOM_DESCRIPTOR_PATH = "";
    @Attribute("package-file-name")
    public String PACKAGE_FILE_NAME = "";
    @Tag("files-to-package")
    @AbstractCollection(surroundWithTag = false)
    public List<FilePathAndPathInPackage> FILES_TO_PACKAGE = new ArrayList<>();
    @Property(surroundWithTag = false)
    public AirSigningOptions SIGNING_OPTIONS = new AirSigningOptions();
  }
}
