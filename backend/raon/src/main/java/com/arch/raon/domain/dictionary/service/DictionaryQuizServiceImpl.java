package com.arch.raon.domain.dictionary.service;

import com.arch.raon.domain.dictionary.dto.response.DictionaryQuizResponseDto;
import com.arch.raon.domain.dictionary.entity.DictionaryDirectionQuiz;
import com.arch.raon.domain.dictionary.entity.DictionaryInitialQuiz;
import com.arch.raon.domain.dictionary.repository.DictionaryDirectionQuizRepository;
import com.arch.raon.domain.dictionary.repository.DictionaryInitialQuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DictionaryQuizServiceImpl implements DictionaryQuizService{

    private final DictionaryInitialQuizRepository dictionaryInitialQuizRepository;
    private final DictionaryDirectionQuizRepository dictionaryDirectionQuizRepository;

    @Override
    public DictionaryQuizResponseDto getDictionaryQuizzes() {

        List<DictionaryInitialQuiz> initialQuizList = dictionaryInitialQuizRepository.random7();
        List<DictionaryDirectionQuiz> directionQuizList = dictionaryDirectionQuizRepository.random3();

        DictionaryQuizResponseDto quizList = DictionaryQuizResponseDto.builder()
                .initialQuizList(initialQuizList)
                .directionQuizList(directionQuizList)
                .build();

        return quizList;
    }
}
