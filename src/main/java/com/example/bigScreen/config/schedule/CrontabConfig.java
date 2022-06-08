package com.example.bigScreen.config.schedule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.bigScreen.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: SuKai
 * @date: 2022/6/8
 * @time: 10:09
 * @description:
 */
@Component
public class CrontabConfig {

    private static final Logger log = LoggerFactory.getLogger(CrontabConfig.class);

    private static final String USERID = "1";

    private static List<String> zhanKuList = List.of("http://10.76.31.178:8851/a11/stationhouse/configservice/authormanager/createtoken","http://10.76.31.178:8851/a11/stationhouse/standardservice/sw_abnormal_condition_batchhandle/0.1.1/standard");

    private static Map<String,Object> urlMap = Map.of("zhanKuList",zhanKuList);

    private static Integer pageNum = 0;
    private static final Integer pageSize = 5;

    /**
     * 定时获取站库信息
     */
    @Scheduled(cron = "*/5 * * * * ?")
    public void crontabZhanKu(){
        //获取站库报警信息token
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));

        //设置url
        String uri = ((List<?>)urlMap.get("zhanKuList")).get(0).toString();

        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json;charset=UTF-8"));
        headers.add("username","gstes2019");
        headers.add("userpwd","1qaz@WSX");

        //封装参数
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params,headers);

        //发送请求
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        System.out.println(response.getBody());

        //解析响应
        Map<String,Object> resultMap = (Map<String, Object>) JSON.parse(response.getBody());
        if (resultMap.get("result") != null && ((Map<String, Object>) JSON.parse(resultMap.get("result").toString())).get("token") != null){

            //获取token成功，开始获取站库信息
            String token = ((Map<String, Object>) JSON.parse(resultMap.get("result").toString())).get("token").toString();
            pageNum += 1;

            //设置url
            String uri2 = ((List<?>)urlMap.get("zhanKuList")).get(1).toString();

            //设置请求头
            HttpHeaders headers2 = new HttpHeaders();
            headers2.setContentType(MediaType.parseMediaType("application/json;charset=UTF-8"));
            headers2.add("username","gstes2019");
            headers2.add("Token",token);

            //封装参数
            JSONObject requestMap = new JSONObject();

            Map<String,Object> auth = new HashMap<>();
            auth.put("name","gstes2019");
            requestMap.put("auth",auth);

            Map<String,Object> business = new HashMap<>();
            business.put("org_id","020211");
            business.put("order_by","fault_level");
            business.put("direction",null);
            business.put("device_name","");
            business.put("level","");
            business.put("system","");
            business.put("type","");
            business.put("state","");
            business.put("begin","2022-06-8 00:00:00");
            business.put("end","2022-06-08 23:59:59");
            business.put("page",pageNum);
            business.put("size",pageSize);
            requestMap.put("business",business);

            requestMap.put("call_label","station_library_security_warning_abnormal_warning_batchprocess");
            HttpEntity<JSONObject> entity2 = new HttpEntity<>(requestMap,headers2);

            //发送请求
            ResponseEntity<String> response2 = restTemplate.exchange(uri2, HttpMethod.POST, entity2, String.class);
            System.out.println(response2.getBody());

            int i = WebSocketServer.sendInfo(JSON.toJSONString(response2.getBody()),USERID);
            if (i != 1){
                pageNum = 0;
                log.warn("获取站库信息: 信息推送失败");
            }
        }else {
            pageNum = 0;
            log.warn("获取站库信息: 获取token失败");
        }
    }
}
