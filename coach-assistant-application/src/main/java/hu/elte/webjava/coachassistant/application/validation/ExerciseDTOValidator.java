package hu.elte.webjava.coachassistant.application.validation;

import hu.elte.webjava.coachassistant.application.common.MessagesBundle;
import hu.elte.webjava.coachassistant.application.common.MsgKeys;
import hu.elte.webjava.coachassistant.application.exception.UnsupportedExerciseTypeException;
import hu.elte.webjava.coachassistant.application.exception.UnsupportedTimeUnitException;
import hu.elte.webjava.coachassistant.application.webdomain.ExerciseDTO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

public class ExerciseDTOValidator implements ConstraintValidator<ExerciseDTOConstraint, ExerciseDTO> {

    private static final String FIELD_NAME_REST = "rest";
    private static final String FIELD_NAME_REST_TIME_UNIT = "rest";
    private static final String FIELD_NAME_LENGTH = "length";
    private static final String FIELD_NAME_LENGTH_TIME_UNIT = "lengthTimeUnit";
    private static final String FIELD_NAME_REPETITIONS = "repetitions";
    private static final int REST_SECONDS_MIN = 1;
    private static final int REST_SECONDS_MAX = 180;
    private static final int REST_MINUTES_MIN = 1;
    private static final int REST_MINUTES_MAX = 10;
    private static final int LENGTH_SECONDS_MIN = 1;
    private static final int LENGTH_SECONDS_MAX = 180;
    private static final int LENGTH_MINUTES_MIN = 1;
    private static final int LENGTH_MINUTES_MAX = 60;
    private static final int REPETITIONS_MIN = 1;
    private static final int REPETITIONS_MAX = 30;
    private final MessagesBundle messages;

    @Autowired
    public ExerciseDTOValidator(MessagesBundle messages) {
        this.messages = messages;
    }

    @Override
    public boolean isValid(ExerciseDTO value, ConstraintValidatorContext context) {
        Map<String, String> fieldsAndMessageMap = new HashMap<>();
        boolean isValid = validateRest(value, fieldsAndMessageMap);

        switch (value.getExerciseType()) {
            case INTERVAL:
                isValid &= validateLength(value, fieldsAndMessageMap);
                break;
            case REPETITIVE:
                isValid &= validateRepetitions(value, fieldsAndMessageMap);
                break;
            default:
                throw new UnsupportedExerciseTypeException("Unsupported exercise type: " + value.getExerciseType());
        }

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            for (String fieldName : fieldsAndMessageMap.keySet()) {
                String message = fieldsAndMessageMap.get(fieldName);
                context.buildConstraintViolationWithTemplate(message)
                       .addPropertyNode(fieldName)
                       .addConstraintViolation();
            }
        }

