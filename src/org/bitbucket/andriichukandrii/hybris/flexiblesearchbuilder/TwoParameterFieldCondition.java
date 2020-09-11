package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchBuilderFieldUtils.createUniqueParameterCode;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.QUESTION_MARK;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.SPACE;

import java.util.Map;


/**
 * Condition on the model field (DB table column) of the flexible search query, that has one parameter, e.g. condition
 * to check if field equals to given value.
 */
public class TwoParameterFieldCondition extends AbstractFieldCondition
{
	private final TwoParameterConditionType conditionType;
	private final CodeToValue firstParam;
	private final CodeToValue secondParam;

	TwoParameterFieldCondition(final Field field, final TwoParameterConditionType conditionType, final Object firstParamValue,
			final Object secondParamValue)
	{
		super(field);
		this.conditionType = conditionType;
		this.firstParam = new CodeToValue(firstParamValue);
		this.secondParam = new CodeToValue(secondParamValue);
	}

	TwoParameterFieldCondition(final AbstractFlexibleSearchQueryChainElement parent, final Field field,
			final TwoParameterConditionType conditionType, final Object firstParam, final Object secondParam)
	{
		super(parent, field);
		this.conditionType = conditionType;
		this.firstParam = new CodeToValue(firstParam);
		this.secondParam = new CodeToValue(secondParam);
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		sb.append(SPACE).append(conditionType.getOperator()).append(SPACE).append(QUESTION_MARK).append(firstParam.getCode())
				.append(SPACE).append(conditionType.getParameterJoinOperation()).append(SPACE).append(QUESTION_MARK)
				.append(secondParam.getCode());
	}

	@Override
	protected void addParameters(final Map<String, Object> parameterMap)
	{
		super.addParameters(parameterMap);

		firstParam.setCode(createUniqueParameterCode(parameterMap, field.getFieldName()));
		parameterMap.put(firstParam.getCode(), firstParam.getValue());

		secondParam.setCode(createUniqueParameterCode(parameterMap, field.getFieldName()));
		parameterMap.put(secondParam.getCode(), secondParam.getValue());
	}

	private static final class CodeToValue
	{
		private String code;
		private final Object value;

		CodeToValue(final Object value)
		{
			this.value = value;
		}

		public String getCode()
		{
			return code;
		}

		public void setCode(String code)
		{
			this.code = code;
		}

		public Object getValue()
		{
			return value;
		}
	}
}
