package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchBuilderFieldUtils.createUniqueParameterCode;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.CLOSING_BRACE;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.OPENING_BRACE;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.QUESTION_MARK;

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
	private String parameterCode;

	ParameterFieldCondition(final Field field, final ParameterConditionType conditionType, final Object conditionParameter)
	{
		super(field);
		this.conditionType = conditionType;
		this.conditionParameter = conditionParameter;
	}

	ParameterFieldCondition(final AbstractFlexibleSearchQueryChainElement parent, final Field field,
			final ParameterConditionType conditionType, final Object conditionParameter)
	{
		super(parent, field);
		this.conditionType = conditionType;
		this.conditionParameter = conditionParameter;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		final boolean collectionParameter = conditionParameter instanceof Collection;

		sb.append(conditionType.getOperator());
		if (collectionParameter)
		{
			sb.append(OPENING_BRACE);
		}
		sb.append(QUESTION_MARK).append(parameterCode);
		if (collectionParameter)
		{
			sb.append(CLOSING_BRACE);
		}
	}

	@Override
	protected void addParameters(final Map<String, Object> parameterMap)
	{
		super.addParameters(parameterMap);

		this.parameterCode = createUniqueParameterCode(parameterMap, this.field.getFieldName());
		parameterMap.put(parameterCode, conditionParameter);
	}

}
