package top.coos.server.web.action;

import top.coos.server.web.handler.Request;
import top.coos.server.web.handler.Response;

/**
 * 请求处理接口<br>
 * 当用户请求某个Path，则调用相应Action的doAction方法
 *
 */
public interface Action {

	public void doAction(Request request, Response response);
}
