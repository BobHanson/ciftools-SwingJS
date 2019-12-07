package org.rcsb.cif;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Arrays;

import org.rcsb.cif.api.CifFile;
import org.rcsb.cif.api.CifIO;

/**
 * A class that demonstrates "generic" cif writing.
 * 
 * Utilizes org.rcsb.cif.*.*Generic.java
 * 
 * @author hansonr
 *
 */
public class DemoWrite {

	
	/**
	 * environment flag to prevent unnecessary Swing classes from loading
	 * 
	 */
	public static boolean j2sHeadless = true;

	protected static final int MODE_READ_PARSE_TEXT     = 0;
	protected static final int MODE_READ_PARSE_BINARY   = 1;
	
	/**
	 *  include transfer time for file reading
	 */
	protected static final int MODE_READ_FILE                = 0 << 2;
	/**
	 * preload byte array to exclude file transfer time
	 */
	protected static final int MODE_READ_BYTE_STREAM         = 1 << 2;

	
	protected static final int MODE_WRITE_PARSE_TEXT     = 1 << 8;
	protected static final int MODE_WRITE_PARSE_BINARY   = 1 << 9;

	/**
	 * don't report
	 */
	protected static final int MODE_SILENT  = 1 << 11;

	private static final int MODE_LOCAL     = 1 << 12;

	
	public static void main(String[] args) {
		String pdbId = "1acj";
		//String pdbId = "3j9m";//"3j3q";//"4v4g";//;
		try {
			
			long t0 = System.nanoTime();
			try {
				// for CifIO static initialization
				CifIO.class.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
			}
			reportTime(t0, "Loading Cif options");
			
//			parse(pdbId, MODE_READ_BYTE_STREAM | MODE_OPTION_GET_ALL_DATA);
//			parse(pdbId, MODE_READ_FILE | MODE_OPTION_GET_ALL_DATA);
			
			parse(pdbId, MODE_READ_BYTE_STREAM);// | MODE_LOCAL);
//			parse(pdbId, MODE_READ_FILE | MODE_OPTION_PROCESS);

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Demo complete");
	}
	
	protected static void parse(String pdbId, int mode) throws IOException {
		boolean useStream = ((mode & MODE_READ_BYTE_STREAM) == MODE_READ_BYTE_STREAM);
		if (useStream) {
			new StreamParser(pdbId, mode).parseTest(mode);
		} else {
			new TextParser(pdbId).parseTest(mode);
		}

	}

	/**
	 * abstract class; either a StreamParser or TextParser
	 * 
	 * @author hansonr
	 *
	 */
	protected static abstract class Parser {
	   
		abstract void parse(int mode) throws IOException;

		abstract void setMode(int mode);
	   
	   protected void parseTest(int mode) throws IOException {
			test(MODE_READ_PARSE_BINARY | mode);
			//test(MODE_READ_PARSE_TEXT | mode);
		}

		private void test(int mode) throws IOException {
			boolean parseBinary = ((mode & MODE_READ_PARSE_BINARY) == MODE_READ_PARSE_BINARY);
			String type = (parseBinary ? "binary" : "text");

			long t0, tgc;
			setMode(mode);
			System.out.println("------");
			t0 = System.nanoTime();
			tgc = 0;
//			parseFile(mode | MODE_SILENT);
			tgc = gcTime(tgc);
			parse(mode);
			reportTime(t0 + tgc, 10, "------PARSE " + type + " (-GC)");
			t0 = reportTime(t0, 10, "------PARSE " + type);
		}
	}
	/**
	 * Preload the binary or text data as byte arrays so as to remove transfer time.
	 *  
	 * @author hansonr
	 *
	 */
	protected static class StreamParser extends Parser {

		protected InputStream inputStream;//, binaryStream, textStream;
		String pdbId;
		
		protected StreamParser(String pdbId, int mode) throws MalformedURLException, IOException {
			this.pdbId = pdbId;
//			binaryStream = loadStream(pdbId, MODE_READ_PARSE_BINARY | (mode & MODE_LOCAL));
//			textStream = loadStream(pdbId, MODE_READ_PARSE_TEXT | (mode & MODE_LOCAL));
		}

		private InputStream loadStream(String pdbId, int mode) throws MalformedURLException, IOException {
			boolean isBinary = ((mode & MODE_READ_PARSE_BINARY) == MODE_READ_PARSE_BINARY);
			String type = (isBinary ? "binary" : "text");
			long t0 = System.nanoTime(), t1 = System.nanoTime();
			ByteArrayInputStream is = getByteStreamFor(pdbId, mode);
			t0 = reportTime(t0, type  + " len=" + is.available() + " transfer from " + getURL(pdbId, isBinary));
			return is;
		}

		@Override
		void setMode(int mode) {
			try {
				inputStream = loadStream(pdbId, mode);
			} catch (IOException e) {
			}
//			
//			inputStream = ((mode & MODE_READ_PARSE_BINARY) == MODE_READ_PARSE_BINARY ? binaryStream : textStream);
		}

		protected void parse(int mode) throws IOException {
			boolean silent = ((mode & MODE_SILENT) == MODE_SILENT);
			boolean parseBinary = ((mode & MODE_READ_PARSE_BINARY) == MODE_READ_PARSE_BINARY);
			long t0 = System.nanoTime(), t1;

			inputStream.reset();
			CifFile cifFile = CifIO.readFromInputStream(inputStream);
			if (!silent)
				t0 = reportTime(t0,
						"parseStream " + (parseBinary ? "BINARY " : "TEXT ") + this.getClass().getSimpleName());
			silent = true;// false;
			t1 = (silent ? 0 : System.nanoTime());
			for (int i = 0; i < 1; i++)
				process(pdbId, cifFile, mode | MODE_SILENT);
			t1 = (silent ? 0
					: reportTime(t1, 1,
							"processStream " + (parseBinary ? "BINARY " : "TEXT ") + this.getClass().getSimpleName()));

		}

	}
	
	/**
	 * Do not pre-load the file data; include transfer time from EBI for each parsing
	 * 
	 * @author hansonr
	 *
	 */
	protected static class TextParser extends Parser {
		
		protected String pdbId;

		TextParser(String pdbId) {
			this.pdbId = pdbId;
		}

		protected void parse(int mode) throws IOException {
			boolean silent = ((mode & MODE_SILENT) == MODE_SILENT);
			boolean parseBinary = ((mode & MODE_READ_PARSE_BINARY) == MODE_READ_PARSE_BINARY);
			long t0 = System.nanoTime();
			CifFile cifFile = CifIO.readFromURL(getURL(pdbId, parseBinary));
			if (!silent)
				t0 = reportTime(t0, "parseStream " + (parseBinary ? "BINARY " : "TEXT ") + this.getClass().getSimpleName());
				process(pdbId, cifFile, mode);
		}

		@Override
		void setMode(int mode) {
			// n/a for TextParser
		}
	}

	protected static void process(String pdbId, CifFile cifFile, int mode) {
		boolean isBinary = ((mode & MODE_WRITE_PARSE_BINARY) == MODE_WRITE_PARSE_BINARY);
		try {
			Path path = new File("test-write." + pdbId + (isBinary ? ".bcif" : ".cif")).toPath();
			System.out.println("Write process " + path);
			if (isBinary) {
				CifIO.writeBinary(cifFile, path);
			} else {
				CifIO.writeText(cifFile, path);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected static ByteArrayInputStream getByteStreamFor(String pdbId, int mode)
			throws MalformedURLException, IOException {
		boolean isBinary = ((mode & MODE_READ_PARSE_BINARY) == MODE_READ_PARSE_BINARY);
		boolean isLocal = ((mode & MODE_LOCAL) == MODE_LOCAL);
		Object o;
		InputStream is = (isLocal ? new FileInputStream((File)
				(o = new File("c:/temp/" + pdbId + (isBinary ? ".b" : ".") + "cif")))
				: ((URL)(o = getURL(pdbId, isBinary))).openStream());
		System.out.println("Reading data from "+ o);
		return new ByteArrayInputStream(getStreamAsBytes(new BufferedInputStream(is)));
	}

	protected static URL getURL(String pdbId, boolean isBinary) throws MalformedURLException {
		return (isBinary ? 
				new URL("https://www.ebi.ac.uk/pdbe/coordinates/" + pdbId + "/full?encoding=bcif")
				: 
					new URL("https://www.ebi.ac.uk/pdbe/coordinates/" + pdbId + "/full?encoding=cif")
//				new URL("https://files.rcsb.org/download/" + pdbId + ".cif")
				);
	}


	protected static long reportTime(long t0, int n, String msg) {
		long now = System.nanoTime();
		System.out.println((now - t0)/n/1000000 + "\tniter=" + n + "\t" + msg);
		return now;
	}

	protected static long reportTime(long t0, String msg) {
		long now = System.nanoTime();
		System.out.println((now - t0)/1000000 + "\t" + msg);
		return now;
	}

	protected static long gcTime(long tgc) {
		long tg = System.nanoTime();
		System.gc();
		return tgc + System.nanoTime() - tg;
	}

	protected static byte[] getStreamAsBytes(BufferedInputStream bis) throws IOException {
		byte[] buf = new byte[1024];
		byte[] bytes = new byte[4096];
		int len = 0;
		int totalLen = 0;
		while ((len = bis.read(buf, 0, 1024)) > 0) {
			totalLen += len;
				if (totalLen >= bytes.length)
					bytes = Arrays.copyOf(bytes, totalLen * 2);
				System.arraycopy(buf, 0, bytes, totalLen - len, len);
		}
		bis.close();
		return Arrays.copyOf(bytes, totalLen);
	}

}
