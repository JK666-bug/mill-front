package net.heimeng.storage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Token Storage Manager
 *
 * @author JK
 */
public class TokenStorage {
    private static final String TOKEN_FILE_PATH = "src/main/resources/config/.tmp"; // 指定文件路径
    private static String token;

    public String getToken() {
        return token;
    }

    public static void setToken(String token) {
        TokenStorage.token = token;
    }

    // 保存 token 到文件
    public static void saveToken(String token) {
        try {
            Files.write(Paths.get(TOKEN_FILE_PATH), token.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 从文件读取 token
    public static String readToken() {
        try {
            return new String(Files.readAllBytes(Paths.get(TOKEN_FILE_PATH)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
