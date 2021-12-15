package cn.gateside.gattmg.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangtiande
 */
public class FileUtil {

    private static boolean flag = false;
    private static BufferedInputStream inputStream = null;
    private static BufferedOutputStream outputStream = null;
    private BufferedReader bufferedReader = null;
    private BufferedWriter bufferedWriter = null;

    /**
     * @param filePath file path
     * @return File
     * @throws IOException ex
     */
    public static File pathToFile(String filePath) throws IOException {
        File file = new File(filePath);
        return file;
    }

    /**
     * @param file file
     * @return String
     * @throws IOException ex
     */
    public static String getFileDir(File file) throws IOException {
        String filePath = file.getCanonicalPath();
        return filePath;
    }

    /**
     * @return String
     * @throws IOException ex
     */
    public static String getProjectDir() throws IOException {
        File directory = new File("");
        String proDir = directory.getCanonicalPath();
        return proDir;
    }

    /**
     * @param filePath dir path
     * @return File
     */
    public static File createFileDir(String filePath) {
        File file = new File(filePath);
        file.mkdirs();
        return file;
    }

    /**
     * @param filePath    file path
     * @param fileName    file name
     * @param fileContent file content
     * @param flag        flag
     * @return boolean
     */
    public static boolean createFile(String filePath, String fileName, String fileContent, boolean flag) {
        FileUtil fileUtil = new FileUtil();
        String pathString = filePath + fileName;

//		System.out.println("=======================================");
        if (!fileUtil.isParamsNull(filePath, fileName)) {
            try {
                System.out.println(filePath);
                System.out.println(fileName);
                File file = FileUtil.pathToFile(pathString.toString());
                flag = file.createNewFile();
                FileWriter fileWriter = new FileWriter(file, flag);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(fileContent.toString());
                printWriter.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * @param inputStream input stream
     * @return String
     * @throws IOException ex
     */
    public static String fileToString(BufferedInputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF8"));

        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            stringBuilder.append(line + "\n");
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }

    /**
     * @param filePath file path
     * @param fileList file list
     */
    public static void getFilesName(String filePath, ArrayList<String> fileList) {
        File root = new File(filePath);
        File[] files = root.listFiles();
        if (null != files) {
            for (File file : files) {
                if (file.isDirectory()) {
                    getFilesName(file.getAbsolutePath(), fileList);
                } else {
                    fileList.add(file.getAbsolutePath());
                }
            }
        } else {
            fileList.add(root.getAbsolutePath());
        }

    }

    /**
     * @param filePath file path
     * @return String list
     */
    public static List<String> getFilesNameNoSuffix(String filePath) {
        List<String> javaFileNames = new ArrayList<String>();
        FileUtil fileUtil = new FileUtil();
        String fileName = "";
        if (!fileUtil.isParamsNull(filePath)) {
            try {
                File file = FileUtil.pathToFile(filePath);
                if (file.isDirectory()) {
                    File[] files = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        fileName = files[i].getName();
                        javaFileNames.add(i, files[i].getName().substring(0, files[i].getName().lastIndexOf(".")));
                    }
                } else if (file.isFile()) {
                    fileName = file.getName();
                    javaFileNames.add(file.getName().substring(0, file.getName().lastIndexOf(".")));
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.println(fileName);
                e.printStackTrace();
            }
        }
        return javaFileNames;
    }

    /**
     * @param oldFilePath old file path
     * @param newFilePath new file path
     * @param fileName    file name
     * @return boolean
     * @throws IOException ex
     */
    public static boolean copyFiles(String oldFilePath, String newFilePath, String fileName) throws IOException {
        FileUtil fileUtil = new FileUtil();

        if (!fileUtil.isParamsNull(oldFilePath, newFilePath, fileName)) {
            try {
                File oldFile = FileUtil.pathToFile(oldFilePath + fileName);
                File newFile = FileUtil.pathToFile(newFilePath);
                if (oldFile.exists() && newFile.exists()) {
                    inputStream = new BufferedInputStream(new FileInputStream(oldFilePath + fileName));
                    outputStream = new BufferedOutputStream(new FileOutputStream(newFilePath + fileName));
                    byte[] buffer = new byte[102400 * 1000];
                    int len = inputStream.read(buffer);
                    while (len != -1) {
                        outputStream.write(buffer, 0, len);
                        len = inputStream.read(buffer, 0, len);
                    }
                    outputStream.flush();
                } else {
                    return flag;
                }
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        }
        return flag;
    }

    /**
     * @param params parameters
     * @return boolean
     */
    public boolean isParamsNull(String... params) {
        if (params == null) {
            return true;
        } else
            return false;
    }

    /**
     * @param resString source string
     * @param desFile   targent file
     * @return boolean
     */
    public boolean stringToFile(String resString, File desFile) {
        char buffer[] = new char[1024];
        int len = 0;
        try {
            bufferedReader = new BufferedReader(new StringReader(resString));
            bufferedWriter = new BufferedWriter(new FileWriter(desFile, true));
            while ((len = bufferedReader.read(buffer)) != -1) {
                bufferedWriter.write(buffer, 0, len);
            }
            bufferedWriter.flush();
            bufferedReader.close();
            bufferedWriter.close();
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
            return flag;
        }
        return flag;
    }
}
