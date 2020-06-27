package com.lsjbc.vdtts.utils.baidu.baiduTools.face;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: SmartGuard
 * @BelongsPackage: com.zjh.entity.method
 * @Author: 25940
 * @CreateTime: 2019-09-11 16:23
 * @Description: TFace表方法
 */
@Component
public class TFaceMethod {

//    @Autowired
//    private DateTools dateTools;

    /**
    * @Description 检查用户上传的人脸图片的后缀(只支持PNG、JPG、JPEG、BMP)
    * @Author  ZhengJianHui
    * @Date   2019/9/11 16:26
    * @Param  [file]
    * @Return      boolean
    */
    public boolean checkFaceExtension(MultipartFile file) {

        boolean result=true;

        //获取最后一个.的下标
        int index=file.getOriginalFilename().lastIndexOf(".");

        //如果有下标
        if (index>0){
            //获取文件的后缀名，并转换成小写
            String extension=file.getOriginalFilename().substring(index).toLowerCase();

            switch (extension){
                //如果符合格式的话，就判断正确
                case ".png":
                case ".jpg":
                case ".jpeg":
                case ".bmp":
                    result=true;
                    break;
                    default:
                        result = false;
            }

        } else {
            //没有下标就是不符合格式
            result=false;
        }

        return result;
    }

//    /**
//    * @Description 通过MultipartFile获取TFace对象
//    * @Author  ZhengJianHui
//    * @Date   2019/9/11 16:43
//    * @Param  [file, tID]
//    * @Return      com.zjh.entity.TFace
//    */
//    public TFace getObjectFromMultipartFile(MultipartFile file, int tID){
//        TFace tFace=new TFace();
//        tFace.setfUserID(tID);
//        tFace.settPath(tID+"_"+dateTools.getCurrentMillisecond()+"_"+file.getOriginalFilename());
//        return tFace;
//    }

    /**
     * @Description 拼接上人脸的上级目录
     * @Author  ZhengJianHui
     * @Date   2019/9/12 13:49
     * @Param  [faceList]
     * @Return      java.util.List<com.zjh.entity.TFace>
     */
    public List<Map<String,Object>> appendFatherFolder(List<Map<String,Object>> faceList){
        for (int i=0;i<faceList.size();i++){
            faceList.get(i).put("tPath","/face/"+String.valueOf(faceList.get(i).get("tPath")));
        }
        return faceList;
    }

    /**
    * @Description 获取用户的上传的表情文件
    * @Author  ZhengJianHui
    * @Date   2019/9/12 19:46
    * @Param  [tID]
    * @Return      java.util.List<java.io.File>
    */
    public List<File> getUserFaceFile(String fatherFolderPath,int tID){
        List<File> faceFileList=new ArrayList<>();
        File fatherFolder=new File(fatherFolderPath);
        String[] faceFileArray=fatherFolder.list();
        for(int i=0;i<faceFileArray.length;i++){
            String faceName=faceFileArray[i];

            if (faceName.startsWith(tID+"_")){
                faceFileList.add(new File(fatherFolder.getPath(),faceName));
            }
        }
        return faceFileList;
    }
}
