package com.uscs.common.utils.logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;

/**
 * A standard logback listener which provides configurable
 * defaults and callback methods to perform custom lifecycle behavior.
 * 
 * @author hqrsingh
 *
 */
public class LogbackListener extends ContextAwareBase implements LoggerContextListener, LifeCycle {

	private static final String MISSING_LOG_FILE_NAME = "Please provide the 'log.file' system property ; e.g. 'java -jar -Dlog.file=my-app-name -D... myapp.jar";
	private static final String DEFAULT_BASE_DIRECTORY = System.getProperty("user.dir");

    private boolean started = false;
    
	@Override
    public void start() {
        if (started) return;
        
        String logBaseDir = System.getProperty("log.base.dir");
        String logFile = System.getProperty("log.file");

        logBaseDir = (logBaseDir != null && logBaseDir.length() > 0) ? logBaseDir : DEFAULT_BASE_DIRECTORY;
        logFile = (logFile != null && logFile.length() > 0) ? logFile : null;

        if (logFile == null) {
        	throw new IllegalStateException(MISSING_LOG_FILE_NAME);
        }
        
        Context context = getContext();

        context.putProperty("BASE_DIR", logBaseDir);
        context.putProperty("LOG_FILE", logFile);

        started = true;
    }

    @Override
    public void stop() {
    }

    @Override
    public boolean isStarted() {
        return started;
    }

    @Override
    public boolean isResetResistant() {
        return true;
    }

    @Override
    public void onStart(LoggerContext context) {
    }

    @Override
    public void onReset(LoggerContext context) {
    }

    @Override
    public void onStop(LoggerContext context) {
    }

    @Override
    public void onLevelChange(Logger logger, Level level) {
    }
}