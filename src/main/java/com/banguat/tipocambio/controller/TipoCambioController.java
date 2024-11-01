package com.banguat.tipocambio.controller;

import com.banguat.tipocambio.model.TipoCambioRegistro;
import com.banguat.tipocambio.service.TipoCambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tipo-cambio")
public class TipoCambioController {

    @Autowired
    private TipoCambioService tipoCambioService;

    @GetMapping("/consultar")
    public ResponseEntity<TipoCambioRegistro> consultarTipoCambio() {
        return ResponseEntity.ok(tipoCambioService.consultarTipoCambio());
    }

    @GetMapping("/ultimo")
    public ResponseEntity<TipoCambioRegistro> obtenerUltimoRegistro() {
        return ResponseEntity.ok(tipoCambioService.obtenerUltimoRegistro());
    }
}