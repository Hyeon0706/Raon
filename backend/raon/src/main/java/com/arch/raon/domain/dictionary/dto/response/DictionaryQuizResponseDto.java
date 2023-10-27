package com.arch.raon.domain.dictionary.dto.response;

import com.arch.raon.domain.dictionary.entity.DictionaryDirectionQuiz;
import com.arch.raon.domain.dictionary.entity.DictionaryInitialQuiz;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class DictionaryQuizResponseDto {
    private List<DictionaryInitialQuiz> initialQuizList;
    private List<DictionaryDirectionQuiz> directionQuizList;

    public DictionaryQuizResponseDto(){
        super();
    }

    @Builder
    public DictionaryQuizResponseDto(List<DictionaryInitialQuiz> initialQuizList, List<DictionaryDirectionQuiz> directionQuizList) {
        this.initialQuizList = initialQuizList;
        this.directionQuizList = directionQuizList;
    }

    @Override
    public String toString() {
        return "DictionaryQuizResponseDto{" +
                "initialQuizList=" + initialQuizList +
                ", directionQuizList=" + directionQuizList +
                '}';
    }
}
