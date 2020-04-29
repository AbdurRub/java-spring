package com.cdk.employee.converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.springframework.util.StringUtils;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

  // Converter Constants
  public static final String SPLIT_REGEX = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
  public static final int DELIMITER_LENGTH = 2;

  @Override
  public String convertToDatabaseColumn(List<String> list) {
    if (StringUtils.isEmpty(list))
      return null;

    List<String> delimitedList = list.stream()
                                     .map(val -> "\"" + val + "\"")
                                     .collect(Collectors.toList());
    return String.join(",", delimitedList);
  }

  @Override
  public List<String> convertToEntityAttribute(String joined) {

    if (StringUtils.isEmpty(joined) || joined.length() < DELIMITER_LENGTH)
      return Collections.emptyList();

    List<String> encodedList = Arrays.asList(joined.split(SPLIT_REGEX, -1));
    return encodedList.stream()
                      .map(val -> val.substring(1, val.length() - 1))
                      .collect(Collectors.toList());
  }
}

