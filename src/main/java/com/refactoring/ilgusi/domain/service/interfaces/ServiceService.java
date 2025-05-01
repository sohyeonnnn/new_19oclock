package com.refactoring.ilgusi.domain.service.interfaces;


import com.refactoring.ilgusi.domain.service.dto.ServiceInsertDto;

public interface ServiceService {
    int selectFreeServiceCount(int mNo);
    void insertService(ServiceInsertDto join);
}
