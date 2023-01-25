package hu.elte.webjava.coachassistant.application.common;

public class Views {

    private static final String PREFIX_ACCOUNT = "/account";
    private static final String PREFIX_CLIENT_DASHBOARD = "/client-dashboard";
    private static final String PREFIX_COACH_DASHBOARD = "/coach-dashboard";
    private static final String PREFIX_CLIENT_PROFILE = "/client-profile";
    private static final String PREFIX_COACH_PROFILE = "/coach-profile";
    private static final String PREFIX_CALORIE_DIARY = "/calorie-diary";
    private static final String PREFIX_WEIGHT_DIARY = "/weight-diary";
    private static final String PREFIX_TRAINING_PLAN = "/training-plan";
    private static final String PREFIX_EXERCISE = "/exercise";
    private static final String PREFIX_CLIENT_DETAILS = "/client-details";
    private static final String REDIRECT = "redirect:";

    public static final String ERROR = "error";
    public static final String ERROR_400 = "error-400";
    public static final String ERROR_404 = "error-404";
    public static final String HOME = "/home";
    public static final String GET_LOGIN = PREFIX_ACCOUNT + "/login";
    public static final String REDIRECT_LOGIN = REDIRECT + GET_LOGIN;
    public static final String REGISTER = PREFIX_ACCOUNT + "/register";
    public static final String REDIRECT_REGISTER = REDIRECT + REGISTER;
    public static final String CLIENT_DASHBOARD_INDEX = PREFIX_CLIENT_DASHBOARD + "/index";
    public static final String REDIRECT_CLIENT_DASHBOARD_INDEX = REDIRECT + CLIENT_DASHBOARD_INDEX;
    public static final String CLIENT_PROFILE_INDEX = PREFIX_CLIENT_PROFILE + "/index";
    public static final String REDIRECT_CLIENT_PROFILE_INDEX = REDIRECT + CLIENT_PROFILE_INDEX;
    public static final String CLIENT_PROFILE_EDIT = PREFIX_CLIENT_PROFILE + "/edit";
    public static final String COACH_PROFILE_INDEX = PREFIX_COACH_PROFILE + "/index";
    public static final String REDIRECT_COACH_PROFILE_INDEX = REDIRECT + COACH_PROFILE_INDEX;
    public static final String COACH_PROFILE_EDIT = PREFIX_COACH_PROFILE + "/edit";
    public static final String CLIENT_DETAILS_INDEX = PREFIX_CLIENT_DETAILS + "/index";
    public static final String CALORIE_DIARY_INDEX = PREFIX_CALORIE_DIARY + "/index";
    public static final String REDIRECT_CALORIE_DIARY_INDEX = REDIRECT + CALORIE_DIARY_INDEX;
    public static final String CALORIE_DIARY_CREATE = PREFIX_CALORIE_DIARY + "/create";
    public static final String CALORIE_DIARY_EDIT = PREFIX_CALORIE_DIARY + "/edit";
    public static final String WEIGHT_DIARY_INDEX = PREFIX_WEIGHT_DIARY + "/index";
    public static final String REDIRECT_WEIGHT_DIARY_INDEX = REDIRECT + WEIGHT_DIARY_INDEX;
    public static final String WEIGHT_DIARY_CREATE = PREFIX_WEIGHT_DIARY + "/create";
    public static final String WEIGHT_DIARY_EDIT = PREFIX_WEIGHT_DIARY + "/edit";
    public static final String COACH_DASHBOARD_INDEX = PREFIX_COACH_DASHBOARD + "/index";
    public static final String REDIRECT_COACH_DASHBOARD_INDEX = REDIRECT + COACH_DASHBOARD_INDEX;
    public static final String TRAINING_PLAN_INDEX = PREFIX_TRAINING_PLAN + "/index";
    public static final String REDIRECT_TRAINING_PLAN_INDEX = REDIRECT + TRAINING_PLAN_INDEX;
    public static final String TRAINING_PLAN_DETAILS = PREFIX_TRAINING_PLAN + "/details";
    public static final String REDIRECT_TRAINING_PLAN_DETAILS = REDIRECT + TRAINING_PLAN_DETAILS + "/";
    public static final String TRAINING_PLAN_CREATE = PREFIX_TRAINING_PLAN + "/create";
    public static final String TRAINING_PLAN_EDIT = PREFIX_TRAINING_PLAN + "/edit";
    public static final String EXERCISE_CREATE = PREFIX_EXERCISE + "/create";
    public static final String EXERCISE_EDIT = PREFIX_EXERCISE + "/edit";
}
