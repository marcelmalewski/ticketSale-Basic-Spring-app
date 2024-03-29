package com.marcel.malewski.ticketsale.ticketbuyer.agerange;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class AgeRangeConverter implements AttributeConverter<AgeRange, String> {
   @Override
   public String convertToDatabaseColumn(AgeRange ageRange) {
      if (ageRange == null) {
         return null;
      }
      return ageRange.getValue();
   }

   @Override
   public AgeRange convertToEntityAttribute(String code) {
      if (code == null) {
         return null;
      }

      return Stream.of(AgeRange.values())
              .filter(c -> c.getValue().equals(code))
              .findFirst()
              .orElseThrow(IllegalArgumentException::new);
   }
}
