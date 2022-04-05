package au.com.totemsoft.b2.v1.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * ShipmentResponse
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ShipmentResponse   {
  @JsonProperty("id")
  private String id;

  @JsonProperty("customer")
  private String customer;

  @JsonProperty("from")
  private String from;

  @JsonProperty("to")
  private String to;

  @JsonProperty("carrier")
  private String carrier;

  @JsonProperty("mode")
  private String mode;

  @JsonProperty("status")
  private String status;

  @JsonProperty("ready")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE)
  private LocalDate ready;

  @JsonProperty("eta")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE)
  private LocalDate eta;

  public ShipmentResponse id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Entity Id (primary key)
   * @return id
  */
  @ApiModelProperty(example = "S-5165614", required = true, value = "Entity Id (primary key)")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ShipmentResponse customer(String customer) {
    this.customer = customer;
    return this;
  }

  /**
   * Get customer
   * @return customer
  */
  @ApiModelProperty(example = "Berry Creek Packing Company", value = "")


  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public ShipmentResponse from(String from) {
    this.from = from;
    return this;
  }

  /**
   * Get from
   * @return from
  */
  @ApiModelProperty(example = "Berry Springs", value = "")


  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public ShipmentResponse to(String to) {
    this.to = to;
    return this;
  }

  /**
   * Get to
   * @return to
  */
  @ApiModelProperty(example = "Collins Adelaide Depot", value = "")


  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public ShipmentResponse carrier(String carrier) {
    this.carrier = carrier;
    return this;
  }

  /**
   * Get carrier
   * @return carrier
  */
  @ApiModelProperty(example = "ABC", value = "")


  public String getCarrier() {
    return carrier;
  }

  public void setCarrier(String carrier) {
    this.carrier = carrier;
  }

  public ShipmentResponse mode(String mode) {
    this.mode = mode;
    return this;
  }

  /**
   * Get mode
   * @return mode
  */
  @ApiModelProperty(example = "Road", value = "")


  public String getMode() {
    return mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }

  public ShipmentResponse status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @ApiModelProperty(example = "Confirmed", value = "")


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public ShipmentResponse ready(LocalDate ready) {
    this.ready = ready;
    return this;
  }

  /**
   * Get ready
   * @return ready
  */
  @ApiModelProperty(example = "Sat Apr 10 10:00:00 AEST 2021", value = "")

  @Valid

  public LocalDate getReady() {
    return ready;
  }

  public void setReady(LocalDate ready) {
    this.ready = ready;
  }

  public ShipmentResponse eta(LocalDate eta) {
    this.eta = eta;
    return this;
  }

  /**
   * Get eta
   * @return eta
  */
  @ApiModelProperty(example = "Thu Apr 15 10:00:00 AEST 2021", value = "")

  @Valid

  public LocalDate getEta() {
    return eta;
  }

  public void setEta(LocalDate eta) {
    this.eta = eta;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShipmentResponse shipmentResponse = (ShipmentResponse) o;
    return Objects.equals(this.id, shipmentResponse.id) &&
        Objects.equals(this.customer, shipmentResponse.customer) &&
        Objects.equals(this.from, shipmentResponse.from) &&
        Objects.equals(this.to, shipmentResponse.to) &&
        Objects.equals(this.carrier, shipmentResponse.carrier) &&
        Objects.equals(this.mode, shipmentResponse.mode) &&
        Objects.equals(this.status, shipmentResponse.status) &&
        Objects.equals(this.ready, shipmentResponse.ready) &&
        Objects.equals(this.eta, shipmentResponse.eta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, customer, from, to, carrier, mode, status, ready, eta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ShipmentResponse {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    customer: ").append(toIndentedString(customer)).append("\n");
    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    to: ").append(toIndentedString(to)).append("\n");
    sb.append("    carrier: ").append(toIndentedString(carrier)).append("\n");
    sb.append("    mode: ").append(toIndentedString(mode)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    ready: ").append(toIndentedString(ready)).append("\n");
    sb.append("    eta: ").append(toIndentedString(eta)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

