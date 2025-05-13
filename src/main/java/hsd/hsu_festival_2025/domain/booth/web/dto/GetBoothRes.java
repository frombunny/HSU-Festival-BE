package hsd.hsu_festival_2025.domain.booth.web.dto;

public record GetBoothRes(
        Long id,
        String boothNum,
        String name,
        String time,
        String description,
        String imageUrl
) {
}
