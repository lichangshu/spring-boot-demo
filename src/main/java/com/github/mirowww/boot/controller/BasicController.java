package com.github.mirowww.boot.controller;

import com.github.mirowww.boot.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Locale;

public abstract class BasicController {
    @Resource
    private MessageSource messageSource;

    public static final ThreadLocal<User> local = new ThreadLocal<>();

    public String i18n(String code, Object... args) throws NoSuchMessageException {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, args, locale);
    }

    protected MessageSource getMessageSource() {
        return messageSource;
    }

    @ModelAttribute
    public void findUserByPrincipal(Principal principal) {
        if (principal != null) {
            //StsUser user = userService.getUserByPrincipal(principal);
            //if (user.isDisable()) {
            //    throw new HttpRequestException(this.i18n("user.is.disable"));
            //}
            //local.set(user);
        } else {
            local.remove();
        }
    }

}
