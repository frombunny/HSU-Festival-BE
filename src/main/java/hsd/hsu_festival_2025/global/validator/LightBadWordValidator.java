package hsd.hsu_festival_2025.global.validator;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class LightBadWordValidator {

    private static final String BAD_WORD_REGEX = """
    씨발|시발|쉬발|ㅅㅂ|ㅆㅂ|씹|씹새|씹놈|ㅆㅅ
    | 좆|좇|좃|좁|쫒|존나|ㅈㄴ|ㅈ같|ㅈ랄|ㅈ밥|ㅈㄹ
    | 병신|븅신|ㅂㅅ|ㅄ|정신병자|돌아이|미친놈|미친년|미친
    | 지랄|지랄하네|염병|개지랄|개염병|ㅈㄹ
    | 개새끼|새끼|새꺄|ㅅㄲ|개색기|개소리|개같|개놈
    | 창녀|년놈|걸레|쌍놈|쌍년|니애미|느금마|애미뒤짐|애미죽어
    | 보지|보지랄|보빨|자지|불알|꼬추|보x|자x
    | 후장|뒷치기
    | 꺼져|닥쳐|꺼지세요|닥치세요
    | 빠가|빠가야로|쪼다|쫄보|멍청이
    | 틀딱|한남충|된장녀|김치녀|흑형|짱깨|쪽바리
    | ㅅ패|ㅅ발놈|ㅅㄲ|ㅂㅅ|ㄲㅈ|ㄲㅂ|ㄱㅅㄲ
    | 죽어라|뒤져라|디져라|디질래|죽일놈
    """;

    private static final Pattern PATTERN = Pattern.compile(
            BAD_WORD_REGEX,
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
    );

    public boolean containsBadWord(String input) {
        return input != null && PATTERN.matcher(input).find();
    }
}
