package au.com.totemsoft.b2.web;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import au.com.totemsoft.b2.v1.api.ShipmentApi;
import au.com.totemsoft.b2.v1.model.ShipmentRequest;
import au.com.totemsoft.b2.v1.model.ShipmentResponse;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class ShipmentController implements ShipmentApi {

    @Resource
    //private NativeWebRequest webRequest;
    private HttpServletRequest httpServletRequest;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ShipmentApi getDelegate() {
        return this;
    }

    @Override
    public ResponseEntity<List<ShipmentResponse>> findShipments(@Valid ShipmentRequest shipmentRequest) {
        log.debug("findShipments: {}", shipmentRequest);
        try {
            // mock data
            List<ShipmentResponse> data = Stream.of(
                new ShipmentResponse()
                    .id("S-5165614")
                    .customer("Berry Creek Packing Company")
                    .from("Berry Springs")
                    .to("Collins Adelaide Depot")
                    .carrier("ABC")
                    .mode("Road")
                    .status("Confirmed")
                    .ready(LocalDate.ofInstant(Instant.now().plus(1, ChronoUnit.DAYS), ZoneId.systemDefault()))
                    .eta(LocalDate.ofInstant(Instant.now().plus(7, ChronoUnit.DAYS), ZoneId.systemDefault())),
                new ShipmentResponse()
                    .id("S-4234116")
                    .customer("Example Logistics")
                    .from("Melbourne Warehouse")
                    .to("Sydney International")
                    .carrier("DDF")
                    .mode("Road")
                    .status("Delayed")
                    .ready(LocalDate.ofInstant(Instant.now().plus(1, ChronoUnit.DAYS), ZoneId.systemDefault()))
                    .eta(LocalDate.ofInstant(Instant.now().plus(1, ChronoUnit.DAYS), ZoneId.systemDefault())),
                new ShipmentResponse()
                    .id("S-8249368")
                    .customer("Acme")
                    .from("Werribee Meats")
                    .to("Woolworths Melbourne Depot")
                    .carrier("ACM")
                    .mode("Road")
                    .status("In Transit")
                    .ready(LocalDate.ofInstant(Instant.now().plus(3, ChronoUnit.DAYS), ZoneId.systemDefault()))
                    .eta(LocalDate.ofInstant(Instant.now().plus(8, ChronoUnit.DAYS), ZoneId.systemDefault())),
                new ShipmentResponse()
                    .id("S-6478086")
                    .customer("Acme")
                    .from("Werribee Meats")
                    .to("Woolworths Sydney Depot")
                    .carrier("ACM")
                    .mode("Road")
                    .status("In Transit")
                    .ready(LocalDate.ofInstant(Instant.now().plus(3, ChronoUnit.DAYS), ZoneId.systemDefault()))
                    .eta(LocalDate.ofInstant(Instant.now().plus(8, ChronoUnit.DAYS), ZoneId.systemDefault())),
                new ShipmentResponse()
                    .id("S-8162364")
                    .customer("Apple Creek Packing Company")
                    .from("Apple Springs")
                    .to("Collins Adelaide Depot")
                    .carrier("XYZ")
                    .mode("Rail")
                    .status("Confirmed")
                    .ready(LocalDate.ofInstant(Instant.now().plus(2, ChronoUnit.DAYS), ZoneId.systemDefault()))
                    .eta(LocalDate.ofInstant(Instant.now().plus(14, ChronoUnit.DAYS), ZoneId.systemDefault()))
            ).collect(Collectors.toList());
            //
            return entity(data, null, null);
        } catch (Exception e) {
            // TODO: ErrorXXXResponse
            List<ShipmentResponse> errors = Arrays.asList(new ShipmentResponse()
                //.message(e.getMessage())
            );
            return entity(errors, HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    protected <T> ResponseEntity<T> entity(T body, HttpStatus status, MediaType contentType) {
        HttpHeaders responseHeaders = new HttpHeaders();
        if (contentType != null) {
            responseHeaders.setContentType(contentType);
        }
        //responseHeaders.setLocation(location);
        //responseHeaders.set("MyResponseHeader", "MyValue");
        //
        return new ResponseEntity<>(body, responseHeaders, status == null ? HttpStatus.OK : status);
        //return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    protected String error(Exception e) {
        return e.getMessage(); //.getRootCauseMessage(e);
    }

//    private <T> void setResponse(T data) {
//        HttpServletResponse resp = webRequest.getNativeRequest(HttpServletResponse.class);
//        resp.setCharacterEncoding("UTF-8");
//        resp.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//        try {
//            resp.getWriter().print(objectMapper.writeValueAsString(data));
//        } catch (IOException e) {
//            LOG.error(e);
//        }
//    }

}
