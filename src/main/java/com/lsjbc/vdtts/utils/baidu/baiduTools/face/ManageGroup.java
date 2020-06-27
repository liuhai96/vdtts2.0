package com.lsjbc.vdtts.utils.baidu.baiduTools.face;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: SmartGuard
 * @BelongsPackage: com.zjh.utils.baidu.baiduTools.face
 * @Author: 25940
 * @CreateTime: 2019-09-10 18:22
 * @Description: 管理用户组
 */
@Component
public class ManageGroup {

    @Autowired
    private CallInterface callInterface;

    public String addGroup(String groupName){
        String url="https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/add";

        Map<String, Object> map = new HashMap<>();
        map.put("group_id", groupName);

        callInterface.callInterface(url,map);
        // 正确返回值
//        {
//
//            "error_code": 0,
//                "log_id": 3314921889,
//        }
        // 发生错误时返回值
//        {
//            "error_code":  223101,
//                "log_id": 815967402,
//                "error_msg": " group is already exist"
//        }

        return null;
    }

    /**
    * @Description 删除用户组
    * @Author  ZhengJianHui
    * @Date   2019/9/10 18:29
    * @Param  [groupName]
    * @Return      java.lang.String
    */
    public String deleteGroup(String groupName){
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/delete";
        Map<String, Object> map = new HashMap<>();
        map.put("group_id", groupName);

        callInterface.callInterface(url,map);

//         正确返回值
//        {
//
//            "error_code":0,
//                "log_id": 3314921889,
//        }
//         发生错误时返回值
//        {
//            "error_code": 223100,
//                "log_id": 815967402,
//                "error_msg": " group is not exist"
//        }

        return null;
    }

    /**
    * @Description 查询出所有组的信息(length默认100，最大1000)
    * @Author  ZhengJianHui
    * @Date   2019/9/10 18:37
    * @Param  [start, length]
    * @Return      java.lang.String
    */
    public String getGroupList(int start,int length){

        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/getlist";


        Map<String, Object> map = new HashMap<>();
        map.put("start", start);
        map.put("length", length);

        callInterface.callInterface(url,map);

        return null;
    }
}
