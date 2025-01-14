package com.bravo.user.localization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Localization of application messages.
 */
@Component
public class MessageHandler {

    private final MessageSource messageSource;

    @Autowired
    public MessageHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Provides a message for the given message key.
     *
     * @param msgKey message key which to lookup to get a localized message
     * @param args   arguments that will be filled in for params (in format like
     *               "{0}", "{1,date}", "{2,time}") within the message
     * @return a localized message from the message source
     */
    public String localizeMessage(String msgKey, Object... args) {
        return messageSource.getMessage(msgKey, args, msgKey, LocaleContextHolder.getLocale());
    }
}
