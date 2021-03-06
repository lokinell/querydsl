package com.querydsl.collections;

import java.util.Arrays;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.TimePath;

public class JodaTimeTemplatesTest {
    
    private CollQuery query = new CollQuery(JodaTimeTemplates.DEFAULT);
    
    @Test
    public void DateTime() {
        DateTimePath<DateTime> entity = Expressions.dateTimePath(DateTime.class, "entity");
        query.from(entity, Arrays.asList(new DateTime(), new DateTime(0l)))
             .list(entity.year(), entity.yearMonth(), entity.month(), entity.week(),
                   entity.dayOfMonth(), entity.dayOfWeek(), entity.dayOfYear(),
                   entity.hour(), entity.minute(), entity.second(), entity.milliSecond());        
    }
    
    @Test
    public void LocalDate() {
        DatePath<LocalDate> entity = Expressions.datePath(LocalDate.class, "entity");
        query.from(entity, Arrays.asList(new LocalDate(), new LocalDate(0l)))
             .list(entity.year(), entity.yearMonth(), entity.month(), entity.week(),
                   entity.dayOfMonth(), entity.dayOfWeek(), entity.dayOfYear());
    }
    
    @Test
    public void LocalTime() {
        TimePath<LocalTime> entity = Expressions.timePath(LocalTime.class, "entity");
        query.from(entity, Arrays.asList(new LocalTime(), new LocalTime(0l)))
             .list(entity.hour(), entity.minute(), entity.second(), entity.milliSecond());
    }

}
