package dev.cacassiano.review_analizr.adapters.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cacassiano.review_analizr.adapters.services.PlataformService;
import dev.cacassiano.review_analizr.core.entities.analyze.Comment;

@RestController
@RequestMapping("/api/v1/analyzes")
public class AnalizerController {

    @Autowired
    private PlataformService plataformService;

    private final String testUrl = "https://www.amazon.com.br/Chinelo-Power-Havaianas-Masculino-Preto/dp/B088CLJ5HZ/ref=sr_1_2_sspa?__mk_pt_BR=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=2R5BV8TDNXEBJ&dib=eyJ2IjoiMSJ9.hSSR0yEVpq6fojTb9OqW5o51nSoy-UfaspG_vSJGjNks3NM1xFu4_Rl4ixLmJg4Z5NdzAcY0RJUFILfDVMrmIy2N8AW15u9l-2mobLMMz2wAaWJnUyuw0fYSM5CEyVXui3uFZ8ZWfDwms1ZxczDxbj3Vrs3Nb2eq_ChNPXNYi7uIRVifXRdyFkGHhWHXhzkfS2huiDTqM4Pve8TYB2xIiMhkBwkxARnxVU_c4IPMkA04UqyRNEB5nf8FHIGQ5vODg8ENI-eqWSTDPy9HlEouKxkEnZACANLQYjC5lAU-hTI._4C4JAsxudRJMC_yMulaUKbhVZzimmUsrtLjmNw12qw&dib_tag=se&keywords=chinelo%2Bhavaianas&qid=1756395985&sprefix=chinelo%2Bhavainas%2Caps%2C236&sr=8-2-spons&sp_csd=d2lkZ2V0TmFtZT1zcF9hdGY&th=1&psc=1";

    @GetMapping
    public ResponseEntity<Map<String,List<Comment>>> getPage() throws IOException{
        return ResponseEntity.ok(plataformService.getComments(testUrl));

    // @PostMapping
    // public ResponseEntity<String> makeAnalyze(@RequestBody @Valid AnalyzeReqDTO req)  {
    //     return ResponseEntity.ok().build();
    // }
}
