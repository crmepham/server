package com.server.frontendservice.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.server.common.model.File;
import com.server.common.service.FileService;
import lombok.val;

@Controller
public class ViewerController extends BaseController {
    private static final String PATH = "applications/viewer";

    @Autowired
    private FileService fileService;

    @Autowired
    private FileController fileController;

    @GetMapping(PATH)
    public void view(Model model) {
        js(model, "viewer");
        css(model, "viewer");
    }

    @PostMapping(PATH + "/{type}/page/{page}")
    public String page(Model model, HttpServletResponse response, @PathVariable String type, @PathVariable Integer page) throws IOException {
        val files = fileService.getFiles(type, page);
        if (files.isEmpty()) {
            response.setStatus(405);
        } else {
            val filesMap = new HashMap<File, String>();
            for (val f : files) {
                val fileData = new java.io.File(f.getAbsolutePath() == null ? "." : f.getAbsolutePath());
                val encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(fileData));
                filesMap.put(f, new String(encoded));
            }

            model.addAttribute("images", filesMap);
        }
        return "/applications/viewer/images";
    }
}
