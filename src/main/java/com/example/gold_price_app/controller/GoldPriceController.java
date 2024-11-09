// src/main/java/com/example/goldpriceapp/controller/GoldPriceController.java
package com.example.gold_price_app.controller;

import com.example.gold_price_app.model.GoldPriceResponse;
import com.example.gold_price_app.service.GoldPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GoldPriceController {

    @Autowired
    private GoldPriceService goldPriceService;

    @GetMapping("/gold-price")
    public String getGoldPrice(Model model) {
        GoldPriceResponse priceResponse = goldPriceService.getGoldPrice();
        model.addAttribute("price", priceResponse.getPrice());
        model.addAttribute("currency", priceResponse.getCurrency());
        return "goldPriceView";
    }

    @GetMapping("/gold-price/calculator")
    public String showCalculator() {
        return "goldPriceCalculatorView"; // 금 시세 계산기 입력 페이지
    }

    @PostMapping("/gold-price/calculator")
    public String calculateGoldPrice(@RequestParam("weight") double weight,
                                     @RequestParam("unit") String unit,
                                     Model model) {
        GoldPriceResponse priceResponse = goldPriceService.getGoldPrice();
        double currentPricePerGram = priceResponse.getPrice(); // null 검사 제거
        double totalPrice;

        if ("don".equals(unit)) {
            totalPrice = weight * currentPricePerGram * 3.75; // 1돈 = 3.75g
            model.addAttribute("unitLabel", "돈");
        } else {
            totalPrice = weight * currentPricePerGram; // g 단위 계산
            model.addAttribute("unitLabel", "그램");
        }

        model.addAttribute("weight", weight);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("currency", priceResponse.getCurrency());
        return "goldPriceCalculatorResultView";
    }

}
