package bookstore.annotations.fieldmatch;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String sourceField;
    private String targetField;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.sourceField = constraintAnnotation.source();
        this.targetField = constraintAnnotation.target();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            String sourceValue = BeanUtils.getProperty(value, sourceField);
            String targetValue = BeanUtils.getProperty(value, targetField);

            return (sourceValue == null && targetValue == null)
                    || (sourceValue != null && sourceValue.equals(targetValue));
        } catch (Exception e) {
            return false;
        }
    }
}
