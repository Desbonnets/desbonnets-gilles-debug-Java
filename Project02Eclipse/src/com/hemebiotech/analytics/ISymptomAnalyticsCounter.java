package com.hemebiotech.analytics;

import java.util.List;
import java.util.Map;

public interface ISymptomAnalyticsCounter {

    Map<String, Integer> getAnalyticsCounterSort(List<String> symptoms, String sortCriteria);
}
