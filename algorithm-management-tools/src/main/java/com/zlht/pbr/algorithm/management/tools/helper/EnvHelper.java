package com.zlht.pbr.algorithm.management.tools.helper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class EnvHelper {

    @Autowired
    private Environment env;

    public String getIp() {
        String ip;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        return ip;
    }

    public String getPort() {
        String port = env.getProperty("server.port");
        return port;
    }

}
