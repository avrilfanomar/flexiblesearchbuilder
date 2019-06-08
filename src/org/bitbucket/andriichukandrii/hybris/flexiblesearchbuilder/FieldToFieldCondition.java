package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

/**
 * Condition on two model fields (DB table columns) of the flexible search query, e.g. condition to check if field
 * equals to another field.
 */
public class FieldToFieldCondition extends AbstractFieldCondition
{
	private final RegularParameterConditionType conditionType;
	private final Field secondField;

	FieldToFieldCondition(final Field field, final RegularParameterConditionType conditionType, final Field secondField)
	{
		super(field);
		this.conditionType = conditionType;
		this.secondField = secondField;
	}

	FieldToFieldCondition(final AbstractFlexibleSearchQueryChainElement parent, final Field field,
						  final RegularParameterConditionType conditionType, final Field secondField)
	{
		super(parent, field);
		this.conditionType = conditionType;
		this.secondField = secondField;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		sb.append(conditionType.getOperator()).append(secondField.toString());
	}

}
