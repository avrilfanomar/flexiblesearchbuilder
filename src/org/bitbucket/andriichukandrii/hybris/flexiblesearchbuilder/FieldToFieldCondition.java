package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

/**
 * Condition on two model fields (DB table columns) of the flexible search query, e.g. condition to check if field
 * equals to another field.
 */
public class FieldToFieldCondition extends AbstractFieldCondition
{
	private final ParameterConditionType conditionType;
	private String secondFieldName;

	FieldToFieldCondition(final String fieldName, final ParameterConditionType conditionType, final String secondFieldName)
	{
		super(fieldName);
		this.conditionType = conditionType;
		this.secondFieldName = secondFieldName;
	}

	FieldToFieldCondition(final AbstractFlexibleSearchQueryChainElement parent, final String fieldName,
			final ParameterConditionType conditionType, final String secondFieldName)
	{
		super(parent, fieldName);
		this.conditionType = conditionType;
		this.secondFieldName = secondFieldName;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		sb.append(conditionType.getValue()).append(OPENING_BRACKET).append(secondFieldName).append(CLOSING_BRACKET);
	}

}
