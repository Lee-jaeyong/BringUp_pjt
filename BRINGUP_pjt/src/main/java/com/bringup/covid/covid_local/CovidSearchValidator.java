package com.bringup.covid.covid_local;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CovidSearchValidator implements Validator {

	@Override
	public void validate(Object target, Errors errors) {
		CovidSearch codivSearch = (CovidSearch) target;
		long end = codivSearch.getEnd().getTime();
		long start = 1586530800000L;
		if (start > end) {
			errors.rejectValue("end", "400");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
