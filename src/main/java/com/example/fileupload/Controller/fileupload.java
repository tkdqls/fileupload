package com.example.fileupload.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class fileupload {
    @GetMapping("/")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("시작");
        ModelAndView mav = new ModelAndView("/index");
        return mav;
    }

    @PostMapping("/upload")
    public ModelAndView upload(@RequestParam("file")MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse response) throws Exception{
        String fileName = uploadFile.getOriginalFilename();
        String filePath = request.getSession().getServletContext().getRealPath("/images/");
        //ㅓ버에서 webapp의 위치 * images폴더를
        //fileupload/src/main/webapp/images
        try {
            uploadFile.transferTo(new File(filePath+fileName));
            System.out.println("이미지를 업로드 성공");
        } catch (IllegalStateException| IOException e){
            e.printStackTrace();
        }
        ModelAndView mav = new ModelAndView("/imageview");
        mav.addObject("file",fileName);
        return mav;
    }
}
