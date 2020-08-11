package gregtechmod.api.util;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * 
 * Just a simple Logging Function. If on Server, then this will point to System.out and System.err
 */
public class GT_Log {
	public static Logger log = LogManager.getLogger("GregTech");
	public static File mOreDictLogFile;
	public static PrintStream ore = new LogBuffer();
	
	public static class LogBuffer extends PrintStream {
		public final List<String> mBufferedOreDictLog = new ArrayList<>();

		public LogBuffer() {
			super(new OutputStream() {
				@Override
				public void write(int val) {}
			});
		}

		public void println(String aString) {
			this.mBufferedOreDictLog.add(aString);
		}
	}
}