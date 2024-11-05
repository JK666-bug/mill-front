package Storage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TokenStorage {
    private static final String TOKEN_FILE_PATH = "src/main/resources/Token/token.txt"; // 指定文件路径

    // 保存 token 到文件
    public static void setToken(String token) {
        try {
            Files.write(Paths.get(TOKEN_FILE_PATH), token.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 从文件读取 token
    public static String getToken() {
        try {
            return new String(Files.readAllBytes(Paths.get(TOKEN_FILE_PATH)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
