package com.server.frontendservice.controller;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

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

    @GetMapping(PATH)
    public void view(Model model) {
        js(model, "viewer");
        css(model, "viewer");
        model.addAttribute("years", getYears());
    }

    private List<Integer> getYears()
    {
        val years = IntStream.rangeClosed(2017, Calendar.getInstance().get(Calendar.YEAR))
                        .boxed()
                        .collect(toList());
        Collections.reverse(years);
        return years;
    }

    @PostMapping(PATH + "/{type}/{year}/{page}")
    public String page(Model model,
                       HttpServletResponse response,
                       @PathVariable String type,
                       @PathVariable String year,
                       @PathVariable Integer page) throws IOException {
        val files = fileService.getFiles(type, year, page);
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
