package com.example.bigScreen.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping(value = "/daGang")
@Api(value = "获取机器人数据",tags = "获取机器人数据")
public class RobotController {
    private static final String Host = "10.76.44.5:9000";

    private static List<String> RobotList = List.of("http://10.76.44.5:9000/hl-inspection/threeParty/daGang/getRobotVideoUrl","http://10.76.44.5:9000/hl-inspection/threeParty/daGang/getRoomVideoUrl");

    private static Map<String,Object> urlMap = Map.of("RobotList",RobotList);
    /**
     * 获取机器人摄像头
     */
    @GetMapping("/getRobotVideoUrl")
    public Map<String,String> getRobotVideoUrl() {
        Map<String, String> resultMap = new HashMap<>();

        RestTemplate restTemplate = new RestTemplate();

        //设置url
        String RobotVideoUrl = ((List<?>) urlMap.get("RobotList")).get(0).toString();
        String VideoUrl = ((List<?>) urlMap.get("RobotList")).get(1).toString();

        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("HOST", Host);

        //封装参数
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        //发送请求
        ResponseEntity<String> response = restTemplate.exchange(RobotVideoUrl, HttpMethod.GET, entity, String.class);
        ResponseEntity<String> response1 = restTemplate.exchange(VideoUrl, HttpMethod.GET, entity, String.class);
        System.out.println(response.getBody());

        //解析响应
        Map<String, Object> resultMap1 = (Map<String, Object>) JSON.parse(response.getBody());
        System.out.println();
        if (resultMap1.get("result") != null) {
            Object qianZhi = ((Map<String, Object>) JSON.parse(resultMap1.get("result").toString())).get("qianZhi");
            Object gaoQing = ((Map<String, Object>) JSON.parse(resultMap1.get("result").toString())).get("gaoQing");
            Object hongWai = ((Map<String, Object>) JSON.parse(resultMap1.get("result").toString())).get("hongWai");
            resultMap.put("qianZhi", "http://" + Host + qianZhi.toString());
            resultMap.put("gaoQing", "http://" + Host + gaoQing.toString());
            resultMap.put("hongWai", "http://" + Host + hongWai.toString());
        }
        Map<String, Object> resultVideoMap = (Map<String, Object>) JSON.parse(response1.getBody());
        if (resultVideoMap.get("result") != null) {
            Object shiNei = ((Map<String, Object>) JSON.parse(resultVideoMap.get("result").toString())).get("shiNei");
            resultMap.put("shiNei", "http://" + Host + shiNei.toString());
        }
        return resultMap;
    }
}
