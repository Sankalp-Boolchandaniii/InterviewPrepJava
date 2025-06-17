package org.streams;

public class CollectorsDemo {

    // Collectors.toList() returns a list of streams
    // Collectors.toSet() return a set of streams. What is a set? A set is a list which has all the objects distinct
    // Collectors.toCollection() returns a collection from the stream example ArrayList, LinkedList
    // Collectors.joining() returns a collective single object of all the objects
    // Collectors.summarize() returns a IntSummaryStatistics object that can be used to summarize the stream. Ex: min, max, sum, avg, count
    // Collectors.counting() returns a count of number of object in a stream
    // Collectors.averagingInt(), Collectors.averagingDouble() returns average of the stream
    // Collectors.groupingBy() returns data from the given stream in a grouped manner. Ex: length of strings
    // Collectors.partitioningBy() returns a stream partitioned by a condition based on true/false. Ex: length greater than 5

    public static void main(String[] args) {

    }

}
