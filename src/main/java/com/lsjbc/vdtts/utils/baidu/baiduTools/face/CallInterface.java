package com.lsjbc.vdtts.utils.baidu.baiduTools.face;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.face.AipFace;
import com.lsjbc.vdtts.utils.baidu.client.BaiduGetAccessToken;
import com.lsjbc.vdtts.utils.baidu.client.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @BelongsProject: SmartGuard
 * @BelongsPackage: com.zjh.utils.baidu.baiduTools.face
 * @Author: 25940
 * @CreateTime: 2019-09-10 17:20
 * @Description: 调用接口
 */
@Component
public class CallInterface {

    private final String AK = "t4F54NhLuwhoOdoQmFFpQ8na";
    private final String SK = "xy0i96ZMuFQjn4xkSxfS8e54nS7phTg0";
    private final String ID = "16285490";

    @Autowired
    private BaiduGetAccessToken baiduGetAccessToken;

    public CallInterface() {
    }

    /**
     * @Description 调用接口
     * @Author  ZhengJianHui
     * @Date   2019/9/10 17:00
     * @Param  [url, map]
     * @Return      java.lang.String
     */
    public String callInterface(String url, Map<String,Object> map){
        String result = null;
        try {
            //将map对象转换成Json字符串
            String param = JSON.toJSONString(map);
            //获取AccessToken          /*第三方接口信息处理*/
            String accessToken = baiduGetAccessToken.getAuth(AK,SK);
            //调用接口 /*同时配置相应字符格式*/
            result = HttpUtil.post(url, accessToken, "application/json", param);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;/*返后相应格式接口信息*/
        }
    }
}
