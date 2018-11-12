package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RedirectURL
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-12T11:58:42.466Z[GMT]")

public class RedirectURL   {
  @JsonProperty("headURL")
  private String headURL = null;

  @JsonProperty("interstitialURL")
  private String interstitialURL = null;

  public RedirectURL headURL(String headURL) {
    this.headURL = headURL;
    return this;
  }

  /**
   * Get headURL
   * @return headURL
  **/
  @ApiModelProperty(example = "https://www.google.es", required = true, value = "")
  @NotNull


  public String getHeadURL() {
    return headURL;
  }

  public void setHeadURL(String headURL) {
    this.headURL = headURL;
  }

  public RedirectURL interstitialURL(String interstitialURL) {
    this.interstitialURL = interstitialURL;
    return this;
  }

  /**
   * Get interstitialURL
   * @return interstitialURL
  **/
  @ApiModelProperty(example = "https://www.unizar.es", required = true, value = "")
  @NotNull


  public String getInterstitialURL() {
    return interstitialURL;
  }

  public void setInterstitialURL(String interstitialURL) {
    this.interstitialURL = interstitialURL;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RedirectURL redirectURL = (RedirectURL) o;
    return Objects.equals(this.headURL, redirectURL.headURL) &&
        Objects.equals(this.interstitialURL, redirectURL.interstitialURL);
  }

  @Override
  public int hashCode() {
    return Objects.hash(headURL, interstitialURL);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RedirectURL {\n");
    
    sb.append("    headURL: ").append(toIndentedString(headURL)).append("\n");
    sb.append("    interstitialURL: ").append(toIndentedString(interstitialURL)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

