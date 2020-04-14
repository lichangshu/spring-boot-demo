package com.github.mirowww.boot.controller;

import com.github.mirowww.boot.exception.ExistException;
import com.github.mirowww.boot.exception.HttpRequestException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionTestController extends BasicController {

    @GetMapping("/api/ex-cause")
    public void test() {
        ExistException.UserNoExistException ex = new ExistException.UserNoExistException("zhangsan", "user name %s not exist", "zhangsan");
        throw new HttpRequestException(ex, ex.i18n(this.getMessageSource()));
    }

    @GetMapping("/api/ex-request")
    public void defException() {
        throw new HttpRequestException(this.i18n("not.find.resourecs", ExceptionTestController.class));
    }

    @GetMapping("/api/ex-runtime")
    public void defRunException() {
        throw new RuntimeException("Test runtime exception");
    }
}
