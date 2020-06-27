package com.lsjbc.vdtts.redis.entity;

import lombok.*;

/**
 * @ClassName: RedisStudent
 * @Description:
 * @Datetime: 2020/6/18   13:46
 * @Author: JX181114 - 郑建辉
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RedisStudent {

    private Integer id;

    private String username;

    private String password;
}
