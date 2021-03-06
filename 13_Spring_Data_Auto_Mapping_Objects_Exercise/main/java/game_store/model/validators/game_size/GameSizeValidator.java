package game_store.model.validators.game_size;

import game_store.constants.ValidationMessages;
import game_store.model.validators.ValidatorsUtil;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

@Component
public class GameSizeValidator implements ConstraintValidator<GameSize, BigDecimal> {

    private int precision;
    private boolean nullable;
    private boolean negative;

    @Override
    public void initialize(GameSize gameSize) {
        this.nullable = gameSize.nullable();
        this.negative = gameSize.negative();
        this.precision = gameSize.precision();
    }

    @Override
    public boolean isValid(final BigDecimal number, final ConstraintValidatorContext context) {

        if (number == null) {
            return this.nullable;
        }

        if (number.remainder(BigDecimal.ONE).scale() > this.precision) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.GAME_SIZE_PRECISION_TOO_HIGH);
            return false;
        }

        if (!this.negative && number.compareTo(BigDecimal.ZERO) < 0) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.GAME_SIZE_NEGATIVE);
            return false;
        }

        return true;
    }
}
