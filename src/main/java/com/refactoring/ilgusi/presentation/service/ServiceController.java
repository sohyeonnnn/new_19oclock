package com.refactoring.ilgusi.presentation.service;

import com.refactoring.ilgusi.domain.service.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceRepository serviceRepository;

    @GetMapping("/serviceList")
    public String serviceList(){
        //serviceRepository.searchService()
        return "";
    }

}
