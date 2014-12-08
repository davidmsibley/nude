package gov.usgs.cida.nude.resultset.http;

import gov.usgs.cida.nude.resultset.ParsingResultSet;
import gov.usgs.cida.nude.connector.parser.IParser;
import java.io.*;
import java.sql.SQLException;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpResultSet extends ParsingResultSet {
	private static final Logger log = LoggerFactory.getLogger(HttpResultSet.class);
	
	protected final HttpEntity httpEntity;
	
	private HttpResultSet(HttpEntity httpEntity, IParser parser, Reader serverResponseReader) {
		super(parser, serverResponseReader);
		this.httpEntity = httpEntity;
	}
	
	public static HttpResultSet newHttpResultSet(HttpEntity httpEntity, IParser responseParser) {
		InputStream in = null;
		try {
			in = httpEntity.getContent();
		} catch (Exception e) {
			log.error("Error getting response", e);
			if (null == in) {
				in = new ByteArrayInputStream(new byte[0]);
			}
		}
		
		InputStreamReader reader = null;
		try {
			String charset = null;

			Header contentHeader = httpEntity.getContentEncoding();
			if (null != contentHeader) {
				String encoding = contentHeader.getValue();
				log.trace("encoding:" + encoding);
				if ("gzip".equals(encoding)) {
					in = new GZIPInputStream(in);
				}
			}
			
			charset = "UTF-8";
			reader = new InputStreamReader(in, charset);
		} catch (Exception e) {
			log.error("Error decoding response", e);
		}
		
		return new HttpResultSet(httpEntity, responseParser, new BufferedReader(reader));
	}
	
	@Override
	public void close() throws SQLException {
		try {
			EntityUtils.consume(this.httpEntity);
		} catch (IOException e) {
			log.error("Error closing HttpResultSet", e);
		}
		super.close();
	}

}
