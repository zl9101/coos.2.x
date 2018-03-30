package top.coos.server.web.handler;

import top.coos.core.lang.Singleton;
import top.coos.server.web.ServerSetting;
import top.coos.server.web.action.Action;
import top.coos.server.web.action.ErrorAction;
import top.coos.server.web.action.FileAction;
import top.coos.server.web.context.Context;
import top.coos.server.web.filter.Filter;

public class WebContext extends Context {

	public WebContext(String contextPath) {

		super(contextPath);
	}

	public void service(Request request, Response response) {

		try {
			// do filter
			boolean isPass = this.doFilter(request, response);

			if (isPass) {
				// do action
				this.doAction(request, response);
			}
		} catch (Exception e) {
			Action errorAction = getAction(ServerSetting.MAPPING_ERROR);
			request.putParam(ErrorAction.ERROR_PARAM_NAME, e);
			errorAction.doAction(request, response);
		}

		// 如果发送请求未被触发，则触发之，否则跳过。
		if (false == response.isSent()) {
			response.send();
		}
	}

	// ----------------------------------------------------------------------------------------
	// Private method start
	/**
	 * 执行过滤
	 * 
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @param 是否继续
	 */
	private boolean doFilter(Request request, Response response) {

		// 全局过滤器
		Filter filter = getFilter(ServerSetting.MAPPING_ALL);
		if (null != filter) {
			if (false == filter.doFilter(request, response)) {
				return false;
			}
		}

		// 自定义Path过滤器
		filter = getFilter(request.getPath());
		if (null != filter) {
			if (false == filter.doFilter(request, response)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 执行Action
	 * 
	 * @param request
	 *            请求对象
	 * @param response
	 *            响应对象
	 */
	private void doAction(Request request, Response response) {

		Action action = getAction(request.getPath());
		if (null == action) {
			// 查找匹配所有路径的Action
			action = getAction(ServerSetting.MAPPING_ALL);
			if (null == action) {
				// 非Action方法，调用静态文件读取
				action = Singleton.get(FileAction.class);
			}
		}

		action.doAction(request, response);
	}
	// ----------------------------------------------------------------------------------------
	// Private method start
}
