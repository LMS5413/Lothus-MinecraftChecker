package com.lmchecker.routers;

import com.lmchecker.MainApplication;
import com.lmchecker.functions.getRequestUUID;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class Routers implements ErrorController {
    @GetMapping("/profile/{uuid}")
    @ResponseBody
    public boolean checkUuid(@PathVariable("uuid") String uuid) throws IOException {
        if (!MainApplication.cache.containsKey(uuid)) {
            boolean isOriginal = getRequestUUID.validIsOriginal(uuid);
            return isOriginal;
        }
        return MainApplication.cache.get(uuid);
    }
    @GetMapping("/")
    @ResponseBody
    public String index() throws IOException {
        return "Hi, welcome to Lothus API Server. Created by LM$5413 and ToddyDeveloper.";
    }
    @GetMapping("/error")
    @ResponseBody
    public String handleError() throws IOException {
        return "An error occurred. Please, contact the administrator.";
    }
}
