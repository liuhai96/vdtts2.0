package com.lsjbc.vdtts.pojo.bo.aliai.client;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName: AliAiClientFactory
 * @Description: 阿里AI的Client对象
 * @Datetime: 2020/6/21   13:31
 * @Author: JX181114 - 郑建辉
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Component(AliAiClientFactory.NAME)
public class AliAiClientFactory {

    /**
     * Bean名
     */
    public static final String NAME = "AliAiClientFactory";

    /**
     * 阿里短信接口的数据
     */
    private final String SMS_AK = "LTAImj67ZtliYTl5";
    private final String SMS_SK = "zrvOOytVkuEMSxIgpRrwAArdaVt9W7";

    /**
     * 阿里短信客户端
     */
    private IAcsClient clientSMS;

    /**
     * 生成发送短信的阿里接口客户端
     * @return
     */
    public synchronized IAcsClient getSmsClient(){
        if(clientSMS==null) {
            DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", SMS_AK, SMS_SK);
            clientSMS=new DefaultAcsClient(profile);
        }
        return clientSMS;
    }
}
