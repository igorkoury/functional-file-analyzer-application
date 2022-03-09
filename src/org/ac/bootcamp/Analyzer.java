package org.ac.bootcamp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Analyzer {

   public static void main(String[] args) {

      Analyzer analyzer = new Analyzer();

      analyzer.pathReader(Path.of("resources/file-1.txt"));
      analyzer.firstLongerWordFinder(Path.of("resources/file-1.txt"), 6);
      analyzer.getLongest(Path.of("resources/file-1.txt"), 6);

      analyzer.getCommonWords(Path.of("resources/file-1.txt"), Path.of("resources/file-2.txt"));

   }

   public void pathReader(Path path) {

      try {

         Stream<String> lines = Files.lines(path);
         long words = lines.flatMap(line -> Stream.of(line.split(" +")))
                 .peek(System.out::println)
                 .count();
         System.out.println(words);

      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void firstLongerWordFinder(Path path, int n) {

      List words = null;

      try {

         Stream<String> lines = Files.lines(path);
         words = lines.flatMap(line -> Stream.of(line.split(" +")))
                 .filter(str -> str.length() > n).toList();

      } catch (IOException e) {
         e.printStackTrace();
      }

      System.out.println("The first longer word is: " + words.get(0) + " in " + path);

   }

   public void getLongestWords(Path path, int n) {

      List words = null;

      try {

         Stream<String> lines = Files.lines(path);
         words = lines.flatMap(line -> Stream.of(line.split(" +")))
                 .filter(str -> str.length() > n).toList();

      } catch (IOException e) {
         e.printStackTrace();
      }

      System.out.println(words);
   }

   public void getLongest(Path path, int number) {

      try {

         Stream<String> lines = Files.lines(path);
         lines.flatMap(line -> Stream.of(line.split(" ")))
                 .sorted((n, n1) -> n1.length() - n.length())
                 .limit(number)
                 .forEach(System.out::println);

      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void getCommonWords(Path path, Path otherPath) {

      List first = null;
      List second = null;

      try {

         Stream<String> lines = Files.lines(path);
         Stream<String> lines1 = Files.lines(otherPath);
         first = lines.flatMap(line -> Stream.of(line.split(" ")))
                 .toList();
         second = lines1.flatMap(line -> Stream.of(line.split(" ")))
                 .toList();

         List<String> common = new ArrayList<String>(first);
         common.retainAll(second);

         System.out.println("The common words between the files are: " + first);

      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
