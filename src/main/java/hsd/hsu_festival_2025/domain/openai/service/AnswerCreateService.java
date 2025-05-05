package hsd.hsu_festival_2025.domain.openai.service;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.chat.ChatMessage;
import hsd.hsu_festival_2025.domain.chatMessage.web.dto.SendMessageReq;
import hsd.hsu_festival_2025.domain.openai.web.dto.ChatCompletionRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerCreateService {
    private final OpenAiService openAiService;

    public String chat(List<SendMessageReq.ChatHistory> chatHistory, String question) {
        String systemPrompt = """
                당신은 부기라는 캐릭터입니다. 한성대학교 대동제에 대해 물어보는 것들에 대한 답변을 주세요.
                귀여운 당신의 캐릭터에 맞게 과하지 않은 적당히 귀여운 말투를 사용해주세요. 공격적인 말투보다 부드러운 청유문을 사용해주세요.
                "☺︎☘︎⛸︎♥︎⛰︎☀︎✈︎⏱︎⏲︎☁︎⛈︎☂︎⛱︎❄︎☃︎☄︎⛑︎☎︎✏︎✉︎⛏︎⚒︎⚠︎⬇︎⬆︎☪︎‼︎⁉︎✔︎‼️🔅🎸🪗🎷🎧🎤🔕🔇💸📬🌦️🏠🍻🍊🍙☘️💘👻👽😽🫣🫠😪🥹😭😎"
                이 이모티콘들 중에 적절한 이모티콘이 있다면 아주 간헐적으로 사용해주세요.
                질문에 대한 대답은 꼭 아래 정보를 기반으로 해주세요. 아래 정보 리스트로 알 수 없는 질문이 들어오면 해당 정보는 없다고 언급해주고 적절하게 대답해주세요.
                
                [대동제 관련 정보 리스트]
                1. 대동제는 5/14~16 3일간 진행됩니다.
                """;

        List<ChatMessage> messages = new ArrayList<>();
        messages.add(new ChatMessage("system", systemPrompt));
        messages.addAll(convertHistoryToMessages(chatHistory)); // 변환 필요
        messages.add(new ChatMessage("user", question));

        ChatCompletionRequestDto request = ChatCompletionRequestDto.builder()
                .model("gpt-4o")
                .temperature(0.7)
                .maxTokens(500)
                .messages(messages)
                .build();

        return openAiService.createChatCompletion(request.toRequest())
                .getChoices()
                .get(0)
                .getMessage()
                .getContent();
    }

    // chatHistory -> openAI의 ChatMessage로 변환
    private List<ChatMessage> convertHistoryToMessages(List<SendMessageReq.ChatHistory> chatHistory) {
        List<ChatMessage> messages = new ArrayList<>();

        for (SendMessageReq.ChatHistory history : chatHistory) {
            String role = history.getType() == 0 ? "user" : "assistant";
            messages.add(new ChatMessage(role, history.getContent()));
        }

        return messages;
    }
}
