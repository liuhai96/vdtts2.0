package com.lsjbc.vdtts.utils.baidu.baiduTools.face;

import com.lsjbc.vdtts.utils.FileTools;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: SmartGuard
 * @BelongsPackage: com.zjh.utils.baidu.baiduTools.face
 * @Author: 25940
 * @CreateTime: 2019-09-14 15:28
 * @Description: 人脸搜索
 */
@Component
public class SearchFace {

    @Autowired
    private CallInterface callInterface;

    @Autowired
    private FileTools fileTools;

    //人脸搜索
    public int searchFace(String base64) {

        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";

        Map<String, Object> map = new HashMap<>();
        map.put("image", base64);
        map.put("liveness_control", "NORMAL");
        map.put("group_id_list", "user");
        map.put("image_type", "BASE64");
        map.put("quality_control", "LOW");

        String resStr=callInterface.callInterface(url, map);

        int aId=0;

        //将返回结果转换成Map形式
        Map<String,Object> resultMap=(HashMap<String,Object>)(new JSONObject(resStr).toMap()).get("result");

        try {
            ArrayList<Map<String, Object>> userList = (ArrayList<Map<String, Object>>) resultMap.get("user_list");
            if (userList.size()==1){
                aId = Integer.parseInt(String.valueOf(userList.get(0).get("user_id")));
            }
        } catch (NullPointerException e){
            aId=-1;
        }
        //返回示例
        //失败示例
        //result:
        //{
            //"error_code":222207,
            // "error_msg":"match user is not found",
            // "log_id":747956984631597151,
            // "timestamp":1568463159,
            // "cached":0,
            // "result":null
            // }
        //}
        //成功示例
        //result:
        // {
            // "error_code":0,
            // "error_msg":"SUCCESS",
            // "log_id":744193284641097461,
            // "timestamp":1568464109,
            // "cached":0,
            // "result":
                // {
                    // "face_token":"0d0cd07c8fb7744186ee6cdd99c66ed7",
                    // "user_list":
                        // [
                            // {
                                // "group_id":"user",
                                // "user_id":"3",
                                // "user_info":""
                                // ,"score":95.822059631348
                            // }
                        // ]
                // }
        // }
        return aId;
    }
}
