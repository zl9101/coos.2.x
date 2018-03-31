package top.coos.server.web.handler;

import java.io.IOException;

import org.slf4j.Logger;

import top.coos.logger.LogFactory;
import top.coos.server.web.ServerSetting;
import top.coos.server.web.action.Action;
import top.coos.server.web.action.ErrorAction;
import top.coos.server.web.context.Context;
import top.coos.util.StrUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * Action处理单元
 */
public class ContextHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

	private static final Logger log = LogFactory.get();

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest) throws Exception {

		final Request request = Request.build(ctx, fullHttpRequest);
		final Response response = Response.build(ctx, request);

		try {
			String path = request.getPath();
			String contextPath = path;
			if (StrUtil.count(path, "/") > 1) {
				contextPath = path.substring(0, path.indexOf("/", 1));
			}
			Context context = ServerSetting.getContext(contextPath);
			System.out.println(contextPath);
			System.out.println(context);
			System.out.println(ServerSetting.getContextMap().keySet());
			if (context == null) {
				context = ServerSetting.getRootContext();
			} 
			if (context == null) {
				Action errorAction = new ErrorAction();
				errorAction.doAction(request, response);
			} else {
				request.setContext(context);
				context.service(request, response);
			}

		} catch (Exception e) {
			Action errorAction = new ErrorAction();
			request.putParam(ErrorAction.ERROR_PARAM_NAME, e);
			errorAction.doAction(request, response);
		}

		// 如果发送请求未被触发，则触发之，否则跳过。
		if (false == response.isSent()) {
			response.send();
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		if (cause instanceof IOException) {
			log.warn("{}", cause.getMessage());
		} else {
			super.exceptionCaught(ctx, cause);
		}
	}

}
