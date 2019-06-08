package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import java.util.Collection;
import java.util.Map;


/**
 * Condition on the model field (DB table column) of the flexible search query, that has one parameter, e.g. condition
 * to check if field equals to given value.
 */
public class ParameterFieldCondition extends AbstractFieldCondition
{
	private final ParameterConditionType conditionType;
	private final Object conditionParameter;
	private final boolean collectionParameter;
	private String parameterCode;

	ParameterFieldCondition(final Field field, final ParameterConditionType conditionType, final Object conditionParameter)
	{
		super(field);
		this.conditionType = conditionType;
		this.conditionParameter = conditionParameter;
		this.collectionParameter = conditionParameter instanceof Collection;
	}

	ParameterFieldCondition(final AbstractFlexibleSearchQueryChainElement parent, final Field field,
			final ParameterConditionType conditionType, final Object conditionParameter)
	{
		super(parent, field);
		this.conditionType = conditionType;
		this.conditionParameter = conditionParameter;
		this.collectionParameter = conditionParameter instanceof Collection;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		sb.append(conditionType.getOperator()).append(collectionParameter ? OPENING_BRACE : "").append(QUESTION_MARK)
				.append(parameterCode).append(collectionParameter ? CLOSING_BRACE : "");
	}

	@Override
	protected void addParameters(final Map<String, Object> parameterMap)
	{
		super.addParameters(parameterMap);

		createUniqueParameterCode(parameterMap);
		parameterMap.put(parameterCode, conditionParameter);
	}

	private void createUniqueParameterCode(final Map<String, Object> parameterMap)
	{
		int counter = 0;
		do
		{
			counter++;
			this.parameterCode = this.field.getFieldName() + counter;
		}
		while (parameterMap.get(this.parameterCode) != null);
	}
}
