package com.lsjbc.vdtts.pojo.vo;

import com.lsjbc.vdtts.entity.Teacher;
import com.lsjbc.vdtts.utils.CustomTimeUtils;
import lombok.*;

/**
 * @ClassName: TeacherDetail
 * @Description: 前台显示教练列表时传输的数据
 * @Datetime: 2020/6/15   20:51
 * @Author: JX181114 - 郑建辉
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TeacherDetail {

    /**
     * 教练ID
     */
    private Integer id;

    /**
     * 教练名字
     */
    private String name;

    /**
     * 教练评分
     */
    private Double score;

    /**
     * 教练性别
     */
    private String sex;

    /**
     * 教练年龄
     */
    private Integer age;

    /**
     * 总学员数
     */
    private Integer studentCount;

    /**
     * 驾校名字
     */
    private String schoolName;

    /**
     * 把平均分转换为小数点后保留一位的分数
     *
     * @param score 分数
     */
    public void setScore(Double score) {
        Double d = score * 10;
        Integer i = d.intValue();
        this.score = i / 10.0;
    }

    /**
     * 根据Teacher对象生成SchoolDetail对象
     *
     * @param teacher 教练对象
     * @return 教练的详细信息对象
     * @author JX181114 --- 郑建辉
     */
    public static TeacherDetail generateDetail(Teacher teacher) {
        TeacherDetail detail = new TeacherDetail();

        try {
            detail.setAge(CustomTimeUtils.getTimeSubTime(teacher.getTBirthday()));
        } catch (Exception e) {
            detail.setAge(0);
        }

        detail.setId(teacher.getTId());

        detail.setName(teacher.getTName());

        detail.setSex(teacher.getTSex());


        return detail;
    }


}
