package com.ngai.auth.components;

import com.ngai.auth.model.entity.TParamters;
import com.ngai.auth.model.repository.TParametersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ParamsCache {
    private static Map<String, TParamters> cachedDbParams;
    private static int calledTimes;
    @Autowired
    private TParametersRepository tParametersRepository;
    public ParamsCache() {
        System.out.println("called one time: " + ++calledTimes);
        cachedDbParams = new HashMap<>();
    }

    public void loadDbParams() {
        cachedDbParams = tParametersRepository.findAll()
                .stream()
                .filter(TParamters::getBStatus)
                .collect(Collectors.toMap(TParamters::getStrKey, Function.identity()));
    }

    public static Object getParam(String key) {
        return cachedDbParams.get(key).getStrValue();
    }
    public static String getParam(String key, String defaultValue){
        TParamters paramters =  cachedDbParams.getOrDefault(key, null);

        if (paramters == null) return  defaultValue;

        return paramters.getStrValue();
    }

}
