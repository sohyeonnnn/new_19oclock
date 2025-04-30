package com.refactoring.ilgusi.presentation.service;

import com.refactoring.ilgusi.common.ResultData;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceRepository serviceRepository;

    @GetMapping("/serviceList")
    public String serviceList(){
        //serviceRepository.searchService()
        return "";
    }

    @ResponseBody
    @GetMapping("/isPossibleMakeService")
    public ResponseEntity<?> isPossibleMakeService(int mNo) {
        System.out.println(serviceRepository.selectFreeServiceCount(mNo)+"!!!!!!!!!!!!!!!!!!!!!!!");
        return ResponseEntity.ok(ResultData.builder().data(serviceRepository.selectFreeServiceCount(mNo)).build());
    }

    @GetMapping("/serviceJoinFrm")
    public String serviceJoinFrm() {
        return "freelancer/servicejoin";
    }


}
