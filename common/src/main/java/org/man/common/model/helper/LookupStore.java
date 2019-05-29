package org.man.common.model.helper;


import static org.man.common.util.LookupConstant.DEFAULT_OVERRIDE_HEADER_AND_DESCRIPTION;

public class LookupStore {
    private String lookupType;
    private boolean overrideHeaderAndDescription;

    public LookupStore() {
    }

    public LookupStore(String lookupType) {
        this.lookupType = lookupType;
        this.overrideHeaderAndDescription = DEFAULT_OVERRIDE_HEADER_AND_DESCRIPTION;
    }

    public LookupStore(String lookupType, boolean overrideHeaderAndDescription) {
        this.lookupType = lookupType;
        this.overrideHeaderAndDescription = overrideHeaderAndDescription;
    }

    public String getLookupType() {
        return lookupType;
    }

    public void setLookupType(String lookupType) {
        this.lookupType = lookupType;
    }

    public boolean isOverrideHeaderAndDescription() {
        return overrideHeaderAndDescription;
    }

    public void setOverrideHeaderAndDescription(boolean overrideHeaderAndDescription) {
        this.overrideHeaderAndDescription = overrideHeaderAndDescription;
    }

    public boolean isEmpty(){
        return this.lookupType == null;
    }
}
