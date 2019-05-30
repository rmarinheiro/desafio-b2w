package br.com.b2w.decoder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {
	

	public static String decode(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

}
