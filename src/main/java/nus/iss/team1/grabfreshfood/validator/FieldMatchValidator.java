package nus.iss.team1.grabfreshfood.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;

/**
 * Validator class for the @FieldMatch annotation.
 * Compares two fields of an object and returns true if they match.
 */
//Done by Lewis
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    // Names of the fields to compare
    private String firstFieldName;
    private String secondFieldName;

    /**
     * Initializes the validator with the field names provided in the annotation.
     */
    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.firstFieldName = constraintAnnotation.first();
        this.secondFieldName = constraintAnnotation.second();
    }

    /**
     * Checks if the two specified fields in the object have equal values.
     *
     * @param value   The object being validated (e.g., DTO)
     * @param context Context in which the constraint is evaluated
     * @return true if fields match, false otherwise
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            // Use BeanUtils to get the property values by name
            String first = BeanUtils.getProperty(value, firstFieldName);
            String second = BeanUtils.getProperty(value, secondFieldName);

            // Both fields must not be null and must be equal
            return first != null && first.equals(second);
        } catch (Exception e) {
            // In case of reflection or access issues, treat as invalid
            return false;
        }
    }
}
