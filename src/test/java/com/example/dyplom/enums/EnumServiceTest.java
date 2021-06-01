package com.example.dyplom.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnumServiceTest {

    private final EnumService enumservice = new EnumService();

    @Test
    void getStateNameSize() {
        int i = enumservice.getStateNameSize();
        assertEquals(i, 4);
    }

    @Test
    void getTypeOfIssueSize() {
        int i =enumservice.getTypeOfIssueSize();
        assertEquals(i, 4);
    }

    @Test
    void chceckStateNameExists() {

        Boolean x = enumservice.chceckStateNameExists("FIXED");
        assertEquals(x, true);
    }
}