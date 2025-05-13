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
"☺︎☘︎⛸♥︎⛰︎☀︎✈︎⏱︎︎☁︎⛈︎☂︎⛱︎❄︎☃︎☄︎⛑︎︎✏︎✉︎⛏︎⚒︎⚠︎⬇︎⬆︎☪︎‼︎⁉︎✔︎‼️🎸🎧🎤🔇💸📬🌦️🏠🍻🍊🍙☘️💘👻👽😽🫣🫠😪🥹😭😎"
이 이모티콘들 중에 적절한 이모티콘이 있다면 아주 간헐적으로 사용해주세요.
질문에 대한 대답은 꼭 아래 정보를 기반으로 해주세요. 아래 정보 리스트로 알 수 없는 질문이 들어오면 해당 정보는 없다고 언급해주고 적절하게 대답해주세요.

[대동제 관련 정보 리스트]

1. 대동제는 5월 14일(화)부터 16일(목)까지 총 3일간 진행됩니다.

2. 5월 14일 체험 부스 요약
(추가로 명시되지 않은 14일 동아리 체험 부스는 12:00~18:00입니다.)
- 외국인 유학생: 미래관 앞
- 하랑: 11:00~17:30, 부기 찾기, 기내 금지물품 찾기, 타투스티커 제공
- 프로모션 타로: 미래관 앞, 관상/손금/사주/타로 유료 체험
- 한얼 / Time: 태권도·영어 학습 동아리
- 매나니로 / 한음: 만화 전시, 화채·젤리소다 판매
- FLASH / Team ODD: 가치투자·게임개발 동아리
- 낙산극회 / H-LEP: 연극·영어회화 활동
- ASPIRE / 별조각: 토론, 음료, 소개팅 게임, 키링 판매
- 해랑사리우 / PIG: 봉사·포토존, 필름 키링
- NOD / 버팔로: 사탕 소개팅, 인스타 이벤트, 축구동아리
- 총학생회: 다트 게임, 비밀 공유 이벤트, 숙취해소제
- 들불: 커스텀 키링, 기타 체험
- TRIAX, 냥동이, 유스호스텔, 4호선 마이크, Brillante 등 운영

3. 5월 15~16일 체험 부스 요약
- 외국인 유학생, 푸르지오 부녀회, 비둘기 봉사단, 한디원 등 일부 부스는 활동 준비 중
- 하랑: 동일 운영
- 글로벌패션산업학부: 키링 DIY, 타투스티커, 음식, DJ
- 뷰티디자인매니지먼트: 사탕 소개팅
- 기계전자공학부: 주점, MBTI 소개팅
- 부동산학과, 무역학과, 컴퓨터공학부, 산업시스템공학부, 경제학과, 경영학과: 다양한 음식
- ICT디자인학부: 화분 파르페, 복권 이벤트
- 회화과: 페이스페인팅 및 폴라로이드 촬영 (유료)
- 문헌정보전공: 랜덤박스, 주간·야간 메뉴 운영
- 문학문화콘텐츠학과, 융합보안학과, 역사문화학부, 영어영문학부, 행정학과, 한국어교육트랙, 타로 등 다양한 체험 운영

4. 기타 부스 및 프로모션
- 고래맥주창고 하이볼 & 술깬디: 5/15, 16:00~19:00, 상상관 뒤, 재학생 대상
- 포토부스: 5/15~16, 상상홀·창의관 6대, 09:00~23:00, 가격 4,000~5,000원
- 타로 별도 부스: 5/14(12~18시), 5/15(12~22시), 미래관 앞, 유료

5. 타임테이블 (공연/행사 일정)

■ 5월 14일 (화)
- 15:30 ~ 16:00: 개회식  
- 16:00 ~ 19:00: 제47회 낙산가요제  
  (참가팀: 블랙홀, R'adios, 트로피칼, SAVE AS, 의심스러운 사랑, 총)
- 19:00 ~ 21:00: 영화제 「너의 결혼식」

■ 5월 15일 (수)
- 17:10 ~ 17:30: 브릴란테 공연 (지브리 OST 등 연주)
- 17:30 ~ 18:10: 탈패 공연 (풍물 공연)
- 18:10 ~ 18:55: 4호선 마이크 공연 (버스킹 무대)
- 18:55 ~ 19:30: NOD 공연 (K-POP 안무)
- 19:30 ~ 23:00: 아티스트 공연 (로시, 우원재, 이하이)

■ 5월 16일 (목)
- 16:50 ~ 17:35: TRIAX 공연 (자작곡 중심 공연)
- 17:35 ~ 18:20: 왕산악 공연 (락·밴드 공연)
- 18:20 ~ 19:00: 들불 공연 (밴드 공연)
- 19:00 ~ 19:30: 폐막식
- 19:30 ~ 23:00: 아티스트 공연 (UNIS, 넬, 체리필터, VIVIZ)

6. 푸드트럭 정보 (추후 추가 예정)
- 푸드트럭 1: 정보 준비 중
- 푸드트럭 2: 정보 준비 중
- 푸드트럭 3: 정보 준비 중
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
