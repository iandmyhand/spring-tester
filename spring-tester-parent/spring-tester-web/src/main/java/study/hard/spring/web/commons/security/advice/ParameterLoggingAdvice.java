package study.hard.spring.web.commons.security.advice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import study.hard.spring.web.commons.security.annotation.ParameterLogging;

/**
 * This class handles whole parameters which are requested through the HTTP request and the URL.
 * @author iandmyhand@gmail.com
 */
public class ParameterLoggingAdvice implements MethodInterceptor {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public Object invoke(MethodInvocation invocation) throws Throwable {

		ParameterLogging annotation = invocation.getMethod().getAnnotation(ParameterLogging.class);
		if (annotation != null && annotation instanceof ParameterLogging) {

			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)(RequestContextHolder.getRequestAttributes());
			HttpServletRequest request = null;
			if (servletRequestAttributes != null) {
				request = servletRequestAttributes.getRequest();
			}
			if (request == null) {
				logger.error("Failed to get HttpServletRequest.");
				return invocation.proceed();
			}

			Map<String, Object> parameterMap = new HashMap<String, Object>();

			addParametersInsideOfPathVariable(parameterMap, invocation);

			addParametersInsideOfHttpServletRequest(parameterMap, request);

			storeLogs(parameterMap);
		}

		return invocation.proceed();
	}

	/**
	 * This method finds parameters marked 'PathVariable' annotation.
	 * @param parametarMap
	 * @param invocation
	 */
	private void addParametersInsideOfPathVariable(Map<String, Object> parametarMap, MethodInvocation invocation) {
		Object[] methodArguments = invocation.getArguments();
		Method method = invocation.getMethod();

		Annotation[][] argumentAnnotations = method.getParameterAnnotations();
		for (int index = 0; index < argumentAnnotations.length; index++) {
			Annotation[] annotations = argumentAnnotations[index];
			for (int j = 0; j < annotations.length; j++) {
				Annotation argumentAnnotation = annotations[j];
				if (argumentAnnotation != null && argumentAnnotation instanceof PathVariable) {
					MethodParameter parameter = new MethodParameter(method, index);
					ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
					parameter.initParameterNameDiscovery(parameterNameDiscoverer);
					String parameterName = parameter.getParameterName();
					String methodArgument = methodArguments[index].toString();
					if (StringUtils.isNotBlank(methodArgument)) {
						parametarMap.put(parameterName, methodArgument);
					}
				}
			}
		}
	}

	/**
	 * This method finds parameters requested through the HTTP requests.
	 * @param parametarMap
	 * @param request
	 */
	private void addParametersInsideOfHttpServletRequest(Map<String, Object> parametarMap, HttpServletRequest request) {
		@SuppressWarnings("unchecked") Enumeration<String> paramNames = request.getParameterNames();

		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement().toString();
			String[] uncheckedParamValueList = request.getParameterValues(paramName);
			List<String> paramValueList = new ArrayList<String>();
			for (String paramValue : uncheckedParamValueList) {
				if (StringUtils.isNotBlank(paramValue)) {
					paramValueList.add(paramValue);
				}
			}
			if (0 < paramValueList.size()) {
				parametarMap.put(paramName, paramValueList);
			}
		}
	}

	/**
	 * This method stores logs.
	 * @param parameterMap
	 */
	private void storeLogs(Map<String, Object> parameterMap) {
		// Storing logs...
		logger.info(parameterMap.toString());
	}

}
