package study.hard.spring.web.commons.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 이 어노테이션이 지정된 메서드로 request가 오는 경우 IIMS2 보안로그서버로 접속 로그가 전송된다. 
 * 실제로 데이터를 보지않는 경우(index 페이지 조회 등)는 로깅하지 않도록 한다.<br />
 * <br />
 * 수집하는 로그는 다음과 같다: <br />
 * 		1) HttpServletRequest객체를 통해 전달되는 form데이터 중 value가 null이 아닌 데이터들<br />
 * 		2) @PathVariable 어노테이션을 사용해 파라미터로 사용하는 데이터
 * 
 * @author iandmyhand@gmail.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ParameterLogging{

}
