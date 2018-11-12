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
 * URL
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-12T11:58:42.466Z[GMT]")

public class URL   {
  @JsonProperty("URL")
  private String URL = null;

  public URL URL(String URL) {
    this.URL = URL;
    return this;
  }

  /**
   * Get URL
   * @return URL
  **/
  @ApiModelProperty(example = "https://www.google.es", required = true, value = "")
  @NotNull


  public String getURL() {
    return URL;
  }

  public void setURL(String URL) {
    this.URL = URL;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    URL URL = (URL) o;
    return Objects.equals(this.URL, URL.URL);
  }

  @Override
  public int hashCode() {
    return Objects.hash(URL);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class URL {\n");
    
    sb.append("    URL: ").append(toIndentedString(URL)).append("\n");
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

