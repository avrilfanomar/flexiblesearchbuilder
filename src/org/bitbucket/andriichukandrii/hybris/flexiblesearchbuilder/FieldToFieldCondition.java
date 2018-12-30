package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

/**
 * Condition on two model fields (DB table columns) of the flexible search query, e.g. condition to check if field
 * equals to another field.
 */
public class FieldToFieldCondition extends AbstractFieldCondition
{
	private final RegularParameterConditionType conditionType;
	private String secondFieldName;

	FieldToFieldCondition(final String fieldName, final RegularParameterConditionType conditionType, final String secondFieldName)
	{
		super(fieldName);
		this.conditionType = conditionType;
		this.secondFieldName = secondFieldName;
	}

	FieldToFieldCondition(final AbstractFlexibleSearchQueryChainElement parent, final String fieldName,
						  final RegularParameterConditionType conditionType, final String secondFieldName)
	{
		super(parent, fieldName);
		this.conditionType = conditionType;
		this.secondFieldName = secondFieldName;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		sb.append(conditionType.getOperator()).append(OPENING_BRACKET).append(secondFieldName).append(CLOSING_BRACKET);
	}

}
