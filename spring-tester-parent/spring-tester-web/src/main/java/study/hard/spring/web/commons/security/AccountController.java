package study.hard.spring.web.commons.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * A controller class which handles requests related to Account.
 * @author iandmyhand@gmail.com
 */
@Controller
@RequestMapping(value = "/account")
public class AccountController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final String FTL_INDEX = "/account/index";

	@RequestMapping
	public String list() {
		logger.debug("return page: {}", FTL_INDEX);
		return FTL_INDEX;
	}

}
