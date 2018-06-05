package com.cn.cz.cloud.management.host.startup.daemon.impl;

import com.cn.cz.cloud.management.host.startup.daemon.JettyRestServer;
import com.cn.cz.cloud.tools.component.SystemProvider;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * @author ywaz
 * @date 5/11/18 11:07
 */
public class JettyRestServerImpl implements JettyRestServer{
    private final static String PATH = "/api/v1.0";
    private final static String PORT = "8080";
    private final static String PATH_SPEC = "/*";
    private final static String INIT_PARAMETER_NAME = "jersey.config.server.provider.classnames";
    private final static String PACKAGE_NAME = "com/cn/cz/cloud/management";
    private final static String CHILE_PACKAGE = "true";

    @Override
    public void start() throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath(PATH);
        Server jettyServer = new Server(Integer.parseInt(PORT));
        jettyServer.setHandler(context);
        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, PATH_SPEC);
        jerseyServlet.setInitOrder(0);
        String providerNames = SystemProvider.getProviderNames(PACKAGE_NAME, Boolean.parseBoolean(CHILE_PACKAGE));
        jerseyServlet.setInitParameter(INIT_PARAMETER_NAME, providerNames);
        try {
            jettyServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            jettyServer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
