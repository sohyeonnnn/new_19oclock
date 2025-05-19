package com.refactoring.ilgusi.domain.member.interfaces;

import com.refactoring.ilgusi.domain.member.dto.FreelancerIntroduceDto;

public interface FreelancerService {
    FreelancerIntroduceDto selectFreelancerIntroduce(int memberNo);
}
