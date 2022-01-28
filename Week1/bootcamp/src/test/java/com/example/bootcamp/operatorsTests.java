package com.example.bootcamp;

import com.example.bootcamp.operators.Book;
import com.example.bootcamp.operators.Operators;
import org.junit.Assert;
import org.junit.Test;
import java.util.*;

public class operatorsTests {


    @Test
    public void testMap(){
        Assert.assertEquals(Arrays.asList(1,4,9), Operators.map(Arrays.asList(1,2,3)));
    }






    @Test
    public void testConcat(){
        //                      BEKLENEN                        GIRILEN
        Assert.assertEquals(Arrays.asList("abc","de"), Operators.concat(Arrays.asList("abc"),Arrays.asList("de")));
    }





    @Test
    public void testRemove(){
        Assert.assertEquals(Arrays.asList(1,2), Operators.remove(Arrays.asList(1,2,3,3,4,4),Arrays.asList(3,4)));
    }
    @Test
    public void testDistinct(){
        Assert.assertEquals(Arrays.asList(1,3), Operators.distinct(Arrays.asList(1,3,3,3)));
    }
    @Test
    public void testSlice(){
        Assert.assertEquals(Arrays.asList(3,4), Operators.slice(Arrays.asList(1,2,3,4,5),2,4));
    }
    @Test
    public void testFilter(){
        Assert.assertEquals(Arrays.asList(3,3,3), Operators.filter(Arrays.asList(1,2,3,3,3),3));
    }
    @Test
    public void testFlatMap(){
        Assert.assertEquals(Arrays.asList("test","test1","test2"), Operators.flatMap(Arrays.asList("test test1 test2")));
    }
    @Test
    public void testFlatten(){
        Assert.assertEquals(Arrays.asList(1,2,3,4), Operators.flatten(Arrays.asList(Arrays.asList(1,2),Arrays.asList(3,4))));
    }
    @Test
    public void testReduce(){
        Assert.assertEquals(Arrays.asList("abc"), Operators.reduce(Arrays.asList("a","b","c")));
    }
    @Test
    public void testSort(){
        Assert.assertEquals(Arrays.asList(1,2,3), Operators.sort(Arrays.asList(3,2,1)));
    }
    @Test
    public void testUnion(){
        Assert.assertEquals(Arrays.asList(1,2,3), Operators.union(Arrays.asList(1,2,3),Arrays.asList(2,3)));
    }
    @Test
    public void testGroupBy(){
        Book book1 = new Book("test",1);
        Book book2 = new Book("test2",2);
        Book book3 = new Book("test3",3);
        Map<String, Long> temp = new HashMap<>();
        temp.put(book1.getName(), 1L);
        temp.put(book2.getName(), 1L);
        temp.put(book3.getName(), 1L);
        Assert.assertEquals(temp, Operators.groupBy(Arrays.asList(book1,book2,book3)));
    }



}
