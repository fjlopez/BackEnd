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
 * Browser
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-12T11:58:42.466Z[GMT]")

public class Browser   {
  @JsonProperty("browser")
  private String browser = null;

  public Browser browser(String browser) {
    this.browser = browser;
    return this;
  }

  /**
   * Get browser
   * @return browser
  **/
  @ApiModelProperty(example = "Opera", required = true, value = "")
  @NotNull


  public String getBrowser() {
    return browser;
  }

  public void setBrowser(String browser) {
    this.browser = browser;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Browser browser = (Browser) o;
    return Objects.equals(this.browser, browser.browser);
  }

  @Override
  public int hashCode() {
    return Objects.hash(browser);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Browser {\n");
    
    sb.append("    browser: ").append(toIndentedString(browser)).append("\n");
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

