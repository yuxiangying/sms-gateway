package com.bs.pro.controller;

import com.bs.pro.bean.ProItem;
import com.bs.pro.bean.ProItemHistory;
import com.bs.pro.dto.ResultPage;
import com.bs.pro.service.ItemHistoryService;
import com.bs.pro.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by czy on 2019/7/13.
 */
@Controller
@Slf4j
public class LedController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemHistoryService itemHistoryService;

    @GetMapping("/led")
    public ModelAndView item() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("led");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        modelAndView.addObject("date", formatter.format(date));
        return modelAndView;
    }

    @GetMapping("/item/validData")
    @ResponseBody
    public List<ProItem> list() {
        List<ProItem> itemList = itemService.selectValidItems();
        return itemList;
    }

    @GetMapping("/item/validDataStatistic")
    @ResponseBody
    public List<ProItem> validDataStatistic() {
        List<ProItem> itemList = itemService.selectValidStatisticItems();
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
