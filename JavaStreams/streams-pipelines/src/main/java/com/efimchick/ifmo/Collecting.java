package com.efimchick.ifmo;

import com.efimchick.ifmo.util.CourseResult;
import com.efimchick.ifmo.util.Person;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Collecting
{
    public int sum(IntStream limit) {
        return limit.sum();
    }

    public int production(IntStream limit) {
        return limit.reduce((o1,o2) -> o1*o2).orElse(0);
    }


    public int oddSum(IntStream limit) {
        return limit.filter(this::isOdd).sum();
    }

    public Map<Integer,Integer> sumByRemainder(int i, IntStream limit) {
        return limit.boxed()
                .collect(Collectors.groupingBy(n -> n % i,
                        Collectors.summingInt(n -> n)));
    }

    public Map<Person, Double> totalScores(Stream<CourseResult> programmingResults)
    {
        List<CourseResult> courseResultList = programmingResults.collect(toList());
        return courseResultList.stream()
                .collect(Collectors.toMap(CourseResult::getPerson, res -> res.getTaskResults()
                        .values()
                        .stream()
                        .mapToInt(v -> v)
                        .sum() / (double) getNumberOfTasks(courseResultList)
                ));
    }

    public double averageTotalScore(Stream<CourseResult> programmingResults)
    {
        List<CourseResult> courseResultList = programmingResults.collect(toList());

        return courseResultList
                .stream()
                .map(CourseResult::getTaskResults)
                .flatMapToDouble(o -> o
                        .values()
                        .stream()
                        .mapToDouble(i -> i))
                .sum() / (getNumberOfTasks(courseResultList) * getNumberOfStudents(courseResultList)
        );
    }

    public Map<String, Double> averageScoresPerTask(Stream<CourseResult> programmingResults) {
        List<CourseResult> courseResultList = programmingResults.collect(Collectors.toList());
        return courseResultList
                .stream()
                .flatMap(r -> r
                        .getTaskResults()
                        .entrySet()
                        .stream())
                .collect(Collectors
                        .groupingBy(
                        Map.Entry::getKey,
                        Collectors
                                .summingDouble(e -> e
                                        .getValue()
                                / (double) getNumberOfStudents(courseResultList))));
    }

    public Map<Person, String> defineMarks(Stream<CourseResult> programmingResults)
    {
        List<CourseResult> courseResultList = programmingResults.collect(Collectors.toList());

        return courseResultList.stream().collect(Collectors.toMap(CourseResult::getPerson, courseResult ->
                defineScore(averageResult(courseResult, courseResultList))));
    }

    public String easiestTask(Stream<CourseResult> programmingResults)
    {
        List<CourseResult> courseResultList = programmingResults.collect(Collectors.toList());

        return courseResultList.stream()
                .flatMap(courseResult -> courseResult.getTaskResults().entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingDouble(Map.Entry::getValue)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("lack of results");
    }

    public Collector<CourseResult, FormatTable, String> printableStringCollector() {
        return new Collector<>() {
            @Override
            public Supplier<FormatTable> supplier() {
                return FormatTable::new;
            }

            @Override
            public BiConsumer<FormatTable, CourseResult> accumulator() {
                return FormatTable::addCourseResult;
            }

            @Override
            public BinaryOperator<FormatTable> combiner() {
                return null;
            }

            @Override
            public Function<FormatTable, String> finisher() {
                return formatTable ->
                {
                    StringBuilder strBuilder = new StringBuilder();
                    formatTable.createTable(strBuilder);
                    return strBuilder.toString();
                };
            }

            @Override
            public Set<Characteristics> characteristics() {
                return Collections.emptySet();
            }
        };
    }

    private boolean isOdd(int i) {
        return i % 2 != 0;
    }

    private long getNumberOfTasks(List<CourseResult> courseResultList) {
        return courseResultList.stream()
                .flatMap(courseResult -> courseResult.getTaskResults().keySet().stream())
                .distinct()
                .count();
    }

    private int getNumberOfStudents(final List<CourseResult> courseResults) {
        return (int) courseResults.stream()
                .map(CourseResult::getPerson)
                .distinct()
                .count();
    }

    public String defineScore(double score)
    {
        if (score > 90) {
            return "A";
        } else if (score >= 83) {
            return "B";
        } else if (score >= 75) {
            return "C";
        } else if (score >= 68) {
            return "D";
        } else if (score >= 60) {
            return "E";
        } else {
            return "F";
        }
    }

    private double averageResult(CourseResult courseResult,
                                 List<CourseResult> courseResultList)
    {
        return courseResult.getTaskResults().values().stream()
                .mapToDouble(val -> val)
                .sum() / getNumberOfTasks(courseResultList);
    }



}
