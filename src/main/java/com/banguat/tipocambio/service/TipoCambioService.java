package com.banguat.tipocambio.service;

import com.banguat.tipocambio.model.TipoCambioRegistro;
import com.banguat.tipocambio.repositoy.TipoCambioRepository;
import com.banguat.tipocambio.wsdl.TipoCambioDiaString;
import com.banguat.tipocambio.wsdl.TipoCambioDiaStringResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.client.WebServiceIOException;

import java.util.UUID;

@Service
public class TipoCambioService {

    private static final Logger logger = LoggerFactory.getLogger(TipoCambioService.class);
    private static final String BANGUAT_ENDPOINT = "https://www.banguat.gob.gt/variables/ws/TipoCambio.asmx";
    private static final String SOAP_ACTION = "http://www.banguat.gob.gt/variables/ws/TipoCambioDiaString";

    private final WebServiceTemplate webServiceTemplate;
    private final TipoCambioRepository repository;

    @Autowired
    public TipoCambioService(WebServiceTemplate webServiceTemplate, TipoCambioRepository repository) {
        this.webServiceTemplate = webServiceTemplate;
        this.repository = repository;
    }

    public TipoCambioRegistro consultarTipoCambio() {
        try {
            logger.info("Iniciando consulta de tipo de cambio");
            
            TipoCambioDiaString request = new TipoCambioDiaString();
            
            Object response = webServiceTemplate.marshalSendAndReceive(
                BANGUAT_ENDPOINT,
                request,
                new SoapActionCallback(SOAP_ACTION)
            );

            if (!(response instanceof TipoCambioDiaStringResponse)) {
                throw new RuntimeException("Respuesta inesperada del servicio SOAP");
            }

            TipoCambioDiaStringResponse tipoCambioResponse = (TipoCambioDiaStringResponse) response;
            if (tipoCambioResponse.getTipoCambioDiaStringResult() == null) {
                throw new RuntimeException("No se recibió resultado válido del servicio SOAP");
            }

            TipoCambioRegistro registro = new TipoCambioRegistro();
            registro.setNumeroSolicitud(UUID.randomUUID().toString());
            registro.setTipoCambio(tipoCambioResponse.getTipoCambioDiaStringResult());
            
            logger.info("Guardando registro de tipo de cambio");
            return repository.save(registro);
            
        } catch (WebServiceIOException e) {
            logger.error("Error de conexión al servicio SOAP: {}", e.getMessage(), e);
            throw new RuntimeException("Error de conexión al servicio de tipo de cambio", e);
        } catch (Exception e) {
            logger.error("Error al consultar el tipo de cambio: {}", e.getMessage(), e);
            throw new RuntimeException("Error al consultar el tipo de cambio: " + e.getMessage(), e);
        }
    }

    public TipoCambioRegistro obtenerUltimoRegistro() {
        try {
            logger.info("Obteniendo último registro de tipo de cambio");
            return repository.findTopByOrderByFechaConsultaDesc()
                .orElseThrow(() -> new RuntimeException("No se encontraron registros de tipo de cambio"));
        } catch (Exception e) {
            logger.error("Error al obtener último registro: {}", e.getMessage(), e);
            throw new RuntimeException("Error al obtener último registro: " + e.getMessage(), e);
        }
    }
}