package com.example.restservices.RestServicesExample.controllers;

import com.example.restservices.RestServicesExample.beans.SomeBean;
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping(path = "/filtering/")
    public SomeBean getSomeBean() {
        return new SomeBean("value1","value2","value3","value4","value5");
    }

    @GetMapping(path = "/filtering-list/")
    public List<SomeBean> getSomeBeanList() {
        return Arrays.asList(new SomeBean("value1","value2","value3","value4","value5"),
                new SomeBean("value11","value22","value33","value44","value55"));
    }

    @GetMapping(path = "/dynamic-filtering/")
    public MappingJacksonValue getSomeBeanDynamic() {
        //will send only field 4 and 5
        SomeBean sm = new SomeBean("value1","value2","value3","value4","value5");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field4","field5");
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("SomeBeanFilter",filter);
        MappingJacksonValue mapping = new MappingJacksonValue(sm);
        mapping.setFilters(filterProvider);
        return mapping;
    }

    @GetMapping(path = "/dynamic-filtering-list/")
    public MappingJacksonValue getSomeBeanListDynamic() {
        //will send only field 4 and 5
        List<SomeBean> someBeanList = Arrays.asList(new SomeBean("value1", "value2", "value3", "value4", "value5"),
                new SomeBean("value11", "value22", "value33", "value44", "value55"));
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field4","field5");
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("SomeBeanFilter",filter);
        MappingJacksonValue mapping = new MappingJacksonValue(someBeanList);
        mapping.setFilters(filterProvider);
        return mapping;
    }
}