        return isValid;
    }

    private boolean validateRest(ExerciseDTO value, Map<String, String> fieldsAndMessageMap) {
        if (! validateRestNotNull(value, fieldsAndMessageMap)) return false;

        switch (value.getRestTimeUnit()) {
            case SECONDS:
                return validateRestRange(value, REST_SECONDS_MIN, REST_SECONDS_MAX,
                        MsgKeys.VALIDATION_EXERCISE_REST_SECONDS_RANGE, fieldsAndMessageMap);
            case MINUTES:
                return validateRestRange(value, REST_MINUTES_MIN, REST_MINUTES_MAX,
                        MsgKeys.VALIDATION_EXERCISE_REST_MINUTES_RANGE, fieldsAndMessageMap);
            default:
                throw new UnsupportedTimeUnitException("Unsupported time unit: " + value.getRestTimeUnit());
        }
    }

    private boolean validateRestNotNull(ExerciseDTO value, Map<String, String> fieldsAndMessageMap) {
        boolean isValid = true;
        if (value.getRest() == null) {
            String messageTemplate = messages.getString(MsgKeys.VALIDATION_NOT_NULL);
            fieldsAndMessageMap.put(FIELD_NAME_REST, messageTemplate);
            isValid = false;
        }
        if (value.getRestTimeUnit() == null) {
            String messageTemplate = messages.getString(MsgKeys.VALIDATION_NOT_NULL);
            fieldsAndMessageMap.put(FIELD_NAME_REST_TIME_UNIT, messageTemplate);
            isValid = false;
        }
        return isValid;
    }

    private boolean validateRestRange(ExerciseDTO value, int min, int max, String messageTemplateKey,
                                      Map<String, String> fieldsAndMessageMap) {
        if (value.getRest() < min || value.getRest() > max) {
            String messageTemplate = messages.getPattern(messageTemplateKey, min, max);
            fieldsAndMessageMap.put(FIELD_NAME_REST, messageTemplate);
            return false;
        } else {
            return true;
        }
    }

    private boolean validateLength(ExerciseDTO value, Map<String, String> fieldsAndMessageMap) {
        if (!validateLengthNotNull(value, fieldsAndMessageMap)) return false;

        switch (value.getLengthTimeUnit()) {
            case SECONDS:
                return validateLengthRange(value, LENGTH_SECONDS_MIN, LENGTH_SECONDS_MAX,
                        MsgKeys.VALIDATION_EXERCISE_LENGTH_SECONDS_RANGE, fieldsAndMessageMap);
            case MINUTES:
                return validateLengthRange(value, LENGTH_MINUTES_MIN, LENGTH_MINUTES_MAX,
                        MsgKeys.VALIDATION_EXERCISE_LENGTH_MINUTES_RANGE, fieldsAndMessageMap);
            default:
                throw new UnsupportedTimeUnitException("Unsupported time unit: " + value.getLengthTimeUnit());
        }
    }

    private boolean validateLengthNotNull(ExerciseDTO value, Map<String, String> fieldsAndMessageMap) {
        boolean isValid = true;
        if (value.getLength() == null) {
            String messageTemplate = messages.getString(MsgKeys.VALIDATION_NOT_NULL);
            fieldsAndMessageMap.put(FIELD_NAME_LENGTH, messageTemplate);
            isValid = false;
        }
        if (value.getLengthTimeUnit() == null) {
            String messageTemplate = messages.getString(MsgKeys.VALIDATION_NOT_NULL);
            fieldsAndMessageMap.put(FIELD_NAME_LENGTH_TIME_UNIT, messageTemplate);
            isValid = false;
        }
        return isValid;
    }

    private boolean validateLengthRange(ExerciseDTO value, int min, int max, String messageTemplateKey,
                                        Map<String, String> fieldsAndMessageMap) {
        if (value.getLength() < min || value.getLength() > max) {
            String messageTemplate = messages.getPattern(messageTemplateKey, min, max);
            fieldsAndMessageMap.put(FIELD_NAME_LENGTH, messageTemplate);
            return false;
        } else {
            return true;
        }
    }

    private boolean validateRepetitions(ExerciseDTO value, Map<String, String> fieldsAndMessageMap) {
        if (!validateRepetitionsNotNull(value, fieldsAndMessageMap))
            return false;
        return validateRepetitionsRange(value, fieldsAndMessageMap);
    }

    private boolean validateRepetitionsNotNull(ExerciseDTO value, Map<String, String> fieldsAndMessageMap) {
        if (value.getRepetitions() == null) {
            String messageTemplate = messages.getString(MsgKeys.VALIDATION_NOT_NULL);
            fieldsAndMessageMap.put(FIELD_NAME_REPETITIONS, messageTemplate);
            return false;
        } else {
            return true;
        }
    }

    private boolean validateRepetitionsRange(ExerciseDTO value, Map<String, String> fieldsAndMessageMap) {
        if (value.getRepetitions() < REPETITIONS_MIN || value.getRepetitions() > REPETITIONS_MAX) {
            String messageTemplate = messages.getPattern(
                    MsgKeys.VALIDATION_EXERCISE_REPETITIONS_RANGE, REPETITIONS_MIN, REPETITIONS_MAX);
            fieldsAndMessageMap.put(FIELD_NAME_REPETITIONS, messageTemplate);
            return false;
        } else {
            return true;
        }
    }
}
