package com.lsjbc.vdtts.service.intf;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public interface sfzService
{
    String sfzdiscern(HttpServletRequest request, HttpServletResponse response, MultipartFile file);
}
