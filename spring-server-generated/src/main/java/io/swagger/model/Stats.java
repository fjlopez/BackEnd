package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.ClickStat;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.LocalDate;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Stats
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-12T11:58:42.466Z[GMT]")

public class Stats   {
  @JsonProperty("day")
  private LocalDate day = null;

  @JsonProperty("clickStat")
  @Valid
  private List<ClickStat> clickStat = new ArrayList<ClickStat>();

  public Stats day(LocalDate day) {
    this.day = day;
    return this;
  }

  /**
   * Get day
   * @return day
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public LocalDate getDay() {
    return day;
  }

  public void setDay(LocalDate day) {
    this.day = day;
  }

  public Stats clickStat(List<ClickStat> clickStat) {
    this.clickStat = clickStat;
    return this;
  }

  public Stats addClickStatItem(ClickStat clickStatItem) {
    this.clickStat.add(clickStatItem);
    return this;
  }

  /**
   * Get clickStat
   * @return clickStat
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<ClickStat> getClickStat() {
    return clickStat;
  }

  public void setClickStat(List<ClickStat> clickStat) {
    this.clickStat = clickStat;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Stats stats = (Stats) o;
    return Objects.equals(this.day, stats.day) &&
        Objects.equals(this.clickStat, stats.clickStat);
  }

  @Override
  public int hashCode() {
    return Objects.hash(day, clickStat);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Stats {\n");
    
    sb.append("    day: ").append(toIndentedString(day)).append("\n");
    sb.append("    clickStat: ").append(toIndentedString(clickStat)).append("\n");
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

