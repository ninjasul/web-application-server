package util;

public class StringUtils {
	
	public static boolean isNullOrEmpty( String str ) {
		return ( str == null || "".equals(str) );
	}
	public static String nvl( String originalString, String alternativeString ) {
		return ( originalString != null && originalString.length() > 0 ) ? originalString : alternativeString;
	}
	
}
