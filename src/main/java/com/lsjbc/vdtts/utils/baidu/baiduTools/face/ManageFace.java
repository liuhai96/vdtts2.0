package com.lsjbc.vdtts.utils.baidu.baiduTools.face;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: SmartGuard
 * @BelongsPackage: com.zjh.utils.baidu.baiduTools.face
 * @Author: 25940
 * @CreateTime: 2019-09-10 15:41
 * @Description: 人脸管理
 */
@Component
public class ManageFace {

    @Autowired
    private CallInterface callInterface;

    /**
    * @Description 在人脸库中新增一个用户，并存入一张人脸，如果这个用户已存在，旧直接新增一张    人脸
    * @Author  ZhengJianHui
    * @Date   2019/9/11 17:28
    * @Param  [imgStr, tID]
    * @Return      java.util.Map<java.lang.String,java.lang.Object>
    */
    public Map<String,Object> add(String imgStr, int tID) {

        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        //返回示例
        //返回成功
        // {
            // "error_code":0,
            // "error_msg":"SUCCESS",
            // "log_id":744193282082747661,
            // "timestamp":1568208274,
            // "cached":0,
            // "result":{
                // "face_token":"46957c038a68198b22f4124178c7ffde",
                // "location":{
                    // "left":187.69,
                    // "top":366.95,
                    // "width":398,
                    // "height":391,
                    // "rotation":0
                // }
            // }
        // }
        //返回失败
        //{
            // "error_code":223105,
            // "error_msg":"face is already exist",
            // "log_id":744193282083847201,
            // "timestamp":1568208384,
            // "cached":0,
            // "result":null
        // }
        return addOrUpdate(url,imgStr,tID);
    }

    /**
    * @Description 针对某一个用户上传一张图片，这一张图片会覆盖这个用户下的所有图片
    * @Author  ZhengJianHui
    * @Date   2019/9/11 17:29
    * @Param  [imgStr, tID]
    * @Return      java.util.Map<java.lang.String,java.lang.Object>
    */
    public Map<String, Object> update(String imgStr, int tID) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/update";

        return addOrUpdate(url,imgStr,tID);
    }

    /**
    * @Description 添加或者覆盖人脸
    * @Author  ZhengJianHui
    * @Date   2019/9/11 17:28
    * @Param  [url, imgStr, tID]
    * @Return      java.util.Map<java.lang.String,java.lang.Object>
    */
    public Map<String,Object> addOrUpdate(String url,String imgStr,int tID){


        Map<String, Object> map = new HashMap<>();
        //图片的Base64编码
        map.put("image", imgStr);
        //用户组ID(固定)
        map.put("group_id", "user");
        //用户ID
        map.put("user_id", String.valueOf(tID));
        //活体检测：正常
        map.put("liveness_control", "NORMAL");
        //图片格式：Base64编码
        map.put("image_type", "BASE64");
        //质量检测：低
        map.put("quality_control", "LOW");

        String resStr=callInterface.callInterface(url,map);/*上传相应信息，同时接收返回响应信息*/
        //将返回结果转换成Map形式
        Map<String,Object> resultMap=(HashMap<String,Object>)new JSONObject(resStr).toMap();

        Map<String,Object> returnMap=new HashMap<>();

        if(resultMap.get("error_code").toString().equals("0")){
            returnMap.put("facetoken",((HashMap<String,Object>)resultMap.get("result")).get("face_token").toString());
        }
        return returnMap;
    }

    /**
    * @Description 删除用户下的一张人脸
    * @Author  ZhengJianHui
    * @Date   2019/9/11 21:17
    * @Param  [faceToken, tID]
    * @Return      java.lang.String
    */
    public String delete(String faceToken, int tID) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/face/delete";
            Map<String, Object> map = new HashMap<>();
            //图片的face_token
            // (注意是图片的face_token)
            // (注意是图片的face_token)
            // (注意是图片的face_token)
            map.put("face_token", faceToken);
            //用户组ID(固定)
            map.put("group_id", "user");
            //人脸所在的用户ID
            map.put("user_id", String.valueOf(tID));


        String resStr=callInterface.callInterface(url,map);
        //返回示例
        //删除成功
        //{
            //"error_code":0,
            //"error_msg":"SUCCESS",
            //"log_id":747956982081454821,
            //"timestamp":1568208145,
            //"cached":0,
            //"result":null
        //}
        //删除发生错误
        //{
            // "error_code":223106,
            // "error_msg":"face is not exist",
            // "log_id":747956982082127541,
            // "timestamp":1568208212,
            // "cached":0,
            // "result":null
        // }

        //将返回结果转换成Map形式
        Map<String,Object> resultMap=(HashMap<String,Object>)new JSONObject(resStr).toMap();

        String returnStr="fail";

        if(resultMap.get("error_code").toString().equals("0")){
            returnStr="success";
        }
        return returnStr;
    }


    /**
    * @Description 获取该用户下的所有脸部信息(不包括图片)  获取指定用户的第三方信息
    * @Author  ZhengJianHui
    * @Date   2019/9/12 19:31
    * @Param  [tID]
    * @Return      java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    */
    public List<Map<String,Object>> getFaceList(int tID) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/face/getlist";
        Map<String, Object> map = new HashMap<>();
        //用户组ID(固定)
        map.put("group_id", "user");
        //要查询所有人脸记录的用户ID    /*配置要去查找的第三方ID*/
        map.put("user_id", String.valueOf(tID));


        String resStr=callInterface.callInterface(url,map);//去第三方查找后的返值

        //将返回结果转换成Map形式
        Map<String,Object> map2=(HashMap<String,Object>)new JSONObject(resStr).toMap();

        //result集合
        Map<String,Object> result=(HashMap<String, Object>)map2.get("result");

        //faceList
        List<Map<String,Object>> faceList=null;
        try {
            faceList = (List<Map<String, Object>>) result.get("face_list");
        }catch (NullPointerException e){
            faceList=new ArrayList<>();
        }

        return faceList;

        //{
            // "error_code":0,
            // "error_msg":"SUCCESS",
            // "log_id":744193282872440562,
            // "timestamp":1568287244,
            // "cached":0,
            // "result":{
                // "face_list":[{
                        // "face_token":"a3a512d3bb9312370b585f411b2e8b24",
                        // "ctime":"2019-09-12 14:51:06"
                    // },{
                        // "face_token":"87d329b9bb62ab613b09d36a450a7606",
                        // "ctime":"2019-09-12 14:51:06"
                    // },{
                        // "face_token":"773aa2fa57f83db6695215b36a6e6b2f",
                        // "ctime":"2019-09-12 14:51:15"
                    // },{
                        // "face_token":"650434ad35c3ba80f56adf84615022a7",
                        // "ctime":"2019-09-12 14:51:23"
                    // },{
                        // "face_token":"f0dfff7d38bcd40ad1634ea3ac8051b3",
                        // "ctime":"2019-09-12 14:51:26"
                    // },{"
                        // face_token":"46957c038a68198b22f4124178c7ffde",
                        // "ctime":"2019-09-12 17:35:59"
                    // },{
                        // "face_token":"91560a678a92a7598ee3339cf90db784",
                        // "ctime":"2019-09-12 17:36:01"
                    // },{
                        // "face_token":"8d11504b4cec76bbbadc03c177566192",
                        // "ctime":"2019-09-12 17:36:02"
                    // },{
                        // "face_token":"37f138b4bc42132a265ecd81a12939f8",
                        // "ctime":"2019-09-12 17:36:07"
                // }]
            // }
        // }
    }
}

