package com.refactoring.ilgusi.config;

import com.refactoring.ilgusi.domain.category.interfaces.CategoryRepository;
import com.refactoring.ilgusi.domain.chat.interfaces.ChatRepository;
import com.refactoring.ilgusi.domain.favorite.FavoriteRepository;
import com.refactoring.ilgusi.domain.member.interfaces.AdminRepository;
import com.refactoring.ilgusi.domain.member.interfaces.MemberRepository;
import com.refactoring.ilgusi.domain.notice.interfaces.NoticeRepository;
import com.refactoring.ilgusi.domain.question.interfaces.QuestionRepository;
import com.refactoring.ilgusi.domain.request.interfaces.RequestRepository;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceFileRepository;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceRepository;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceTradeRepository;
import com.refactoring.ilgusi.infrastructure.repository.jpa.category.JpaCategoryRepository;
import com.refactoring.ilgusi.infrastructure.repository.jpa.category.SpringDataCategoryRepository;
import com.refactoring.ilgusi.infrastructure.repository.jpa.chat.JpaChatRepository;
import com.refactoring.ilgusi.infrastructure.repository.jpa.chat.SpringDataChatRepository;
import com.refactoring.ilgusi.infrastructure.repository.jpa.favorite.JpaFavoriteRepository;
import com.refactoring.ilgusi.infrastructure.repository.jpa.favorite.SpringDataFavoriteRepository;
import com.refactoring.ilgusi.infrastructure.repository.jpa.member.JpaAdminRepository;
import com.refactoring.ilgusi.infrastructure.repository.jpa.member.JpaMemberRepository;
import com.refactoring.ilgusi.infrastructure.repository.jpa.member.SpringDataAdminRepository;
import com.refactoring.ilgusi.infrastructure.repository.jpa.member.SpringDataMemberRepository;
import com.refactoring.ilgusi.infrastructure.repository.jpa.notice.JpaNoticeRepository;
import com.refactoring.ilgusi.infrastructure.repository.jpa.notice.SpringDataNoticeRepository;
import com.refactoring.ilgusi.infrastructure.repository.jpa.question.JpaQuestionRepository;
import com.refactoring.ilgusi.infrastructure.repository.jpa.question.SpringDataQuestionRepository;
import com.refactoring.ilgusi.infrastructure.repository.jpa.request.JpaRequestRepository;
import com.refactoring.ilgusi.infrastructure.repository.jpa.request.SpringDataRequestRepository;
import com.refactoring.ilgusi.infrastructure.repository.jpa.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
//@MapperScan(basePackages = "com.refactoring.infrastructure.repository.mybatis")
public class RepositoryConfig {

    @Bean
    @Profile("jpa")
    public CategoryRepository jpaCategoryRepository(SpringDataCategoryRepository repo) {
        return new JpaCategoryRepository(repo);
    }

    @Bean
    @Profile("jpa")
    public ChatRepository jpaChatRepository(SpringDataChatRepository repo) {
        return new JpaChatRepository(repo);
    }

    @Bean
    @Profile("jpa")
    public FavoriteRepository jpaFavoriteRepository(SpringDataFavoriteRepository repo) {
        return new JpaFavoriteRepository(repo);
    }

    @Bean
    @Profile("jpa")
    public AdminRepository jpaAdminRepository(SpringDataAdminRepository repo) {
        return new JpaAdminRepository(repo);
    }

    @Bean
    @Profile("jpa")
    public MemberRepository jpaMemberRepository(SpringDataMemberRepository repo) {
        return new JpaMemberRepository(repo);
    }

    @Bean
    @Profile("jpa")
    public NoticeRepository jpaNoticeRepository(SpringDataNoticeRepository repo) {
        return new JpaNoticeRepository(repo);
    }

    @Bean
    @Profile("jpa")
    public QuestionRepository jpaQuestionRepository(SpringDataQuestionRepository repo) {
        return new JpaQuestionRepository(repo);
    }

    @Bean
    @Profile("jpa")
    public RequestRepository jpaRequestRepository(SpringDataRequestRepository repo) {
        return new JpaRequestRepository(repo);
    }

    @Bean
    @Profile("jpa")
    public ServiceRepository jpaServiceRepository(SpringDataServiceRepository repo) {
        return new JpaServiceRepository(repo);
    }

    @Bean
    @Profile("jpa")
    public ServiceTradeRepository jpaServiceTradeRepository(SpringDataServiceTradeRepository repo) {
        return new JpaServiceTradeRepository(repo);
    }

    @Bean
    @Profile("jpa")
    public ServiceFileRepository jpaServiceFileRepository(SpringDataServiceFileRepository repo) {
        return new JpaServiceFileRepository(repo);
    }


  /*  @Bean
    @Profile("mybatis")
    public MemberRepository myBatisMemberRepository(MemberMapper mapper) {
        return new MybatisMemberRepository(mapper);
    }*/

}