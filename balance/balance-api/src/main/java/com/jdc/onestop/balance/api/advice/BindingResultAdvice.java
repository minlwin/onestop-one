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

	@Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
	public void restControllerMethods() {}
		
	@Before(value = "restControllerMethods() && args(..,result)", argNames = "result")
	public void check(BindingResult result) {
		if(result.hasErrors()) {
			throw new BalanceAppValidationException(result.getFieldErrors().stream()
					.map(FieldError::getDefaultMessage).toList());
		}
	}
}
