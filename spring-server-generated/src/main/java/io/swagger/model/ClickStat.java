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
 * ClickStat
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-12T11:58:42.466Z[GMT]")

public class ClickStat   {
  @JsonProperty("browser")
  private String browser = null;

  @JsonProperty("clicks")
  private String clicks = null;

  public ClickStat browser(String browser) {
    this.browser = browser;
    return this;
  }

  /**
   * Get browser
   * @return browser
  **/
  @ApiModelProperty(example = "Google Chrome", required = true, value = "")
  @NotNull


  public String getBrowser() {
    return browser;
  }

  public void setBrowser(String browser) {
    this.browser = browser;
  }

  public ClickStat clicks(String clicks) {
    this.clicks = clicks;
    return this;
  }

  /**
   * Get clicks
   * @return clicks
  **/
  @ApiModelProperty(example = "154", required = true, value = "")
  @NotNull


  public String getClicks() {
    return clicks;
  }

  public void setClicks(String clicks) {
    this.clicks = clicks;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClickStat clickStat = (ClickStat) o;
    return Objects.equals(this.browser, clickStat.browser) &&
        Objects.equals(this.clicks, clickStat.clicks);
  }

  @Override
  public int hashCode() {
    return Objects.hash(browser, clicks);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClickStat {\n");
    
    sb.append("    browser: ").append(toIndentedString(browser)).append("\n");
    sb.append("    clicks: ").append(toIndentedString(clicks)).append("\n");
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

