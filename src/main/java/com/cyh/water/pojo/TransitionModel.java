package com.cyh.water.pojo;

import lombok.Data;

/**
 *  记录客户各月用水量和水费 综合表
 */
@Data
public class TransitionModel implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    private int customer_id;
    private String customer_name;
    private String year;
    private String month;
    private String create_time;
    private Double water_consumption; // 客户用水量
    private Double month_bill_cost;  // 客户月水费

}
