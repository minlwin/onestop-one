package com.jdc.onestop.balance.api.advice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.jdc.onestop.balance.model.BalanceAppValidationException;

@Aspect
@Component
public class BindingResultAdvice {

	@Pointcut("args(..,org.springframework.validation.BindingResult)")
	void validationMethod() {}
		
	@Pointcut("within(com.jdc..api.*)")
	void apiClasses() {}
	
	@Before(argNames = "result", 
			value = "apiClasses() && validationMethod() && args(result)")
	void checkBindingResult(BindingResult result) {
		if(result.hasErrors()) {
			throw new BalanceAppValidationException(
					result.getFieldErrors()
					.stream().map(FieldError::getDefaultMessage)
					.toList());
		}
	}
}
