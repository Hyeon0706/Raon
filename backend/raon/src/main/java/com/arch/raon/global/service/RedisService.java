package com.arch.raon.global.service;

import com.arch.raon.domain.dictionary.dto.query.DictionaryMyRankQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
public class RedisService {

    private final RedisTemplate<String, String> rankingRedis;
    private final RedisTemplate<String, String> securityRedis;

    @Autowired
    public RedisService(@Qualifier("RankingRedis") RedisTemplate<String, String> rankingRedis,
                        @Qualifier("SecurityRedis") RedisTemplate<String, String> securityRedis) {
        this.rankingRedis = rankingRedis;
        this.securityRedis = securityRedis;
    }

    /** 여기에 리프레쉬 관련 로직 넣으면 좋아용 **/
    public void setRefreshToken(String id, String refreshToken){
        // key : accessToken, value : refreshToken
        securityRedis.opsForValue().set(id, refreshToken);
        //30일
        securityRedis.expire(id,30L, TimeUnit.DAYS);
    }

    public String getRefreshToken(String id){
        return securityRedis.opsForValue().get(id);
    }


    public boolean deleteRefreshToken(String id){
        return Boolean.TRUE.equals(securityRedis.delete(id));
    }


    public void setCountryGrammarPoint(String nickName, int point){
        rankingRedis.opsForZSet().incrementScore("countryGrammar",nickName, point);
    }

    public long getCountryGrammarPoint(String nickName){
        long rank = 0;
        double score = rankingRedis.opsForZSet().score("countryGrammar",nickName);
        System.out.println("Gscore = " + score);
        rank = rankingRedis.opsForZSet().reverseRank("countryGrammar", nickName);
        return rank;
    }

    public void setCountryDictionaryPoint(Long id, int point){
        rankingRedis.opsForZSet().incrementScore("countryDictionary", String.valueOf(id), point);
    }

    public double getCountryDictionaryPoint(Long id){
        double score = rankingRedis.opsForZSet().score("countryDictionary",String.valueOf(id));
        return score;
    }

    public long getCountryDictionaryMyRank(Long id){
        long rank = rankingRedis.opsForZSet().reverseRank("countryDictionary", String.valueOf(id));
        return rank;
    }

    public List<DictionaryMyRankQueryDTO> getCountryDictionaryRank(long myRank){
        ZSetOperations<String, String> stringStringZSetOperations = rankingRedis.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> typedTuples;
        if(myRank==0 || myRank==1){
            typedTuples = stringStringZSetOperations.reverseRangeWithScores("countryDictionary", 0, 2);
        }else{
            typedTuples = stringStringZSetOperations.reverseRangeWithScores("countryDictionary", myRank-2, myRank);
        }
        List<DictionaryMyRankQueryDTO> collect = typedTuples.stream().map(DictionaryMyRankQueryDTO::convertToDictionaryMyRankQueryDTO).collect(Collectors.toList());
        return collect;
    }



}
