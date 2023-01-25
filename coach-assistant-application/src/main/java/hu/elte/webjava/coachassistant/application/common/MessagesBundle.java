package hu.elte.webjava.coachassistant.application.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

@Component
public class MessagesBundle {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessagesBundle.class);

    public String getString(String key) {
        return getValue(key);
    }

    public String getPattern(String key, Object... arguments) {
        String pattern = getValue(key);
        try {
            return MessageFormat.format(pattern, arguments);
        } catch (IllegalArgumentException e) {
            String msg = "Invalid arguments given or invalid pattern found for the given key: " + key;
            LOGGER.error(msg, e);
            return msg;
        }
    }

    private String getValue(String key) {
        try {
            ResourceBundle messages = getMessagesBundle();
            return messages.getString(key);
        } catch (MissingResourceException e) {
            String msg = "No value found with the given key: " + key;
            LOGGER.error(msg, e);
            return msg;
        }
    }

    private ResourceBundle getMessagesBundle() {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            return PropertyResourceBundle.getBundle(AppConst.MESSAGES_BUNDLE_NAME, locale);
        } catch (MissingResourceException e) {
            LOGGER.info("No messages bundle found for locale: " + locale.getDisplayName());
            return PropertyResourceBundle.getBundle(AppConst.MESSAGES_BUNDLE_NAME);
        }
    }
}
