package me.matthewe.atherial.api.command;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Matthew E on 1/1/2018.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    String name();
    String[] aliases() default {};
    String description() default "none";
    String usage() default "";
}
