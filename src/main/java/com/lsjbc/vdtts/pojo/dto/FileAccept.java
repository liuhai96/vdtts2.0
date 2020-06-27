package com.lsjbc.vdtts.pojo.dto;

/**
 * @author LiLang9725
 * @date 2020/6/11 17:00
 */
import lombok.*;
import java.io.File;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FileAccept {
    /*
    * 文件名称
    * */
    private String fName;
    /*
     * 文件大小
     * */
    private Long fSize;

    /*
     * 文件类型
     * */
    private String fType;
    /*
     * 传输时间
     * */
    private String fProTime;
    /*
     * 传输结果
     * */
    private String fProResult;
    /*
     * 文件存放路径
     * */
    private String fPath;


    public final static String IMG_PATH_PREFIX = "image";
    public static File getImgDirFile(String path){// 构建上传文件的存放 “文件夹” 路径
        String fileDirPath = null;
        if(path == null || path.equals("")) path = IMG_PATH_PREFIX;
        fileDirPath = new String("src/main/webapp/" + path);
        File fileDir = new File(fileDirPath);
        if(!fileDir.exists())// 递归生成文件夹
            fileDir.mkdirs();
        return fileDir;
    }
}
