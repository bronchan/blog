package com.chan.controller.admin;

import com.chan.dto.ImageDto;
import com.chan.dto.WangEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author bronchan
 * @ClassName FileUpload
 * @date 2021/8/2 14:03
 * @Version 1.0
 * @Description 处理上传的文件如markdown编辑器的本地上传的图片
 */
@Controller
public class FileUpload {

    /**
    *@Author chenqitao
    *@Description 将图片保存在项目根目录下的upload文件夹下，并返回回调信息
    *@Date 2021/8/5 13:10
    *@Param [file]
    *@Return com.chan.dto.ImageDto
    */
    @RequestMapping("/img/upload")
    @ResponseBody
    public ImageDto saveImg(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file) {
        //获得SpringBoot当前项目的路径：System.getProperty("user.dir")
        String path = System.getProperty("user.dir")+"/upload";

        File realPath = new File(path);
        if (!realPath.exists()){
            boolean mkdir = realPath.mkdir();
            System.out.println(mkdir);
        }

        //上传文件地址
        System.out.println("上传文件保存地址："+realPath);
//        获取图片的类型
        String[] type = file.getContentType().split("/");
        //解决文件名字问题：我们使用uuid;
        String filename = "chan-"+ UUID.randomUUID().toString().replaceAll("-", "")+"."+type[type.length-1];
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        try {
            file.transferTo(new File(realPath +"/"+ filename));
        } catch (IOException e) {
            e.printStackTrace();
        }


        ImageDto img = new ImageDto();
        img.setUrl("/upload/"+ filename);
        img.setMessage("upload success!");
        img.setSuccess(1);

        return img;
    }


    /**
    *@Author chenqitao
    *@Description wangEditor的图片上传保存函数
    *@Date 2021/8/5 13:13
    *@Param [file]
    *@Return com.chan.dto.WangEditor
    */
    @RequestMapping("/img/upload2")
    @ResponseBody
    public WangEditor test(@RequestParam(value = "imgFile", required = true) MultipartFile[] file){
        //获得SpringBoot当前项目的路径：System.getProperty("user.dir")
        String path = System.getProperty("user.dir")+"/upload/";
        File realPath = new File(path);
        if (!realPath.exists()){
            realPath.mkdir();
        }

        //上传文件地址
        System.out.println("上传文件保存地址："+realPath);
        String[] filename = new String[file.length];
        for (int i = 0; i < file.length; i++) {
            //解决文件名字问题：我们使用uuid;
            filename[i] = "chan-"+ UUID.randomUUID().toString().replaceAll("-", "");
        }

        String[] resFilename = new String[file.length];

        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        try {
            for (int i = 0; i < file.length; i++) {
                String[] type = file[i].getContentType().split("/");
                file[i].transferTo(new File(realPath +"/"+ filename[i]+"."+type[type.length-1]));

                StringBuilder stringBuilder = new StringBuilder("/upload/");
                StringBuilder append = stringBuilder.append(filename[i]+"."+type[type.length-1]);
                resFilename[i] = String.valueOf(append);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        WangEditor wangEditor = new WangEditor(resFilename);
        return wangEditor;
    }
}
