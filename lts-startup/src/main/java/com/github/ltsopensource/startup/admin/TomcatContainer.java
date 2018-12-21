package com.github.ltsopensource.startup.admin;

import org.apache.catalina.startup.Tomcat;

/**
 * @author tb
 * @date 2018/12/21 12:43
 */
public class TomcatContainer {

    public static void main(String[] args) {
        Tomcat tomcat = new Tomcat();
        tomcat.addWebapp("/","");
    }
}
