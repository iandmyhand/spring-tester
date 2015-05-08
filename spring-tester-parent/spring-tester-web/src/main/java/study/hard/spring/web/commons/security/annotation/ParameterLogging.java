package study.hard.spring.web.commons.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * finds Below data : <br />
 * 		1) Parameters requested through the HttpServletRequest<br />
 * 		2) Parameters marked @PathVariable .<br />
 * 
 * @author iandmyhand@gmail.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ParameterLogging{

}
