package com.work.logistics.utils;

import org.springframework.stereotype.Component;

@Component
public class Distance {
    /**
     * 计算两点经纬度距离，单位：公里
     * @param loc1 经度纬度字符串 "lng,lat"
     * @param loc2 经度纬度字符串 "lng,lat"
     * @return 两点之间距离（公里）
     */
    public double calculateDistance(String loc1, String loc2) {
        if (loc1 == null || loc2 == null) {
            return 0;
        }
        String[] parts1 = loc1.split(",");
        String[] parts2 = loc2.split(",");
        if (parts1.length != 2 || parts2.length != 2) {
            return 0;
        }

        double lng1 = Double.parseDouble(parts1[0]);
        double lat1 = Double.parseDouble(parts1[1]);
        double lng2 = Double.parseDouble(parts2[0]);
        double lat2 = Double.parseDouble(parts2[1]);

        double distance = haversine(lat1, lng1, lat2, lng2);
        return Math.ceil(distance);  // 向上取整返回整数公里数
    }

    /**
     * 哈弗辛公式计算球面距离
     */
    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // 地球半径，单位千米
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
