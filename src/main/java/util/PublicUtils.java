package util;

import request.ApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import mill.ChatPanel;

import model.MillDTO;
import model.Question;
import model.R;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PublicUtils {

    // Using a list of map to save the context
    public static List<Map<String, String>> context = new ArrayList<>();

    public static void refreshContext() {
        context = new ArrayList<>();
    }

    public static MillDTO sendAndGetMillDTO(String request) throws JsonProcessingException {
        // 将用户发送消息显示在屏幕上
        MethodUtil.runWithThread(() -> ChatPanel.showArea.append(request + "\n\n"));

        // Send request and get response
        String str = OkHttpUtils.builder()
                .url(ApiService.HOST + "/mill")
                .post(JsonUtils.toJsonString(context))
                .async();

        System.out.println("\nPost successfully! The response is: " + str);

        // Resolve the R<MillDTO> Json String
        R<MillDTO> result = JsonUtils.parseObject(str, new TypeReference<>() {
        });
        System.out.println("\nThe Json has been parsed to R<MillDTO> successfully: " + result);

        // 在此处我们拿到了 MillDTO
        System.out.println("MillDTO: " + result);
        MillDTO response = Objects.requireNonNull(result).getData();

        // 将 AI 返回的消息显示在屏幕上
        MethodUtil.runWithThread(() -> ChatPanel.showArea.append(buildMessage(response) + "\n\n"));


        // 用 LinkedHashMap 存储用户与 AI 的聊天记录
        context.add(Map.of("user", request));
        context.add(Map.of("assistant", response.toString()));

        System.out.println("\nThe context list: " + context);
        return response;
    }

    private static String buildMessage(MillDTO dto) {
        String result = dto.getMessage();
        Question question = dto.getQuestion();
        if (question != null && dto.getProgress() < 100) {
            result += "\n" + question.getQuestion() + "\n";
            result += "A. " + question.getOptionA() + "\t" + "B. " + question.getOptionB() + "\n"
                    + "C. " + question.getOptionC() + "\t" + "D. " + question.getOptionD();
        }
        return result;
    }
}
