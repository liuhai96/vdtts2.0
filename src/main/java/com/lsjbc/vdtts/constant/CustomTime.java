package com.lsjbc.vdtts.constant;

/**
 * @ClassName: CustomTime
 * @Description: 关于时间的常量
 * @Datetime: 2020/6/6   18:04
 * @Author: JX181114 - 郑建辉
 */
public class CustomTime {

    /**
     * 以秒表示1秒
     */
    public static final long SECOND_OF_SECOND = 1;

    /**
     * 以秒表示1分钟
     */
    public static final long SECOND_OF_MINUTE = 60 * SECOND_OF_SECOND;

    /**
     * 以秒表示半小时
     */
    public static final long SECOND_OF_HALF_HOUR = 30 * SECOND_OF_MINUTE;

    /**
     * 以秒表示1小时
     */
    public static final long SECOND_OF_HOUR = 60 * SECOND_OF_MINUTE;

    /**
     * 以秒表示1天
     */
    public static final long SECOND_OF_DAY = 60 * SECOND_OF_HOUR;

    /**
     * 以秒表示30天
     */
    public static final long SECOND_OF_30_DAY = 30 * SECOND_OF_DAY;

    /**
     * 以毫秒表示半小时
     */
    public static final long MILLISECOND_OF_HALF_HOUR = 1000 * SECOND_OF_HALF_HOUR;

    /**
     * 以毫秒表示1小时
     */
    public static final long MILLISECOND_OF_HOUR = 1000 * SECOND_OF_HOUR;
}
