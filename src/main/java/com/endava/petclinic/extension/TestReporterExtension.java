package com.endava.petclinic.extension;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TestReporterExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final Logger LOGGER = LogManager.getLogger(TestReporterExtension.class);

    static {
        String env = System.getProperty("env");
        LOGGER.info("Starting test on env {}", env);
    }
    @Override
    public void afterTestExecution(final ExtensionContext context) throws Exception {
        LOGGER.info("Finishing test {}/{}",context.getTestClass().get().getSimpleName(), context.getDisplayName());

    }

    @Override
    public void beforeTestExecution(final ExtensionContext context) throws Exception {
        LOGGER.info("Starting test {}/{}", context.getTestClass().get().getSimpleName(), context.getDisplayName());
    }
}
