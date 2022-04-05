/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.1.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package au.com.totemsoft.b2.v1.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import au.com.totemsoft.b2.v1.model.ShipmentRequest;
import au.com.totemsoft.b2.v1.model.ShipmentResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@Validated
@Api(value = "shipment", description = "the shipment API")
public interface ShipmentApi {

    ShipmentApi getDelegate();

    /**
     * POST /shipment/find : Find Shipments
     * Find Shipments
     *
     * @param shipmentRequest Shipment Request (required)
     * @return Success (status code 200)
     *         or Not authenticated (status code 401)
     *         or Access token does not have the required scope (status code 403)
     */
    @ApiOperation(value = "Find Shipments", nickname = "findShipments", notes = "Find Shipments", response = ShipmentResponse.class, responseContainer = "List", authorizations = {
         }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = ShipmentResponse.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Not authenticated"),
        @ApiResponse(code = 403, message = "Access token does not have the required scope") })
    @PostMapping(
        value = "/shipment/find",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<List<ShipmentResponse>> findShipments(@ApiParam(value = "Shipment Request" ,required=true )  @Valid @RequestBody ShipmentRequest shipmentRequest) {
        return getDelegate().findShipments(shipmentRequest);
    }

}
