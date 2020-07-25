package com.bs.pro.controller;

import com.bs.pro.bean.ProItem;
import com.bs.pro.bean.ProItemHistory;
import com.bs.pro.constant.Constants;
import com.bs.pro.dto.ResultPage;
import com.bs.pro.dto.input.ItemInput;
import com.bs.pro.service.ItemHistoryService;
import com.bs.pro.service.ItemService;
import com.bs.pro.utils.ErrorCode;
import com.bs.pro.utils.MD5;
import com.bs.pro.utils.UID;
import com.fasterxml.jackson.databind.BeanProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by czy on 2019/7/13.
 */
@Controller
@RequestMapping("/pro")
@Slf4j
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemHistoryService itemHistoryService;

    @GetMapping("/item")
    public String item() {
        return "item";
    }

    @PostMapping("/item/add")
    @ResponseBody
    public ResultPage add(ProItem itemInput) {
        // 校验code是否重复
        ProItem proItem = itemService.selectByCode(itemInput.getCode());
        if (null != proItem) {
            return ResultPage.getResultInstance(ErrorCode.VERIFY_FAILD, "项目编号已经存在！");
        }
        if(StringUtils.isNotEmpty(itemInput.getEndTime())) {
            itemInput.setPublishTime(itemInput.getEndTime());
        }
        itemService.insert(itemInput);
        ProItemHistory itemHistory = new ProItemHistory();
        BeanUtils.copyProperties(itemInput, itemHistory);
        itemHistoryService.insert(itemHistory);
        return ResultPage.getResultInstance(ErrorCode.SUCCESS, "success");
    }

    /*
     * 登记进度
     */
    @PostMapping("/item/registerProgress")
    @ResponseBody
    public ResultPage registerProgress(ProItem itemInput) {
        if (StringUtils.isNotEmpty(itemInput.getPublishRealTime())) {
            itemInput.setStatus(2);
        }
        itemService.updateProgress(itemInput);
        ProItem proItem = itemService.selectByCode(itemInput.getCode());
        ProItemHistory itemHistory = new ProItemHistory();
        BeanUtils.copyProperties(proItem, itemHistory);
        itemHistory.setSchemeRealTime(itemInput.getSchemeRealTime());
        itemHistory.setSchemeProgress(itemInput.getSchemeProgress());
        itemHistory.setPhoneRealTime(itemInput.getPhoneRealTime());
        itemHistory.setPhoneProgress(itemInput.getPhoneProgress());
        itemHistory.setPrototypeRealTime(itemInput.getPrototypeRealTime());
        itemHistory.setPrototypeProgress(itemInput.getPrototypeProgress());
        itemHistory.setTestRealTime(itemInput.getTestRealTime());
        itemHistory.setTestProgress(itemInput.getTestProgress());
        itemHistory.setPublishRealTime(itemInput.getPublishRealTime());
        itemHistory.setPublishProgress(itemInput.getPublishProgress());

        itemHistoryService.insert(itemHistory);
        return ResultPage.getResultInstance(ErrorCode.SUCCESS, "success");
    }

    /*
     * 修改item基本信息
     */
    @PostMapping("/item/modItem")
    @ResponseBody
    public ResultPage modItem(ProItem itemInput) {
        if(StringUtils.isNotEmpty(itemInput.getEndTime())) {
            itemInput.setPublishTime(itemInput.getEndTime());
        }
        itemService.modItem(itemInput);
        ProItem proItem = itemService.selectByCode(itemInput.getCode());
        ProItemHistory itemHistory = new ProItemHistory();
        BeanUtils.copyProperties(proItem, itemHistory);
        itemHistoryService.insert(itemHistory);
        return ResultPage.getResultInstance(ErrorCode.SUCCESS, "success");
    }

    /*
     * 修改item的状态（暂停，取消，继续）
     */
    @PostMapping("/item/updateStatus")
    @ResponseBody
    public ResultPage updateStatus(String code, Integer status, String reason) {
        Map paramMap = new HashMap();
        paramMap.put("code", code);
        paramMap.put("status", status);
        paramMap.put("reason", reason);
        itemService.updateStatus(paramMap);
        ProItem proItem = itemService.selectByCode(code);
        ProItemHistory itemHistory = new ProItemHistory();
        BeanUtils.copyProperties(proItem, itemHistory);
        itemHistoryService.insert(itemHistory);
        return ResultPage.getResultInstance(ErrorCode.SUCCESS, "success");
    }

    @GetMapping("/item/add_new")
    public ModelAndView addNew() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("itemadd");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        modelAndView.addObject("startTime", formatter.format(date));
        return modelAndView;
    }

    @GetMapping("/item/register")
    public ModelAndView register(String code) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        ProItem proItem = itemService.selectByCode(code);
        modelAndView.addObject("code", proItem.getCode());
        modelAndView.addObject("name", proItem.getName());
        modelAndView.addObject("model", proItem.getModel());
        modelAndView.addObject("chargeMan", proItem.getChargeMan());
        modelAndView.addObject("members", proItem.getMembers());
        String startTime = proItem.getStartTime();
        if (StringUtils.isEmpty(startTime) || "1970-01-01".equals(startTime)) {
            startTime = "";
        }
        modelAndView.addObject("startTime", startTime);
        String endTime = proItem.getEndTime();
        if (StringUtils.isEmpty(endTime) || "1970-01-01".equals(endTime)) {
            endTime = "";
        }
        modelAndView.addObject("endTime", endTime);
        modelAndView.addObject("concernLevel", proItem.getConcernLevel());
        String schemeTime = proItem.getSchemeTime();
        if (StringUtils.isEmpty(schemeTime) || "1970-01-01".equals(schemeTime)) {
            schemeTime = "";
        }
        modelAndView.addObject("schemeTime", schemeTime);
        modelAndView.addObject("schemeMan", proItem.getSchemeMan());
        String schemeRealTime = proItem.getSchemeRealTime();
        if (StringUtils.isEmpty(schemeRealTime) || "1970-01-01".equals(schemeRealTime)) {
            schemeRealTime = "";
        }
        modelAndView.addObject("schemeRealTime", schemeRealTime);
        modelAndView.addObject("schemeProgress", proItem.getSchemeProgress());
        String phoneTime = proItem.getPhoneTime();
        if (StringUtils.isEmpty(phoneTime) || "1970-01-01".equals(phoneTime)) {
            phoneTime = "";
        }
        modelAndView.addObject("phoneTime", phoneTime);
        String phoneRealTime = proItem.getPhoneRealTime();
        if (StringUtils.isEmpty(phoneRealTime) || "1970-01-01".equals(phoneRealTime)) {
            phoneRealTime = "";
        }
        modelAndView.addObject("phoneRealTime", phoneRealTime);
        modelAndView.addObject("phoneProgress", proItem.getPhoneProgress());
        modelAndView.addObject("phoneMan", proItem.getPhoneMan());
        String prototypeTime = proItem.getPrototypeTime();
        if (StringUtils.isEmpty(prototypeTime) || "1970-01-01".equals(prototypeTime)) {
            prototypeTime = "";
        }
        modelAndView.addObject("prototypeTime", prototypeTime);
        String prototypeRealTime = proItem.getPrototypeRealTime();
        if (StringUtils.isEmpty(prototypeRealTime) || "1970-01-01".equals(prototypeRealTime)) {
            prototypeRealTime = "";
        }
        modelAndView.addObject("prototypeRealTime", prototypeRealTime);
        modelAndView.addObject("prototypeProgress", proItem.getPrototypeProgress());
        modelAndView.addObject("prototypeMan", proItem.getPrototypeMan());
        String testTime = proItem.getTestTime();
        if (StringUtils.isEmpty(testTime) || "1970-01-01".equals(testTime)) {
            testTime = "";
        }
        modelAndView.addObject("testTime", testTime);
        String testRealTime = proItem.getTestRealTime();
        if (StringUtils.isEmpty(testRealTime) || "1970-01-01".equals(testRealTime)) {
            testRealTime = "";
        }
        modelAndView.addObject("testRealTime", testRealTime);
        modelAndView.addObject("testProgress", proItem.getTestProgress());
        modelAndView.addObject("testMan", proItem.getTestMan());
        String publishTime = proItem.getPublishTime();
        if (StringUtils.isEmpty(publishTime) || "1970-01-01".equals(publishTime)) {
            publishTime = "";
        }
        modelAndView.addObject("publishTime", publishTime);
        String publishRealTime = proItem.getPublishRealTime();
        if (StringUtils.isEmpty(publishRealTime) || "1970-01-01".equals(publishRealTime)) {
            publishRealTime = "";
        }
        modelAndView.addObject("publishRealTime", publishRealTime);
        modelAndView.addObject("publishProgress", proItem.getPublishProgress());
        modelAndView.addObject("publishMan", proItem.getPublishMan());
        return modelAndView;
    }

    @GetMapping("/item/change")
    public ModelAndView change(String code) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("change");
        ProItem proItem = itemService.selectByCode(code);
        modelAndView.addObject("code", proItem.getCode());
        modelAndView.addObject("name", proItem.getName());
        modelAndView.addObject("model", proItem.getModel());
        modelAndView.addObject("chargeMan", proItem.getChargeMan());
        modelAndView.addObject("members", proItem.getMembers());
        modelAndView.addObject("content", proItem.getContent());
        String startTime = proItem.getStartTime();
        if (StringUtils.isEmpty(startTime) || "1970-01-01".equals(startTime)) {
            startTime = "";
        }
        modelAndView.addObject("startTime", startTime);
        String endTime = proItem.getEndTime();
        if (StringUtils.isEmpty(endTime) || "1970-01-01".equals(endTime)) {
            endTime = "";
        }
        modelAndView.addObject("endTime", endTime);
        modelAndView.addObject("concernLevel", proItem.getConcernLevel());
        String schemeTime = proItem.getSchemeTime();
        if (StringUtils.isEmpty(schemeTime) || "1970-01-01".equals(schemeTime)) {
            schemeTime = "";
        }
        modelAndView.addObject("schemeTime", schemeTime);
        modelAndView.addObject("schemeMan", proItem.getSchemeMan());
        String phoneTime = proItem.getPhoneTime();
        if (StringUtils.isEmpty(phoneTime) || "1970-01-01".equals(phoneTime)) {
            phoneTime = "";
        }
        modelAndView.addObject("phoneTime", phoneTime);
        modelAndView.addObject("phoneMan", proItem.getPhoneMan());
        String prototypeTime = proItem.getPrototypeTime();
        if (StringUtils.isEmpty(prototypeTime) || "1970-01-01".equals(prototypeTime)) {
            prototypeTime = "";
        }
        modelAndView.addObject("prototypeTime", prototypeTime);
        modelAndView.addObject("prototypeMan", proItem.getPrototypeMan());
        String testTime = proItem.getTestTime();
        if (StringUtils.isEmpty(testTime) || "1970-01-01".equals(testTime)) {
            testTime = "";
        }
        modelAndView.addObject("testTime", testTime);
        modelAndView.addObject("testMan", proItem.getTestMan());
        String publishTime = proItem.getPublishTime();
        if (StringUtils.isEmpty(publishTime) || "1970-01-01".equals(publishTime)) {
            publishTime = "";
        }
        modelAndView.addObject("publishTime", publishTime);
        modelAndView.addObject("publishMan", proItem.getPublishMan());
        modelAndView.addObject("status", proItem.getStatus());
        return modelAndView;
    }

    @GetMapping("/item/history")
    public ModelAndView history(String code) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("history");
        ProItem proItem = itemService.selectByCode(code);
        modelAndView.addObject("code", proItem.getCode());
        modelAndView.addObject("name", proItem.getName());
        modelAndView.addObject("model", proItem.getModel());
        modelAndView.addObject("chargeMan", proItem.getChargeMan());
        modelAndView.addObject("members", proItem.getMembers());
        String startTime = proItem.getStartTime();
        if (StringUtils.isEmpty(startTime) || "1970-01-01".equals(startTime)) {
            startTime = "";
        }
        modelAndView.addObject("startTime", startTime);
        String endTime = proItem.getEndTime();
        if (StringUtils.isEmpty(endTime) || "1970-01-01".equals(endTime)) {
            endTime = "";
        }
        modelAndView.addObject("endTime", endTime);
        modelAndView.addObject("concernLevel", proItem.getConcernLevel());
        String schemeTime = proItem.getSchemeTime();
        if (StringUtils.isEmpty(schemeTime) || "1970-01-01".equals(schemeTime)) {
            schemeTime = "";
        }
        modelAndView.addObject("schemeTime", schemeTime);
        modelAndView.addObject("schemeMan", proItem.getSchemeMan());
        String schemeRealTime = proItem.getSchemeRealTime();
        if (StringUtils.isEmpty(schemeRealTime) || "1970-01-01".equals(schemeRealTime)) {
            schemeRealTime = "";
        }
        modelAndView.addObject("schemeRealTime", schemeRealTime);
        String phoneTime = proItem.getPhoneTime();
        if (StringUtils.isEmpty(phoneTime) || "1970-01-01".equals(phoneTime)) {
            phoneTime = "";
        }
        modelAndView.addObject("phoneTime", phoneTime);
        modelAndView.addObject("phoneMan", proItem.getPhoneMan());
        String phoneRealTime = proItem.getPhoneRealTime();
        if (StringUtils.isEmpty(phoneRealTime) || "1970-01-01".equals(phoneRealTime)) {
            phoneRealTime = "";
        }
        modelAndView.addObject("phoneRealTime", phoneRealTime);
        String prototypeTime = proItem.getPrototypeTime();
        if (StringUtils.isEmpty(prototypeTime) || "1970-01-01".equals(prototypeTime)) {
            prototypeTime = "";
        }
        modelAndView.addObject("prototypeTime", prototypeTime);
        modelAndView.addObject("prototypeMan", proItem.getPrototypeMan());
        String prototypeRealTime = proItem.getPrototypeRealTime();
        if (StringUtils.isEmpty(prototypeRealTime) || "1970-01-01".equals(prototypeRealTime)) {
            prototypeRealTime = "";
        }
        modelAndView.addObject("prototypeRealTime", prototypeRealTime);
        String testTime = proItem.getTestTime();
        if (StringUtils.isEmpty(testTime) || "1970-01-01".equals(testTime)) {
            testTime = "";
        }
        modelAndView.addObject("testTime", testTime);
        modelAndView.addObject("testMan", proItem.getTestMan());
        String testRealTime = proItem.getTestRealTime();
        if (StringUtils.isEmpty(testRealTime) || "1970-01-01".equals(testRealTime)) {
            testRealTime = "";
        }
        modelAndView.addObject("testRealTime", testRealTime);
        String publishTime = proItem.getPublishTime();
        if (StringUtils.isEmpty(publishTime) || "1970-01-01".equals(publishTime)) {
            publishTime = "";
        }
        modelAndView.addObject("publishTime", publishTime);
        modelAndView.addObject("publishMan", proItem.getPublishMan());
        String publishRealTime = proItem.getPublishRealTime();
        if (StringUtils.isEmpty(publishRealTime) || "1970-01-01".equals(publishRealTime)) {
            publishRealTime = "";
        }
        modelAndView.addObject("publishRealTime", publishRealTime);
        modelAndView.addObject("status", proItem.getStatus());
        return modelAndView;
    }

    @GetMapping("/item/historyList")
    @ResponseBody
    public List<ProItemHistory> historyList(String code) {
        ResultPage resultPage = new ResultPage();
        List<ProItemHistory> itemList = itemHistoryService.selectByCode(code);
        return itemList;
    }

    @GetMapping("/item/list")
    @ResponseBody
    public List<ProItem> list() {
        ResultPage resultPage = new ResultPage();
        List<ProItem> itemList = itemService.selectAll();
        return itemList;
    }

    @PostMapping("/item/search")
    @ResponseBody
    public List<ProItem> search(String code, String name, String startTimeBegin, String startTimeEnd, Integer status, String chargeMan, String endTimeBegin, String endTimeEnd) {
        Map<Object, Object> paramMap = new HashMap<>();
        if (StringUtils.isNotEmpty(code)) {
            paramMap.put("code", code);
        }
        if (StringUtils.isNotEmpty(name)) {
            paramMap.put("name", name);
        }
        if (StringUtils.isNotEmpty(startTimeBegin)) {
            paramMap.put("startTimeBegin", startTimeBegin);
        }
        if (StringUtils.isNotEmpty(startTimeEnd)) {
            paramMap.put("startTimeEnd", startTimeEnd);
        }
        if (status > -10) {
            paramMap.put("status", status);
        }
        if (StringUtils.isNotEmpty(chargeMan)) {
            paramMap.put("chargeMan", chargeMan);
        }
        if (StringUtils.isNotEmpty(endTimeBegin)) {
            paramMap.put("endTimeBegin", endTimeBegin);
        }
        if (StringUtils.isNotEmpty(endTimeEnd)) {
            paramMap.put("endTimeEnd", endTimeEnd);
        }

        List<ProItem> itemList = itemService.searchByParam(paramMap);
        return itemList;
    }

    @PostMapping("/item/renderProgress")
    @ResponseBody
    public Map<String, String> search(String codes) {
        Map<String, String> result = new HashMap<>();
        String[] codesArr = codes.split(",");
        List<ProItemHistory> itemHistoryList = itemHistoryService.selectByCodes(Arrays.asList(codesArr));
        Map<String, List<ProItemHistory>> dataMap = new HashMap<>();
        for (int i = 0; i < itemHistoryList.size(); i++) {
            ProItemHistory proItemHistory = itemHistoryList.get(i);
            List<ProItemHistory> tempList = dataMap.get(proItemHistory.getCode());
            if (null == tempList) {
                tempList = new ArrayList<>();
            }
            tempList.add(proItemHistory);
            dataMap.put(proItemHistory.getCode(), tempList);
        }
        Iterator<Map.Entry<String, List<ProItemHistory>>> itor = dataMap.entrySet().iterator();
        while (itor.hasNext()) {
            Map.Entry<String, List<ProItemHistory>> entry = itor.next();
            String code = entry.getKey();
            List<ProItemHistory> historyList = entry.getValue();
            ProItemHistory curHistory = historyList.get(0);
            result.put(code, curHistory.getSchemeProgress() + curHistory.getPhoneProgress() + curHistory.getPrototypeProgress() + curHistory.getTestProgress() + curHistory.getPublishProgress());
            for (int i = 1; i < historyList.size(); i++) {
                ProItemHistory temp = historyList.get(i);
                if (!temp.getSchemeProgress().equals(curHistory.getSchemeProgress()) || !temp.getPhoneProgress().equals(curHistory.getPhoneProgress()) ||
                        !temp.getPrototypeProgress().equals(curHistory.getPrototypeProgress()) || !temp.getTestProgress().equals(curHistory.getTestProgress()) ||
                        !temp.getPublishProgress().equals(curHistory.getPublishProgress())) {
                    String modProgress = "";
                    if(!temp.getSchemeProgress().equals(curHistory.getSchemeProgress())){
                        modProgress +=curHistory.getSchemeProgress();
                    }
                    if(!temp.getPhoneProgress().equals(curHistory.getPhoneProgress())){
                        modProgress +=curHistory.getPhoneProgress();
                    }
                    if(!temp.getPrototypeProgress().equals(curHistory.getPrototypeProgress())){
                        modProgress +=curHistory.getPrototypeProgress();
                    }
                    if(!temp.getTestProgress().equals(curHistory.getTestProgress())){
                        modProgress +=curHistory.getTestProgress();
                    }
                    if(!temp.getPublishProgress().equals(curHistory.getPublishProgress())){
                        modProgress +=curHistory.getPublishProgress();
                    }
                    result.put(code,modProgress);
                    break;
                }
            }
        }
        return result;
    }
}
