package com.refactoring.ilgusi.domain.service.interfaces;


import com.refactoring.ilgusi.domain.service.Join;

public interface ServiceService {
    int selectFreeServiceCount(int mNo);
    void insertService(Join join);
}
