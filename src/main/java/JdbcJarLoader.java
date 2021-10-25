import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class JdbcJarLoader {

	public static void addToClasspath(String jarPath) {
		try {
			File dbFile = new File(jarPath);
			URL path = dbFile.toURI().toURL();
			URLClassLoader cl = (URLClassLoader) ClassLoader.getSystemClassLoader();
			Method m = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
			m.setAccessible(true);
			m.invoke(cl, new Object[]{path});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
