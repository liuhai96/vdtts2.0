package com.lsjbc.vdtts.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * @BelongsProject: SmartGuard
 * @BelongsPackage: com.zjh.utils.file
 * @Author: 25940
 * @CreateTime: 2019-07-28 10:20
 * @Description: 对文件进行操作的一些方法
 */
@Component
public class FileTools {

    public FileTools() {
    }

    /**
     * @Description 通过文件名去获取文件路径
     * @Author  ZhengJianHui
     * @Date   2019/7/19 18:14
     * @Param  [fileName]
     * @Return      java.lang.String
     */
    public String getFilePathByFileName(String fileName){
        //先根据文件的文件名去构建一个文件
        String path =this.getClass().getClassLoader().getResource(fileName).getPath();

        //根据查找到的路径去创建一个File对象
        File file=new File(path);

        //判断这个File对象是否存在
        //如果File对象存在，获取绝对路径用于后续读取XML文件
        //如果File对象不存在，路径设置为Error
        path=(file.exists())?file.getAbsolutePath().toString():"Error";

        return path;
    }

    /**
    * @Description 将Base64转为MultipartFile
    * @Author  ZhengJianHui
    * @Date   2019/7/31 18:18
    * @Param  [base64]
    * @Return      org.springframework.web.multipart.MultipartFile
    */
    public MultipartFile base64ToMultipartFile(String base64) {
        try {
            String[] baseStrs = base64.split(",");
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer(baseStrs[1]);//原值1，改为0
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            return new BASE64DecodedMultipartFile(b, baseStrs[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
    * @Description MultipartFile转File
    * @Author  ZhengJianHui
    * @Date   2019/9/4 21:00
    * @Param  [file]
    * @Return      java.io.File
    */
    public File multipartFileToFile(MultipartFile file) {
        File toFile = null;
        if(file.equals("")||file.getSize()<=0){
            file = null;
        }else {
            try {
                InputStream ins = null;
                ins = file.getInputStream();
                toFile = new File(file.getOriginalFilename());
                inputStreamToFile(ins, toFile);
                ins.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return toFile;

    }

    /**
    * @Description 从输入流读取文件
    * @Author  ZhengJianHui
    * @Date   2019/9/4 20:57
    * @Param  [ins, file]
    * @Return      void
    */
    public void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
    * @Description 将前端传过来的图片转换为二进制数组
    * @Author  ZhengJianHui
    * @Date   2019/9/8 22:47
    * @Param  [file]
    * @Return      byte[]
    */
    public byte[] multipartFileToBase64(MultipartFile file){
        byte[] imageByte= new byte[0];
        try {
            imageByte = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return imageByte;
        }
    }

    /**
    * @Description 把File文件转成Base64位
    * @Author  ZhengJianHui
    * @Date   2019/9/11 16:54
    * @Param  [file]
    * @Return      java.lang.String
    */
    public String encodeBase64File(File file) {
        FileInputStream inputFile = null;
        byte[] buffer=null;
        try {
            inputFile = new FileInputStream(file);
            buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new BASE64Encoder().encode(buffer);
    }

    /**
    * @Description 保存MultipartFile文件至本地
    * @Author  ZhengJianHui
    * @Date   2019/9/11 17:40
    * @Param  [multipartFile, folderName, fileName, request]
    * @Return      java.lang.Object
    */
    public File saveFileFromMultipartFile(MultipartFile multipartFile, String folderName,String fileName, HttpServletRequest request){

        File saveFile=null;


        //获取要保存文件夹的物理路径(绝对路径)
        File savePath = new File(request.getServletContext().getRealPath("/"+folderName+"/"));

        //测试此抽象路径名表示的文件或目录是否存在。若不存在，创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
        if(!savePath.exists()) {
            savePath.mkdirs();
        }
        //定义最后保存文件的地址
        saveFile=new File(savePath,fileName);

        //保存文件
        try {
            multipartFile.transferTo(saveFile);
        } catch (IOException e) {
            saveFile=null;
        } finally {
            return saveFile;
        }
    }

    /**
    * @Description 假如文件存在，就删除文件
    * @Author  ZhengJianHui
    * @Date   2019/9/11 18:04
    * @Param  [file]
    * @Return      void
    */
    public void deleteFileIfExist(File file){
        if (file.exists()){
            file.delete();
        }
    }

}
