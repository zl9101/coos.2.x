package top.coos.server.web.action;

import top.coos.server.web.handler.Request;
import top.coos.server.web.handler.Response;

/**
 * 默认的主页Action，当访问主页且没有定义主页Action时，调用此Action
 *
 */
public class DefaultIndexAction implements Action {

	@Override
	public void doAction(Request request, Response response) {

		response.setContent("Welcome to LoServer.");
	}

}