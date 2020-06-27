package com.lsjbc.vdtts.utils;


import com.lsjbc.vdtts.enums.ReturnStatus;
import org.apache.commons.lang.StringUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @Description 网络工具包
 * @Author
 * @Date 2019/01/23 14:13
 */
public class NetUtil {
    private static volatile String ip = "";

    /**
     * 通过NetworkInterfaces + getInetAddresses 获取IP地址
     * 注意：
     * 1）本方法将获取第一个ipv4地址，如果系统存在如虚拟机等将会有多个ipv4地址可选，可能导致获取到虚拟机地址
     * 2）由于该方法要遍历逻辑网络接口，是一个重量级方法，这里采用static来确保全局只获取一次
     * 3）为防止当前JVM中多次调用并发耗费资源，这里采用类锁控制
     * @return IP地址
     * @throws BusinessException 获取系统IP失败,getNetworkInterfaces()如果发生IO异常
     */
    public static String getHostAddress() {
        // 如果已经获取到了直接返回
        if (StringUtils.isNotEmpty(ip)) {
            return ip;
        }
        synchronized (NetUtil.class) {
            if (StringUtils.isNotEmpty(ip)) {
                return ip;
            }
            // 枚举所有逻辑网络接口
            Enumeration<NetworkInterface> en;
            try {
                en = NetworkInterface.getNetworkInterfaces();
            } catch (SocketException e) {
                throw new BusinessException(ReturnStatus.SC_INTERNAL_SERVER_ERROR, "获取网络逻辑接口发生IO异常", e);
            }
            // 遍历所有接口
            a:
            while (en.hasMoreElements()) {
                NetworkInterface networkInterface = en.nextElement();
                // 遍历该接口的所有地址
                for (Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses(); inetAddresses.hasMoreElements(); ) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    String      address     = inetAddress.getHostAddress();
                    // 排除ip6等其它选项
                    if (!address.contains("::") && !address.contains("0:0:") && !address.contains("fe80")) {
                        // 排除127
                        if (!inetAddress.isLoopbackAddress() && inetAddress.isSiteLocalAddress()) {
                            // 赋值到静态变量
                            ip = address;
                            // 取第一个即结束
                            break a;
                        }
                    }
                }
            }
        }
        if (StringUtils.isEmpty(ip)) {
            throw new BusinessException(ReturnStatus.SC_INTERNAL_SERVER_ERROR, "未能获取本机IP，IP=" + ip);
        }
        return ip;
    }
}
