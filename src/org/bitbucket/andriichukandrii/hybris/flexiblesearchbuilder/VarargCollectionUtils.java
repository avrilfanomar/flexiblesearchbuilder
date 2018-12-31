package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class VarargCollectionUtils
{
	@SafeVarargs
	public static <T> List<T> toList(final T first, T... rest)
	{
		return toStream(first, rest).collect(Collectors.toList());
	}

	@SafeVarargs
	public static <T> Stream<T> toStream(final T first, final T... rest)
	{
		return Stream.concat(Stream.of(first), Arrays.stream(rest));
	}
}
