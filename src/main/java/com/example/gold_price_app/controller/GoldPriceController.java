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

    @PostMapping("/gold-price/calculate")
    public String calculateGoldPrice(@RequestParam("weight") double weight, Model model) {
        GoldPriceResponse priceResponse = goldPriceService.getGoldPrice();
        double currentPricePerUnit = priceResponse.getPrice();
        double totalPrice = weight * currentPricePerUnit;

        model.addAttribute("weight", weight);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("currency", priceResponse.getCurrency());

        return "goldPriceCalculatorResultView"; // 결과를 보여줄 새로운 뷰를 반환
    }

}
