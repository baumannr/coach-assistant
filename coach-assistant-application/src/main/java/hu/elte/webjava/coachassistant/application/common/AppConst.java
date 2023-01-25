package hu.elte.webjava.coachassistant.application.common;

public class AppConst {
    public static final String MESSAGES_BUNDLE_NAME = "messages";
    public static final String REGEXP_USER_NAME = "^[A-ZÁÉÍÓÖŐÚÜŰ][a-zA-Za-zA-ZáéíóöőúüűÁÉÍÓÖŐÚÜŰ]{1,49}$";
    public static final String REGEXP_PASSWORD =
            "^(?=.*[a-záéíóöőúüű])(?=.*[A-ZÁÉÍÓÖŐÚÜŰ])(?=.*\\d)[a-zA-ZáéíóöőúüűÁÉÍÓÖŐÚÜŰ\\d\\w\\W]{8,20}$";
    public static final String REGEXP_TRAINING_PLAN_NAME = "^[a-záéíóöőúüűA-ZÁÉÍÓÖŐÚÜŰ\\d _-]*$";
    public static final String ATTRIBUTE_NAME_ERROR_MESSAGE = "errorMessage";
    public static final String MESSAGE_SUCCESS = "messageSuccess";
    public static final String MESSAGE_WARNING = "messageWarning";
}
