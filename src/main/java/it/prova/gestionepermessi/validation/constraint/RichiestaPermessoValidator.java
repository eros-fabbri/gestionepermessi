package it.prova.gestionepermessi.validation.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import it.prova.gestionepermessi.dto.RichiestaPermessoInsertDTO;


public class RichiestaPermessoValidator implements ConstraintValidator<RichiestaPermessoValid, RichiestaPermessoInsertDTO> {

	@Override
	public void initialize(RichiestaPermessoValid constraintAnnotation) {
	}

	@Override
	public boolean isValid(RichiestaPermessoInsertDTO richiestaPermesso, ConstraintValidatorContext context) {
		
		if (richiestaPermesso == null) {
			return true;
		}
		
		boolean isValid = true;
		
		
		
		
		return isValid;
	}
}
