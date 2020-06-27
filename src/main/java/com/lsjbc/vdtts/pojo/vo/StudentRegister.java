package com.lsjbc.vdtts.pojo.vo;

import com.alibaba.fastjson.JSON;
import com.lsjbc.vdtts.constant.AccountType;
import com.lsjbc.vdtts.entity.Account;
import com.lsjbc.vdtts.entity.Student;
import com.lsjbc.vdtts.utils.CustomTimeUtils;
import com.lsjbc.vdtts.utils.Tool;
import lombok.*;

import java.util.Map;

/**
 * @ClassName: StudentRegister
 * @Description:
 * @Datetime: 2020/6/20   15:50
 * @Author: JX181114 - 郑建辉
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentRegister {

    /**
     * 用户姓名
     */
    private String username;

    /**
     * 身份证号
     */
    private String sfz;

    /**
     * 注册账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 验证码
     */
    private String code;

    Tool tool = new Tool();

    /**
     * 将注册时的信息存到Map中返回给前端
     *
     * @param map ModelAndView中的Map对象
     */
    public void putInfoToMap(Map<String, Object> map) {
        map.put("username", username);
        map.put("sfz", sfz);
        map.put("account", account);
        map.put("password", password);
        map.put("phone", phone);
    }

    /**
     * 将注册时的信息和附言存在Map中返回给前端
     *
     * @param map ModelAndView中的Map对象
     * @param msg 附言
     */
    public void putInfoAndMsgToMap(Map<String, Object> map, String msg) {
        putInfoToMap(map);
        map.put("zjh_msg", msg);
    }

    /**
     * 生成一个账号对象
     *
     * @return 账号对象
     * @author JX181114 --- 郑建辉
     */
    public Account generateAccount() {
        return Account.builder().aAccount(account).aPassword(tool.createMd5(password)).aType(AccountType.STUDENT).build();
    }

    /**
     * 生成一个学生对象
     *
     * @param accountId 账号ID
     * @return 学生对象
     * @author JX181114 --- 郑建辉
     */
    public Student generateStudent(Integer accountId) {

        Student student = Student.builder().sName(username).sSfz(sfz).sPhone(phone).build();

        student.setSRegTime(tool.getDate("yyyy/MM/dd"));

        student.setSAccountId(accountId);

        Integer sex = Integer.parseInt(sfz.charAt(sfz.length() - 2) + "");
        if (sex % 2 == 0) {
            student.setSSex("女");
        } else {
            student.setSSex("男");
        }

        student.setSBirthday(CustomTimeUtils.getTimeFromSFZ(sfz));


        return student;
    }
}
