package com.lsjbc.vdtts.utils.baidu.baiduTools.face;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: SmartGuard
 * @BelongsPackage: com.zjh.utils.baidu.baiduTools.face
 * @Author: 25940
 * @CreateTime: 2019-09-10 17:16
 * @Description: 管理人脸库的用户
 */
@Component
public class ManageUser {

    @Autowired
    private CallInterface callInterface;

    /**
    * @Description 获取用户组内的
    * @Author  ZhengJianHui
    * @Date   2019/9/10 17:26
    * @Param  []
    * @Return      java.lang.String
    */
    public String getUserList() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/getusers";

        Map<String, Object> map = new HashMap<>();
        //用户组ID(固定)
        map.put("group_id", "user");

        callInterface.callInterface(url,map);


//        返回示例
//        {
//            "user_id_list": [
//            "uid1", "uid2"
//            ]
//        }
        return null;
    }

    /**
    * @Description 把用户复制到其他的用户组
    * @Author  ZhengJianHui
    * @Date   2019/9/10 17:30
    * @Param  [groupID, userID]
    * @Return      java.lang.String
    */
    public String copyUserToOtherGroup(String groupID,String userID){

        String url="https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/copy";


        Map<String, Object> map = new HashMap<>();
        //来源用户组(固定)
        map.put("src_group_id", "user");
        //目标用户组
        map.put("dst_group_id", groupID);
        //要复制的用户ID
        map.put("user_id", userID);

        callInterface.callInterface(url,map);

//        返回示例
//        // 正确返回值
//        {
//            "error_code": 0,
//                "log_id": 3314921889,
//        }
//        // 发生错误时返回值
//        {
//            "error_code": 223111,
//                "log_id": 3111284097,
//                "error_msg": "dst group is not exist"
//        }
        return null;
    }

    public String deleteUser(int tID){
        String url="https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/delete";

        Map<String, Object> map = new HashMap<>();
        //用户组ID(固定)
        map.put("group_id", "user");
        //用户ID
        map.put("user_id",tID);

        callInterface.callInterface(url,map);


//        正确返回值
//        {
//            "error_code": 0,
//                "log_id": 3314921889,
//        }
//        发生错误时返回值
//        {
//            "error_code":  223103,
//                "log_id": 815967402,
//                "error_msg": "user is not exist"
//        }

        return null;
    }


}
