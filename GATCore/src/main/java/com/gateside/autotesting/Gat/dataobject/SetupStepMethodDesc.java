package com.gateside.autotesting.Gat.dataobject;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SetupStepMethodDesc
{
	public String description()default "";
	public String owner() default "";
}
