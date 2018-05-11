package com.cn.cz.cloud.tools.wsrs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author ywaz
 * @date 5/11/18 11:19
 */
public class FileUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
    private static final String fileSeparator = System.getProperty("file.separator");

    private FileUtil() {
    }

    public static void write(String fileName, String content, boolean append) {
        File file = new File(fileName);
        BufferedWriter writer;
        try {
            if (file.exists()) {
                writer = new BufferedWriter(new FileWriter(file, append));
            } else {
                writer = new BufferedWriter(new FileWriter(fileName));
            }
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (Exception e) {
        }
    }

    /**
     * 获取随机的文件名称
     *
     * @param seed	随机种子
     * @return
     */
    public static String getRandomFileName(String seed) {
        byte[] ra = new byte[100];
        new Random().nextBytes(ra);
        StringBuilder build = new StringBuilder("");
        for (int i = 0; i < ra.length; i++) {
            build.append(Byte.toString(ra[i]));
        }
        String currentDate = Long.toString(new Date().getTime());
        seed = seed + currentDate + build.toString();
        return EncryptUtils.getMD5ofStr(seed).toLowerCase();
    }

    /**
     * 列出所有当前层的文件和目录
     *
     * @param dir	目录名称
     * @return fileList	列出的文件和目录
     */
    public static List<File> ls(String dir) {
        try {
            return Arrays.asList(new File(dir).listFiles());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static List<String> getClassName(String packageName, boolean childPackage) {
        List<String> fileNames = null;

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String packagePath = packageName.replace(".", "/");
        URL url = loader.getResource(packagePath);
        LOGGER.debug("package url: {}", url);
        if (url != null) {
            String type = url.getProtocol();
            if (type.equals("file")) {
                fileNames = getClassNameByFile(url.getPath(), null, childPackage);
            } else if (type.equals("jar")) {
                fileNames = getClassNameByJar(url.getPath(), childPackage);
            }
        } else {
            fileNames = getClassNameByJars(((URLClassLoader) loader).getURLs(),
                    packagePath, childPackage);
        }
        return fileNames;
    }

    private static List<String> getClassNameByFile(String filePath, List<String> className, boolean childPackage) {
        List<String> myClassName = new ArrayList<String>();
        File file = new File(filePath);
        File[] childFiles = file.listFiles();
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                if (childPackage) {
                    myClassName.addAll(getClassNameByFile(childFile.getPath(), myClassName, childPackage));
                }
            } else {
                final String classesSeparator = "classes" + fileSeparator;
                String childFilePath = childFile.getPath();
                if (childFilePath.endsWith(".class")) {
                    System.out.println(childFilePath);
                    int index = childFilePath.lastIndexOf(classesSeparator);
                    if (index != -1) {
                        childFilePath = childFilePath.substring(index + classesSeparator.length(), childFilePath.length() - 6);
                        childFilePath = childFilePath.replace(fileSeparator, ".");
                        myClassName.add(childFilePath);
                    }
                }
            }
        }

        return myClassName;
    }

    private static List<String> getClassNameByJar(String jarPath,
                                                  boolean childPackage) {
        List<String> myClassName = new ArrayList<String>();
        String[] jarInfo = jarPath.split("!");
        String jarFilePath = jarInfo[0].substring(jarInfo[0].indexOf("/"));
        String packagePath = jarInfo[1].substring(1);
        try (JarFile jarFile = new JarFile(jarFilePath);) {
            Enumeration<JarEntry> entrys = jarFile.entries();
            while (entrys.hasMoreElements()) {
                JarEntry jarEntry = entrys.nextElement();
                String entryName = jarEntry.getName();
                if (entryName.endsWith(".class")) {
                    if (childPackage) {
                        if (entryName.startsWith(packagePath)) {
                            entryName = entryName.replace("/", ".").substring(
                                    0, entryName.lastIndexOf("."));
                            myClassName.add(entryName);
                        }
                    } else {
                        int index = entryName.lastIndexOf("/");
                        String myPackagePath;
                        if (index != -1) {
                            myPackagePath = entryName.substring(0, index);
                        } else {
                            myPackagePath = entryName;
                        }
                        if (myPackagePath.equals(packagePath)) {
                            entryName = entryName.replace("/", ".").substring(
                                    0, entryName.lastIndexOf("."));
                            myClassName.add(entryName);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return myClassName;
    }

    private static List<String> getClassNameByJars(URL[] urls,
                                                   String packagePath, boolean childPackage) {
        List<String> myClassName = new ArrayList<String>();
        if (urls != null) {
            for (int i = 0; i < urls.length; i++) {
                URL url = urls[i];
                String urlPath = url.getPath();
                // 不必搜索classes文件夹
                if (urlPath.endsWith("classes/")) {
                    continue;
                }
                String jarPath = urlPath + "!/" + packagePath;
                myClassName.addAll(getClassNameByJar(jarPath, childPackage));
            }
        }
        return myClassName;
    }

    /**
     * 根据需要创建文件夹
     *
     * @param dirPath 文件夹路径
     * @param del	存在文件夹是否删除
     */
    public static void mkdir(String dirPath, boolean del) {
        File dir = new File(dirPath);
        try {
            if (dir.exists()) {
                if (del) {
                    rm(dir.getAbsolutePath(), del);
                } else {
                    return;
                }
            }
            dir.mkdirs();
        } catch (Exception e) {
        }
    }

    /**
     * 删除文件和目录
     *
     * @param path
     * @param self
     */
    public static void rm(String path, boolean self) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] fileList = file.listFiles();
            if (fileList == null || fileList.length == 0) {
                if (self) {
                    file.delete();
                }
            } else {
                for (File _file : fileList) {
                    rm(_file.getAbsolutePath(), true);
                }
            }
            if (self) {
                file.delete();
            }
        } else {
            if (self) {
                file.delete();
            }
        }
    }

    /**
     * 移动文件
     *
     * @param source 源文件
     * @param target 目标文件
     * @param cache	文件缓存大小
     * @throws Exception
     */
    public static void mv(String source, String target, int cache) throws Exception {
        if (source.trim().equals(target.trim())) {
            return;
        }
        byte[] cached = new byte[cache];
        FileInputStream fromFile = new FileInputStream(source);
        FileOutputStream toFile = new FileOutputStream(target);
        while (fromFile.read(cached) != -1) {
            toFile.write(cached);
        }
        toFile.flush();
        toFile.close();
        fromFile.close();
        new File(source).deleteOnExit();
    }

    /**
     * 把属性文件转换成Map
     *
     * @param propertiesFile
     * @return
     * @throws Exception
     */
    public static final Map<String, String> getPropertiesMap(String propertiesFile) throws Exception {
        Properties properties = new Properties();
        FileInputStream inputStream = new FileInputStream(propertiesFile);
        properties.load(inputStream);
        Map<String, String> map = new HashMap<String, String>();
        Set<Object> keySet = properties.keySet();
        for (Object key : keySet) {
            map.put((String) key, properties.getProperty((String) key));
        }
        return map;
    }

    public static final Map<String, String> getPropertiesMap(Class clazz, String fileName) throws Exception {
        Properties properties = new Properties();
        InputStream inputStream = clazz.getResourceAsStream(fileName);
        if (inputStream == null) {
            inputStream = clazz.getClassLoader().getResourceAsStream(fileName);
        }
        properties.load(inputStream);
        Map<String, String> map = new HashMap<String, String>();
        Set<Object> keySet = properties.keySet();
        for (Object key : keySet) {
            map.put((String) key, properties.getProperty((String) key));
        }
        return map;
    }

    /**
     * 把属性文件转换成Map
     *
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static final Map<String, String> getPropertiesMap(InputStream inputStream) throws Exception {
        Properties properties = new Properties();
        properties.load(inputStream);
        Map<String, String> map = new HashMap<String, String>();
        Set<Object> keySet = properties.keySet();
        for (Object key : keySet) {
            map.put((String) key, properties.getProperty((String) key));
        }
        return map;
    }

    /**
     * 把属性文件转换成Map
     *
     * @return
     * @throws Exception
     */
    public static final Map<String, String> getPropertiesMap(File file) throws Exception {
        Properties properties = new Properties();
        FileInputStream inputStream = new FileInputStream(file);
        properties.load(inputStream);
        Map<String, String> map = new HashMap<String, String>();
        Set<Object> keySet = properties.keySet();
        for (Object key : keySet) {
            map.put((String) key, properties.getProperty((String) key));
        }
        inputStream.close();
        return map;
    }

    /**
     * 把文本文件转换成String
     *
     * @param fullPath
     * @param encoding
     * @return
     * @throws Exception
     */
    public static String cat(String fullPath, String encoding) throws Exception {
        if (StringUtils.isEmpty(fullPath)) {
            return null;
        }
        StringBuilder builder;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fullPath), encoding))) {
            builder = new StringBuilder("");
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
        }
        return builder.toString();
    }

    public static String cat(File file, String encoding) {
        if (file == null) {
            return null;
        }
        String fullPath = file.getAbsolutePath();
        try {
            return cat(fullPath, encoding);
        } catch (Exception e) {
            return null;
        }
    }

    public static String cat(InputStream stream, String encoding) throws Exception {
        if (stream == null) {
            return null;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, encoding));
        StringBuilder builder = new StringBuilder("");
        String line = null;
        while ((line = reader.readLine()) != null) {
            builder.append(line + "\n");
        }
        stream.close();
        reader.close();
        return builder.toString();
    }

    /**
     * 获取资源文件流
     *
     * @param clazz
     * @param name
     * @return
     */
    public static InputStream getResourceAsStream(Class clazz, String name) {
        if (clazz == null || StringUtils.isEmpty(name)) {
            return null;
        }
        try {
            InputStream inputStream = clazz.getClassLoader().getResourceAsStream(name);
            if (inputStream == null) {
                inputStream = clazz.getResourceAsStream(name);
            }
            return inputStream;
        } catch (Exception e) {
            return null;
        }
    }

    public static URL getResource(Class clazz, String name) {
        if (clazz == null || StringUtils.isEmpty(name)) {
            return null;
        }
        try {
            URL inputStream = clazz.getClassLoader().getResource(name);
            if (inputStream == null) {
                inputStream = clazz.getResource(name);
            }
            return inputStream;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getUnixPath(String path) {
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        char[] chars = path.toCharArray();
        char[] unixChars = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '\\') {
                c = '/';
            }
            unixChars[i] = c;
        }
        return new String(unixChars);
    }

    public static boolean exist(String path, FileType fileType) {
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        if (fileType == null) {
            return true;
        }
        if (fileType.equals(FileType.DIR)) {
            if (file.isDirectory()) {
                return true;
            } else {
                return false;
            }
        } else if (fileType.equals(FileType.FILE)) {
            if (file.isFile()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static enum FileType {

        FILE, DIR
    }
}

