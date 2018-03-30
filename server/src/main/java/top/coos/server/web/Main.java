package top.coos.server.web;

import top.coos.server.web.action.Action;
import top.coos.server.web.context.Context;
import top.coos.server.web.handler.Request;
import top.coos.server.web.handler.Response;
import top.coos.server.web.handler.WebContext;

public class Main {

	public static void main(String[] args) {

		ServerSetting.setPort(1100);
		new Thread() {

			@Override
			public void run() {

				WebServer.start();
			}

		}.start();

		Context rootContext = new WebContext(null);
		rootContext.setAction("/user/toIndex.do", new Action() {

			@Override
			public void doAction(Request request, Response response) {

				response.setContent("this is index page");
			}
		});
		ServerSetting.addContext(rootContext);

		Context p1Context = new WebContext("/p1");
		p1Context.setAction("/user/toIndex.do", new Action() {

			@Override
			public void doAction(Request request, Response response) {

				response.setContent("this is p1 index page");
			}
		});
		ServerSetting.addContext(p1Context);

		Context p2Context = new WebContext("/p2");
		p2Context.setAction("/user/toIndex.do", new Action() {

			@Override
			public void doAction(Request request, Response response) {

				response.setContent("this is p2 index page");
			}
		});
		ServerSetting.addContext(p2Context);

		System.out.println(ServerSetting.getRootContext());

	}
}
