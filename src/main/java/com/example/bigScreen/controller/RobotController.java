package com.example.bigScreen.controller;

import com.alibaba.fastjson.JSON;
import com.example.bigScreen.config.Result;
import io.swagger.annotations.Api;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping(value = "/daGang")
@Api(value = "获取机器人数据",tags = "获取机器人数据")
public class RobotController {
    private static final String Host = "10.76.44.5:9000";

    private static List<String> RobotList = Arrays.asList("http://10.76.44.5:9000/hl-inspection/threeParty/daGang/getRobotVideoUrl","http://10.76.44.5:9000/hl-inspection/threeParty/daGang/getRoomVideoUrl");

    private static Map<String,Object> urlMap = new HashMap<String,Object>();
    static {
        urlMap.put("RobotVideoUrl",RobotList.get(0));
        urlMap.put("VideoUrl",RobotList.get(1));
    }
    /**
     * 获取机器人摄像头
     */
    @GetMapping("/getRobotVideoUrl")
    public Map<String,String> getRobotVideoUrl(){
        Map<String,String> resultMap = new HashMap<>();

        RestTemplate restTemplate = new RestTemplate();

        //设置url
        String RobotVideoUrl = ((List<?>)urlMap.get("RobotVideoUrl")).get(0).toString();
        String VideoUrl = ((List<?>)urlMap.get("VideoUrl")).get(0).toString();

        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("HOST",Host);

        //封装参数
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params,headers);

        //发送请求
        ResponseEntity<String> response = restTemplate.exchange(RobotVideoUrl, HttpMethod.GET, entity, String.class);
        ResponseEntity<String> response1 = restTemplate.exchange(VideoUrl, HttpMethod.GET, entity, String.class);

        //解析响应
        Map<String,Object> resultMap1 = (Map<String, Object>) JSON.parse(response.getBody());
        if (resultMap1.get("result") != null){
            String qianZhi = (String) resultMap.get("qianZhi");
            String gaoQing = (String) resultMap.get("gaoQing");
            String hongWai = (String) resultMap.get("hongWai");
            resultMap.put("qianZhi","http://"+Host+qianZhi);
            resultMap.put("gaoQing","http://"+Host+gaoQing);
            resultMap.put("hongWai","http://"+Host+hongWai);
        }
        Map<String,Object> resultVideoMap = (Map<String, Object>) JSON.parse(response1.getBody());
        if (resultVideoMap.get("result") != null){
            String shiNei = (String) resultMap.get("shiNei");
            resultMap.put("shiNei","http://"+Host+shiNei);
        }
        for(String key:resultMap.keySet()){
            System.out.println("key:"+key+" "+"Value:"+resultMap.get(key));
        }
        return resultMap;
    }
}
