/*
 * Copyright 2011, Mysema Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mysema.query.types;

import static com.mysema.query.alias.Alias.$;
import static com.mysema.query.alias.Alias.alias;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.expr.CaseBuilder;
import com.mysema.query.types.expr.NumberExpression;
import com.mysema.query.types.expr.StringExpression;

public class CaseBuilderTest {

    public static class Customer{
        private long annualSpending;
        public long getAnnualSpending() {
            return annualSpending;
        }
    }

    @Test
    public void BooleanTyped() {
        Customer c = alias(Customer.class, "customer");
        BooleanExpression cases = new CaseBuilder()
            .when($(c.getAnnualSpending()).gt(10000)).then(true)
            .otherwise(false);

        assertEquals(
                "case " +
                "when customer.annualSpending > 10000 then true " +
                "else false " +
                "end", cases.toString());
    }

    @Test
    public void NumberTyped() {
        Customer c = alias(Customer.class, "customer");
        NumberExpression<Integer> cases = new CaseBuilder()
            .when($(c.getAnnualSpending()).gt(10000)).then(1)
            .when($(c.getAnnualSpending()).gt(5000)).then(2)
            .when($(c.getAnnualSpending()).gt(2000)).then(3)
            .otherwise(4);

        assertEquals(
                "case " +
                "when customer.annualSpending > 10000 then 1 " +
                "when customer.annualSpending > 5000 then 2 " +
                "when customer.annualSpending > 2000 then 3 " +
                "else 4 " +
                "end", cases.toString());
    }

    @Test
    public void StringTyped() {
//        CASE
//          WHEN c.annualSpending > 10000 THEN 'Premier'
//          WHEN c.annualSpending >  5000 THEN 'Gold'
//          WHEN c.annualSpending >  2000 THEN 'Silver'
//          ELSE 'Bronze'
//        END

        Customer c = alias(Customer.class, "customer");
        StringExpression cases = new CaseBuilder()
            .when($(c.getAnnualSpending()).gt(10000)).then("Premier")
            .when($(c.getAnnualSpending()).gt(5000)).then("Gold")
            .when($(c.getAnnualSpending()).gt(2000)).then("Silver")
            .otherwise("Bronze");

        // NOTE : this is just a test serialization, not the real one
        assertEquals(
           "case " +
           "when customer.annualSpending > 10000 then Premier " +
           "when customer.annualSpending > 5000 then Gold " +
           "when customer.annualSpending > 2000 then Silver " +
           "else Bronze " +
           "end", cases.toString());

    }

}
