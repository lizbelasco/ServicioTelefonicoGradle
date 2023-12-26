/**
 * 
 */
package com.soa.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.google.gson.Gson;
import com.soa.CrearReporteRequest;
import com.soa.CrearReporteResponse;
import com.soa.CrearReporteResponse.Cliente;
import com.soa.CrearReporteResponse.Llamadas;
import com.soa.CrearReporteResponse.Llamadas.Llamada;
import com.soa.CrearReporteResponse.Servicio;

/**
 * Clase que publica un servicio web tipo SOAP. Capa Boundary
 */
@Endpoint
public class PhoneServiceEndpoint {
    /** Objeto inyectado de la capa de negocio. */
    @Autowired
//    private CalculadoraBusiness calculadora;

    /** Targetnamespace. */
    private static final String NAMESPACE_URI = "http://soa.com";

    /**
     * Operación SOAP expuesta en http.
     * @param request Objeto con datos de entrada. (Capa de Modelo).
     * @return objeto con datos de salida (Capa de modelo)
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "crearReporteRequest")
    @ResponsePayload
    public CrearReporteResponse crearReporteOperation(
            @RequestPayload CrearReporteRequest request) {
        CrearReporteResponse out = new CrearReporteResponse();
        Cliente cliente = new Cliente();
        cliente.setNombre("abc");
        cliente.setApellidoPaterno("bbb");
        cliente.setApellidoMaterno("ccc");
        out.setCliente(cliente);
        Llamadas llamadas = new Llamadas();
        
        List<Llamada> llamadaList = llamadas.getLlamada();
        Llamada llamada = new Llamada();
        llamada.setFecha("fecha1");
        llamada.setNoTelefonico("phone1");
        llamadaList.add(llamada);
        
        llamada = new Llamada(); // 2do objeto llamada.
        llamada.setFecha("fecha2");
        llamada.setNoTelefonico("phone2");
        llamadaList.add(llamada);
        out.setLlamadas(llamadas);
        
        List<Servicio> servicioList = out.getServicio();
        Servicio servicio = new Servicio();
        servicio.setNombre("servicio1");
        servicio.setTarifa(1);
        servicioList.add(servicio);
        servicio = new Servicio();
        servicio.setNombre("servicio2");
        servicio.setTarifa(2);
        servicioList.add(servicio);
        return out;
    }
}