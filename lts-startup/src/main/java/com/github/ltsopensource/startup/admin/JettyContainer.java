package com.github.ltsopensource.startup.admin;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.CatalinaBaseConfigurationSource;
import org.apache.catalina.startup.CatalinaProperties;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Robert HG (254963746@qq.com) on 9/1/15.
 */
public class JettyContainer {

    public static void main(String[] args) {
        try {
            String confPath = args[0];

            confPath = confPath.trim();

            Properties conf = new Properties();
            InputStream is = new FileInputStream(new File(confPath + "/conf/lts-admin.cfg"));
            conf.load(is);
            String port = conf.getProperty("port");
            if (port == null || "".equals(port.trim())) {
                port = "8081";
            }

            String contextPath = conf.getProperty("contextPath");
            if (contextPath == null || "".equals(contextPath.trim())) {
                contextPath = "/";
            }
            if(!contextPath.endsWith("/")) {
                contextPath =  contextPath+"/";
            }
            Tomcat tomcat = new Tomcat();
            String protocol = getProtocol();
            Connector connector = new Connector(protocol);
            // Listen only on localhost
            connector.setAttribute("address",
                    InetAddress.getByName("localhost").getHostAddress());
            // Use random free port
            connector.setPort(0);
            // Mainly set to reduce timeouts during async tests
            connector.setAttribute("connectionTimeout", "3000");
            tomcat.getService().addConnector(connector);
            tomcat.setConnector(connector);
            File war = new File(confPath + "/war/lts-admin.war");
            tomcat.setPort(Integer.parseInt(port));
            tomcat.addContext(contextPath, war.getAbsolutePath()).addParameter("lts.admin.config.path",confPath + "/conf");
            tomcat.addWebapp(contextPath, war.getAbsolutePath());
            tomcat.start();

//            Server server = new Server(Integer.parseInt(port));
//            WebAppContext webapp = new WebAppContext();
//            webapp.setWar(confPath + "/war/lts-admin.war");
//            webapp.setContextPath(contextPath);
//            Map<String, String> initParams = new HashMap<String, String>();
//            initParams.put("lts.admin.config.path", confPath + "/conf");
//            webapp.setInitParams(initParams);
//            server.setHandler(webapp);
//            server.setStopAtShutdown(true);
//            server.start();

            System.out.println("LTS-Admin started. http://" + NetUtils.getLocalHost() + ":" + port + (contextPath == "/" ? "" : contextPath) + "/index.htm");

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    private static String getProtocol() {
        // Has a protocol been specified
        String protocol = System.getProperty("tomcat.test.protocol");
        // Use NIO by default starting with Tomcat 8
        if (protocol == null) {
            protocol = Http11NioProtocol.class.getName();
        }

        return protocol;
    }

}
