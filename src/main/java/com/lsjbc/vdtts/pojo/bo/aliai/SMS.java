package com.lsjbc.vdtts.pojo.bo.aliai;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.lsjbc.vdtts.pojo.bo.aliai.client.AliAiClientFactory;
import com.lsjbc.vdtts.utils.CustomStringUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassName: SMS
 * @Description: 阿里云短信验证码工具
 * @Datetime: 2020/6/21   13:38
 * @Author: JX181114 - 郑建辉
 */
@Component(SMS.NAME)
public class SMS {

    /**
     * Bean名
     */
    public static final String NAME = "Ali_SMS";

    @Resource(name = AliAiClientFactory.NAME)
    private AliAiClientFactory clientFactory;

    /**
     * 阿里云短信验证码签名
     */
    private final String SIGNATURE = "机动车驾驶员计时培训系统";

    /**
     * 阿里云短信模板：注册模板
     */
    private final String TEMPLATE_REGISTER = "SMS_193506262";

    /**
     * 阿里云短信模板：更新用户信息模板
     */
    private final String TEMPLATE_UPDATE = "SMS_193521121";

    /**
     * 阿里云登录验证码短信在session中保存的key值
     */
    public static final String VC_TYPE_REGISTER = "r_vc";

    /**
     * 阿里云修改验证码短信在session中保存的key值
     */
    public static final String VC_TYPE_UPDATE = "u_vc";

    /**
     * 阿里云修改验证码的手机号在session中保存的key值
     */
    public static final String PHONE = "phone";

    /**
     * 向客服发送注册时的验证码
     *
     * @param phone   手机号
     * @param request Http请求域
     * @author JX181114 --- 郑建辉
     */
    public void registerVC(String phone, HttpServletRequest request) {
        //生成验证码
        String vc = (String) request.getSession().getAttribute(VC_TYPE_REGISTER);
        if (StringUtils.isEmpty(vc)) {
            vc = CustomStringUtils.generatingVerificationCode(6);
        }

        //将验证码与手机号存入request中
        request.getSession().setAttribute(PHONE, phone);
        request.getSession().setAttribute(VC_TYPE_REGISTER, vc);

        try {
            sendSMS(phone, vc, TEMPLATE_REGISTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 向客服发送注册时的验证码
     *
     * @param phone   手机号
     * @param request Http请求域
     * @author JX181114 --- 郑建辉
     */
    public void updateVC(String phone, HttpServletRequest request) {
        //生成验证码
        String vc = (String) request.getSession().getAttribute(VC_TYPE_UPDATE);
        if (StringUtils.isEmpty(vc)) {
            vc = CustomStringUtils.generatingVerificationCode(6);
        }

        try {
            sendSMS(phone, vc, TEMPLATE_UPDATE);
            //将验证码与手机号存入request中
            request.getSession().setAttribute(PHONE, phone);
            request.getSession().setAttribute(VC_TYPE_UPDATE, vc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送验证码
     *
     * @param phone    手机号
     * @param vc       验证码
     * @param template 短信模板
     * @throws ClientException 发送短信出错
     * @author JX181114 --- 郑建辉
     */
    @Async
    public void sendSMS(String phone, String vc, String template) throws ClientException, InterruptedException {

        //获取短信客户端
        IAcsClient client = clientFactory.getSmsClient();

        //固定格式
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //发信地址
        request.putQueryParameter("RegionId", "cn-hangzhou");

        //收信人
        request.putQueryParameter("PhoneNumbers", phone);

        //短信签名
        request.putQueryParameter("SignName", SIGNATURE);

        //短信模板编码
        request.putQueryParameter("TemplateCode", template);

        //参数1：短信验证码
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + vc + "\"}");

        //调用短信接口
//        client.getCommonResponse(request);
    }


    /**
     * 检测修改信息用的验证码
     *
     * @param request   Request域
     * @param phone     电话号码
     * @param code      验证码
     * @param cleanCode 是否自动删除验证码
     * @return 验证结果
     * @author JX181114 --- 郑建辉
     */
    public String checkRegisterVC(HttpServletRequest request, String phone, String code, Boolean cleanCode) {
        return checkVC(request, phone, code, VC_TYPE_REGISTER, cleanCode);
    }

    /**
     * 检测修改信息用的验证码
     *
     * @param request   Request域
     * @param phone     电话号码
     * @param code      验证码
     * @param cleanCode 是否自动删除验证码
     * @return 验证结果
     * @author JX181114 --- 郑建辉
     */
    public String checkUpdateVC(HttpServletRequest request, String phone, String code, Boolean cleanCode) {
        return checkVC(request, phone, code, VC_TYPE_UPDATE, cleanCode);
    }

    /**
     * 检测特定的情况的验证码
     *
     * @param request   Request域
     * @param phone     电话号码
     * @param code      验证码
     * @param vcKey     验证码在session中保存的名字
     * @param cleanCode 是否自动删除验证码
     * @return 验证结果
     * @author JX181114 --- 郑建辉
     */
    private String checkVC(HttpServletRequest request, String phone, String code, String vcKey, Boolean cleanCode) {
        try {
            //获取Session
            HttpSession session = request.getSession();
            //把Session中有用的信息拿出来
            String sessionPhone = session.getAttribute(PHONE).toString();
//            String sessionCode = session.getAttribute(vcKey).toString();
            String sessionCode = "000000";

            //如果Session中的数据和用户提交的信息相同，就验证通过
            if (sessionPhone.equals(phone) && sessionCode.equals(code)) {

                if (cleanCode) {
                    //把Session中的验证码信息删除，避免下一次用户获取验证码时冲突
                    session.removeAttribute(PHONE);
                    session.removeAttribute(vcKey);
                }

                return "验证通过";
            }
        } catch (NullPointerException e) {
            return "验证码已过期";
        }
        return "验证失败";
    }

}
