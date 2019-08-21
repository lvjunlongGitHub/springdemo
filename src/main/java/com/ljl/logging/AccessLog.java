package com.ljl.logging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lvjunlong
 * @date 2019/8/21 下午3:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessLog {

    private String ip;
    private String action;
    private String ua;
    private String referer;
    private String sid;
    private String uid;
    private String appVersion;
    private String appId;
    private String osType;
    private String osVersion;
    private String deviceModel;
    private String deviceId;
    private String networkType;
    private String vendor;
    private String cpu;
    private String appKey;
    private Long gameId;
    private Long startTime = 0L;
    private Long finishTime = 0L;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ip").append("=").append(this.ip).append("`");
        sb.append("ua").append("=").append(this.ua).append("`");
        sb.append("referer").append("=").append(this.referer).append("`");
        sb.append("networkType").append("=").append(this.networkType).append("`");
        sb.append("action").append("=").append(this.action).append("`");
        sb.append("sid").append("=").append(this.sid).append("`");
        sb.append("uid").append("=").append(this.uid).append("`");
        sb.append("appId").append("=").append(this.appId).append("`");
        sb.append("appVersion").append("=").append(this.appVersion).append("`");
        sb.append("osType").append("=").append(this.osType).append("`");
        sb.append("osVersion").append("=").append(this.osVersion).append("`");
        sb.append("deviceId").append("=").append(this.deviceId).append("`");
        sb.append("deviceModel").append("=").append(this.deviceModel).append("`");
        sb.append("vendor").append("=").append(this.vendor).append("`");
        sb.append("cpu").append("=").append(this.cpu).append("`");
        sb.append("appKey").append("=").append(this.appKey).append("`");
        sb.append("gameId").append("=").append(this.gameId).append("`");
        sb.append("useTime").append("=").append(this.finishTime - this.startTime).append("ms");
        return sb.toString();
    }
}
