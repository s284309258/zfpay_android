package com.lckj.jycm.network;

public class VersionInfoRequest {

    /**
     * versionNo : 1.0.1
     * sign : 417544F5CDC2FEF722961FE7FC0D0BF7
     * token : 11|UO5UW0MJM52VSG5R47N1XEGBXIQVLAKM
     */

    private String deviceType;
    private String system_type = "getVersionInfo";

    public VersionInfoRequest(String deviceType) {
        this.deviceType = deviceType;
    }

    private String getDeviceType() {
        return deviceType;
    }

    private void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}
