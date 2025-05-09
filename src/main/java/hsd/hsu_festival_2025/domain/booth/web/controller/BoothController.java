package hsd.hsu_festival_2025.domain.booth.web.controller;

import hsd.hsu_festival_2025.domain.booth.entity.Booth;
import hsd.hsu_festival_2025.domain.booth.entity.enums.BoothType;
import hsd.hsu_festival_2025.domain.booth.service.BoothService;
import hsd.hsu_festival_2025.domain.booth.web.dto.GetBoothRes;
import hsd.hsu_festival_2025.domain.booth.web.dto.SaveBoothReq;
import hsd.hsu_festival_2025.domain.booth.web.dto.UpdateBoothReq;
import hsd.hsu_festival_2025.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booth")
public class BoothController {
    private final BoothService boothService;

    @PostMapping
    public SuccessResponse<Void> saveBooth(
            @RequestPart("data") @Valid SaveBoothReq saveBoothReq,
            @RequestPart("file") MultipartFile file) throws IOException {
        boothService.saveBooth(saveBoothReq, file);
        return SuccessResponse.empty();
    }

    @PutMapping("/{boothId}")
    public SuccessResponse<Void> updateBooth(@PathVariable Long boothId,
                                             @RequestPart("data") @Valid UpdateBoothReq updateBoothReq,
                                             @RequestPart("file") MultipartFile file) throws IOException {
        boothService.updateBooth(boothId, updateBoothReq, file);
        return SuccessResponse.empty();
    }

    @DeleteMapping("/{boothId}")
    public SuccessResponse<Void> deleteBooth(@PathVariable Long boothId) {
        boothService.deleteBooth(boothId);
        return SuccessResponse.empty();
    }

    @GetMapping
    public SuccessResponse<List<GetBoothRes>> getBoothList(@RequestParam BoothType type) {
        List<GetBoothRes> boothList = boothService.getBoothList(type);
        return SuccessResponse.from(boothList);

    }


}
