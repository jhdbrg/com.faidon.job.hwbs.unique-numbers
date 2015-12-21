package com.faidon.job.hwbs.un.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DatabaseContextListener implements ServletContextListener {
    private final Logger LOGGER = LoggerFactory.getLogger(DatabaseContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String path = servletContextEvent.getServletContext().getRealPath("/");

        //TODO: Use this URL for db connection
        String dbUrl = "jdbc:hsqldb:file:" + path + "/WEB-INF/db/un";
        LOGGER.info("USE THIS DB URL: {}", dbUrl);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
