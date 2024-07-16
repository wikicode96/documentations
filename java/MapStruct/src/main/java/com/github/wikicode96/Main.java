package com.github.wikicode96;

import com.github.wikicode96.entity.SourceEntity;
import com.github.wikicode96.entity.TargetEntity;
import com.github.wikicode96.mapper.SourceToTargetMapper;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SourceEntity source1 = new SourceEntity("John Doe", 30);
        SourceEntity source2 = new SourceEntity("Jane Doe", 25);

        List<SourceEntity> sourceList = Arrays.asList(source1, source2);

        List<TargetEntity> targetList = SourceToTargetMapper.INSTANCE.sourceListToTargetList(sourceList);

        targetList.forEach(target -> System.out.println("Name: " + target.getFullName() + ", Age: " + target.getAge()));
    }
}