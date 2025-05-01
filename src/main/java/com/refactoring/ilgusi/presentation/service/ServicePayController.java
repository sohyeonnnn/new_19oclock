package com.refactoring.ilgusi.presentation.service;

import com.refactoring.ilgusi.domain.service.interfaces.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ServicePayController {
    private final ServiceService serviceService;


}
