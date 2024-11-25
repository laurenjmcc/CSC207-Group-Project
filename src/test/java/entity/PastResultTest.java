package entity;

import data_access.DiseaseDataAccessObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PastResultTest {
    private PastResult pastResult;


    @BeforeEach
    void setUp() throws Exception {

        pastResult = new PastResult("p53");
        Field field = pastResult.getClass().getDeclaredField("result");
        field.setAccessible(true);
        field.set(pastResult, null);
    }
}