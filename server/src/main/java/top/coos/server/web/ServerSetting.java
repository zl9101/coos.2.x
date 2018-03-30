package top.coos.server.web;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;

import top.coos.core.io.FileUtil;
import top.coos.log.LogFactory;
import top.coos.server.web.context.Context;
import top.coos.server.web.exception.ServerSettingException;
import top.coos.util.StrUtil;

/**
 * 全局设定文件
 * 
 * @author xiaoleilu
 *
 */
public class ServerSetting {

	private static final Logger log = LogFactory.get();

	public static final String ROOT_CONTEXT = "/ROOT";

	// -------------------------------------------------------- Default value
	// start
	/** 默认的字符集编码 */
	public final static String DEFAULT_CHARSET = "utf-8";

	public final static String MAPPING_ALL = "/*";

	public final static String MAPPING_ERROR = "/_error";
	// -------------------------------------------------------- Default value
	// end

	/** 字符编码 */
	private static String charset = DEFAULT_CHARSET;

	/** 端口 */
	private static int port = 8090;

	/** 根目录 */
	private static File root;

	private static Map<String, Context> contextMap = new ConcurrentHashMap<String, Context>();

	/**
	 * @return 获取编码
	 */
	public static String getCharset() {

		return charset;
	}

	/**
	 * @return 字符集
	 */
	public static Charset charset() {

		return Charset.forName(getCharset());
	}

	/**
	 * 设置编码
	 * 
	 * @param charset
	 *            编码
	 */
	public static void setCharset(String charset) {

		ServerSetting.charset = charset;
	}

	/**
	 * @return 监听端口
	 */
	public static int getPort() {

		return port;
	}

	/**
	 * 设置监听端口
	 * 
	 * @param port
	 *            端口
	 */
	public static void setPort(int port) {

		ServerSetting.port = port;
	}

	// -----------------------------------------------------------------------------------------------
	// Root start
	/**
	 * @return 根目录
	 */
	public static File getRoot() {

		return root;
	}

	/**
	 * @return 根目录
	 */
	public static boolean isRootAvailable() {

		if (root != null && root.isDirectory() && root.isHidden() == false && root.canRead()) {
			return true;
		}
		return false;
	}

	/**
	 * @return 根目录
	 */
	public static String getRootPath() {

		return FileUtil.getAbsolutePath(root);
	}

	/**
	 * 根目录
	 * 
	 * @param root
	 *            根目录绝对路径
	 */
	public static void setRoot(String root) {

		ServerSetting.root = FileUtil.mkdir(root);
		log.debug("Set root to [{}]", ServerSetting.root.getAbsolutePath());
	}

	/**
	 * 根目录
	 * 
	 * @param root
	 *            根目录绝对路径
	 */
	public static void setRoot(File root) {

		if (root.exists() == false) {
			root.mkdirs();
		} else if (root.isDirectory() == false) {
			throw new ServerSettingException(StrUtil.format("{} is not a directory!", root.getPath()));
		}
		ServerSetting.root = root;
	}

	// -----------------------------------------------------------------------------------------------
	// Root end

	/**
	 * 获得路径对应的Context
	 * 
	 * @param contextPath
	 * 
	 * @return Action
	 */
	public static Context getContext(String contextPath) {

		if (StrUtil.isBlank(contextPath)) {
			contextPath = StrUtil.SLASH;
		}
		return getContextMap().get(contextPath.trim());
	}

	/**
	 * 获得路径对应的Context
	 * 
	 * @param contextPath
	 * 
	 * @return Action
	 */
	public static Context getRootContext() {

		return getContextMap().get(ROOT_CONTEXT);
	}

	public static void addContext(Context context) {

		if (null == context) {
			log.warn("Added blank context, pass it.");
			return;
		}
		String contextPath = context.getContextPath();
		if (StrUtil.isBlank(contextPath)) {
			contextPath = ROOT_CONTEXT;
		}

		// 所有路径必须以 "/" 开头，如果没有则补全之
		if (false == contextPath.startsWith(StrUtil.SLASH)) {
			contextPath = StrUtil.SLASH + contextPath;
		}

		contextMap.put(contextPath, context);
	}

	public static Map<String, Context> getContextMap() {

		return contextMap;
	}

}
