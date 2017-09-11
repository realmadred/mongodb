package com.feng.search;

import java.io.Serializable;

/**
 * Created by lf on 2017/9/5.
 * 车辆运行搜索vo
 */
public class MongoCarSearch extends MongoTime implements Serializable {

    private static final long serialVersionUID = -1520415087436548740L;

    private String lpn; // 车牌号
    private String orderId; // 订单id
    private double speed; // 速度

    public String getLpn() {
        return lpn;
    }

    public void setLpn(String lpn) {
        this.lpn = lpn;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
