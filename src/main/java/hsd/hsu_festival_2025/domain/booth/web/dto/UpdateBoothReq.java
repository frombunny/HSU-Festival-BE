package hsd.hsu_festival_2025.domain.booth.web.dto;

import hsd.hsu_festival_2025.domain.booth.entity.enums.BoothType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateBoothReq(
        @NotNull(message = "부스 종류는 필수입니다.")
        BoothType type,

        @NotBlank(message = "부스 이름은 필수입니다.")
        String name,

        @NotBlank(message = "부스 설명은 필수입니다.")
        String description,

        @NotBlank(message = "운영 시간은 필수입니다.")
        String time
) {
}
