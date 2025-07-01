package com.challang.backend.feedback.converter;

import com.challang.backend.feedback.entity.FeedbackType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class FeedbackTypeConverter implements AttributeConverter<FeedbackType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(FeedbackType attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public FeedbackType convertToEntityAttribute(Integer dbData) {
        return dbData != null ? FeedbackType.fromCode(dbData) : null;
    }
}
