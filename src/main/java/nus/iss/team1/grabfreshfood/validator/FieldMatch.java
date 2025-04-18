// Done by Lewis
package nus.iss.team1.grabfreshfood.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * Custom annotation to validate that two fields in a class match.
 * Commonly used for password and confirm password validation.
 */
@Documented
@Target({ ElementType.TYPE }) // Annotation can be applied at the class level
@Retention(RetentionPolicy.RUNTIME) // Available at runtime for reflection
@Constraint(validatedBy = FieldMatchValidator.class) // Specifies the validator class to be used
public @interface FieldMatch {

    // Default validation message if no custom message is provided
    String message() default "Fields do not match";

    // Groups for grouping constraints (not commonly used in basic cases)
    Class<?>[] groups() default {};

    // Payload for clients of the Bean Validation API to assign custom payload objects
    Class<? extends Payload>[] payload() default {};

    // First field to compare
    String first();

    // Second field to compare
    String second();
}
