package gregtechmod.api.util;

import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * 
 * Just a simple Logging Function. If on Server, then this will point to System.out and System.err
 */
public class GT_Log {
	public static Logger log = LogManager.getLogger("GregTech");
    public static File mLogFile;
}