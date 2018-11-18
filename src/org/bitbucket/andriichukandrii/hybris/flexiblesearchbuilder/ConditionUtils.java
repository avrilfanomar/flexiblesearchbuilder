package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

class ConditionUtils
{
	static AbstractCondition getConditionRoot(final AbstractCondition condition)
	{
		AbstractCondition conditionRoot = condition;
		while (conditionRoot.getParent() != null)
		{
			conditionRoot = conditionRoot.getParent();
		}
		return conditionRoot;
	}
}
