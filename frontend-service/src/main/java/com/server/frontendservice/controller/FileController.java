package com.server.frontendservice.controller;

import com.server.common.model.File;
import com.server.common.model.InputResult;
import com.server.common.service.FileService;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.lang.String.format;
import static org.springframework.util.StringUtils.hasText;

@Controller
public class FileController extends BaseController
{
    private static final String PATH = "applications/files";

    @Autowired
    private FileService fileService;

    @GetMapping(PATH)
    public void files(Model model) throws ExecutionException, InterruptedException
    {
        CompletableFuture<List<File>> files = fileService.getAll();

        CompletableFuture.allOf(files).join();

        model.addAttribute("files", files.get());
        model.addAttribute("styles", Arrays.asList("data-tables", "data-tables/files", "font-awesome.min"));
        model.addAttribute("sheets", Arrays.asList("data-tables", "font-awesome.min"));
    }

    @GetMapping("applications/uploader")
    public void uploader() {}

    @GetMapping("applications/files/{id}")
    public String file(Model model, @PathVariable("id") long id) throws ExecutionException, InterruptedException {

        CompletableFuture<File> file = fileService.getById(id);

        CompletableFuture.allOf(file).join();

        model.addAttribute("fileExists", hasText(file.get().getAbsolutePath()) ? fileService.exists(file.get()) : true);
        model.addAttribute("item", file.get());
        model.addAttribute("types", getTypes());
        return "/applications/files/edit";
    }

    @GetMapping(value = "applications/files/create")
    public String createView(Model model)
    {
        model.addAttribute("item", new File());
        model.addAttribute("types", getTypes());

        return "/applications/files/edit";
    }

    @GetMapping(value = "applications/files/{id}/delete/file")
    public String deleteFile(Model model, RedirectAttributes redirect, @PathVariable("id") long id) throws ExecutionException, InterruptedException
    {
        CompletableFuture<File> fileMeta = fileService.getById(id);

        CompletableFuture.allOf(fileMeta).join();

        fileService.delete(fileMeta.get());

        model.addAttribute("item", fileMeta);
        model.addAttribute("types", getTypes());

        toast(format("Successfully deleted file at %s", fileMeta.get().getAbsolutePath()), redirect);

        fileMeta.get().setAbsolutePath(null);
        fileService.update(fileMeta.get());

        return format("redirect:/applications/files/%s", fileMeta.get().getId());
    }

    @PostMapping(value = "applications/files/update")
    public String create(Model model,
                         @ModelAttribute("file") File file,
                         RedirectAttributes redirect) throws ExecutionException, InterruptedException
    {
        final boolean isNew = file.getId() == null;

        fileService.update(file);

        if (isNew)
        {
            CompletableFuture<List<File>> all = fileService.getAll();
            CompletableFuture.allOf(all).join();
            Collections.reverse(all.get());
            file = all.get().iterator().next();

        }

        model.addAttribute("item", file);
        model.addAttribute("types", getTypes());

        toast(format("Successfully %s file", isNew ? "created" : "updated"), redirect);

        return format("redirect:/applications/files/%s", file.getId());
    }

    @GetMapping(value = "applications/files/{id}/delete")
    public String delete(@PathVariable("id") long id,
                         RedirectAttributes redirect) throws ExecutionException, InterruptedException
    {
        fileService.delete(id);

        toast("Successfully deleted file", redirect);

        return "redirect:/applications/files";
    }

    @PostMapping("applications/files/{id}/upload")
    public String handleFileUpload(@PathVariable("id") long id,
                                   @RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirect) throws ExecutionException, InterruptedException {

        CompletableFuture<File> fileMeta = fileService.getById(id);

        CompletableFuture.allOf(fileMeta).join();

        boolean success = fileService.store(file, fileMeta.get());

        final String message = success ? "Successfully uploaded file" : "Failed to upload file. Ensure the file and " +
                "the path for the specified type exist and have valid permissions.";

        toast(message, redirect);

        return format("redirect:/applications/files/%s", id);
    }

    @PostMapping("applications/files/multiple/upload")
    public String uploadMultiple(@RequestParam("files") MultipartFile[] files,
                                 @RequestParam("pathSuffix") String pathSuffix,
                                   RedirectAttributes redirect) throws IOException
    {

        if (files.length <= 0) {
            toast("No files were selected for upload.", redirect);
            return "redirect:/applications/uploader";
        }

        final InputResult result = fileService.store(files, pathSuffix);

        final String message = result.isSuccess() ? "Successfully uploaded " + result.getTotalCount() + " file(s)." : result.getFailureReason();

        toast(message, redirect);

        return "redirect:/applications/files";
    }

    @GetMapping("applications/files/download/{externalReference}")
    public void download(HttpServletResponse response, @PathVariable String externalReference)
            throws Exception
    {
        File file = fileService.getByExternalReference(externalReference);
        java.io.File fileData = new java.io.File(file.getAbsolutePath());
        FileInputStream inputStream = new FileInputStream(fileData);

        response.setContentType("application/octet-stream");
        response.setContentLength((int)fileData.length());

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", new Object[] { fileData.getName() });
        response.setHeader(headerKey, headerValue);

        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }

    @ResponseBody
    @GetMapping("/file/{externalReference}")
    public byte[] getFileData(@PathVariable String externalReference) throws Exception
    {
        File file = fileService.getByExternalReference(externalReference);
        java.io.File fileData = new java.io.File(file.getAbsolutePath());
        FileInputStream is = new FileInputStream(fileData);

        return FileUtils.readFileToByteArray(fileData);
    }

    private List<String> getTypes()
    {
        return Arrays.asList(getPropertyService().getByExternalReference("file_types").getValue().split(","));

    }
}
