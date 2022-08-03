package com.tosan.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@SpringBootApplication(scanBasePackages = "com.tosan")
public class Application {

    public static void main(String[] args) throws Exception {
        Logger logger = LoggerFactory.getLogger("main");

        SpringApplication app = new SpringApplication(Application.class);
        if (args.length != 0) {
            Optional<String> argStr = Arrays.stream(args)
                    .filter(arg -> StringUtils.hasText(arg.split("=")[0]) &&
                            arg.split("=")[0].equals("port"))
                    .findFirst();
            if (argStr.isPresent()) {
                String port = argStr.get().split("=").length == 2 ? argStr.get().split("=")[1] : null;
                if (StringUtils.hasText(port) && isNumeric(port)) {
                    app.setDefaultProperties(Collections.singletonMap("server.port", port));
                    logger.info("Server port changed to: " + port);
                } else {
                    throw new Exception("port is incorrect");
                }
            }
        }
        app.run(args);
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
