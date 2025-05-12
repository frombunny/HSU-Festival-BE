package hsd.hsu_festival_2025.domain.booth.web.dto;

public record GetBoothRes(
        Long id,
        Long boothNum,
        String name,
        String time,
        String description,
        String imageUrl
) {
}
