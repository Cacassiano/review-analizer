package dev.cacassiano.review_analizr.adapters.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cacassiano.review_analizr.adapters.services.AnalyzeService;
import dev.cacassiano.review_analizr.core.entities.analyze.Analyze;

@RestController
@RequestMapping("/api/v1/analyzes")
public class AnalizerController {
    
    @Autowired
    private AnalyzeService analyzeService;    
    
    private final String testUrl = "https://produto.mercadolivre.com.br/MLB-3966886517-chinelo-havaianas-power-20-anatmica-massageadora-masculino-_JM#reviews";
    //"https://www.amazon.com/-/pt/dp/B08H75RTZ8/ref=sr_1_1?crid=3V03GGH33JFEM&dib=eyJ2IjoiMSJ9.DQ7dQ2Wdt7c6lzUHbPD5Ea1roYNzYSyIEYNdXZ17wyGcUPEnzVTekuFitTLW8IBlN1tTdOCyPOL0_8_ZTjb21ziPdzx7W3_1uFOxei8zOUHpVmGgaC2_iBFjel826kjlqbnBD4KdPaXDIP8bDPUXF6Ty6LgE8G_WCohSkMvJ2EWFKZPGgOOp6G0ov3EYjeoDYlxHKVQx1tV8odJh5RTxwYxWH0vevGY3ahEtc-iykmg.NII8UqeJTpEjW-gRjbtrq2yt1npu-TFua_WP81i2nOU&dib_tag=se&keywords=xbox%2Bseries%2Bx&qid=1756585950&sprefix=x%2Caps%2C380&sr=8-1&th=1";
    //"https://www.amazon.com.br/Chinelo-Power-Havaianas-Masculino-Preto/dp/B088CLJ5HZ/ref=sr_1_2_sspa?__mk_pt_BR=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=2R5BV8TDNXEBJ&dib=eyJ2IjoiMSJ9.hSSR0yEVpq6fojTb9OqW5o51nSoy-UfaspG_vSJGjNks3NM1xFu4_Rl4ixLmJg4Z5NdzAcY0RJUFILfDVMrmIy2N8AW15u9l-2mobLMMz2wAaWJnUyuw0fYSM5CEyVXui3uFZ8ZWfDwms1ZxczDxbj3Vrs3Nb2eq_ChNPXNYi7uIRVifXRdyFkGHhWHXhzkfS2huiDTqM4Pve8TYB2xIiMhkBwkxARnxVU_c4IPMkA04UqyRNEB5nf8FHIGQ5vODg8ENI-eqWSTDPy9HlEouKxkEnZACANLQYjC5lAU-hTI._4C4JAsxudRJMC_yMulaUKbhVZzimmUsrtLjmNw12qw&dib_tag=se&keywords=chinelo%2Bhavaianas&qid=1756395985&sprefix=chinelo%2Bhavainas%2Caps%2C236&sr=8-2-spons&sp_csd=d2lkZ2V0TmFtZT1zcF9hdGY&th=1&psc=1";

    @GetMapping
    public ResponseEntity<Analyze> getPage() throws IOException{
        Analyze analyze = analyzeService.createAnalyze(testUrl, "mercadolivre");
        return ResponseEntity.ok(analyze);
    }
}
