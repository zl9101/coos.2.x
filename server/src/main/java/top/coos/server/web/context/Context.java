package top.coos.server.web.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;

import top.coos.core.lang.Singleton;
import top.coos.logger.LogFactory;
import top.coos.server.web.action.Action;
import top.coos.server.web.annotation.Route;
import top.coos.server.web.exception.ServerSettingException;
import top.coos.server.web.filter.Filter;
import top.coos.server.web.handler.Request;
import top.coos.server.web.handler.Response;
import top.coos.util.StrUtil;

public abstract class Context {

	private static final Logger log = LogFactory.get();

	public abstract void service(Request request, Response response);

	/** Filter映射表 */
	private Map<String, Filter> filterMap = new ConcurrentHashMap<String, Filter>();
	/** Action映射表 */
	private Map<String, Action> actionMap = new ConcurrentHashMap<String, Action>();

	private final String contextPath;

	public Context(String contextPath) {

		this.contextPath = contextPath;
	}

	public String getContextPath() {

		return contextPath;
	}

	// -----------------------------------------------------------------------------------------------
	// Filter start
	/**
	 * @return 获取FilterMap
	 */
	public Map<String, Filter> getFilterMap() {

		return filterMap;
	}

	/**
	 * 获得路径对应的Filter
	 * 
	 * @param path
	 *            路径，为空时将获得 根目录对应的Action
	 * @return Filter
	 */
	public Filter getFilter(String path) {

		if (StrUtil.isBlank(path)) {
			path = StrUtil.SLASH;
		}
		return getFilterMap().get(path.trim());
	}

	/**
	 * 设置FilterMap
	 * 
	 * @param filterMap
	 *            FilterMap
	 */
	public void setFilterMap(Map<String, Filter> filterMap) {

		this.filterMap = filterMap;
	}

	/**
	 * 设置Filter类，已有的Filter类将被覆盖
	 * 
	 * @param path
	 *            拦截路径（必须以"/"开头）
	 * @param filter
	 *            Action类
	 */
	public void setFilter(String path, Filter filter) {

		if (StrUtil.isBlank(path)) {
			path = StrUtil.SLASH;
		}

		if (null == filter) {
			log.warn("Added blank action, pass it.");
			return;
		}
		// 所有路径必须以 "/" 开头，如果没有则补全之
		if (false == path.startsWith(StrUtil.SLASH)) {
			path = StrUtil.SLASH + path;
		}

		filterMap.put(path, filter);
	}

	/**
	 * 设置Filter类，已有的Filter类将被覆盖
	 * 
	 * @param path
	 *            拦截路径（必须以"/"开头）
	 * @param filterClass
	 *            Filter类
	 */
	public void setFilter(String path, Class<? extends Filter> filterClass) {

		setFilter(path, (Filter) Singleton.get(filterClass));
	}

	// -----------------------------------------------------------------------------------------------
	// Filter end

	// -----------------------------------------------------------------------------------------------
	// Action start
	/**
	 * @return 获取ActionMap
	 */
	public Map<String, Action> getActionMap() {

		return this.actionMap;
	}

	/**
	 * 获得路径对应的Action
	 * 
	 * @param path
	 *            路径，为空时将获得 根目录对应的Action
	 * @return Action
	 */
	public Action getAction(String path) {

		if (StrUtil.isBlank(path)) {
			path = StrUtil.SLASH;
		}
		return getActionMap().get(path.trim());
	}

	/**
	 * 设置ActionMap
	 * 
	 * @param actionMap
	 *            ActionMap
	 */
	public void setActionMap(Map<String, Action> actionMap) {

		this.actionMap = actionMap;
	}

	/**
	 * 设置Action类，已有的Action类将被覆盖
	 * 
	 * @param path
	 *            拦截路径（必须以"/"开头）
	 * @param action
	 *            Action类
	 */
	public void setAction(String path, Action action) {

		if (StrUtil.isBlank(path)) {
			path = StrUtil.SLASH;
		}

		if (null == action) {
			log.warn("Added blank action, pass it.");
			return;
		}
		// 所有路径必须以 "/" 开头，如果没有则补全之
		if (false == path.startsWith(StrUtil.SLASH)) {
			path = StrUtil.SLASH + path;
		}

		this.actionMap.put(path, action);
	}

	/**
	 * 增加Action类，已有的Action类将被覆盖<br>
	 * 所有Action都是以单例模式存在的！
	 * 
	 * @param path
	 *            拦截路径（必须以"/"开头）
	 * @param actionClass
	 *            Action类
	 */
	public void setAction(String path, Class<? extends Action> actionClass) {

		setAction(path, (Action) Singleton.get(actionClass));
	}

	/**
	 * 增加Action类，已有的Action类将被覆盖<br>
	 * 自动读取Route的注解来获得Path路径
	 * 
	 * @param action
	 *            带注解的Action对象
	 */
	public void setAction(Action action) {

		final Route route = action.getClass().getAnnotation(Route.class);
		if (route != null) {
			final String path = route.value();
			if (StrUtil.isNotBlank(path)) {
				setAction(path, action);
				return;
			}
		}
		throw new ServerSettingException("Can not find Route annotation,please add annotation to Action class!");
	}

	/**
	 * 增加Action类，已有的Action类将被覆盖<br>
	 * 所有Action都是以单例模式存在的！
	 * 
	 * @param actionClass
	 *            带注解的Action类
	 */
	public void setAction(Class<? extends Action> actionClass) {

		setAction((Action) Singleton.get(actionClass));
	}

	// -----------------------------------------------------------------------------------------------
	// Action start
}
