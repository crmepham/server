package com.server.frontendservice.controller;

import static org.springframework.util.StringUtils.hasText;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public void view() {}

    @ResponseBody
    @PostMapping(PATH + "/files")
    public List<File> files() throws InterruptedException, ExecutionException {
        val files = fileService.getAllImages();
        CompletableFuture.allOf(files);
        return files.get();
    }

    @PostMapping(PATH + "/image/{shortReference}")
    public String files(Model model, @PathVariable String shortReference) throws IOException {
        if (hasText(shortReference)) {
            val file = fileController.getFile(shortReference);
            if (file != null)
            {
                fileController.populateImageModel(model, file);
            }
        }
        return "/applications/viewer/image";
    }
}
