package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchBuilderFieldUtils.createUniqueParameterCode;

import java.util.Map;


/**
 * Condition on the model field (DB table column) of the flexible search query, that has one parameter, e.g. condition
 * to check if field equals to given value.
 */
public class TwoParameterFieldCondition extends AbstractFieldCondition
{
	private final TwoParameterConditionType conditionType;
	private final Object firstConditionParameter;
	private final Object secondConditionParameter;
	private String firstParameterCode;
	private String secondParameterCode;

	TwoParameterFieldCondition(final Field field, final TwoParameterConditionType conditionType,
			final Object firstConditionParameter, final Object secondConditionParameter)
	{
		super(field);
		this.conditionType = conditionType;
		this.firstConditionParameter = firstConditionParameter;
		this.secondConditionParameter = secondConditionParameter;
	}

	TwoParameterFieldCondition(final AbstractFlexibleSearchQueryChainElement parent, final Field field,
			final TwoParameterConditionType conditionType, final Object firstConditionParameter,
			final Object secondConditionParameter)
	{
		super(parent, field);
		this.conditionType = conditionType;
		this.firstConditionParameter = firstConditionParameter;
		this.secondConditionParameter = secondConditionParameter;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		sb.append(SPACE).append(conditionType.getOperator()).append(SPACE).append(QUESTION_MARK).append(firstParameterCode)
				.append(SPACE).append(conditionType.getParameterJoinOperation()).append(SPACE).append(QUESTION_MARK)
				.append(secondParameterCode);
	}

	@Override
	protected void addParameters(final Map<String, Object> parameterMap)
	{
		super.addParameters(parameterMap);

		firstParameterCode = createUniqueParameterCode(parameterMap, field.getFieldName());
		parameterMap.put(firstParameterCode, firstConditionParameter);
		secondParameterCode = createUniqueParameterCode(parameterMap, field.getFieldName());
		parameterMap.put(secondParameterCode, secondConditionParameter);
	}
}
